package com.example.demo;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class TodoService {

    public List<Todo> getTodos() {
        var todo = new Todo();
        todo.setTitle("Testtitle");
        todo.setDescription("This is a test description.");

        return Arrays.asList(todo);
    }

    public Todo getTodo(@NonNull String todoId) {
        var todo = new Todo();
        todo.setTitle("Testtitle " + todoId);
        todo.setDescription("This is a test description.");

        return todo;
    }
}
