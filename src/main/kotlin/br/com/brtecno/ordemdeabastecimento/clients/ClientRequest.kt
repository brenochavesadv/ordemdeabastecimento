package br.com.brtecno.ordemdeabastecimento.clients

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class ClientRequest(

    @field:NotBlank
    val name: String,
    @field:Email
    val email: String,
    @field:NotBlank
    val phone: String,
) {
    fun toClient() = Client(
        name = name,
        email = email,
        phone = phone,
    )
}
