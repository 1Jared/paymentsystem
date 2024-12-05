package com.projectmgt.task;


import com.projectmgt.task.controller.TaskController;
import com.projectmgt.task.entity.Status;
import com.projectmgt.task.entity.Task;
import com.projectmgt.task.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private Task task;

    @BeforeEach
   public  void setUp() {
        MockitoAnnotations.openMocks(this);  // Initializes the mock objects

        task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");
        task.setDescription("Test Task Description");
        task.setStatus(Status.TO_DO);
    }

    @Test
    public void testCreateTask() {
        // Given
        when(taskService.createTask(any(Task.class))).thenReturn(task);

        // When
        ResponseEntity<Task> response = taskController.createTask(1L, task);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test Task", response.getBody().getTitle());
        verify(taskService, times(1)).createTask(any(Task.class));
    }

    @Test
    public void testDeleteTask() {
        // Given
        doNothing().when(taskService).deleteTask(1L);

        // When
        ResponseEntity<Void> response = taskController.deleteTask(1L);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(taskService, times(1)).deleteTask(1L);
    }
}
