package UnitTesting;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import Project.InputManager;
import Project.Item;
import Project.ItemList;
import Project.UserInfo;

class Tests {

	// Item class testing
	@Test
	void ItemGets() {
		String name = "Drink";
		double price = 0.50;

		Item item = new Item(name, price);
		String expect = name + price;
		String real = item.getName() + item.getPrice();
		assertEquals(expect, real);
	}

	@Test
	void ItemToString() {
		String name = "Drink";
		double price = 0.50;

		Item item = new Item(name, price);
		String expect = name + " $" + String.format("%.2f", price);
		String real = item.toString();
		assertEquals(expect, real);
	}

	// ItemList class testing
	@Test
	void ItemListAdd() {
		ItemList itemList = new ItemList();
		Item item1 = new Item("a", 2);
		Item item2 = new Item("b", 1);
		Item item3 = new Item("c", 3);
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);

		String expect = "";
		expect += item1.toString();
		expect += "\n" + item2.toString();
		expect += "\n" + item3.toString() + "\n";

		String actual = "";
		for (Item item : itemList.GetItemList()) {
			actual += item.toString() + "\n";
		}
		assertEquals(expect, actual);
	}

	@Test
	void ItemListSort() {
		ItemList itemList = new ItemList();
		Item item1 = new Item("a", 2);
		Item item2 = new Item("b", 1);
		Item item3 = new Item("c", 3);
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);
		itemList.sort();

		String expect = "";
		expect += item3.toString();
		expect += "\n" + item1.toString();
		expect += "\n" + item2.toString() + "\n";

		String actual = "";
		for (Item item : itemList.GetItemList()) {
			actual += item.toString() + "\n";
		}
		assertEquals(expect, actual);
	}

	// UserInfo class testing
	@Test
	void UserInfoPushList() {
		UserInfo ui = new UserInfo();
		Item item1 = new Item("a", 2);
		Item item2 = new Item("b", 1);
		Item item3 = new Item("c", 3);
		ui.addToPushList(item1);
		ui.addToPushList(item2);
		ui.addToPushList(item3);

		String expect = "";
		expect += item1.toString();
		expect += "\n" + item2.toString();
		expect += "\n" + item3.toString() + "\n";

		String actual = "";
		for (Item item : ui.getPushList()) {
			actual += item.toString() + "\n";
		}
		assertEquals(expect, actual);
	}

	@Test
	void UserInfoPushItems() {
		UserInfo ui = new UserInfo();
		Item item1 = new Item("a", 2);
		Item item2 = new Item("b", 1);
		Item item3 = new Item("c", 3);
		ui.addToPushList(item1);
		ui.addToPushList(item2);
		ui.addToPushList(item3);
		ui.pushItems();

		assertEquals(0, ui.getPushList().size());
	}

	// InputManager class testing
	@Test
	void ValidateInputTrue() {
		InputManager im = new InputManager();
		ItemList itemList = new ItemList();
		Item item1 = new Item("a", 2);
		Item item2 = new Item("b", 1);
		Item item3 = new Item("c", 3);
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);

		String input = "a";
		boolean expected = true;
		boolean actual = im.validateInput(itemList, input);
		assertEquals(expected, actual);
	}

	@Test
	void ValidateInputFalse() {
		InputManager im = new InputManager();
		ItemList itemList = new ItemList();
		Item item1 = new Item("a", 2);
		Item item2 = new Item("b", 1);
		Item item3 = new Item("c", 3);
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);

		String input = "d";
		boolean expected = false;
		boolean actual = im.validateInput(itemList, input);
		assertEquals(expected, actual);
	}

	@Test
	void FindInputItem() {
		InputManager im = new InputManager();
		ItemList itemList = new ItemList();
		Item item1 = new Item("a", 2);
		Item item2 = new Item("b", 1);
		Item item3 = new Item("c", 3);
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);

		String input = "c";
		Item expected = item3;
		Item actual = im.findInputItem(itemList, input);

		assertEquals(expected, actual);
	}
}
