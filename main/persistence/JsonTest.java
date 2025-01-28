package persistence;

import model.ShoppingItem;
import model.ShoppingList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//source EDX
public class JsonTest {
    protected void checkShoppingItem(String name, int quantity, int month, int day, int year, ShoppingItem s) {
        assertEquals(name, s.getName());
        assertEquals(quantity, s.getQuantity());
        assertEquals(month, s.getMonth());
        assertEquals(day,s.getDay());
        assertEquals(year,s.getYear());
    }
}
