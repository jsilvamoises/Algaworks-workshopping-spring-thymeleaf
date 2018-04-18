package com.jsm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsm.model.Vinho;

@Repository
public interface VinhoRepository extends JpaRepository<Vinho,Long> {

}
