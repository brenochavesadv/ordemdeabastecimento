package br.com.brtecno.ordemdeabastecimento.clients

import br.com.brtecno.ordemdeabastecimento.utils.SortDir
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ClientService(
    val repository: ClientRepository,
) {
    fun findByIdOrNull(id: Long): Client? = repository.findByIdOrNull(id)

    fun save(data: Client) = repository.save(data)

    fun findAll(dir: SortDir): List<Client> =
          when (dir) {
            SortDir.ASC -> repository.findAll(Sort.by("name").ascending())
            SortDir.DESC -> repository.findAll(Sort.by("name").descending())
        }

    fun delete(id: Long) = repository.findByIdOrNull(id)
            .also { repository.deleteById(id) }

        fun updateClient(id: Long, request: ClientRequest): Client? {

            val rowsUpdated = repository.updateById(
                id = id,
                name = request.name,
                email = request.email,
                phone = request.phone
            )

            return if (rowsUpdated > 0) repository.findByIdOrNull(id) else null
        }

}
