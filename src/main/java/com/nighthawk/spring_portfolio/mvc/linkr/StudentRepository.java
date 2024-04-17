package com.nighthawk.spring_portfolio.mvc.linkr;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllById(Long id);

    @Query(value = "SELECT coalesce(max(id), 0) FROM Student")
     Long getMaxId();

    Student findByEmail(String email);

    List<Student> findAllByEmail(String email);
}