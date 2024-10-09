package com.example.RabbitMq_Task_project.schedular;

import com.example.RabbitMq_Task_project.entity.CommonResponse;
import com.example.RabbitMq_Task_project.entity.Student;
import com.example.RabbitMq_Task_project.entity.Department;
import com.example.RabbitMq_Task_project.repository.CommonResponseRepository;
import com.example.RabbitMq_Task_project.repository.StudentRepository;
import com.example.RabbitMq_Task_project.repository.DepartmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CommonResponseScheduler {

    private static final Logger logger = LoggerFactory.getLogger(CommonResponseScheduler.class);

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final CommonResponseRepository commonResponseRepository;
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    public CommonResponseScheduler(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper,
                                   CommonResponseRepository commonResponseRepository,
                                   StudentRepository studentRepository,
                                   DepartmentRepository departmentRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
        this.commonResponseRepository = commonResponseRepository;
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }
    @Scheduled(fixedRate = 10000) // Every 10 seconds
    @Transactional
    public void processCommonResponses() {
        logger.info("Scheduler triggered at: {}", System.currentTimeMillis());
        try {
            List<CommonResponse> responses = commonResponseRepository.findByProcessedFalse(); // Fetch only unprocessed responses

            for (CommonResponse response : responses) {
                logger.info("Processing CommonResponse: {}", response);
                // Process and save Student details
                Student student = new Student();
                student.setName(response.getName());
                student.setEmail(response.getEmail());
                student.setContact(response.getContact());
                student.setCollege(response.getCollege());
                studentRepository.save(student);
                logger.info("Saved Student: {}", student);

                // Process and save Department details
                Department department = new Department();
                department.setBranch(response.getBranch());
                department.setYear(response.getYear());
                department.setSection(response.getSection());
                departmentRepository.save(department);
                logger.info("Saved Department: {}", department);

                // Mark CommonResponse as processed
                response.setProcessed(true);
                commonResponseRepository.save(response);
                logger.info("Marked CommonResponse as processed: {}", response);
            }
        } catch (Exception e) {
            logger.error("Error processing CommonResponse: ", e);
        }
    }

}
