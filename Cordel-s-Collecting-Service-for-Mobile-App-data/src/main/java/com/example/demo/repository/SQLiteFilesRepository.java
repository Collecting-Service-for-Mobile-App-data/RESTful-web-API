package com.example.demo.repository;

import com.example.demo.models.Company;
import com.example.demo.models.SQLiteFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SQLiteFilesRepository extends JpaRepository<SQLiteFiles, Long> {
    List<SQLiteFiles> findAllByCompany(Company company);
}

