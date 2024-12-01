package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers;

import com.smile.clinic.smile_clinic.domain.models.auth.AuthenticationRequest;
import com.smile.clinic.smile_clinic.domain.models.auth.AuthenticationResponse;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.authenticationsDTO.AuthenticationRequestDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.authenticationsDTO.AuthenticationResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthRestMapper {

    AuthenticationRequest toAuthenticationRequest(AuthenticationRequestDTO authenticationRequestDTO);

    AuthenticationRequestDTO toAuthenticationRequestDTO(AuthenticationRequest authenticationRequest);

    @Mapping(source = "token", target = "token")
    AuthenticationResponse toAuthenticationResponse(AuthenticationResponseDTO authenticationResponseDTO);

    @Mapping(source = "token", target = "token")
    AuthenticationResponseDTO toAuthenticationResponseDTO(AuthenticationResponse authenticationResponse);

}
