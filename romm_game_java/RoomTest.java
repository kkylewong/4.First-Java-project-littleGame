

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RoomTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RoomTest
{
    /**
     * Default constructor for test class RoomTest
     */
    public RoomTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void checkDes()
    {
        Room room2 = new Room("a room");
        assertEquals("a room", room2.getDescription());
    }

    @Test
    public void checkItem()
    {
        Room room1 = new Room("room");
        room1.creatItem("item1", room1, 12);
        assertEquals("[Item 1: item1 12\n]\n", room1.getRoomItem());
    }
}


