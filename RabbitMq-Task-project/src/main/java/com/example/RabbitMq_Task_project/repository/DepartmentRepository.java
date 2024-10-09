package com.example.RabbitMq_Task_project.repository;

//import com.example.RabbitMq_Task_project1.entity.Department;
import com.example.RabbitMq_Task_project.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}