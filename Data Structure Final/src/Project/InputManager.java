package Project;

public class InputManager {

	public boolean validateInput(ItemList list, String input) {
		for (Item item : list.GetItemList()) {
			if (item.getName().toLowerCase().equals(input.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public Item findInputItem(ItemList list, String input) {
		for (Item item : list.GetItemList()) {
			if (item.getName().toLowerCase().equals(input.toLowerCase())) {
				return item;
			}
		}
		return null;
	}
}
