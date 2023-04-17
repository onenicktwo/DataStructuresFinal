package Project;

import java.util.LinkedList;
import java.util.Set;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/*
 * Add a Find Item Order# and Remove Button for orders page (Will probably use an Input manager of some sort)
 */

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		// Set the window title
		primaryStage.setTitle("Online Ordering System");

		// Stores all orders
		UserInfo user = new UserInfo();

		// Helps user input
		InputManager inputMgr = new InputManager();

		// Create the ordering page layout
		VBox orderingLayout = new VBox();
		orderingLayout.setAlignment(Pos.CENTER);
		orderingLayout.setSpacing(10);
		orderingLayout.setPadding(new Insets(20));

		Label titleLabel = new Label("Item Selection");
		titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		orderingLayout.getChildren().add(titleLabel);

		Label menuLabel = new Label("Item List:");
		menuLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		orderingLayout.getChildren().add(menuLabel);

		// List of items for Testing
		ItemList itemList = new ItemList();
		itemList.add(new Item("Hamburger", 1.99));
		itemList.add(new Item("Cheeseburger", 2.99));
		itemList.add(new Item("French Fries", 0.50));
		itemList.add(new Item("Onion Rings", 0.75));
		itemList.add(new Item("Milkshake", 1.50));
		itemList.add(new Item("Soft Drink", 1.00));
		itemList.sort();

		ListView<Item> menuList = new ListView<>();
		ObservableList<Item> menuItems = FXCollections.observableArrayList();
		for (Item item : itemList.GetItemList()) {
			menuItems.add(item);
		}
		menuList.setItems(menuItems);
		orderingLayout.getChildren().add(menuList);

		LinkedList<Item> tempOrders = new LinkedList<Item>();
		Button addButton = new Button("Add to Order");
		addButton.setStyle("-fx-background-color: #27AE60; -fx-text-fill: white;");
		orderingLayout.getChildren().add(addButton);
		addButton.setOnAction(event -> {
			tempOrders.add(menuList.getSelectionModel().getSelectedItem());
		});
		// Create the orders page layout
		GridPane ordersLayout = new GridPane();
		ordersLayout.setAlignment(Pos.CENTER);
		ordersLayout.setHgap(10);
		ordersLayout.setVgap(10);
		ordersLayout.setPadding(new Insets(20));

		Label ordersLabel = new Label("Orders:");
		ordersLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		ordersLayout.add(ordersLabel, 0, 0);

		ListView<Item> ordersList = new ListView<>();
		ObservableList<Item> orderItems = FXCollections.observableArrayList();
		ordersList.setItems(orderItems);
		ordersLayout.add(ordersList, 0, 1, 1, 2);

		Label inventoryLabel = new Label("Inventory Management:");
		inventoryLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		ordersLayout.add(inventoryLabel, 1, 0);

		TextArea inventoryTextArea = new TextArea();
		inventoryTextArea.setEditable(false);
		inventoryTextArea.setPrefRowCount(15);
		inventoryTextArea.setText("Sample");
		ordersLayout.add(inventoryTextArea, 1, 1);

		ComboBox<Integer> allOrders = new ComboBox<>();
		ObservableList<Integer> allOrdersList = FXCollections.observableArrayList();
		allOrders.setPromptText("Choose Order");
		ordersLayout.add(allOrders, 1, 3);

		Button showOrderButton = new Button("Show Items");
		showOrderButton.setStyle("-fx-background-color: #27AE60; -fx-text-fill: white;");
		ordersLayout.add(showOrderButton, 1, 4);
		showOrderButton.setOnAction(event -> {
			inventoryTextArea.setText("");
			String tempStr = "";
			if (allOrders.getValue() != null) {
				for (Item item : user.getOrders().get(allOrders.getValue())) {
					tempStr += item + "\n";
				}
				inventoryTextArea.setText(tempStr);
			}
		});

		Button removeOrder = new Button("Remove Order");
		removeOrder.setStyle("-fx-background-color: #D63447; -fx-text-fill: white;");
		ordersLayout.add(removeOrder, 1, 5);
		removeOrder.setOnAction(event -> {
			if (allOrders.getValue() != null)
				user.getOrders().remove(allOrders.getValue());

			allOrdersList.clear();
			allOrders.getItems().clear();
			Set<Integer> keys = user.getOrders().keySet();
			for (Integer key : keys) {
				allOrdersList.add(key);
			}
			allOrders.getItems().addAll(allOrdersList);
		});

		Button updateButton = new Button("Update");
		updateButton.setStyle("-fx-background-color: #27AE60; -fx-text-fill: white;");
		ordersLayout.add(updateButton, 0, 3);
		updateButton.setOnAction(event -> {
			// Updates List of items in the order text area
			for (Item item : tempOrders) {
				orderItems.add(item);
				user.addToPushList(item);
			}
			ordersList.setItems(orderItems);
			tempOrders.clear();

			// Updates List of orders in the combo box
			allOrdersList.clear();
			allOrders.getItems().clear();
			Set<Integer> keys = user.getOrders().keySet();
			for (Integer key : keys) {
				allOrdersList.add(key);
			}
			allOrders.getItems().addAll(allOrdersList);
		});

		Button completeButton = new Button("Complete Order");
		completeButton.setStyle("-fx-background-color: #D63447; -fx-text-fill: white;");
		ordersLayout.add(completeButton, 0, 4);
		completeButton.setOnAction(event -> {
			if (user.getPushList().size() > 0) {
				user.pushItems();
				orderItems.clear();
			}
		});

		Button clearButton = new Button("Clear Order");
		clearButton.setStyle("-fx-background-color: #D63447; -fx-text-fill: white;");
		ordersLayout.add(clearButton, 0, 5);
		clearButton.setOnAction(event -> {
			user.getPushList().clear();
			orderItems.clear();
		});

		TextArea inputTextArea = new TextArea();
		inputTextArea.setEditable(true);
		inputTextArea.setPrefRowCount(1);
		inputTextArea.setText("");
		ordersLayout.add(inputTextArea, 1, 2);

		Button addItemButton = new Button("Add Item");
		addItemButton.setStyle("-fx-background-color: #27AE60; -fx-text-fill: white;");
		ordersLayout.add(addItemButton, 1, 6);
		addItemButton.setOnAction(event -> {
			if (allOrders.getValue() != null && inputMgr.validateInput(itemList, inputTextArea.getText())) {
				LinkedList<Item> prevList = user.getOrders().get(allOrders.getValue());
				Item item = inputMgr.findInputItem(itemList, inputTextArea.getText());
				prevList.add(item);
				user.getOrders().put(allOrders.getValue(), prevList);
			} else {
				inputTextArea.setText("Item does not exist or Order is not set");
			}
		});

		Button removeItemButton = new Button("Remove Item");
		removeItemButton.setStyle("-fx-background-color: #D63447; -fx-text-fill: white;");
		ordersLayout.add(removeItemButton, 1, 7);
		removeItemButton.setOnAction(event -> {
			if (allOrders.getValue() != null && inputMgr.validateInput(itemList, inputTextArea.getText())) {
				LinkedList<Item> prevList = user.getOrders().get(allOrders.getValue());
				Item item = inputMgr.findInputItem(itemList, inputTextArea.getText());
				if (prevList.contains(item)) {
					prevList.remove(item);
					user.getOrders().put(allOrders.getValue(), prevList);
				} else {
					inputTextArea.setText("Item not found in order");
				}
			} else {
				inputTextArea.setText("Item does not exist or Order is not set");
			}
		});

		// Create the main layout for the application
		BorderPane mainLayout = new BorderPane();
		mainLayout.setStyle("-fx-background-color: #FADBD8;");

		// Create buttons to switch between the ordering and orders pages
		Button orderingButton = new Button("Order");
		orderingButton.setStyle("-fx-background-color: #F7DC6F; -fx-text-fill: #34495E;");
		orderingButton.setOnAction(event -> {
			mainLayout.setCenter(orderingLayout);
		});

		Button ordersButton = new Button("Orders");
		ordersButton.setStyle("-fx-background-color: #D63447; -fx-text-fill: white;");
		ordersButton.setOnAction(event -> {
			mainLayout.setCenter(ordersLayout);
		});

		// Create a box to hold the buttons for switching between pages
		HBox buttonBox = new HBox();
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setSpacing(10);
		buttonBox.getChildren().addAll(orderingButton, ordersButton);

		// Add the button box to the bottom of the main layout
		mainLayout.setBottom(buttonBox);
		BorderPane.setAlignment(buttonBox, Pos.CENTER);

		// Create the scene for the main layout
		Scene scene = new Scene(mainLayout, 800, 600);

		// Set the scene for the primary stage
		primaryStage.setScene(scene);

		// Show the primary stage
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
