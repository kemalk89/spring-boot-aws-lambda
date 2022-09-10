package com.examples.todo;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TodoRepository {
    private Long id = 0l;
    private List<Todo> todos = new ArrayList<>();

    public TodoRepository() {
        Todo todo = new Todo();
        todo.setId(++id);
        todo.setTitle("First todo");
        todo.setDescription("This is the description of your first Todo.");

        todos.add(todo);
    }

    public Page<Todo> getTodos(Pageable pageable) {
        Page<Todo> page = new PageImpl<>(todos, pageable, todos.size());
        return page;
    }

    public Todo getTodo(@NonNull Long todoId) {
        Todo found = todos.stream().filter(t -> t.getId() == todoId).findAny().orElse(null);
        return found;
    }

    public Todo addTodo(Todo todo) {
        todo.setId(++id);

        todos.add(todo);

        return todo;
    }
}
