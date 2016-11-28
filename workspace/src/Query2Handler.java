import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Query2Handler {
	private int limit;
	private HashMap<String, Integer> map = new HashMap<>();
	private int c = 0;

	public Query2Handler(int _k) {
		limit = _k;
		doWork();
	}

	public void doWork() {
		for (int i = 0; i < Database.allData.size(); i++) {
			for (String a : Database.allData.get(i).getRawAuthor()) {
				map.put(a, 0);
			}
		}
		System.out.println(map.size());
		for (int i = 0; i < Database.allData.size(); i++) {
			for (String a : Database.allData.get(i).getRawAuthor()) {
				map.put(a, map.get(a) + 1);
			}
		}
		for (String s : map.keySet()) {
			if (map.get(s) > limit) {
				c++;
			}
		}
		showResult();
	}

	void showResult() {
		String[][] temp = new String[c][2];
		int i = 0;
		for (String s : map.keySet()) {
			if (map.get(s) > limit) {
				temp[i][0] = s;
				temp[i++][1] = map.get(s).toString();
			}
		}
		Arrays.sort(temp, new ColumnComparator(1, SortingOrder.DESCENDING));
		String columnNames[] = { "Author", "No. of publishes" };
		ResultPanel.updateData(temp, columnNames);
		ResultPanel.updateTable();
	}

}

enum SortingOrder {
	ASCENDING, DESCENDING;
};

class ColumnComparator implements Comparator<Comparable[]> {
	private final int iColumn;
	private final SortingOrder order;

	public ColumnComparator(int column, SortingOrder order) {
		this.iColumn = column;
		this.order = order;
	}

	@Override
	public int compare(Comparable[] c1, Comparable[] c2) {
		int result = c1[iColumn].compareTo(c2[iColumn]);
		return order == SortingOrder.ASCENDING ? result : -result;
	}
}
