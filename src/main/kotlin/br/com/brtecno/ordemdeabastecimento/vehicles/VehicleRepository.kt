package br.com.brtecno.ordemdeabastecimento.vehicles

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface VehicleRepository: JpaRepository<Vehicle, Long> {

        @Modifying
        @Transactional
        @Query(
            "update Vehicle t set" +
                    " t.name = :name," +
                    " t.enabled = :enabled, " +
                    " t.plate = :plate," +
                    " t.client = :client" +
                    " where t.id = :id")
        fun updateById(
            @Param("id") id: Long,
            @Param("name") name: String,
            @Param("enabled") enabled: Boolean,
            @Param("plate") plate: String,
            @Param("client") client: String,
        ): Int
}