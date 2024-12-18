package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import com.example.entity.Message;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{

    // Method to find a message by its unique message ID.
    Optional<Message> findByMessageId(Integer id);

    // Method to retrieve all messages posted by a specific user.
    List<Message> findAllByPostedBy(Integer id);
    
    // Method to delete a message by its unique message ID.
    // The @Transactional annotation ensures that the deletion operation is performed within a transaction
    @Transactional
    void deleteByMessageId( Integer id);

    // Method to check whether a message exists in the database by its unique message ID.
    boolean existsByMessageId (Integer id);
    
}
