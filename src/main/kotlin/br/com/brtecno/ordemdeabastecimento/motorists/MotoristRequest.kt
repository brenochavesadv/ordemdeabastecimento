package br.com.brtecno.ordemdeabastecimento.motorists

import br.com.brtecno.ordemdeabastecimento.clients.Client
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class MotoristRequest(

    @field:NotBlank
    val name: String,

    @field:Email
    val email: String,

    @field:NotBlank
    val phone: String,

    @field:NotBlank
    val client: MutableSet<Client>,

    val enabled: Boolean = false,
) {
    fun toMotorist() = Motorist(
        name = name,
        enabled = enabled,
        email = email,
        phone = phone,
        client = client,
    )
}