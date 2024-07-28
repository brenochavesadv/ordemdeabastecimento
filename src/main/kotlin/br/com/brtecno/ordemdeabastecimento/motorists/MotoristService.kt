package br.com.brtecno.ordemdeabastecimento.motorists

import br.com.brtecno.ordemdeabastecimento.utils.SortDir
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MotoristService(
    val repository: MotoristRepository,
) {
    fun save(motorist: Motorist) = repository.save(motorist)

    fun findAll(dir: SortDir): List<Motorist> =
          when (dir) {
            SortDir.ASC -> repository.findAll(Sort.by("name").ascending())
            SortDir.DESC -> repository.findAll(Sort.by("name").descending())
        }

    fun findByIdOrNull(id: Long) = repository.findByIdOrNull(id)

    fun delete(id: Long) = repository.findByIdOrNull(id)
            .also { repository.deleteById(id) }


        fun updateMotorist(id: Long, request: MotoristRequest): Motorist? {

            val rowsUpdated = repository.updateById(
                id = id,
                name = request.name,
                enabled = request.enabled,
                email = request.email,
                phone= request.phone,
                client = request.client.toString(),
            )

            return if (rowsUpdated > 0) repository.findByIdOrNull(id) else null
        }

}
