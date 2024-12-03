package com.projectmgt.task.controller;

import com.projectmgt.task.entity.Project;
        import com.projectmgt.task.entity.Status;
        import com.projectmgt.task.entity.Task;
        import com.projectmgt.task.repository.ProjectRepository;
        import com.projectmgt.task.service.TaskService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.Pageable;
        import org.springframework.format.annotation.DateTimeFormat;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.Date;
        import java.util.List;
        import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectRepository projectRepository;

    @PostMapping("/{projectId}/tasks")
    public ResponseEntity<Task> createTask(@PathVariable Long projectId, @RequestBody Task task) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        task.setProject(project);
        Task savedTask = taskService.createTask(task);
        return ResponseEntity.ok(savedTask);
    }

    @GetMapping("/{projectId}/tasks")
    public ResponseEntity<Page<Task>> getTasksByProjectId(@PathVariable Long projectId, Pageable pageable) {
        Page<Task>  tasks = taskService.getTasksByProjectId(projectId,pageable);
        return ResponseEntity.ok(tasks);
    }
//    public Page<Task> getTasksByProjectId(@PathVariable Long projectId, Pageable pageable) {
//        return taskRepository.findByProjectId(projectId, pageable);
//    }

    @GetMapping("/{projectId}/tasks")
    public ResponseEntity<Page<Task>> getTasksByProjectIdAndStatusAndDueDate(
            @PathVariable Long projectId,
            @RequestParam Status status,
            @RequestParam Date dueDate, Pageable pageable) {
        Page<Task> tasks = taskService.getTasksByProjectIdAndStatusAndDueDate(projectId, status, dueDate, pageable);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        Optional<Task> existingTask = taskService.getTaskById(taskId);
        if (existingTask.isPresent()) {
            Task updatedTask = taskService.createTask(task);
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{projectId}/tasks")
    public ResponseEntity<Page<Task>> getTasks(
            @PathVariable Long projectId,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dueDate,
            Pageable pageable
    ) {
        Page<Task> tasks = taskService.getTasks(projectId, status, dueDate, pageable);
        return ResponseEntity.ok(tasks);
    }
}