package com.example.compose_ui.ui.cores.data.sources.remotes

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import com.example.compose_ui.ui.cores.data.model.Category
import com.example.compose_ui.ui.cores.data.model.Person
import com.example.compose_ui.ui.cores.data.model.Product
import com.example.compose_ui.ui.cores.data.model.mapDataToProduct
import com.example.compose_ui.ui.cores.data.sources.response.ApiResponse
import com.example.compose_ui.ui.extensions.getStringValue
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await


class FirebaseServices {

    companion object {
        const val SHOES = "shoes"
        const val ID = "id"
        const val CATEGORIES = "categories"
        const val TYPE = "type"
        const val NAME = "name"
        const val SEARCH = "Search"
        const val GET = "Get"
    }

    private val auth: FirebaseAuth
        get() = Firebase.auth
    private val dataBase: FirebaseFirestore
        get() = Firebase.firestore


    private val currentUser: FirebaseUser?
        get() = auth.currentUser

    var isLoggedIn: ApiResponse<Boolean> =
        if (currentUser != null) ApiResponse.Success(true) else ApiResponse.Success(false)

    private fun getCollection(path: String) = dataBase.collection(path)

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


    private suspend fun fetchData(path: String): QuerySnapshot? {
        return try {
            getCollection(path).get().await()
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun fetchDataWhere(
        path: String,
        field: String,
        value: String,
        type: String = GET
    ): QuerySnapshot? {
        return try {
            getQueryPath(type = type, path = path, field = field, value = value).get().await()
        } catch (e: Exception) {
            null
        }
    }

    /*Auth API*/
    suspend fun login(email: String, password: String): ApiResponse<Person> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            ApiResponse.Success(Person(email, password))
        } catch (e: Exception) {
            ApiResponse.Error(e.message.getStringValue())
        }
    }

    suspend fun register(user: Person): ApiResponse<Person> {
        return try {
            auth.createUserWithEmailAndPassword(user.email, user.password).await()
            currentUser?.let {
                val profileUpdates = userProfileChangeRequest {
                    displayName = user.name
                }
                it.updateProfile(profileUpdates).await()
            }
            ApiResponse.Success(user)
        } catch (e: Exception) {
            ApiResponse.Error(e.message.toString())
        }
    }

    suspend fun logout(): ApiResponse<Boolean> {
        return try {
            auth.signOut()
            ApiResponse.Success(false)
        } catch (e: Exception) {
            ApiResponse.Error(e.message.toString())
        }
    }

    /*Home api*/
    suspend fun getCategories(): ApiResponse<MutableList<Category>> {
        return fetchData(CATEGORIES)?.run {
            val newCategories: MutableList<Category> = mutableListOf()
            for (document in this) {
                newCategories.add(
                    Category(
                        id = document.get("id").toString(),
                        name = document.get("name").toString(),
                        description = document.get("description").toString()
                    )
                )
            }
            ApiResponse.Success(newCategories)
        } ?: ApiResponse.Success(mutableListOf())
    }

    suspend fun getProducts(): ApiResponse<MutableList<Product>> {
        return fetchData(SHOES)?.run {
            val products: MutableList<Product> = mutableListOf()
            for (document in this) {
                products.add(mapDataToProduct(document))
            }
            ApiResponse.Success(products)
        } ?: ApiResponse.Success(mutableListOf())
    }

    suspend fun getProduct(id: String): ApiResponse<Product> {
        fetchDataWhere(SHOES, ID, id)?.let {
            return ApiResponse.Success(mapDataToProduct(it.firstOrNull()))
        } ?: return ApiResponse.Error("Can't find this product")
    }


    suspend fun getSimilarProducts(type: String): ApiResponse<MutableList<Product>> {
        return fetchDataWhere(SHOES, TYPE, type)?.run {
            val products: MutableList<Product> = mutableListOf()
            for (document in this) {
                products.add(mapDataToProduct(document))
            }
            return ApiResponse.Success(products)
        } ?: ApiResponse.Success(mutableListOf())
    }
}