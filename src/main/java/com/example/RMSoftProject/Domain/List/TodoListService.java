package com.example.RMSoftProject.Domain.List;

import com.example.RMSoftProject.Domain.User.User;
import com.example.RMSoftProject.Domain.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TodoListService {
    private final TodoListRepository todoListRepository;
    private final UserRepository userRepository;

    public void addTodoList(String userEmail, TodoListDto todoListDto) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new NoSuchElementException("User not found.");
        }

        TodoList todoList = new TodoList();
        todoList.setTitle(todoListDto.getTitle());
        todoList.setUser(user);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = now.format(formatter);

        todoList.setDate(formattedDate);
        todoListRepository.save(todoList);
    }
    public void updateTodoListTitle(TodoListDto todoListDto) {
        User user = userRepository.findByEmail(todoListDto.getEmail());
        if (user == null) {
            throw new NoSuchElementException("User not found.");
        }

        TodoList todoList = todoListRepository.findByTitle(todoListDto.getTitle());


        if (!todoList.getUser().equals(user)) {
            throw new IllegalArgumentException("You can only update your own TodoList.");
        }

        todoList.setTitle(todoListDto.getTitle());
        todoListRepository.save(todoList);
    }









}
