package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShoppingListTests {
    ShoppingItem s1;
    ShoppingItem s2;
    ShoppingItem s3;
    ShoppingItem s4;
    ShoppingItem s5;
    ShoppingItem s6;
    ShoppingItem s7;
    ShoppingItem s8;

    ShoppingList sl1;
    ShoppingList sl2;
    ShoppingList sl3;
    ShoppingList sl4;
    ShoppingList sl5;

    @BeforeEach
    void runBefore() {
        s1 = new ShoppingItem("Apple" , 3, 10, 21, 21);
        s2 = new ShoppingItem("Chocolate Bar" , 1, 11, 31, 21);
        s3 = new ShoppingItem("Jeans" , 1, 11, 30, 21);
        s4 = new ShoppingItem("McChickens" , 3, 13, 40, 21);
        s5 = new ShoppingItem("Flags" , 3, 14, 1, 21);
        s6 = new ShoppingItem("Keyboard" , 3, 1, 21, 21);
        s7 = new ShoppingItem("T-Shirt" , 3, 12, 21, 21);
        s8 = new ShoppingItem("Heart" , 1, 2, 29, 21);

        sl1 = new ShoppingList();
        sl2 = new ShoppingList();
        sl2.addShoppingItem(s1);
        sl3 = new ShoppingList();
        sl3.addShoppingItem(s1);
        sl3.addShoppingItem(s3);
        sl4 = new ShoppingList();
        sl4.addShoppingItem(s1);
        sl4.addShoppingItem(s3);
        sl4.addShoppingItem(s6);
        sl5 = new ShoppingList();
        sl5.addShoppingItem(s1);
        sl5.addShoppingItem(s3);
        sl5.addShoppingItem(s6);
        sl5.addShoppingItem(s7);
    }

    @Test
    void addShoppingItem() {
        assertTrue(sl1.addShoppingItem(s1));
        assertTrue(sl2.addShoppingItem(s3));
        assertTrue(sl3.addShoppingItem(s6));
        assertTrue(sl4.addShoppingItem(s7));
    }

    @Test
    void removeShoppingItem() {
        assertFalse(sl1.removeShoppingItem("Apple",10,21,21));
        assertFalse(sl1.removeShoppingItem("Pineapple",11,22,22));
        assertTrue(sl2.removeShoppingItem("Apple",10,21,21));
        assertFalse(sl2.removeShoppingItem("Pineapple",11,22,22));
        assertTrue(sl3.removeShoppingItem("Apple",10,21,21));
        assertTrue(sl3.removeShoppingItem("Jeans",11,30, 21));
        assertFalse(sl3.removeShoppingItem("Pineapple",11,22,22));
    }

    @Test
    void viewShoppingList() {
        String newline = System.lineSeparator();
        assertEquals("", sl1.viewShoppingList());
        assertEquals(" Name:Apple Quantity:3 Month:10 Day:21 Year:21" + newline, sl2.viewShoppingList());
        assertEquals(" Name:Apple Quantity:3 Month:10 Day:21 Year:21" + newline +
                " Name:Jeans Quantity:1 Month:11 Day:30 Year:21" + newline, sl3.viewShoppingList());
        assertEquals(" Name:Apple Quantity:3 Month:10 Day:21 Year:21" + newline +
                " Name:Jeans Quantity:1 Month:11 Day:30 Year:21" + newline +
                " Name:Keyboard Quantity:3 Month:1 Day:21 Year:21" + newline, sl4.viewShoppingList());
        assertEquals(" Name:Apple Quantity:3 Month:10 Day:21 Year:21" + newline +
                " Name:Jeans Quantity:1 Month:11 Day:30 Year:21" + newline +
                " Name:Keyboard Quantity:3 Month:1 Day:21 Year:21" + newline +
                " Name:T-Shirt Quantity:3 Month:12 Day:21 Year:21" + newline, sl5.viewShoppingList());
    }

    @Test
    void emptyList() {
        assertTrue(sl1.emptyList());
        assertFalse(sl2.emptyList());
        assertFalse(sl3.emptyList());
        assertFalse(sl4.emptyList());
        assertFalse(sl5.emptyList());
    }

    @Test
    void itemListQuantity() {
        assertEquals(0, sl1.itemListQuantity());
        assertEquals(1, sl2.itemListQuantity());
        assertEquals(2, sl3.itemListQuantity());
        assertEquals(3, sl4.itemListQuantity());
        assertEquals(4, sl5.itemListQuantity());
    }
}