

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CommandWordsTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CommandWordsTest
{
    /**
     * Default constructor for test class CommandWordsTest
     */
    public CommandWordsTest()
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
    public void isCommand()
    {
        CommandWords commandW1 = new CommandWords();
        assertEquals(true, commandW1.isCommand("look"));
        assertEquals(true, commandW1.isCommand("go"));
        assertEquals(true, commandW1.isCommand("help"));
        assertEquals(true, commandW1.isCommand("look"));
        assertEquals(true, commandW1.isCommand("pick"));
    }
}


