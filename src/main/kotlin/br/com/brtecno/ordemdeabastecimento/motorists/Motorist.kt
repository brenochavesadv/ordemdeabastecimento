package br.com.brtecno.ordemdeabastecimento.motorists

import br.com.brtecno.ordemdeabastecimento.clients.Client
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
@Table(name="tblMotorist")
class Motorist(

    @Id @GeneratedValue
    var id: Long? = null,

    @Column(unique = true, nullable = false)
    var name: String = "",

    @Column
    var enabled: Boolean = true,

    @Column(unique = true, nullable = false)
    var email: String = "",

    @NotNull
    var phone: String = "",

    @OneToMany
    @JoinTable(
        name = "tblMotoristClient",
        joinColumns = [JoinColumn(name = "motoristId")],
        inverseJoinColumns = [JoinColumn(name = "clientIdFk")]
    )
    @JsonIgnore
    val client: MutableSet<Client> = mutableSetOf(),
)
