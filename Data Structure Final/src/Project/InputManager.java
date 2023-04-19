package Project;

/**
 * Contains methods that relate to the input of the user
 * 
 * @author Nicholas Morrow
 * @version 1.0
 */

public class InputManager {

	/**
	 * Returns true if the input string exists in the itemlist
	 * 
	 * @param list
	 * @param input
	 * @return boolean
	 */
	public boolean validateInput(ItemList list, String input) {
		for (Item item : list.GetItemList()) {
			if (item.getName().toLowerCase().equals(input.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Finds the exact item based on its name, return null if it doesn't exist
	 * 
	 * @param list
	 * @param input
	 * @return Item
	 */
	public Item findInputItem(ItemList list, String input) {
		for (Item item : list.GetItemList()) {
			if (item.getName().toLowerCase().equals(input.toLowerCase())) {
				return item;
			}
		}
		return null;
	}
}
