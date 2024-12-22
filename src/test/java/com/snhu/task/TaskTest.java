package com.snhu.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void testValidTask() {
        Task task = new Task("Task1", "TestTask", "This is a test task");
        assertEquals("Task1", task.getTaskId());
        assertEquals("TestTask", task.getName());
        assertEquals("This is a test task", task.getDescription());
    }

    @Test
    void testInvalidTaskId() {
        // When taskId is null
        assertThrows(IllegalArgumentException.class, () -> {
            new Task(null, "TestTask", "This is a test task");
        });

        // When taskId is greater than 10 characters
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("TaskID12345", "TestTask", "This is a test task");
        });
    }

    @Test
    void testInvalidName() {
        // When name is null
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("Task1", null, "This is a test task");
        });

        // When name is greater than 20 characters
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("Task1", "This name is way too long for a task", "This is a test task");
        });
    }

    @Test
    void testInvalidDescription() {
        // When description is null
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("Task1", "TestTask", null);
        });

        // When description is greater than 50 characters
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("Task1", "TestTask", "This description is way too long for a task and should cause an error");
        });
    }

    @Test
    void testSetName() {
        Task task = new Task("Task1", "TestTask", "This is a test task");
        task.setName("NewName");
        assertEquals("NewName", task.getName());
    }

    @Test
    void testSetDescription() {
        Task task = new Task("Task1", "TestTask", "This is a test task");
        task.setDescription("Updated description");
        assertEquals("Updated description", task.getDescription());
    }

}
