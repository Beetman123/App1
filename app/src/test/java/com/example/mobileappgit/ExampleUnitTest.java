package com.example.mobileappgit;

import com.example.mobileappgit.data.Item.Item;

import org.junit.Test;
//import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    // Constructor
    @Test
    public void testItemConstructor() {
        Item item = new Item("Title", "Category", "Description",
                "Username", "Condition", "Price", "Trade",
                "TradeFor", "Date");
        assertNotNull(item);
    }
    @Test
    public void testItemConstructorAllNull() {
        Item item = new Item(null, "Category", "Description",
                "Username", "Condition", "Price", "Trade",
                "TradeFor", "Date");
        assertNotNull(item);
    }

    // Getters
    @Test
    public void testItemTitleGetter() {
        Item item = new Item("Title", "Category", "Description",
                "Username", "Condition", "Price", "Trade",
                "TradeFor", "Date");
        assertEquals(item.getTitle(),"Title");
    }
    @Test
    public void testItemCategoryGetter() {
        Item item = new Item("Title", "Category", "Description",
                "Username", "Condition", "Price", "Trade",
                "TradeFor", "Date");
        assertEquals(item.getCategory(),"Category");
    }
    @Test
    public void testItemDescriptionGetter() {
        Item item = new Item("Title", "Category", "Description",
                "Username", "Condition", "Price", "Trade",
                "TradeFor", "Date");
        assertEquals(item.getDescription(),"Description");
    }
    @Test
    public void testItemUsernameGetter() {
        Item item = new Item("Title", "Category", "Description",
                "Username", "Condition", "Price", "Trade",
                "TradeFor", "Date");
        assertEquals("Username", item.getUsername());
    }
    @Test
    public void testItemConditionGetter() {
        Item item = new Item("Title", "Category", "Description",
                "Username", "Condition", "Price", "Trade",
                "TradeFor", "Date");
        assertEquals("Condition", item.getCondition());
    }
    @Test
    public void testItemPriceGetter() {
        Item item = new Item("Title", "Category", "Description",
                "Username", "Condition", "Price", "Trade",
                "TradeFor", "Date");
        assertEquals("Price", item.getPrice());
    }
    @Test
    public void testItemTradeGetter() {
        Item item = new Item("Title", "Category", "Description",
                "Username", "Condition", "Price", "Trade",
                "TradeFor", "Date");
        assertEquals("Trade" ,item.getTrade());
    }
    @Test
    public void testItemTradeForGetter() {
        Item item = new Item("Title", "Category", "Description",
                "Username", "Condition", "Price", "Trade",
                "TradeFor", "Date");
        assertEquals("TradeFor" ,item.getTradeFor());
    }
    @Test
    public void testItemDateGetter() {
        Item item = new Item("Title", "Category", "Description",
                "Username", "Condition", "Price", "Trade",
                "TradeFor", "Date");
        assertEquals("Date", item.getDate());
    }



}