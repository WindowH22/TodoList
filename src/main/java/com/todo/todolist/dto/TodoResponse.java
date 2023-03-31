package com.todo.todolist.dto;

import com.todo.todolist.domain.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponse {
    private Long id;
    private String title;
    private Long order;
    private Boolean completed;
    private String url;

    public TodoResponse(TodoEntity todoEntity) {
        this.id = todoEntity.getId();
        this.title = todoEntity.getTitle();
        this.order = todoEntity.getOrder();
        this.completed = todoEntity.getCompleted();

        //todo: 추후에 config를 사용해서 유지보수할때 url설정부분을 config로 관리해야함.
        this.url = "http://localhost:8080/" + this.id;

    }

}
