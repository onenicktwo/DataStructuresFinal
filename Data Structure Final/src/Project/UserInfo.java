package Project;
import java.util.HashMap;
import java.util.LinkedList;

public class UserInfo {
	private HashMap<Integer, LinkedList<Item>> orders = new HashMap<Integer, LinkedList<Item>>();
	private LinkedList<Item> pushList = new LinkedList<Item>();

	public HashMap<Integer, LinkedList<Item>> getOrders() {
		return orders;
	}

	public LinkedList<Item> getPushList() {
		return pushList;
	}

	public void pushItems() {
		int randKey = (int) (Math.random() * 899999) + 1000000;
		while (orders.containsKey(randKey)) {
			randKey = (int) (Math.random() * 899999) + 1000000;
		}
		orders.put(randKey, (LinkedList<Item>) pushList.clone());
		pushList.clear();
	}

	public void addToPushList(Item item) {
		pushList.add(item);
	}
}
