package Project;

import java.util.LinkedList;

/**
 * Holds the items for the order page layout, including how they are sorted
 * 
 * @author Nicholas Morrow
 * @version 1.0
 */

public class ItemList {
	LinkedList<Item> items; // List of items

	/**
	 * Sets the list of items
	 */
	public ItemList() {
		items = new LinkedList<Item>();
	}

	/**
	 * Adds the item to the itemlist
	 * 
	 * @param item
	 */
	public void add(Item item) {
		items.add(item);
	}

	/**
	 * Returns the itemlist
	 * 
	 * @return LinkedList<Item>
	 */
	public LinkedList<Item> GetItemList() {
		return items;
	}

	/**
	 * Uses a merge sort algorithm that sorts the items in the itemlist from highest
	 * price to lowest price.
	 */
	public void sort() {
		int size = items.size();
		for (int i = 1; i < size; ++i) {
			Item item = items.get(i);
			int j = i - 1;
			while (j >= 0 && items.get(j).getPrice() < item.getPrice()) {
				items.set(j + 1, items.get(j));
				j -= 1;
			}
			items.set(j + 1, item);
		}
	}
}
