package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.Student;
import com.example.demo.repos.StudentRepo;

@Service
public class StudentService {
	@Autowired
	StudentRepo repo;
	
	public Optional<Student> findStudentById(int id){
		Optional<Student> stu = repo.findById(id);
		if(stu.isEmpty()) {
			throw new ResponseStatusException(HttpStatusCode.valueOf(204),"Invalid Student id");
		}
		else return stu;
	}
}
