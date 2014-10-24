

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class GameEngineTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GameEngineTest
{
    /**
     * Default constructor for test class GameEngineTest
     */
    public GameEngineTest()
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
    public void test()
    {
        Game game1 = new Game(0);
        Room room1 = new Room("balcony");
        GameEngine gameEngi1 = new GameEngine(room1);
    }
}

