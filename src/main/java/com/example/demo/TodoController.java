package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/todos")
public class TodoController {

	@GetMapping
	public List<Todo> getTodos() {
		log.info("Running getTodos...");
		
		var todo = new Todo();
		todo.setTitle("Testtitle");
		todo.setDescription("This is a test description.");
		
		return Arrays.asList(todo);
	}
	
	@GetMapping("/{todoId}")
	public Todo getTodo(@PathVariable String todoId) {
		log.info("Running getTodo where ID is " + todoId);
		
		var todo = new Todo();
		todo.setTitle("Testtitle " + todoId);
		todo.setDescription("This is a test description.");
		
		return todo;
	}
	
}
