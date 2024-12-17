package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
    Optional<Message> findByMessageId(Integer id);

   // @Query("FROM Message WHERE accountId =:id")
    List<Message> findAllByPostedBy(Integer id);
    
}
