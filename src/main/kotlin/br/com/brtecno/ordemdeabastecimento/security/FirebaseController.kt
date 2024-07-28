package br.com.brtecno.ordemdeabastecimento.security

    import com.google.firebase.auth.FirebaseAuth
    import com.google.firebase.auth.FirebaseToken
    import org.springframework.beans.factory.annotation.Autowired
    import org.springframework.http.ResponseEntity
    import org.springframework.web.bind.annotation.*

    @RestController
    @RequestMapping("/firebase")
    class FirebaseController @Autowired constructor(
        private val firebaseAuth: FirebaseAuth
    ) {

        @PostMapping("/verifyToken")
        fun verifyToken(@RequestBody request: VerifyTokenRequest): ResponseEntity<VerifyTokenResponse> {
            return try {
                val decodedToken: FirebaseToken = firebaseAuth.verifyIdToken(request.token)
                println("Token Verified, UID: ${decodedToken.uid}")
                ResponseEntity.ok(VerifyTokenResponse(decodedToken.uid))

            } catch (e: Exception) {
                e.printStackTrace()
                ResponseEntity.status(401).body(VerifyTokenResponse("Invalid token"))
            }
        }
    }

    data class CustomTokenRequest(val uid: String)
    data class CustomTokenResponse(val customToken: String)

    data class VerifyTokenRequest(val token: String)
    data class VerifyTokenResponse(val uid: String)
