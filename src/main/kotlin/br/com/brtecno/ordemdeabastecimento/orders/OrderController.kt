package br.com.brtecno.ordemdeabastecimento.orders

import br.com.brtecno.ordemdeabastecimento.motorists.Motorist
import br.com.brtecno.ordemdeabastecimento.users.UserResponse
import br.com.brtecno.ordemdeabastecimento.utils.SortDir
import br.com.brtecno.ordemdeabastecimento.vehicles.Vehicle
import br.com.brtecno.ordemdeabastecimento.vehicles.VehicleRequest
import br.com.brtecno.ordemdeabastecimento.vehicles.VehicleResponse
import br.com.brtecno.ordemdeabastecimento.vehicles.VehicleService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order")
@SecurityRequirement(name="WebToken")
class OrderController(
    val service: OrderService
) {
    @PostMapping
    fun insert(@RequestBody @Valid request: OrderRequest): ResponseEntity<Order> =
        ResponseEntity.status(HttpStatus.CREATED)
            .body(service.save(request.toOrder()))

    @GetMapping
    fun findAll(
        @RequestParam sortDir: String? = null,
        ) =
        SortDir.entries.firstOrNull { it.name == (sortDir ?: "ASC").uppercase() }
            ?.let { service.findAll(it) }
            ?.map { OrderResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.badRequest().build()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) =
        service.findByIdOrNull(id)
            ?.let { OrderResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/client/{id}")
    fun findByClientId(@PathVariable id: Long) =
        service.findByClientId(id)
            .map { OrderResponse(it) }
            .let { ResponseEntity.ok(it) }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Void> =
        service.delete(id)
            ?.let { ResponseEntity.ok().build() }
            ?: ResponseEntity.notFound().build()

    @PutMapping("/{id}")
    fun updateById(
        @PathVariable id: Long,
        @RequestBody @Valid request: OrderRequest
        ): ResponseEntity<Motorist>
            = service.updateOrder(id, request)
                ?.let { ResponseEntity.ok().build() }
                ?: ResponseEntity.notFound().build()

}