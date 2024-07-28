package br.com.brtecno.ordemdeabastecimento.clients

data class ClientResponse(
    val id: Long,
    val name: String,
    val email: String,
    val phone: String
) {
    constructor(c: Client): this(
        id=c.id!!,
        name=c.name,
        email=c.email,
        phone=c.phone,
    )
}
