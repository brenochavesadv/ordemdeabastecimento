package br.com.brtecno.ordemdeabastecimento.users

import br.com.brtecno.ordemdeabastecimento.roles.RoleRepository
import br.com.brtecno.ordemdeabastecimento.utils.SortDir
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
    val roleRepository: RoleRepository,
) {
    fun save(user: User) = userRepository.save(user)

    fun findAll(dir: SortDir, role: String?) =
        role?.let { r ->
            when (dir) {
                SortDir.ASC -> userRepository.findByRole(r.uppercase()).sortedBy { it.name }
                SortDir.DESC -> userRepository.findByRole(r.uppercase()).sortedByDescending { it.name }
            }
        } ?: when (dir) {
            SortDir.ASC -> userRepository.findAll(Sort.by("name").ascending())
            SortDir.DESC -> userRepository.findAll(Sort.by("name").descending())
        }

    fun findByIdOrNull(id: Long) = userRepository.findByIdOrNull(id)

    fun delete(id: Long) = userRepository.findByIdOrNull(id)
            .also { userRepository.deleteById(id) }

    fun addRole(id: Long, roleName: String): Boolean {
        val user = userRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("User $id not found!")

        if (user.role.any { it.name == roleName }) return false

        val role = roleRepository.findByName(roleName)
            ?: throw IllegalArgumentException("Invalid role $roleName!")

        user.role.add(role)
        userRepository.save(user)
        return true
    }

    companion object {
        val log = LoggerFactory.getLogger(UserService::class.java)
    }
}