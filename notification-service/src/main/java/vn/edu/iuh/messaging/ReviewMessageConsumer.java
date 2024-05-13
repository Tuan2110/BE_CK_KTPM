package vn.edu.iuh.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import vn.edu.iuh.models.EmailDetails;
import vn.edu.iuh.services.EmailService;

@RequiredArgsConstructor
@Service
public class ReviewMessageConsumer {
    private final EmailService emailService;

    @RabbitListener(queues = "classRegistration")
    public void receiveMessage(EmailDetails emailDetails) {
        emailService.sendSimpleMail(emailDetails);
    }
}
