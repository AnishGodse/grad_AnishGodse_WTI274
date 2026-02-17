package com.example.demo.repos.pg;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Student;

public interface StudentPostgrerepo extends JpaRepository<Student, Integer>{

}
