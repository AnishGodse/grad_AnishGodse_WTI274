package com.example.demo.services;

import com.example.demo.entities.Student;

public interface StudentService {
    void saveToH2(Student s);
    void saveToPostgres(Student s);
    void saveToBothBestEffort(Student s);
    boolean exists(Integer rollNo);
}
