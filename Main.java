import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;


import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private PriorityQueue pq = new PriorityQueue();
    private TextArea outputArea = new TextArea();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("IT Ticketing System");

        // Input fields for creating a new ticket
        Label titleLabel = new Label("Create a Ticket");
        titleLabel.setFont(new Font("Arial", 20));

        Label filterLabel = new Label("Filter Tickets");
        filterLabel.setFont(new Font("Arial", 16));

        TextField creatorField = new TextField();
        creatorField.setPromptText("Creator");
       
        TextField editIdField = new TextField();
        editIdField.setPromptText("Ticket ID to edit");

        TextField newOwnerField = new TextField();
        newOwnerField.setPromptText("New Owner (optional)");

        TextField newPriorityField = new TextField();
        newPriorityField.setPromptText("New Priority (optional)");

        TextField tagFilter = new TextField();
        tagFilter.setPromptText("Tag");

        Button editButton = new Button("Edit Ticket");

        Button filterButton = new Button("Apply Filter");


        TextField ownerField = new TextField();
        ownerField.setPromptText("Owner");

        TextField priorityField = new TextField();
        priorityField.setPromptText("Priority (1 = highest)");

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Description");

        TextField tagsField = new TextField();
        tagsField.setPromptText("Tags (comma-separated)");

        ComboBox<String> statusDropdown = new ComboBox<>();
        statusDropdown.getItems().addAll("Open", "In Progress", "Resolved");
        statusDropdown.setValue("Open");

        ComboBox<Integer> priorityFilter = new ComboBox<>(FXCollections.observableArrayList(1, 2, 3, 4, 5));
        priorityFilter.setPromptText("Priority");


        Button addButton = new Button("Add Ticket");
        Button removeButton = new Button("Remove Highest Priority Ticket");
        Button showAllButton = new Button("Display All Tickets");

        // Action to create and insert a new ticket into the queue
        addButton.setOnAction(e -> {
            try {
                String creator = creatorField.getText();
                String owner = ownerField.getText();
                int priority = Integer.parseInt(priorityField.getText());
                String description = descriptionField.getText();
                String[] tagsArray = tagsField.getText().split(",");
                String status = statusDropdown.getValue();

                List<String> tags = new ArrayList<>();
                for (String tag : tagsArray) {
                    tags.add(tag.trim());
                }

                Ticket ticket = new Ticket(creator, owner, priority, description, tags);
                ticket.setStatus(status);
                pq.insert(ticket);
                outputArea.appendText("Ticket added successfully!\n");

                creatorField.clear();
                ownerField.clear();
                priorityField.clear();
                descriptionField.clear();
                tagsField.clear();
            } catch (NumberFormatException ex) {
                outputArea.appendText("Invalid priority! Must be a number.\n");
            }
        });

        // Removes the highest-priority ticket from the system
        removeButton.setOnAction(e -> {
            if (!pq.isEmpty()) {
                Ticket removed = pq.remove();
                outputArea.appendText("Removed Ticket:\n");
                outputArea.appendText(removed.toString() + "\n");
            } else {
                outputArea.appendText("Queue is empty.\n");
            }
        });

         
        showAllButton.setOnAction(e -> {
            outputArea.appendText("All Tickets in Queue:\n");
            List<Ticket> allTickets = pq.getAllTickets();
            for (Ticket t : allTickets) {
                outputArea.appendText(t.toString() + "\n");
            }
        });

        // Edits ticket by ID allows for changing owners or priority's
        editButton.setOnAction(e -> {
            try {
                int ticketId = Integer.parseInt(editIdField.getText());
                String newOwner = newOwnerField.getText().trim();
                String newPriorityText = newPriorityField.getText().trim();
        
                Ticket ticket = pq.findById(ticketId); 
                if (ticket == null) {
                    outputArea.appendText("Ticket with ID " + ticketId + " not found.\n");
                    return;
                }
        
                if (!newOwner.isEmpty()) {
                    ticket.setOwner(newOwner);
                }
        
                if (!newPriorityText.isEmpty()) {
                    int newPriority = Integer.parseInt(newPriorityText);
                    ticket.setPriority(newPriority);
                }
        
                outputArea.appendText("Ticket " + ticketId + " updated successfully.\n");
            } catch (NumberFormatException ex) {
                outputArea.appendText("Invalid input. Ticket ID and Priority must be numbers.\n");
            }
        });

        // Filters tickets by tag and/or priority level
        filterButton.setOnAction(e -> {
            Integer selectedPriority = priorityFilter.getValue();
            String tagText = tagFilter.getText().trim().toLowerCase();
        
            outputArea.clear();
            outputArea.appendText("Filtered Results:\n");
        
            for (Ticket ticket : pq.getAllTickets()) {
                boolean matchesPriority = selectedPriority == null || ticket.getPriority() == selectedPriority;
                boolean matchesTag = tagText.isEmpty() || ticket.getTags().stream()
                        .anyMatch(tag -> tag.toLowerCase().contains(tagText));
        
                if (matchesPriority && matchesTag) {
                    outputArea.appendText(ticket.toString() + "\n");
                }
            }
        });

        

        

        outputArea.setEditable(false);
        outputArea.setPrefHeight(250);

        VBox inputBox = new VBox(10, creatorField, ownerField, priorityField, descriptionField, tagsField,statusDropdown, addButton, removeButton, showAllButton);
        VBox.setVgrow(outputArea, Priority.ALWAYS);


        HBox filterBox = new HBox(10, priorityFilter, tagFilter, filterButton);
        filterBox.setPadding(new Insets(10, 0, 10, 0));

        VBox layout = new VBox(15, titleLabel, inputBox, filterLabel, filterBox, outputArea);
        layout.setPadding(new Insets(20));


        Scene scene = new Scene(layout, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
