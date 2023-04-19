package Project;

/**
 * Stores the information and format for a resturant item
 * 
 * @author FirstName LastName
 * @version 1.0
 */
public class Item {
	private String name; // Name of item
	private double price; // Price of item

	/**
	 * Sets the name and price of the item
	 * 
	 * @param name
	 * @param price
	 */
	public Item(String name, double price) {
		this.name = name;
		this.price = price;
	}

	/**
	 * Returns the name of the item
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the price of the item
	 * 
	 * @return double
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Returns the formatted string of the item
	 * 
	 * @return String
	 */
	public String toString() {
		return getName() + " $" + String.format("%.2f", getPrice());
	}
}
