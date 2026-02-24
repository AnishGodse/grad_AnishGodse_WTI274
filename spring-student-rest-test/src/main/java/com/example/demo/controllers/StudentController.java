package com.example.demo.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Student;
import com.example.demo.repos.StudentRepo;

@RestController
public class StudentController {
    @Autowired
    StudentRepo repo;

    @GetMapping("/greet")
     public String hi(){
        return "Good Morning Everyone!";
    }


    @GetMapping("/students")
    public List<Student> getStudent(){
        return repo.findAll();
    }

    @GetMapping("/students/{id}")
    public Optional<Student> getstu(@PathVariable int id){
        return repo.findById(id);
    }

    @PostMapping("/students")
    public String saveEmployee(@RequestBody Student s){
        if(repo.existsById(s.getUnivRegNo())){
            return "Student Already Exists!";
        }
        repo.save(s);
        return "Successfully saved record!";
    }

    @PutMapping("/students/{id}")
    public String updateStudent(@PathVariable int id, @RequestBody Student s){
        if(s.getUnivRegNo() != id){
            return "Studet Ids doesnt match!";
        }
        if(!repo.existsById(s.getUnivRegNo())){
            return "Student with given id is not available!";
        }
        repo.save(s);
        return "Successfully saved record!";
    }

    
    @PatchMapping("students/reg")
    public String updateStudentByRegNo(@RequestBody Student s,@RequestParam int regNo) {
		if(!repo.existsById(s.getUnivRegNo())) return "Student does not exist";
		else if(s.getUnivRegNo() != regNo) return "Registration no does not match";
		Optional<Student> s1 = repo.findById(regNo);
		if(s.getName() == null)s.setName(s1.get().getName());
		if(s.getRollNo() == 0)s.setRollNo(s1.get().getRollNo());
		if(s.getGender()== null)s.setGender(s1.get().getGender());
		if(s.getPercentage() == 0)s.setPercentage(s1.get().getPercentage());
		if(s.getStandard() == 0)s.setStandard(s1.get().getStandard());
		if(s.getSchool() == null)s.setSchool(s1.get().getSchool());
		repo.save(s);
		return "Student has been updated";
	}

    @DeleteMapping("/students/{id}")
    public String deleteStudent1(@PathVariable int id){
        if(!repo.existsById(id)){
            return "No record available with the given id!";
        }
        repo.deleteById(id);
        return "Deleted successfully!";
    }
    
    @GetMapping("students/school")
    public List<Student> custom(@RequestParam String name){
        return repo.myCustomQuery(name);
    }

    @GetMapping("students/school/count")
    public Long custom1List(@RequestParam String name){
        return repo.countBySchool(name);
    }

    @GetMapping("students/school/standard/count")
    public Long countByStand(@RequestParam int standard){
        return repo.countByStandard(standard);
    }

    @GetMapping("students/result")
    public List<Student> getpercstudent(String pass){
        if(pass.equals("true")){
            return repo.myCustomQuery2();
        }
        return repo.myCustomQuery3();
    }

    @GetMapping("students/strength")
    public Long countgenderstandard(@RequestParam String gender, @RequestParam int standard){
        return repo.countByGenderAndStandard(gender, standard);
    }
}
