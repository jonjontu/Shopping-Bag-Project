package model;

import org.json.JSONObject;
import persistence.Writable;

public class ShoppingItem implements Writable {
    private String name;             // item name
    private int quantity;            // item quantity
    private int month;               // calendar month
    private int day;                 // calendar day
    private int year;                // calendar year

    //REQUIRES: all int parameters > 0
    //MODIFIES: this
    //EFFECTS: template for ShoppingItem
    public ShoppingItem(String name, int quantity, int month, int day, int year) {
        this.name = name;
        this.quantity = quantity;
        this.month = month;
        this.day = day;
        this.year = year;
    }

    //EFFECTS: returns true if there is a valid month
    public boolean validMonth() {
        return ((1 <= month) && (month <= 12));
    }

    //EFFECTS: returns true if there is a valid day in a particular month
    public boolean validDay() {
        if (month == 2) {
            return ((1 <= day) && (day <= 28));
        } else if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
            return ((1 <= day) && (day <= 30));
        } else {
            return ((1 <= day) && (day <= 31));
        }
    }

    //EFFECTS: returns true if a shopping item has both valid day and month
    public boolean validDate() {
        return ((validDay()) && (validMonth()));
    }

    //EFFECTS: returns name of shopping item
    public String getName() {
        return name;
    }

    //EFFECTS: returns quantity of shopping item
    public int getQuantity() {
        return quantity;
    }

    //EFFECTS: returns day of shopping item
    public int getDay() {
        return day;
    }

    //EFFECTS: returns month of shopping item
    public int getMonth() {
        return month;
    }

    //EFFECTS: returns year of shopping item
    public int getYear() {
        return year;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("quantity", quantity);
        json.put("month", month);
        json.put("day", day);
        json.put("year", year);
        return json;
    }
}

