package persistence;

import model.ShoppingItem;
import model.ShoppingList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ShoppingList sl = new ShoppingList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }
//
//    @Test
//    void testWriterValidFile() {
//        try {
//            ShoppingList sl = new ShoppingList();
//            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
//            writer.open();
//            fail("IOException was expected");
//        } catch (IOException e) {
//            // pass
//        }
//

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ShoppingList sl = new ShoppingList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyShoppingList.json");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyShoppingList.json");
            sl = reader.read();
            assertEquals(0, sl.itemListQuantity());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ShoppingList sl = new ShoppingList();
            sl.addShoppingItem(new ShoppingItem("Potato", 5,4,20,2068));
            sl.addShoppingItem(new ShoppingItem("Pajamas", 1,4,21,2068));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralShoppingList.json");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralShoppingList.json");
            sl = reader.read();
            List<ShoppingItem> shoppingItems = sl.getShoppingItems();
            assertEquals(2, shoppingItems.size());
            checkShoppingItem("Potato", 5, 4,20,2068, shoppingItems.get(0));
            checkShoppingItem("Pajamas", 1, 4, 21, 2068, shoppingItems.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}