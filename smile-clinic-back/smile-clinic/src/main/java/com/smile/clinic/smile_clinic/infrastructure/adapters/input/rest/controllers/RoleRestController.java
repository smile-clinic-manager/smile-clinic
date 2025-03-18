package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.controllers;

import com.smile.clinic.smile_clinic.application.ports.input.RoleServicePort;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers.RoleRestMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleRestController {
    private final RoleServicePort roleServicePort;
    private final RoleRestMapper roleMapper;

    @GetMapping("/findAllRoles")
    public ResponseEntity<Object> findAllRoles() {
        try{
            List<RoleDTO> roles = roleMapper.toRoleDTOList(this.roleServicePort.findAllRoles());
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
