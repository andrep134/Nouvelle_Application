package com.example.eebb.auth

import android.content.Context
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

object AuthManager {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    fun currentUser(): FirebaseUser? = auth.currentUser

    fun ensureFirebase(context: Context): Boolean {
        return try {
            if (FirebaseApp.getApps(context).isEmpty()) {
                FirebaseApp.initializeApp(context)
            }
            true
        } catch (e: Exception) {
            Toast.makeText(
                context,
                "Configuration Firebase manquante",
                Toast.LENGTH_LONG
            ).show()
            false
        }
    }

    suspend fun signIn(context: Context, email: String, password: String): FirebaseUser? {
        ensureFirebase(context)
        return try {
            auth.signInWithEmailAndPassword(email, password).await().user
        } catch (e: Exception) {
            Toast.makeText(context, e.localizedMessage ?: "Connexion impossible", Toast.LENGTH_LONG).show()
            null
        }
    }

    suspend fun register(context: Context, email: String, password: String): FirebaseUser? {
        ensureFirebase(context)
        return try {
            auth.createUserWithEmailAndPassword(email, password).await().user
        } catch (e: Exception) {
            Toast.makeText(context, e.localizedMessage ?: "Inscription impossible", Toast.LENGTH_LONG).show()
            null
        }
    }

    suspend fun resetPassword(context: Context, email: String): Boolean {
        ensureFirebase(context)
        return try {
            auth.sendPasswordResetEmail(email).await()
            true
        } catch (e: Exception) {
            Toast.makeText(context, e.localizedMessage ?: "Envoi impossible", Toast.LENGTH_LONG).show()
            false
        }
    }

    fun signOut() {
        auth.signOut()
    }
}
