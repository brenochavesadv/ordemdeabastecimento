package br.com.brtecno.ordemdeabastecimento.motorists

import br.com.brtecno.ordemdeabastecimento.utils.SortDir
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/motorist")
@SecurityRequirement(name="WebToken")
class MotoristController(
    val service: MotoristService
) {
    @PostMapping
    fun insert(@RequestBody @Valid request: MotoristRequest): ResponseEntity<Motorist> =
        ResponseEntity.status(HttpStatus.CREATED)
            .body(service.save(request.toMotorist()))

    @GetMapping
    fun findAll(
        @RequestParam sortDir: String? = null,
        ) =
        SortDir.entries.firstOrNull { it.name == (sortDir ?: "ASC").uppercase() }
            ?.let { service.findAll(it) }
            ?.map { MotoristResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.badRequest().build()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) =
        service.findByIdOrNull(id)
            ?.let { MotoristResponse(it) }
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
        @RequestBody @Valid request: MotoristRequest
        ): ResponseEntity<Motorist>
            = service.updateMotorist(id, request)
                ?.let { ResponseEntity.ok().build() }
                ?: ResponseEntity.notFound().build()

}