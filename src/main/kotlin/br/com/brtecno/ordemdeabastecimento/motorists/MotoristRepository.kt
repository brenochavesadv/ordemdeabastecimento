package br.com.brtecno.ordemdeabastecimento.motorists

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface MotoristRepository: JpaRepository<Motorist, Long> {

        @Modifying
        @Transactional
        @Query(
            "update Motorist t set" +
                    " t.name = :name," +
                    " t.enabled = :enabled, " +
                    " t.email = :email," +
                    " t.phone = :phone," +
                    " t.client = :client" +
                    " where t.id = :id")
        fun updateById(
            @Param("id") id: Long,
            @Param("name") name: String,
            @Param("enabled") enabled: Boolean,
            @Param("email") email: String,
            @Param("phone") phone: String,
            @Param("client") client: String,
        ): Int
}