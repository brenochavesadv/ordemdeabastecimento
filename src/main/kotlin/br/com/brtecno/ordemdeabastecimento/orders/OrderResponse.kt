package br.com.brtecno.ordemdeabastecimento.orders

data class OrderResponse(
    val id: Long,
    val enabled: Boolean,
    val fulfilled: Boolean,
    val client: String,
    val vehicle: String,
    val value: Long,
    val liters: Long,
    val date: String,
    val user: String,
    val operator: String,
    val createdAt: String,
) {
    constructor(value: Order): this(
        id=value.id!!,
        enabled= value.enabled,
        fulfilled= value.fulfilled,
        client= value.client.toString(),
        vehicle= value.vehicle.toString(),
        value= value.value,
        liters= value.liters,
        date= value.date.toString(),
        user= value.user.toString(),
        operator= value.operator.toString(),
        createdAt= value.createdAt.toString(),
    )
}
