package clearcb.imin.BusinessApi.email.infrastructure.controller;

import clearcb.imin.BusinessApi.common.domain.criteria.ApiResponse;
import clearcb.imin.BusinessApi.email.infrastructure.dto.EmailSendRequestDTO;
import clearcb.imin.BusinessApi.email.infrastructure.service.EmailServiceImpl;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {


    private final EmailServiceImpl emailService;

    public EmailController(
            EmailServiceImpl emailService
    ) {
        this.emailService = emailService;
    }

    @SneakyThrows
    @PostMapping(path = "/send-email",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getAllCategories(@RequestBody @Valid EmailSendRequestDTO emailSendRequestDTO) {

        emailService.sendSimpleMessage(emailSendRequestDTO);

        ApiResponse apiResponse = new ApiResponse(
                "Email sent" + emailSendRequestDTO,
                "",
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(apiResponse);
    }

}
