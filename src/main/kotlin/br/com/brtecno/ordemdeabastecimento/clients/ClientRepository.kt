package br.com.brtecno.ordemdeabastecimento.clients

import br.com.brtecno.ordemdeabastecimento.motorists.Motorist
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface ClientRepository: JpaRepository<Client, Long> {

        @Modifying
        @Transactional
        @Query(
            "update Client c set" +
                    " c.name = :name," +
                    " c.email = :email," +
                    " c.phone = :phone" +
                    " where c.id = :id")
        fun updateById(
            @Param("id") id: Long,
            @Param("name") name: String,
            @Param("email") email: String,
            @Param("phone") phone: String
        ): Int
}