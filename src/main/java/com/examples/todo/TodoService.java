package com.examples.todo;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TodoService {

    private Long id = 0l;
    private List<Todo> todos = new ArrayList<>();

    public TodoService() {
        Todo todo = new Todo();
        todo.setId(++id);
        todo.setTitle("First todo");
        todo.setDescription("This is the description of your first Todo.");

        todos.add(todo);
    }

    public List<Todo> getTodos() {
        return todos;
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
