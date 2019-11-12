package tests;

import model.TodoList;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestUser {
    private User user;
    private TodoList todoList;

    @BeforeEach
    public void runBefore() {
        user = new User();
        todoList = new TodoList();
    }

    @Test
    public void testAddTodo() {
        user.addTodo(todoList);
        assertEquals(todoList,user.todo);
        assertTrue(todoList.observers.contains(user));
        assertTrue(todoList.users.contains(user));
    }

    @Test
    public void testParseString() {
        assertEquals("temperature 282K\nhumidity 87%\nlow of 281K\nhigh of 283K",
                User.parseString("{\"rain\":{},\"visibility\":48279,\"timezone\":-28800,\"main\":{\"temp\":282.52,"
                        + "\"temp_min\":281.48,\"humidity\":87,\"pressure\":1023,\"temp_max\":283.71},"
                        + "\"clouds\":{\"all\":90}}"));
    }
}
