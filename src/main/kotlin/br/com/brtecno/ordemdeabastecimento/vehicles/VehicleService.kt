package br.com.brtecno.ordemdeabastecimento.vehicles

import br.com.brtecno.ordemdeabastecimento.utils.SortDir
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class VehicleService(
    val repository: VehicleRepository,
) {
    fun save(data: Vehicle) = repository.save(data)

    fun findAll(dir: SortDir): List<Vehicle> =
          when (dir) {
            SortDir.ASC -> repository.findAll(Sort.by("name").ascending())
            SortDir.DESC -> repository.findAll(Sort.by("name").descending())
        }

    fun findByIdOrNull(id: Long) = repository.findByIdOrNull(id)

    fun delete(id: Long) = repository.findByIdOrNull(id)
            .also { repository.deleteById(id) }


        fun updateVehicle(id: Long, request: VehicleRequest): Vehicle? {

            val rowsUpdated = repository.updateById(
                id = id,
                name = request.name,
                enabled = request.enabled,
                plate = request.plate,
                client = request.client.toString(),
            )

            return if (rowsUpdated > 0) repository.findByIdOrNull(id) else null
        }

}
