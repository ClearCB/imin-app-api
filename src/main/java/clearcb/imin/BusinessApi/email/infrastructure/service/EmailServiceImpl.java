package clearcb.imin.BusinessApi.email.infrastructure.service;

import clearcb.imin.BusinessApi.auth.infrastructure.entity.UserEntity;
import clearcb.imin.BusinessApi.email.infrastructure.dto.EmailSendRequestDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class EmailServiceImpl {

    private final JavaMailSender emailSender;

    EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendSimpleMessage(
            EmailSendRequestDTO emailSendRequestDTO) throws MessagingException {

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        helper.setTo(emailSendRequestDTO.to());

        if (!emailSendRequestDTO.cc().isEmpty()){
            helper.setCc(emailSendRequestDTO.cc());
        }

        helper.setSubject(emailSendRequestDTO.subject());

        helper.setText(emailSendRequestDTO.message(), true);

        emailSender.send(mimeMessage);

    }

    public void sendVerificationEmail(UserEntity userEntity, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = userEntity.getEmail();
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "I'm in Team.";

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", userEntity.getUsername());
        String verifyURL = siteURL + "/api/v1/auth/authentication/verify?code=" + userEntity.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        emailSender.send(message);

    }
}