package vn.edu.iuh.creditclass.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import vn.edu.iuh.creditclass.dto.EmailDetails;

@RequiredArgsConstructor
@Service
public class MessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(EmailDetails emailDetails) {
        rabbitTemplate.convertAndSend("classRegistration",  emailDetails);
    }
}
