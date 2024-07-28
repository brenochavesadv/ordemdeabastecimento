package br.com.brtecno.ordemdeabastecimento.orders

import br.com.brtecno.ordemdeabastecimento.utils.SortDir
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class OrderService(
    val repository: OrderRepository,
) {
    fun save(data: Order) = repository.save(data)

    fun findAll(dir: SortDir): List<Order> =
          when (dir) {
            SortDir.ASC -> repository.findAll(Sort.by("name").ascending())
            SortDir.DESC -> repository.findAll(Sort.by("name").descending())
        }

    fun findByIdOrNull(id: Long) = repository.findByIdOrNull(id)

    fun findByClientId(id: Long) = repository.findByClientId(id)

    fun delete(id: Long) = repository.findByIdOrNull(id)
            .also { repository.deleteById(id) }


        fun updateOrder(id: Long, request: OrderRequest): Order? {

            val rowsUpdated = repository.updateById(
                id = id,
                client = request.client.toString(),
                enabled = request.enabled,
                fulfilled = request.fulfilled,
                vehicle = request.vehicle.toString(),
                motorist = request.motorist.toString(),
                value = request.value,
                liters = request.liters,
                date = request.date,
                user = request.user.toString(),
                operator = request.operator.toString(),
            )

            return if (rowsUpdated > 0) repository.findByIdOrNull(id) else null
        }

}
