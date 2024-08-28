package clearcb.imin.BusinessApi.auth.infrastructure.controller;

import clearcb.imin.BusinessApi.auth.application.service.AuthorizationContextManager;
import clearcb.imin.BusinessApi.auth.domain.model.ActionContextEnum;
import clearcb.imin.BusinessApi.auth.domain.model.AuthorizationRequest;
import clearcb.imin.BusinessApi.auth.domain.model.AuthorizationResponse;
import clearcb.imin.BusinessApi.auth.infrastructure.dto.AuthorizationRequestDTO;
import clearcb.imin.BusinessApi.auth.infrastructure.dto.AuthorizationResponseDTO;
import clearcb.imin.BusinessApi.auth.infrastructure.mapper.AuthorizationRequestMapper;
import clearcb.imin.BusinessApi.common.domain.port.output.Logger;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/authorize")
@RequiredArgsConstructor
public class AuthorizeController {

    private final AuthorizationContextManager authorizationContextManager;
    private final Logger logger;

    @PostMapping(path = "/event", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorizationResponseDTO> authorizeEventAction(@RequestBody @Valid AuthorizationRequestDTO authorizationRequestDTO) {

        logger.info("Authorization request: " + authorizationRequestDTO);

        AuthorizationRequest authorizationRequest = AuthorizationRequestMapper.toDomain(authorizationRequestDTO);
        AuthorizationResponse authorizationResponse = authorizationContextManager.executeStrategy(ActionContextEnum.EVENT, authorizationRequest);

        AuthorizationResponseDTO authorizationResponseDTO = AuthorizationRequestMapper.toDTO(authorizationResponse);

        logger.info("Authorization response status: " + authorizationResponseDTO);
        if (authorizationResponse.result()) {
            return ResponseEntity.ok(authorizationResponseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(authorizationResponseDTO);
        }

    }

    @PostMapping(path = "/community", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorizationResponseDTO> authorizeCommunityAction(@RequestBody @Valid AuthorizationRequestDTO authorizationRequestDTO) {

        logger.info("Authorization request: " + authorizationRequestDTO);

        AuthorizationRequest authorizationRequest = AuthorizationRequestMapper.toDomain(authorizationRequestDTO);
        AuthorizationResponse authorizationResponse = authorizationContextManager.executeStrategy(ActionContextEnum.COMMUNITY, authorizationRequest);

        AuthorizationResponseDTO authorizationResponseDTO = AuthorizationRequestMapper.toDTO(authorizationResponse);

        logger.info("Authorization response status: " + authorizationResponseDTO);
        if (authorizationResponse.result()) {
            return ResponseEntity.ok(authorizationResponseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(authorizationResponseDTO);
        }

    }

}