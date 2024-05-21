package com.ms.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.application.data.entity.Application;
import org.springframework.data.jpa.repository.Query;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query(value="select nextval('application_running_no_seq')", nativeQuery = true)
    Integer getApplicationRunningNo();
}
