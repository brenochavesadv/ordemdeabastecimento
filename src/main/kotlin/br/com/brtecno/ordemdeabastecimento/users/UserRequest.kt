package br.com.brtecno.ordemdeabastecimento.users

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class UserRequest(

    @field:NotBlank
    val uid: String,
    @field:Email
    val email: String,
    @field:NotBlank
    val name: String,
) {
    fun toUser() = User(
        uid = uid,
        email = email,
        name = name,
    )
}
