package com.projectmgt.task.service;

import com.projectmgt.task.entity.Status;
import com.projectmgt.task.entity.Task;
import com.projectmgt.task.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Page<Task> getTasksByProjectId(Long projectId, Pageable pageable) {
        return taskRepository.findByProjectId(projectId, pageable);
    }
    public Page<Task> getTasksByProjectIdAndStatusAndDueDate(Long projectId, Status status, Date dueDate,Pageable pageable) {
        return taskRepository.findByProjectIdAndStatusAndDueDate(projectId, status, dueDate, pageable);
    }

    public Page<Task> getTasks(Long projectId, Status status, Date dueDate, Pageable pageable) {
        if (status != null && dueDate != null) {
            return taskRepository.findByProjectIdAndStatusAndDueDate(projectId, status, dueDate, pageable);
        } else if (status != null) {
            return taskRepository.findByProjectIdAndStatus(projectId, status, pageable);
        } else if (dueDate != null) {
            return taskRepository.findByProjectIdAndDueDate(projectId, dueDate, pageable);
        } else {
            return taskRepository.findByProjectId(projectId, pageable);
        }
    }

    public Optional<Task> getTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
