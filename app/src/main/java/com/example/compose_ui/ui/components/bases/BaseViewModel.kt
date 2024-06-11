package com.example.compose_ui.ui.components.bases

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.compose_ui.ui.cores.data.vo.Category
import com.example.compose_ui.ui.cores.data.vo.Person
import com.example.compose_ui.ui.cores.data.vo.Product
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UiState(
    var isLoading: Boolean = false,
    var errorMessage: String? = null
)

@HiltViewModel
open class BaseViewModel @Inject constructor(val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val database: FirebaseFirestore = Firebase.firestore
    private val auth: FirebaseAuth = Firebase.auth
    private var _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    companion object {
        const val SHOES = "shoes"
        const val ID = "id"
        const val CATEGORIES = "categories"
        const val TYPE = "type"
        const val NAME = "name"
        const val SEARCH = "Search"
        const val GET = "Get"
    }

    private fun resetState(boolean: Boolean = false, string: String? = "") {
        _uiState.update {
            it.copy().apply {
                isLoading = boolean
                errorMessage = string.toString()
            }
        }
    }

    protected fun loginToServer(
        email: String,
        password: String,
        onFinish: (isSuccess: Boolean) -> Unit
    ) {
        try {
            resetState(true)
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                onFinish(true)
                resetState(false)
            }.addOnFailureListener {
                resetState(false, it.message)
                onFinish(false)
            }
        } catch (e: Exception) {
            resetState(false, e.message)
        }
    }

    protected fun registerAccountToFirebaseServer(
        user: Person,
        onFinish: (isSuccess: Boolean) -> Unit
    ) {
        try {
            resetState(true)
            auth.createUserWithEmailAndPassword(user.email, user.password).addOnSuccessListener {
                auth.currentUser?.let {
                    val profileUpdates = userProfileChangeRequest {
                        displayName = user.name
                    }
                    it.updateProfile(profileUpdates).addOnSuccessListener {
                        onFinish(true)
                        resetState(false)
                    }.addOnFailureListener { error ->
                        resetState(false, error.message)
                    }
                }
            }.addOnFailureListener {
                resetState(false, it.message)
            }
        } catch (e: Exception) {
            resetState(false, e.message)
            onFinish(false)
        }
    }

    private fun getCollection(path: String) = database.collection(path)
    private fun getQueryPath(
        type: String? = GET,
        path: String = SHOES,
        field: String,
        value: String
    ) =
        when {
            type == SEARCH -> {
                getCollection(path).whereArrayContains(field, value.toLowerCase(Locale.current))
                    .limit(10)
            }

            else -> {
                getCollection(path).whereEqualTo(field, value)
            }
        }

    private fun callApiFromFireBase(
        path: String,
        onSuccess: (QuerySnapshot) -> Unit
    ) {
        try {
            resetState(true)
            getCollection(path).get().addOnSuccessListener { documents ->
                documents?.let(onSuccess)
                resetState(false)
            }
        } catch (e: Exception) {
            resetState(false, e.message)
        }
    }

    private fun callApiWhereFromFirebase(
        path: String,
        field: String,
        value: String,
        type: String = GET,
        isLoading: Boolean = true,
        onSuccess: (QuerySnapshot) -> Unit
    ) {
        try {
            resetState(isLoading)
            getQueryPath(type = type, path = path, field = field, value = value).get()
                .addOnSuccessListener { documents ->
                    documents?.let(onSuccess)
                    resetState(false)
                }
        } catch (e: Exception) {
            resetState(false, e.message)
        }
    }


    protected fun getCategories(onFinish: (categories: MutableList<Category>) -> Unit) {
        callApiFromFireBase(CATEGORIES) {
            val categories: MutableList<Category> = mutableListOf()
            for (document in it) {
                categories.add(
                    Category(
                        id = document.get("id").toString(),
                        name = document.get("name").toString(),
                        description = document.get("description").toString()
                    )
                )
            }
            onFinish(categories)
        }
    }

    private fun getProductInfo(document: QueryDocumentSnapshot): Product {
        return Product(
            id = document.get("id").toString(),
            name = document.get("name").toString(),
            description = document.get("description").toString(),
            price = document.get("price").toString().toDouble(),
            isFavorite = document.get("isFavorite").toString().toBoolean(),
            isBestSeller = document.get("isBestSeller").toString().toBoolean(),
            image = document.get("image").toString(),
            type = document.get("type").toString()
        )
    }

    protected fun getProducts(onFinish: (products: MutableList<Product>) -> Unit) {
        callApiFromFireBase(SHOES) {
            val products: MutableList<Product> = mutableListOf()
            for (document in it) {
                products.add(getProductInfo(document))
            }
            onFinish(products)
        }
    }

    protected fun getProduct(id: String, onFinish: (product: Product) -> Unit) {
        callApiWhereFromFirebase(SHOES, ID, id) {
            for (document in it) {
                onFinish(getProductInfo(document))
            }
        }
    }

    protected fun getSimilarProducts(
        type: String,
        onFinish: (products: MutableList<Product>) -> Unit
    ) {
        callApiWhereFromFirebase(SHOES, TYPE, type, isLoading = false) {
            val products: MutableList<Product> = mutableListOf()
            for (document in it) {
                products.add(getProductInfo(document))
            }
            onFinish(products)
        }
    }

    protected fun searchProducts(
        productName: String,
        onFinish: (products: MutableList<Product>) -> Unit
    ) {
        if (productName.isEmpty()) onFinish(mutableListOf())
        callApiFromFireBase(SHOES) {
            val products: MutableList<Product> = mutableListOf()
            for (document in it) {
                getProductInfo(document).let { pro ->
                    if (pro.name.toLowerCase(Locale.current)
                            .contains(productName.toLowerCase(Locale.current))
                    ) {
                        products.add(pro)
                    }
                }
            }
            onFinish(products)
        }
    }

    internal fun isUserSigned() = auth.currentUser != null

    @OptIn(DelicateCoroutinesApi::class)
    internal fun userSignOut(onFinish: () -> Unit) {
        resetState(true)
        GlobalScope.launch(Dispatchers.IO) {
            val logout = async { auth.signOut() }
            logout.onAwait
            resetState(false)
            onFinish()
        }
    }
}