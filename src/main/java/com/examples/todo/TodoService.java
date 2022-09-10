package com.examples.todo;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TodoService {

    private TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Page<Todo> getTodos(Pageable pageable) {
        return todoRepository.getTodos(pageable);
    }

    public Todo getTodo(@NonNull Long todoId) {
        return todoRepository.getTodo(todoId);
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.addTodo(todo);
    }
}
