package com.example.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Student;
import com.example.demo.repos.h2.StudentH2repo;
import com.example.demo.repos.pg.StudentPostgrerepo;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentH2repo h2Repo;
    private final StudentPostgrerepo pgRepo;

    public StudentServiceImpl(StudentH2repo h2Repo,StudentPostgrerepo pgRepo) {
        this.h2Repo = h2Repo;
        this.pgRepo = pgRepo;
    }

    @Override
    @Transactional(transactionManager = "h2TransactionManager")
    public void saveToH2(Student s) {
        h2Repo.save(s);
    }

    @Override
    @Transactional(transactionManager = "postgresTransactionManager")
    public void saveToPostgres(Student s) {
        pgRepo.save(s);
    }

    @Override
    public void saveToBothBestEffort(Student s) {
        saveToH2(s);         
        saveToPostgres(s);   
    }

    @Override
    public boolean exists(Integer rollNo) {
        boolean inH2 = h2Repo.existsById(rollNo);
        boolean inPg = pgRepo.existsById(rollNo);
        return inH2 || inPg;
    }

}
