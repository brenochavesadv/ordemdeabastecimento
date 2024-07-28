package br.com.brtecno.ordemdeabastecimento.orders

import br.com.brtecno.ordemdeabastecimento.clients.Client
import br.com.brtecno.ordemdeabastecimento.motorists.Motorist
import br.com.brtecno.ordemdeabastecimento.users.User
import br.com.brtecno.ordemdeabastecimento.vehicles.Vehicle
import jakarta.validation.constraints.NotBlank
import br.com.brtecno.ordemdeabastecimento.utils.ConvertDate

data class OrderRequest(

    @field:NotBlank
    val client: MutableSet<Client>,

    val enabled: Boolean = false,

    val fulfilled: Boolean = false,

    @field:NotBlank
    val vehicle: Vehicle? = null,

    val motorist: Motorist? = null,

    val value: Long = 0,

    val liters: Long = 0,

    val date: String = "",

    val user: MutableSet<User>,

    val operator: MutableSet<User>,

    ) {
    fun toOrder() = Order(
        client = client,
        enabled = enabled,
        fulfilled = fulfilled,
        vehicle = vehicle,
        motorist = motorist,
        value = value,
        liters = liters,
        date = ConvertDate().StringToTimestamp(date),
        user = user,
        operator = operator,
    )
}