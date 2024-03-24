package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;
    @Test
    void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L,"title1", "content1");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(1L, task.getId());
        assertEquals("title1", task.getTitle());
        assertEquals("content1", task.getContent());
    }

    @Test
    void testMapToTaskDto() {
        //Given
        Task task = new Task(1L,"title1", "content1");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(1L, taskDto.getId());
        assertEquals("title1", taskDto.getTitle());
        assertEquals("content1", taskDto.getContent());
    }

    @Test
    void testMapToTaskDtoList() {
        //Given
        Task task = new Task(1L, "title1", "content1");
        Task task1 = new Task(1L, "title1", "content1");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        taskList.add(task1);
        //when
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(2,taskDtoList.size());
    }
}