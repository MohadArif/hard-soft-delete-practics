package com.practice.demospringbootproject.repository;

import com.practice.demospringbootproject.entity.Customer;
import java.util.Optional;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByIdAndIsDeletedFalse(Long id);
    List<Customer> findByIsDeletedFalse();
}
