package com.nighthawk.spring_portfolio.mvc.message;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageJpaRepository extends JpaRepository<Message, Long> {
    
}
