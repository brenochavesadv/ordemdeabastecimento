package br.com.brtecno.ordemdeabastecimento.vehicles

data class VehicleResponse(
    val id: Long,
    val name: String,
    val enabled: Boolean,
    val plate: String,
    val client: String,
) {
    constructor(value: Vehicle): this(
        id=value.id!!,
        name=value.name,
        enabled=value.enabled,
        plate=value.plate,
        client= value.client.toString(),
    )
}
