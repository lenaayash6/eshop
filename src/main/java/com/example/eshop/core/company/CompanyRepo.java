package com.example.eshop.core.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {
}