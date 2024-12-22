import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

class TaskServiceTest {

    private TaskService taskService;
    private Task testTask;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
        testTask = new Task("Task1", "TestTask", "This is a test task");
    }

    @Test
    void testAddTask() {
        taskService.addTask(testTask);
        assertEquals(testTask, taskService.getTask("Task1"));
    }

    @Test
    void testAddDuplicateTask() {
        taskService.addTask(testTask);
        Task duplicateTask = new Task("Task1", "DuplicateTask", "This is a duplicate task");
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.addTask(duplicateTask);
        });
    }

    @Test
    void testDeleteTask() {
        taskService.addTask(testTask);
        taskService.deleteTask("Task1");
        assertNull(taskService.getTask("Task1"));
    }

    @Test
    void testDeleteNonexistentTask() {
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.deleteTask("nonexistent");
        });
    }

    @Test
    void testUpdateTask() {
        taskService.addTask(testTask);
        taskService.updateTask("Task1", "UpdatedTask", "This is an updated task");

        Task updatedTask = taskService.getTask("Task1");
        assertEquals("UpdatedTask", updatedTask.getName());
        assertEquals("This is an updated task", updatedTask.getDescription());
    }

    @Test
    void testUpdateNonexistentTask() {
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.updateTask("nonexistent", "UpdatedTask", "This is an updated task");
        });
    }

    @Test
    void testPartialUpdate() {
        taskService.addTask(testTask);
        taskService.updateTask("Task1", "UpdatedTask", null);

        Task updatedTask = taskService.getTask("Task1");
        assertEquals("UpdatedTask", updatedTask.getName());
        assertEquals("This is a test task", updatedTask.getDescription());
    }

}
