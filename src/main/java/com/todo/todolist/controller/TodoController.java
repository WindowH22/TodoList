package com.todo.todolist.controller;

import com.todo.todolist.domain.TodoEntity;
import com.todo.todolist.dto.TodoRequest;
import com.todo.todolist.dto.TodoResponse;
import com.todo.todolist.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/")
public class TodoController {

    private final TodoService service;

    @PostMapping
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request) {
        System.out.println("CREATE");

        if (ObjectUtils.isEmpty(request.getTitle())) {
            return ResponseEntity.badRequest().build();
        }
        if (ObjectUtils.isEmpty(request.getOrder())) {
            request.setOrder(0L);
        }
        if (ObjectUtils.isEmpty(request.getCompleted())) {
            request.setCompleted(false);
        }
        TodoEntity result = service.add(request);
        TodoResponse response = new TodoResponse(result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id) {
        System.out.println("READ ONE");
        TodoEntity result = service.searchById(id);
        TodoResponse response = new TodoResponse(result);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> readAll() {

        System.out.println("READ ALL");
        List<TodoEntity> list = service.searchAll();
        List<TodoResponse> response = new ArrayList<>();
        for (TodoEntity todoEntity : list) {
            TodoResponse response1 = new TodoResponse(todoEntity);
            response.add(response1);
        }

        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest request) {
        System.out.println("UPDATE");
        TodoEntity result = service.updateById(id, request);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TodoResponse> deleteOne(@PathVariable Long id) {
        System.out.println("DELETEONE");
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public ResponseEntity<TodoResponse> deleteAll() {
        System.out.println("DELETE ALL");
        service.deleteAll();
        return ResponseEntity.ok().build();
    }

}
