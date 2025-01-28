package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ShoppingList implements Writable {
    private LinkedList<ShoppingItem> shoppingList;

    public ShoppingList() {
        this.shoppingList = new LinkedList<ShoppingItem>();
    }

    //EFFECTS: produces true if list is empty
    public boolean emptyList() {
        if (shoppingList.isEmpty()) {
            EventLog.getInstance().logEvent(new Event("Shopping List is empty"));
            return true;
        } else {
            return false;
        }
    }

    //EFFECTS: adds a shopping item to the shopping list
    public boolean addShoppingItem(ShoppingItem shoppingItem) {
        shoppingList.add(shoppingItem);
        EventLog.getInstance().logEvent(new Event("Added shopping item to shopping list"));
        return true;
    }

    //EFFECTS: removes a shopping item from the shopping list
    public boolean removeShoppingItem(String name, int month, int day, int year) {
        if (!shoppingList.isEmpty()) {
            for (int i = 0; i < shoppingList.size(); i++) {
                if ((name == shoppingList.get(i).getName())
                        && (month == shoppingList.get(i).getMonth())
                        && (day == shoppingList.get(i).getDay())
                        && (year == shoppingList.get(i).getYear())) {
                    shoppingList.remove(shoppingList.get(i));
                    EventLog.getInstance().logEvent(new Event("Removed shopping item from shopping list"));
                    return true;
                }
            }
        }
        return false;
    }

    //REQUIRES: item in the shopping list
    //EFFECTS: prints out shopping list of shopping items
    public String viewShoppingList() {
        String viewacc1;
        String viewacc2;
        String viewacc3;
        String viewacc4;
        String viewacc5;
        String newline = System.lineSeparator();
        String printed = "";
        for (int i = 0; i < shoppingList.size(); i++) {
            viewacc1 = "Name:" + shoppingList.get(i).getName();
            viewacc2 = "Quantity:" + shoppingList.get(i).getQuantity();
            viewacc3 = "Month:" + shoppingList.get(i).getMonth();
            viewacc4 = "Day:" + shoppingList.get(i).getDay();
            viewacc5 = "Year:" + shoppingList.get(i).getYear();
            printed = printed + " " + viewacc1 + " " + viewacc2 + " "
                    + viewacc3 + " " + viewacc4 + " " + viewacc5 + "\n";
        }
        EventLog.getInstance().logEvent(new Event("Viewing shopping list"));
        return printed;
    }

    //EFFECTS: returns number of items in the list
    public int itemListQuantity() {
        EventLog.getInstance().logEvent(new Event("Showing different # of items"));
        return shoppingList.size();
    }

    // EFFECTS: returns an unmodifiable list of shopping items in this shopping list
    public List<ShoppingItem> getShoppingItems() {
        return Collections.unmodifiableList(shoppingList);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("items", shoppingListToJson());
        return json;
    }

    // EFFECTS: returns items in this shopping list as a JSON array
    private JSONArray shoppingListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (ShoppingItem s : shoppingList) {
            jsonArray.put(s.toJson());
        }
        return jsonArray;
    }
}

