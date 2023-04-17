package Project;

import java.util.LinkedList;

public class ItemList {
	LinkedList<Item> items;

	public ItemList() {
		items = new LinkedList<Item>();
	}

	public void add(Item item) {
		items.add(item);
	}

	public LinkedList<Item> GetItemList() {
		return items;
	}

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
