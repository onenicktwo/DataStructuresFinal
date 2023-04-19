package Project;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Contains all the methods and variables relating to the user's input and
 * deicisions
 * 
 * @author Nicholas Morrow
 * @version 1.0
 */
public class UserInfo {
	private HashMap<Integer, LinkedList<Item>> orders = new HashMap<Integer, LinkedList<Item>>(); // Finilized orders
	private LinkedList<Item> pushList = new LinkedList<Item>(); // Orders in progress

	/**
	 * Returns the HashMap of orders
	 * 
	 * @return HashMap<Integer, LinkedList<Item>>
	 */
	public HashMap<Integer, LinkedList<Item>> getOrders() {
		return orders;
	}

	/**
	 * Returns the push list
	 * 
	 * @return LinkedList<Item>
	 */
	public LinkedList<Item> getPushList() {
		return pushList;
	}

	/**
	 * Sends the push list to the orders hashmap to be added
	 */
	public void pushItems() {
		int randKey = (int) (Math.random() * 899999) + 1000000;
		while (orders.containsKey(randKey)) {
			randKey = (int) (Math.random() * 899999) + 1000000;
		}
		orders.put(randKey, (LinkedList<Item>) pushList.clone());
		pushList.clear();
	}

	/**
	 * Adds an item to the push list
	 * 
	 * @param item
	 */
	public void addToPushList(Item item) {
		pushList.add(item);
	}
}
