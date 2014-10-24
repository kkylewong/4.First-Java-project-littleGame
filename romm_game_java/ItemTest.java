

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ItemTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ItemTest
{
    /**
     * Default constructor for test class ItemTest
     */
    public ItemTest()
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
    public void getWeight()
    {
        Item item1 = new Item();
        item1.creatItem("item1", 10);
        item1.creatItem("item2", 20);
        assertEquals(20, item1.getWeight("item2"));
    }

    @Test
    public void removeItem()
    {
        Item item2 = new Item();
        item2.creatItem("item1", 20);
        item2.removeItem("item1");
        assertEquals("There is no item here!!", item2.itemName());
    }
}



