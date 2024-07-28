package br.com.brtecno.ordemdeabastecimento.motorists


data class MotoristResponse(
    val id: Long,
    val name: String,
    val enabled: Boolean,
    val email: String,
    val phone: String,
    val client: String,
) {
    constructor(value: Motorist): this(
        id=value.id!!,
        name=value.name,
        enabled=value.enabled,
        email=value.email,
        phone=value.phone,
        client= value.client.toString(),
    )
}
