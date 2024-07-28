package br.com.brtecno.ordemdeabastecimento.clients

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
@Table(name="tblClient")
class Client(
    @Id @GeneratedValue
    var id: Long? = null,
    @Column(unique = true, nullable = false)
    var name: String = "",
    @Column(unique = true, nullable = false)
    var email: String = "",
    @NotNull
    var phone: String = "",
)
