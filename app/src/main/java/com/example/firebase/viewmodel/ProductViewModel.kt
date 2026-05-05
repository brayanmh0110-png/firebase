package com.example.firebase.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.firebase.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

class ProductViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val productsCollection = db.collection("products")

    // Estado observable para la UI
    val products = mutableStateListOf<Product>()

    init {
        getProducts()
    }

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

    fun saveProduct(name: String, price: String, onComplete: () -> Unit) {
        val product = hashMapOf(
            "name" to name,
            "price" to price
        )
        productsCollection.add(product)
            .addOnCompleteListener { 
                // Esto se ejecuta SIEMPRE (éxito o error)
                onComplete() 
            }
    }
}
