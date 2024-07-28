package br.com.brtecno.ordemdeabastecimento.orders

import br.com.brtecno.ordemdeabastecimento.users.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface OrderRepository: JpaRepository<Order, Long> {

        @Modifying
        @Transactional
        @Query(
            "update Order t set" +
                    " t.enabled = :enabled, " +
                    " t.fulfilled = :fulfilled, " +
                    " t.client = :client, " +
                    " t.vehicle = :vehicle, " +
                    " t.motorist = :motorist, " +
                    " t.value = :value, " +
                    " t.liters = :liters, " +
                    " t.date = :date, " +
                    " t.user = :user, " +
                    " t.operator = :operator " +
                    " where t.id = :id")
        fun updateById(
            @Param("id") id: Long,
            @Param("enabled") enabled: Boolean,
            @Param("fulfilled") fulfilled: Boolean,
            @Param("client") client: String,
            @Param("vehicle") vehicle: String,
            @Param("motorist") motorist: String,
            @Param("value") value: Long,
            @Param("liters") liters: Long,
            @Param("date") date: String,
            @Param("user") user: String,
            @Param("operator") operator: String,
        ): Int

    @Query(
        "select distinct o from Order o" +
                " join o.client c" +
                " where c.id = :id" +
                " order by c.name"
                )
    fun findByClientId(
        @Param("id") id: Long,
    ): List<Order>
}