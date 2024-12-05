package com.projectmgt.task;

import com.projectmgt.task.entity.Project;
import com.projectmgt.task.entity.Status;
import com.projectmgt.task.entity.Task;
import com.projectmgt.task.repository.ProjectRepository;
import com.projectmgt.task.repository.TaskRepository;
import com.projectmgt.task.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest(classes = TaskApplication.class)
@ExtendWith(SpringExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private TaskService taskService;

    private Project project;
    private Task task;

    @BeforeEach
   public  void setUp() {
        MockitoAnnotations.openMocks(this);  // Initializes the mock objects
        project = new Project();
        project.setId(1L);
        project.setName("Test Project");
        project.setDescription("Test Project Description");

        task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");
        task.setDescription("Test Task Description");
        task.setStatus(Status.TO_DO);
        task.setDueDate(new Date());
        task.setProject(project);
    }

    @Test
    void testCreateTask() {
        // Given
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        // When
        Task savedTask = taskService.createTask(task);

        // Then
        assertNotNull(savedTask);
        assertEquals("Test Task", savedTask.getTitle());
        assertEquals(Status.TO_DO, savedTask.getStatus());
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    public void testGetTaskById() {
        // Given
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        // When
        Optional<Task> result = taskService.getTaskById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals("Test Task", result.get().getTitle());
    }

    @Test
    public void testDeleteTask() {
        // Given
        doNothing().when(taskRepository).deleteById(1L);

        // When
        taskService.deleteTask(1L);

        // Then
        verify(taskRepository, times(1)).deleteById(1L);
    }
}


