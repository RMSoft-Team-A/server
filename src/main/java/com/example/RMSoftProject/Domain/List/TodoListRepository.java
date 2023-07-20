package com.example.RMSoftProject.Domain.List;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoListRepository extends JpaRepository<TodoList,Long> {
    TodoList findByTitle(String title);

}
