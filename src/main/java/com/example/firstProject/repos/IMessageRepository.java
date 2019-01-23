package com.example.firstProject.repos;

import com.example.firstProject.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IMessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);
}
