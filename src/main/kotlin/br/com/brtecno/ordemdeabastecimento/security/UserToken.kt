package br.com.brtecno.ordemdeabastecimento.security

import br.com.brtecno.ordemdeabastecimento.users.User

data class UserToken(
    val id: Long,
    val uid: String,
    val email: String,
    //val roles: Set<String>
) {
    constructor(): this(0, "", "")
    constructor(user: User): this(
        id = user.id!!,
        uid = user.uid,
        email = user.email,
        //roles = user.roles.map { it.name }.toSortedSet()
    )

 //   @get:JsonIgnore
 //   val isAdmin: Boolean get() = "ADMIN" in roles
}
