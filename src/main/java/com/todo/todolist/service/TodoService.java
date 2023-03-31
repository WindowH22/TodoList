package com.todo.todolist.service;

import com.todo.todolist.domain.TodoEntity;
import com.todo.todolist.dto.TodoRequest;
import com.todo.todolist.repository.TodoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class TodoService {

    private final TodoRepository todoRepository;

    // 리스트 목록에 아이템을 추가
    public TodoEntity add(TodoRequest request) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setTitle(request.getTitle());
        todoEntity.setCompleted(request.getCompleted());
        todoEntity.setOrder(request.getOrder());
        return todoRepository.save(todoEntity);
    }

    // 리스트 목록 중 특정 아이템을 조회
    public TodoEntity searchById(Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // 리스트 전체 목록을 조히
    public List<TodoEntity> searchAll() {
        return todoRepository.findAll();
    }

    // 특정 아이템 수정
    public TodoEntity updateById(Long id, TodoRequest request) {
        TodoEntity todoEntity = this.searchById(id);
        if (request.getTitle() != null) {
            todoEntity.setTitle(request.getTitle());
        }
        if (request.getOrder() != null) {
            todoEntity.setOrder((request.getOrder()));
        }
        if (request.getCompleted() != null) {
            todoEntity.setCompleted((request.getCompleted()));
        }

        return todoRepository.save(todoEntity);
    }

    // 특정 아이템 삭제
    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

    // 리스트 전체 목록 삭제
    public void deleteAll() {
        todoRepository.deleteAll();
    }


}
