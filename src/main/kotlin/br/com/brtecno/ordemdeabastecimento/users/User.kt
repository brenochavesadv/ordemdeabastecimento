package br.com.brtecno.ordemdeabastecimento.users

import br.com.brtecno.ordemdeabastecimento.clients.Client
import br.com.brtecno.ordemdeabastecimento.roles.Role
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
@Table(name="tblUser")
class User(
    @Id @GeneratedValue
    var id: Long? = null,
    @Column(unique = true, nullable = false)
    var uid: String = "",
    @Column(unique = true, nullable = false)
    var email: String = "",
    @NotNull
    var name: String = "",

    @OneToMany
    @JoinTable(
        name = "tblUserClient",
        joinColumns = [JoinColumn(name = "userId")],
        inverseJoinColumns = [JoinColumn(name = "clientIdFk")]
    )
    @JsonIgnore
    val client: Set<Client> = mutableSetOf(),

    @ManyToMany
    @JoinTable(
        name="tblUserRole",
        joinColumns = [JoinColumn(name = "userId")],
        inverseJoinColumns = [JoinColumn(name = "roleIdFk")]
    )
    @JsonIgnore
    val role: MutableSet<Role> = mutableSetOf()
)
