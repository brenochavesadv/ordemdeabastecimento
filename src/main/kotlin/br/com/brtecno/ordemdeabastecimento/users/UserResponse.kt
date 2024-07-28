package br.com.brtecno.ordemdeabastecimento.users

data class UserResponse(
    val id: Long,
    val uid: String,
    val name: String,
    val email: String
) {
    constructor(u: User): this(
        id=u.id!!,
        uid=u.uid,
        name=u.name,
        email=u.email
    )
}
