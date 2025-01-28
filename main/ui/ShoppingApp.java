package ui;

import model.Event;
import model.EventLog;
import model.ShoppingItem;
import model.ShoppingList;
import persistence.JsonWriter;
import persistence.JsonReader;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingApp implements ActionListener {
    private ShoppingList shoppingList;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/shoppingList.json";

    private JFrame frame;
    private JPanel panel;
    private JLabel label1;
    private JLabel output;
    private JLabel imageLabel;
    private ImageIcon icon;
    private JButton viewButton;
    private JButton editButton;
    private JButton createButton;
    private JButton loadButton;
    private JButton saveButton;
    private JButton quitButton;
    private JButton removeButton;
    private JButton backButton;
    private JButton quantity;

    public ShoppingApp() throws FileNotFoundException {
        this.shoppingList = new ShoppingList();

        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);

        this.frame = new JFrame();
        this.panel = new JPanel();
        this.label1 = new JLabel();
        this.output = new JLabel();
        this.imageLabel = new JLabel();
        this.icon = new ImageIcon();
        this.viewButton = new JButton("View");
        this.editButton = new JButton("Edit");
        this.createButton = new JButton("Create");
        this.loadButton = new JButton("Load");
        this.saveButton = new JButton("Save");
        this.quitButton = new JButton("Quit");
        this.removeButton = new JButton("Remove");
        this.backButton = new JButton("Return to options");
        this.quantity = new JButton("Get # of different items in list");

        setupGui();
        shoppingChoices();
    }

    //EFFECTS: setups gui
    private void setupGui() {
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("My Shopping List");
        frame.pack();
        frame.setSize(750,1000);
        frame.setVisible(true);

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new GridLayout(0, 1));

        icon = new ImageIcon(this.getClass().getResource("/coolimage.jpeg"));
        imageLabel = new JLabel(icon);
    }

    //EFFECTS: takes in user inputs and produces choices based on their inputs
    public void shoppingChoices() {
        panelReset();
        panel.add(label1);
        label1.setText("Welcome! " + "Please select an option below");

        panel.add(imageLabel);
        panel.add(viewButton);
        panel.add(editButton);
        panel.add(createButton);
        panel.add(loadButton);
        panel.add(saveButton);
        panel.add(quitButton);

        viewButton.addActionListener(this);
        editButton.addActionListener(this);
        createButton.addActionListener(this);
        loadButton.addActionListener(this);
        saveButton.addActionListener(this);
        quitButton.addActionListener(this);
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {
        panelReset();
        if (e.getSource() == viewButton) {
            shoppingView();
        } else if (e.getSource() == editButton) {
            shoppingEdit();
        } else if (e.getSource() == loadButton) {
            loadShoppingList();
        } else if (e.getSource() == saveButton) {
            saveShoppingList();
        } else if (e.getSource() == quitButton) {
            printLog(EventLog.getInstance());
            System.exit(0);
        } else if (e.getSource() == createButton) {
            shoppingCreate();
        } else if (e.getSource() == removeButton) {
            removeShoppingItem();
        } else if (e.getSource() == backButton) {
            shoppingChoices();
        } else if (e.getSource() == quantity) {
            quantityView();
        } else {
            printLog(EventLog.getInstance());
            System.exit(0);

        }
    }

    //EFFECTS: views current shopping list, if there are none, calls a create method
    private void shoppingView() {
        if (shoppingList.emptyList()) {
            shoppingListEmpty();
        } else {
            panelReset();
            label1.setText("Viewing Shopping List");
            output.setText(shoppingList.viewShoppingList());
            panel.add(output);
            panel.add(quantity);
            panel.add(backButton);

            quantity.addActionListener(this);
            backButton.addActionListener(this);
        }
    }

    //EFFECTS: prepares quantity view panel
    private void quantityView() {
        panelReset();
        label1.setText("You have " + shoppingList.itemListQuantity() + " different items in your list");

        panel.add(backButton);
        backButton.addActionListener(this);
    }

    //EFFECTS: prepares editing panel
    private void shoppingEdit() {
        if (shoppingList.emptyList()) {
            shoppingListEmpty();
        } else {
            label1.setText("Do you want to 'add' or 'remove' a shopping item?");

            panelReset();
            panel.add(createButton);
            panel.add(removeButton);

            createButton.addActionListener(this);
            removeButton.addActionListener(this);
        }
    }

    //EFFECTS: prepares panel if shopping list is empty
    private void shoppingListEmpty() {
        label1.setText("Your shopping list is empty. Do you want to 'add' an item or 'quit'?");

        panel.add(createButton);
        panel.add(quitButton);

        createButton.addActionListener(this);
        quitButton.addActionListener(this);
    }

    //EFFECTS: creates a new shopping list if input is 'create', breaks if input is 'quit'
    private void shoppingCreate() {
        label1.setText("Creating a new shopping item");
        panelReset();
        inputItem();
//        validDate();
        addedSuccessfully();
    }

    //EFFECTS: creates multiple Joptionpanes for user to input info.
    private void inputItem() {
        String name = JOptionPane.showInputDialog("Please type in the item's name");
        label1.setText("You named the item " + name);

        int quantity = Integer.parseInt(JOptionPane.showInputDialog("Please type in the item's quantity "
                + "(a number, eg. 1, 2, 3)"));
        label1.setText("You want to purchase " + quantity + " of " + name);

        int month = Integer.parseInt(JOptionPane.showInputDialog("Please type the month "
                + "in which you will purchase the item (a number, eg. 1, 2, 3)"));
        label1.setText("The month you will purchase this item is in " + month);

        int day = Integer.parseInt(JOptionPane.showInputDialog("Please type the day in"
                + " which you will purchase the item (a number)"));
        label1.setText("The day of the month you will purchase this item is on the " + day);

        int year = Integer.parseInt(JOptionPane.showInputDialog("Please type the year"
                + " in which you will purchase the item (a number)"));
        label1.setText("The year you will purchase this item is in " + year);

        shoppingList.addShoppingItem(new ShoppingItem(name, quantity, month, day, year));
    }

    //EFFECTS: confirms item has been added
    private void addedSuccessfully() {
        label1.setText("Item added successfully!");

        panel.add(backButton);

        backButton.addActionListener(this);
    }

//    private boolean validDate() {
//        if (shoppingItem.validDate()) {
//            return true;
//        }
//        System.out.println("Please enter a valid date");
//        shoppingList.removeRecentItem();
//        return false;
//    }

    //EFFECTS: removes item from the shopping list
    private void removeShoppingItem() {
        String name = JOptionPane.showInputDialog("Please type in the item's name");

        int month = Integer.parseInt(JOptionPane.showInputDialog("Please type the month of the item"));

        int day = Integer.parseInt(JOptionPane.showInputDialog("Please type the day of the item"));

        int year = Integer.parseInt(JOptionPane.showInputDialog("Please type the year of the item"));
        shoppingList.removeShoppingItem(name, month, day, year);
    }

    //EFFECTS: saves the shoppingList to file
    private void saveShoppingList() {
        try {
            jsonWriter.open();
            jsonWriter.write(shoppingList);
            jsonWriter.close();
            label1.setText("Saved ShoppingList to " + JSON_STORE);
            shoppingChoices();
        } catch (FileNotFoundException e) {
            label1.setText("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads shoppingList from file
    private void loadShoppingList() {
        try {
            shoppingList = jsonReader.read();
            label1.setText("Loaded ShoppingList from " + JSON_STORE);
            shoppingChoices();
        } catch (IOException e) {
            label1.setText("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: resets panel
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void panelReset() {
        if (viewButton.isVisible()) {
            panel.remove(viewButton);
        }
        if (editButton.isVisible()) {
            panel.remove(editButton);
        }
        if (loadButton.isVisible()) {
            panel.remove(loadButton);
        }
        if (saveButton.isVisible()) {
            panel.remove(saveButton);
        }
        if (createButton.isVisible()) {
            panel.remove(createButton);
        }
        if (quitButton.isVisible()) {
            panel.remove(quitButton);
        }
        if (backButton.isVisible()) {
            panel.remove(backButton);
        }
        if (output.isVisible()) {
            panel.remove(output);
        }
        if (imageLabel.isVisible()) {
            panel.remove(imageLabel);
        }
        if (removeButton.isVisible()) {
            panel.remove(removeButton);
        }
        if (quantity.isVisible()) {
            panel.remove(quantity);
        }
    }

    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }
}