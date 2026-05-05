package com.example.firebase

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

class ProductViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val productsCollection = db.collection("products")

    // Estado para la lista de productos
    val products = mutableStateListOf<Product>()

    init {
        getProducts()
    }

    // Escucha cambios en tiempo real desde Firestore
    private fun getProducts() {
        productsCollection.addSnapshotListener { snapshot, error ->
            if (error != null) return@addSnapshotListener
            
            snapshot?.let {
                products.clear()
                for (doc in it) {
                    val product = doc.toObject<Product>().copy(id = doc.id)
                    products.add(product)
                }
            }
        }
    }

    // Guarda un nuevo producto
    fun saveProduct(name: String, price: String, onComplete: () -> Unit) {
        val product = hashMapOf(
            "name" to name,
            "price" to price,
        )
        productsCollection.add(product)
            .addOnSuccessListener { onComplete() }
    }
}
