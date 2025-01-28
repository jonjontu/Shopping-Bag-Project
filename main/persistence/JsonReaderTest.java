package persistence;

import model.ShoppingItem;
import model.ShoppingList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ShoppingList sl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyShoppingList.json");
        try {
            ShoppingList sl = reader.read();
            assertEquals(0, sl.itemListQuantity());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralShoppingList.json");
        try {
            ShoppingList sl = reader.read();
            List<ShoppingItem> shoppingItems = sl.getShoppingItems();
            assertEquals(2, shoppingItems.size());
            checkShoppingItem("Boat", 1, 3,2,2020, shoppingItems.get(0));
            checkShoppingItem("Sawmill", 2, 3, 3, 2021, shoppingItems.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}