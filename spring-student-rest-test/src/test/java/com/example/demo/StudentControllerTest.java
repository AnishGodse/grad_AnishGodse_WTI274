package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.Student;
import com.example.demo.services.StudentService;

@SpringBootTest
public class StudentControllerTest {
	
	@Autowired
	StudentService service;
	
	@Test
	public void testService() {
		Optional<Student> res = service.findStudentById(1);
		assertTrue(res.isPresent());
	}
	
	@Test
	void shouldThrowException() {
		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> service.findStudentById(1));
		assertEquals("Invalid Student id", exception.getReason());
		assertEquals(204, exception.getStatusCode().value());
	}
}
