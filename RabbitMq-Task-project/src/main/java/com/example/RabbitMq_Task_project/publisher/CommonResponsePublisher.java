package com.example.RabbitMq_Task_project.publisher;

import com.example.RabbitMq_Task_project.config.MessagingConfig;
import com.example.RabbitMq_Task_project.entity.CommonResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/commonresponse")
public class CommonResponsePublisher {

    private static final Logger logger = LoggerFactory.getLogger(CommonResponsePublisher.class);

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/{source}")
    public String publishCommonResponse(@RequestBody CommonResponse response, @PathVariable String source) {
        try {
            response.setProcessed(false); // Set as not processed
            logger.info("Publishing CommonResponse from source: {}", source);

            byte[] messageBody = objectMapper.writeValueAsBytes(response);
            template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, messageBody);
            return "CommonResponse published successfully!";
        } catch (JsonProcessingException e) {
            logger.error("Error converting CommonResponse to byte array: ", e);
            return "Failed to publish CommonResponse";
        }
    }
}
