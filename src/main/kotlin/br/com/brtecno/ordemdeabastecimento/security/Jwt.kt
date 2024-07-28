package br.com.brtecno.ordemdeabastecimento.security

import br.com.brtecno.ordemdeabastecimento.users.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseToken
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component

@Component
class Jwt(private val firebaseAuth: FirebaseAuth) {

    /*fun createToken(user: User): String {
        // Firebase handles token creation, so this method can be simplified or removed
        throw UnsupportedOperationException("Token creation is handled by Firebase")
    }*/

    fun extract(req: HttpServletRequest): Authentication? {
        try {
            val header = req.getHeader(AUTHORIZATION)
            if (header == null || !header.startsWith("Bearer ")) return null
            val token = header.replace("Bearer ", "").trim()

            val decodedToken: FirebaseToken = firebaseAuth.verifyIdToken(token)
            val user = UserToken(0, decodedToken.uid, decodedToken.email)
            val authorities = listOf(SimpleGrantedAuthority("ROLE_USER")) // Adjust roles as needed
            return UsernamePasswordAuthenticationToken(user, null, authorities)
        } catch (e: Exception) {
            log.debug("Token rejected", e)
            return null
        }
    }

    companion object {
        val log = LoggerFactory.getLogger(Jwt::class.java)
    }
}