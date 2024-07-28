package br.com.brtecno.ordemdeabastecimento.vehicles

import br.com.brtecno.ordemdeabastecimento.motorists.Motorist
import br.com.brtecno.ordemdeabastecimento.utils.SortDir
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/vehicle")
@SecurityRequirement(name="WebToken")
class VehicleController(
    val service: VehicleService
) {
    @PostMapping
    fun insert(@RequestBody @Valid request: VehicleRequest): ResponseEntity<Vehicle> =
        ResponseEntity.status(HttpStatus.CREATED)
            .body(service.save(request.toVehicle()))

    @GetMapping
    fun findAll(
        @RequestParam sortDir: String? = null,
        ) =
        SortDir.entries.firstOrNull { it.name == (sortDir ?: "ASC").uppercase() }
            ?.let { service.findAll(it) }
            ?.map { VehicleResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.badRequest().build()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) =
        service.findByIdOrNull(id)
            ?.let { VehicleResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Void> =
        service.delete(id)
            ?.let { ResponseEntity.ok().build() }
            ?: ResponseEntity.notFound().build()

    @PutMapping("/{id}")
    fun updateById(
        @PathVariable id: Long,
        @RequestBody @Valid request: VehicleRequest
        ): ResponseEntity<Motorist>
            = service.updateVehicle(id, request)
                ?.let { ResponseEntity.ok().build() }
                ?: ResponseEntity.notFound().build()

}