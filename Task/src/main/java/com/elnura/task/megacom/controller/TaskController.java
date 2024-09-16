package com.elnura.task.megacom.controller;

import com.elnura.task.megacom.db.entity.Task;
import com.elnura.task.megacom.db.service.MailSenderService;
import com.elnura.task.megacom.db.service.TaskService;
import com.elnura.task.megacom.exception.ResourceNotFoundException;
import com.elnura.task.megacom.exception.CustomErrorResponse;
import com.elnura.task.megacom.exception.InvalidDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private static final Logger logInfo =  LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;

    private final MailSenderService mailSenderService;

    public TaskController(TaskService taskService, MailSenderService mailSenderService) {
        this.taskService = taskService;
        this.mailSenderService = mailSenderService;
    }


    @GetMapping
    public ResponseEntity<?> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        try {
            Task task = taskService.getTaskById(id);
            return ResponseEntity.ok(task);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        try {
            Task createdTask = taskService.createTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        } catch (InvalidDataException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody Task task) {
        try {
            Task updatedTask = taskService.updateTask(id, task);
            return ResponseEntity.ok(updatedTask);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
        } catch (InvalidDataException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
        }
    }

    @GetMapping("/send-task")
    public String sendTask(@RequestParam String email) {
        String subject = "Новая задача";
        String body = "Описание задачи...";
        mailSenderService.sendSimpleEmail(email, subject, body);
//        mailSenderService.sendSimpleEmail(
//                "tadzhibaevaelnura@gmail.com",
//                "Task",
//                "Task" + taskService.getAllTasks());
        return "Задача успешно отправлена на email: " + email;
    }
}
