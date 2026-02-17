package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entities.Student;
import com.example.demo.services.StudentService;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping("/")
    public String studentManagement(){
        return "index.jsp";
    }

    @RequestMapping("addStudent")
    public String addStudent(Student s){
        System.out.println(s);
		if(studentService.exists(s.getRollNo()) == true){
            System.out.println("Employee already exists!");
        }else{
            studentService.saveToBothBestEffort(s);
        }
        return "index.jsp";
    }
}