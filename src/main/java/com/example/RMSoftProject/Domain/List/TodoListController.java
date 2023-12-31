package com.example.RMSoftProject.Domain.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoListController {

    private final TodoListService todoListService;

    @PostMapping("/{email}/todos")
    public ResponseEntity<Object> addTodoList(@PathVariable String email, @RequestBody TodoListDto todoListDto) {
        try {
            todoListService.addTodoList(email, todoListDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("TodoList 추가에 성공했습니다.");
        } catch (NoSuchElementException e) {
            Map<String, String> response = new HashMap<>();
            response.put("Error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("/change")
    public ResponseEntity<Object> updateTodoListTitle(
            @RequestBody TodoListDto todoListDto
    ) {
        try {
            todoListService.updateTodoListTitle( todoListDto);
            return ResponseEntity.ok("TodoList 제목 수정에 성공했습니다.");
        } catch (NoSuchElementException e) {
            Map<String, String> response = new HashMap<>();
            response.put("Error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteTodoList(
            @RequestBody TodoListDto todoListDto
    ) {
        try {
            todoListService.deleteTodoList(todoListDto);
            return ResponseEntity.ok("TodoList 삭제에 성공했습니다.");
        } catch (NoSuchElementException e) {
            Map<String, String> response = new HashMap<>();
            response.put("Error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("/complete")
    public ResponseEntity<Object> completeTodoList(
            @RequestBody TodolistCompleteDto todoListDto
    ) {
        try {
            todoListService.completeTodoList(todoListDto);
            return ResponseEntity.ok("TodoList 완료 처리에 성공했습니다.");
        } catch (NoSuchElementException e) {
            Map<String, String> response = new HashMap<>();
            response.put("Error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }



}
