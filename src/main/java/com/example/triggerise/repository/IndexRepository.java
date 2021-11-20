package com.example.triggerise.repository;

import com.example.triggerise.entity.Index;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndexRepository extends JpaRepository<Index,String> {

    Optional<Index> findByCode(String code);
}
