package com.example.demo2.repository;

import com.example.demo2.models.Escoba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscobaRepository extends JpaRepository<Escoba, Long> {}