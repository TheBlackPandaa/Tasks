package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class DbServiceTests {

    @InjectMocks
    private DbService dbService;
    @Mock
    private TaskRepository repository;

    @Test
    void testFindAll(){
        //Given
        Task task = new Task(1L, "title1", "content1");
        Mockito.when(repository.findAll()).thenReturn(List.of(task));
        //When
        List<Task> allTasks = dbService.getAllTasks();
        //Then
        assertEquals(List.of(task),allTasks);
    }

    @Test
    void shouldGetTask() throws TaskNotFoundException {
        //Given
        long id = 1L;
        Task task = new Task(id, "title1", "content1");
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(task));
        //When
        Task actualTask = dbService.getTask(id);
        //Then
        assertEquals(task, actualTask);
    }

    @Test
    void shouldThrowExceptionWhenTaskNotFound(){
        //Given
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());
        //When&Then
        assertThrows(TaskNotFoundException.class, () -> dbService.getTask(1L));
    }

    @Test
     void shouldSaveTask(){
        //Given
        Task task = new Task(1L, "title1", "content1");
        Mockito.when(repository.save(task)).thenReturn(task);
        //When
        Task savedTask = dbService.saveTask(task);
        //Then
        assertEquals(savedTask, task);
    }

    @Test
    void shouldDeleteTask(){
        //Given
        long id = 1L;
        //When
        dbService.deleteTask(id);
        //Then
        Mockito.verify(repository).deleteById(id);
    }
}
