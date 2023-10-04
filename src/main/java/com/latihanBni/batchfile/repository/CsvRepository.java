package com.latihanBni.batchfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.latihanBni.batchfile.entity.CsvFile;

@Repository
public interface CsvRepository extends JpaRepository<CsvFile, Integer> {

}
