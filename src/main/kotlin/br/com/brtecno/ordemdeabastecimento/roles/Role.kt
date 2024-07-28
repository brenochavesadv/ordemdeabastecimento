package br.com.brtecno.ordemdeabastecimento.roles

import jakarta.persistence.*

@Entity
@Table(name="tblRole")
class Role(
    @Id @GeneratedValue
    var id: Long? = null,

    @Column(unique = true, nullable = false)
    var name: String,

    var description: String = "",
) {
    constructor() : this(id=0, name = "")  {

    }
}
