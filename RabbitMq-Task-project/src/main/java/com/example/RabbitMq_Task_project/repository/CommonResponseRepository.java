package com.example.RabbitMq_Task_project.repository;


//import com.example.RabbitMq_Task_project.entity.CommonMessage;
import com.example.RabbitMq_Task_project.entity.CommonResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommonResponseRepository extends JpaRepository<CommonResponse, Long> {
    List<CommonResponse> findByProcessedFalse(); // Fetch only unprocessed responses

}