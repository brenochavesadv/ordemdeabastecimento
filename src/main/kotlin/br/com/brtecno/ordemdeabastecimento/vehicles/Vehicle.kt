package br.com.brtecno.ordemdeabastecimento.vehicles

import br.com.brtecno.ordemdeabastecimento.clients.Client
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name="tblVehicle")
class Vehicle(

    @Id @GeneratedValue
    var id: Long? = null,

    @Column(unique = true, nullable = false)
    var name: String = "",

    @Column
    var enabled: Boolean = true,

    @Column(nullable = false)
    var plate: String = "",

    @OneToMany
    @JoinTable(
        name = "tblVehicleClient",
        joinColumns = [JoinColumn(name = "vehicleId")],
        inverseJoinColumns = [JoinColumn(name = "clientIdFk")]
    )
    @JsonIgnore
    val client: MutableSet<Client> = mutableSetOf(),
)
