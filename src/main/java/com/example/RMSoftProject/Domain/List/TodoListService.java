package com.example.RMSoftProject.Domain.List;

import com.example.RMSoftProject.Domain.Squid.Squid;
import com.example.RMSoftProject.Domain.Squid.SquidRepository;
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
    private final SquidRepository squidRepository;

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
        todoList.setCompleted(false);
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

    public void deleteTodoList(TodoListDto todoListDto) {
        User user = userRepository.findByEmail(todoListDto.getEmail());
        if (user == null) {
            throw new NoSuchElementException("User not found.");
        }

        TodoList todoList = todoListRepository.findByTitle(todoListDto.getTitle());

        if (!todoList.getUser().equals(user)) {
            throw new IllegalArgumentException("You can only delete your own TodoList.");
        }

        todoListRepository.delete(todoList);
    }

    public void completeTodoList(TodolistCompleteDto todoListDto) {
        User user = userRepository.findByEmail(todoListDto.getEmail());



        if (user == null) {
            throw new NoSuchElementException("User not found.");
        }


        TodoList todoList = todoListRepository.findByTitle(todoListDto.getTitle());

        if (!todoList.getUser().equals(user)) {
            throw new IllegalArgumentException("You can only complete your own TodoList.");
        }

        todoList.setCompleted(true);
        todoListRepository.save(todoList);
        Squid squid = user.getSquid();
        int currentExp = squid.getExp() + 1;
        squid.setExp(currentExp);

        int level = squid.getLevel();
        int requiredExp = 5 + (level - 1) * 2;
        if (currentExp >= requiredExp) {
            squid.setLevel(level + 1);
        }


        squidRepository.save(squid);



    }

}
