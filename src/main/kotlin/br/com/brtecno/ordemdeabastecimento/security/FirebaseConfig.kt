package br.com.brtecno.ordemdeabastecimento.security

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.IOException
import javax.annotation.PostConstruct

@Configuration
class FirebaseConfig {

    @Value("\${firebase.config.path}")
    lateinit var firebaseConfigPath: String

    @PostConstruct
    fun initializeFirebase() {
        try {
            println("Initializing Firebase with config path: $firebaseConfigPath")
            val serviceAccount = this::class.java.getResourceAsStream(firebaseConfigPath)
                ?: throw RuntimeException("File not found: $firebaseConfigPath")

            val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build()

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options)
            }
        } catch (e: IOException) {
            println("Error initializing Firebase: ${e.message}")
            e.printStackTrace()
            throw RuntimeException("Error initializing Firebase", e)
        }
    }

    @Bean
    fun firebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}