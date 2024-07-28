package br.com.brtecno.ordemdeabastecimento.orders

import br.com.brtecno.ordemdeabastecimento.clients.Client
import br.com.brtecno.ordemdeabastecimento.motorists.Motorist
import br.com.brtecno.ordemdeabastecimento.users.User
import br.com.brtecno.ordemdeabastecimento.vehicles.Vehicle
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name="tblOrder")
class Order(

    @Id @GeneratedValue
    var id: Long? = null,

    @Column
    var enabled: Boolean = true,

    @Column
    var fulfilled: Boolean = false,

    @OneToMany
    @JoinTable(
        name = "tblOrderClient",
        joinColumns = [JoinColumn(name = "orderId")],
        inverseJoinColumns = [JoinColumn(name = "clientIdFk")]
    )
    @JsonIgnore
    val client: MutableSet<Client> = mutableSetOf(),

    @ManyToOne
    @JoinTable(
        name = "tblOrderVehicle",
        joinColumns = [JoinColumn(name = "orderId")],
        inverseJoinColumns = [JoinColumn(name = "vehicleIdFk")]
    )
    @JsonIgnore
    val vehicle: Vehicle? = null,

    @ManyToOne
    @JoinTable(
        name = "tblOrderMotorist",
        joinColumns = [JoinColumn(name = "orderId")],
        inverseJoinColumns = [JoinColumn(name = "motoristIdFk")]
    )
    @JsonIgnore
    val motorist: Motorist? = null,

    @Column(nullable = false)
    var value: Long = 0,

    @Column(nullable = false)
    var liters: Long = 0,

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    var date: Timestamp? = null,

    @ManyToMany
    @JoinTable(
        name = "tblOrderUser",
        joinColumns = [JoinColumn(name = "orderId")],
        inverseJoinColumns = [JoinColumn(name = "userIdFk")]
    )
    @JsonIgnore
    val user: MutableSet<User> = mutableSetOf(),

    @ManyToMany
    @JoinTable(
        name = "tblOrderOperator",
        joinColumns = [JoinColumn(name = "orderId")],
        inverseJoinColumns = [JoinColumn(name = "userIdFk")]
    )
    @JsonIgnore
    val operator: MutableSet<User> = mutableSetOf(),

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var createdAt: Timestamp = Timestamp(System.currentTimeMillis()),

    )
