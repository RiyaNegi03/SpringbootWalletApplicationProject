package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;



import com.masai.model.LogDetails;

public interface LogDetailsDao extends JpaRepository<LogDetails, Integer>{

}