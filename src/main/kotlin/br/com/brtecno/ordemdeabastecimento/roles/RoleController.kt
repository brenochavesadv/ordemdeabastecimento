package br.com.brtecno.ordemdeabastecimento.roles

import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/roles")
@SecurityRequirement(name="WebToken")
class RoleController(
    val service: RoleService
) {
    @PostMapping
    fun insert(@Valid @RequestBody role: RoleRequest) =
        role.toRole()
            .let { service.insert(it) }
            .let { RoleResponse(it) }
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping
    fun list() = service.findAll()
        .map { RoleResponse(it) }
        .let { ResponseEntity.ok(it) }


}
