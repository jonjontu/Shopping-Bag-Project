package persistence;

import model.ShoppingItem;
import model.ShoppingList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads shoppingList from JSON data stored in file
// source EDX
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads shoppingList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ShoppingList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseShoppingList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses shoppingList from JSON object and returns it
    private ShoppingList parseShoppingList(JSONObject jsonObject) {
        ShoppingList sl = new ShoppingList();
        addShoppingItems(sl, jsonObject);
        return sl;
    }

    // MODIFIES: sl
    // EFFECTS: parses shopping items from JSON object and adds them to shopping list
    private void addShoppingItems(ShoppingList sl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addShoppingItem(sl, nextItem);
        }
    }

    // MODIFIES: sl
    // EFFECTS: parses shopping items from JSON object and adds it to shopping list
    private void addShoppingItem(ShoppingList sl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int quantity = (int) jsonObject.getNumber("quantity");
        int month = (int) jsonObject.getNumber("month");
        int day = (int) jsonObject.getNumber("day");
        int year = (int) jsonObject.getNumber("year");
        ShoppingItem shoppingItem = new ShoppingItem(name, quantity, month, day, year);
        sl.addShoppingItem(shoppingItem);
    }
}
