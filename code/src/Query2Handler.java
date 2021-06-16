/**
 *
 * @author Mridul Gupta | Divyanshu Talwar
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Query2Handler {
	private int limit;
	private int c = 0;
	ArrayList<String> authorAlias;

	public Query2Handler(int _k) {
		limit = _k;
		doWork();
	}

	public boolean isPresent(ArrayList<String> arr, String name_title) {
		for (String a : arr) {
			if (a.equalsIgnoreCase(name_title)) {
				return true;
			}
		}
		return false;
	}

	public void searchSimilarAuthor(String name_title) {
		boolean flag = true;
		for (Author a : Database.authors) {
			if (isPresent(a.getAlias(), name_title)) {
				authorAlias = a.getAlias();
				flag = false;
			}
		}
		if (flag) {
			authorAlias = new ArrayList<String>();
			authorAlias.add(name_title);
		}

	}

	public void doWork() {
//		if(Database.yah){
//			for (Data d : Database.allData) {
//				for (String a : d.getRawAuthor()) {
//					searchSimilarAuthor(a);
//					Database.map.put(authorAlias.get(0), 0);
//				}
//			}
//			System.out.println(Database.map.size());
//			for (Data d : Database.allData) {
//				for (String a : d.getRawAuthor()) {
//					searchSimilarAuthor(a);
//					Database.map.put(authorAlias.get(0), Database.map.get(authorAlias.get(0)) + 1);
//				}
//			}
//			Database.yah = false;
//		}
		for (String s : Database.map.keySet()) {
			if (Database.map.get(s) > limit) {
				c++;
			}
		}
		showResult();
	}

	void showResult() {
		String[][] temp = new String[c][2];
		int i = 0;
		for (String s : Database.map.keySet()) {
			if (Database.map.get(s) > limit) {
				temp[i][0] = s;
				temp[i++][1] = Database.map.get(s).toString();
			}
		}
		Arrays.sort(temp, new ColumnComparator(1, SortingOrder.DESCENDING));
		String columnNames[] = { "Author", "No. of publications" };
		Database.resultCount = temp.length;
		ResultPanel.updateData(temp, columnNames);
		ResultPanel.updateTable();
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
}
