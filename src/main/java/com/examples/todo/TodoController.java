package com.examples.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("/api/todos")
public class TodoController {

	private TodoService todoService;

	@Autowired
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping
	public List<Todo> getTodos() {
		log.info("Running getTodos...");
		return todoService.getTodos();
	}
	
	@GetMapping("/{todoId}")
	public Todo getTodo(@PathVariable Long todoId) {
		log.info("Running getTodo where ID is " + todoId);
		Todo todo = todoService.getTodo(todoId);
		if (todo == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found");
		}

		return todoService.getTodo(todoId);
	}

	@PostMapping
	public Todo addTodo(@RequestBody  Todo todo) {
		return todoService.addTodo(todo);
	}
}
