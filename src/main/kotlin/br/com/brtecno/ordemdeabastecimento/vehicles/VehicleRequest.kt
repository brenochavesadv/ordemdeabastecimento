package br.com.brtecno.ordemdeabastecimento.vehicles

import br.com.brtecno.ordemdeabastecimento.clients.Client
import jakarta.validation.constraints.NotBlank

data class VehicleRequest(

    @field:NotBlank
    val name: String,

    @field:NotBlank
    val plate: String,

    @field:NotBlank
    val client: MutableSet<Client>,

    val enabled: Boolean = false,
) {
    fun toVehicle() = Vehicle(
        name = name,
        enabled = enabled,
        plate = plate,
        client = client,
    )
}