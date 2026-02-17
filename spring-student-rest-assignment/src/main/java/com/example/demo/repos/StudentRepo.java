package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Student;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Integer>{
    @Query("from Student where school=?1")
    public List<Student> myCustomQuery(String school);

    public long countBySchool(String school);

    public long countByStandard(int standard);

    @Query(value = "from Student where percentage > 40 order by percentage desc")
    public List<Student> myCustomQuery2();

    @Query(value = "from Student where percentage < 40 order by percentage desc")
    public List<Student> myCustomQuery3();

    
    @Query(
      value = "SELECT COUNT(*) FROM student WHERE gender = :gender AND standard = :standard",
      nativeQuery = true
    )
    long countByGenderAndStandard(@Param("gender") String gender, @Param("standard") int standard);

}
