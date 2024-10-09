package com.example.RabbitMq_Task_project.consumer;

import com.example.RabbitMq_Task_project.config.MessagingConfig;
import com.example.RabbitMq_Task_project.entity.CommonResponse;
import com.example.RabbitMq_Task_project.repository.CommonResponseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonResponseConsumer {

    private static final Logger logger = LoggerFactory.getLogger(CommonResponseConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CommonResponseRepository commonResponseRepository;

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void receiveMessage(byte[] messageBody) {
        try {
            logger.info("Received byte array: {}", byteArrayToHex(messageBody));
            CommonResponse response = objectMapper.readValue(messageBody, CommonResponse.class);
            logger.info("Received CommonResponse: {}", response);
            commonResponseRepository.save(response);
            logger.info("CommonResponse saved to repository.");
        } catch (Exception e) {
            logger.error("Error processing message: ", e);
        }
    }

    private String byteArrayToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString().trim();
    }
}
