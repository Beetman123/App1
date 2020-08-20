package com.example.mobileappgit;

import com.example.mobileappgit.data.Item.Item;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class Item_JUnit_Test {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    // constructor
    @Test
    public void testItemConstructor() {
        Item item = new Item("title","category","description", "username",
                "condition", "price", "trade", "tradeFor", "mDate");
        assertNotNull(item);
    }
    @Test (expected = NullPointerException.class)
    public void testItemConstructorNullTitle() throws Exception {
        Item item = new Item(null,"category","description", "username",
                "condition", "price", "trade", "tradeFor", "mDate");
        assertEquals("title", item.getTitle());
    }
    @Test (expected = NullPointerException.class)
    public void testItemConstructorNullCategory() throws Exception {
        Item item = new Item("title",null,"description", "username",
                "condition", "price", "trade", "tradeFor", "mDate");
        assertEquals("category", item.getCategory());
    }
    @Test (expected = NullPointerException.class)
    public void testItemConstructorNullDescription() throws Exception {
        Item item = new Item("title","category",null, "username",
                "condition", "price", "trade", "tradeFor", "mDate");
        assertEquals("description", item.getDescription());
    }
    @Test (expected = NullPointerException.class)
    public void testItemConstructorNullUsername() throws Exception {
        Item item = new Item("Title","category","description", null,
                "condition", "price", "trade", "tradeFor", "mDate");
        assertEquals("username", item.getUsername());
    }
    @Test (expected = NullPointerException.class)
    public void testItemConstructorNullCondition() throws Exception {
        Item item = new Item("title","category","description", "username",
                null, "price", "trade", "tradeFor", "mDate");
        assertEquals("condition", item.getCondition());
    }
    @Test (expected = NullPointerException.class)
    public void testItemConstructorNullPrice() throws Exception {
        Item item = new Item("Title","category","description", "username",
                "condition", null, "trade", "tradeFor", "mDate");
        assertEquals("price", item.getPrice());
    }
    @Test (expected = NullPointerException.class)
    public void testItemConstructorNullTrade() throws Exception {
        Item item = new Item("title","category","description", "username",
                "condition", "price", null, "tradeFor", "mDate");
        assertEquals("trade", item.getTrade());
    }
    /*@Test (expected = NullPointerException.class)
    public void testItemConstructorNullTradeFor() throws Exception {
        Item item = new Item("Title","category","description", "username",
                "condition", "price", "trade", null, "mDate");
        assertEquals("tradeFor", item.getTradeFor());
    }*/
    @Test (expected = NullPointerException.class)
    public void testItemConstructorNullDate() throws Exception {
        Item item = new Item("Title","category","description", "username",
                "condition", "price", "trade", "tradeFor", null);
        assertEquals("mDate", item.getDate());
    }


    // getters
    @Test
    public void testItemTitleGetter() {
        Item item = new Item("title","category","description", "username",
                "condition", "price", "trade", "tradeFor", "mDate");
        assertEquals("title", item.getTitle());
    }
    @Test
    public void testItemCategoryGetter() {
        Item item = new Item("title","category","description", "username",
                "condition", "price", "trade", "tradeFor", "mDate");
        assertEquals("category", item.getCategory());
    }
    @Test
    public void testItemDescriptionGetter() {
        Item item = new Item("title","category","description", "username",
                "condition", "price", "trade", "tradeFor", "mDate");
        assertEquals("description", item.getDescription());
    }
    @Test
    public void testItemUsernameGetter() {
        Item item = new Item("title","category","description", "username",
                "condition", "price", "trade", "tradeFor", "mDate");
        assertEquals("username", item.getUsername());
    }
    @Test
    public void testItemConditionGetter() {
        Item item = new Item("title","category","description", "username",
                "condition", "price", "trade", "tradeFor", "mDate");
        assertEquals("condition", item.getCondition());
    }
    @Test
    public void testItemPriceGetter() {
        Item item = new Item("title","category","description", "username",
                "condition", "price", "trade", "tradeFor", "mDate");
        assertEquals("price", item.getPrice());
    }
    @Test
    public void testItemTradeGetter() {
        Item item = new Item("title","category","description", "username",
                "condition", "price", "trade", "tradeFor", "mDate");
        assertEquals("trade", item.getTrade());
    }
    @Test
    public void testItemTradeForGetter() {
        Item item = new Item("title","category","description", "username",
                "condition", "price", "trade123", "tradeFor", "mDate");
        assertEquals("mDate", item.getDate());
    }

}