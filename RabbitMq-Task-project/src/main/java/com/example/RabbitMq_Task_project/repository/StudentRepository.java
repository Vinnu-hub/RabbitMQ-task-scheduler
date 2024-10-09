package com.example.RabbitMq_Task_project.repository;
//import com.example.RabbitMq_Task_project1.entity.Student;
import com.example.RabbitMq_Task_project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}