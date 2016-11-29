
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import entity.Jaccard;

public class Query1Handler {

	private int sortby, from, to;
	private String name_title;
	private ArrayList<Data> list = new ArrayList<>();
	private ArrayList<String> authorAlias = new ArrayList<>();

	public Query1Handler(String _name_title, int _sortby, int _from, int _to) {
		sortby = _sortby;
		from = _from;
		to = _to;
		name_title = _name_title;
	}

	// sort==1 for year and sort==2 for relevance

	public boolean isPresent(ArrayList<String> arr, String name_title) {
		for (String a : arr) {
			if (a.equalsIgnoreCase(name_title)) {
				return true;
			}
		}
		return false;
	}

	public void searchSimilarAuthor() {
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

	public void doWork(boolean searchBy) {
		// Sort Authors by Year w/o relevance
		if (searchBy && sortby == 1) {
			searchSimilarAuthor();
			for (String a : authorAlias) {
				for (Data tmpData : Database.allData) {
					if (tmpData.searchAuthor(a) && tmpData.getYear() >= from && tmpData.getYear() <= to) {
						list.add(tmpData);
					}
				}
			}
		}
		// Sort Authors by Year w/ relevance
		else if (searchBy && sortby == 2) {
			for (int i = 0; i < Database.allData.size(); i++) {
				// System.out.println("sup");
				Data tmpData = Database.allData.get(i);
				if (tmpData.searchRelAuthor(name_title) && tmpData.getYear() >= from && tmpData.getYear() <= to) {
					list.add(Database.allData.get(i));
				}
			}
		}

		// Sort Titles by Year w/o relevance
		else if (!searchBy && sortby == 1) {
			for (int i = 0; i < Database.allData.size(); i++) {
				// System.out.println("sup");
				Data tmpData = Database.allData.get(i);
				if (tmpData.getTitle().equalsIgnoreCase(name_title) && tmpData.getYear() >= from && tmpData.getYear() <= to) {
					list.add(Database.allData.get(i));
				}
			}
		}

		// Sort Titles by Year w/ relevance
		else {
			for (int i = 0; i < Database.allData.size(); i++) {
				// System.out.println("sup");
				Data tmpData = Database.allData.get(i);
				String s1 = name_title.toLowerCase(),s2=tmpData.getTitle().toLowerCase();
				Jaccard J = new Jaccard(2);
				Double tolerance = 0.4;
				if (J.similarity(s1, s2)>=tolerance && tmpData.getYear() >= from && tmpData.getYear() <= to) {
					Data toAdd = Database.allData.get(i);
					toAdd.setRelevance(J.similarity(s1, s2));
					list.add(toAdd);
				}
			}

		}

		sort();
		print();
		showResult();
	}

	public void sort() {
		Collections.sort(list);
		if(sortby==2){
			Collections.sort(list, new Comparator<Data>(){
			     public int compare(Data o1, Data o2){
			         if(o1.getRelevance() == o2.getRelevance())
			             return 0;
			         return o1.getRelevance() > o2.getRelevance() ? -1 : 1;
			     }
			});
		}
	}

	public void add(Data x) {
		list.add(x);
	}

	void print() {
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

	}
	//
	// public void findSimilarNames() {
	// Iterator it = Database.allData.iterator();
	// Data tmp;
	//
	// while (it.hasNext()) {
	// tmp = (Data) it.next();
	// ArrayList<String> auth= tmp.getRawAuthor();
	// for (int i=0; i<auth.size(); i++) {
	// if (auth.get(i).contains(name_title))
	// list.add(tmp);
	// }
	// }
	// }

	void showResult() {
		Object[][] temp = new Object[list.size()][8];
		for (int i = 0; i < list.size(); i++) {
			temp[i][0] = i + 1;
			temp[i][1] = list.get(i).getTitle();
			temp[i][2] = list.get(i).getAuthor();
			temp[i][3] = list.get(i).getYear();
			temp[i][4] = list.get(i).getVolume();
			temp[i][5] = list.get(i).getPages();
			temp[i][6] = list.get(i).getJournal_booktitle();
			temp[i][7] = list.get(i).getUrl();
		}
		String columnNames[] = { "S.No.", "Title", "Author(s)", "Year", "Volume", "Pages", "Journal/Booktitle", "Url" };
		ResultPanel.updateData(temp, columnNames);
		ResultPanel.updateTable();
	}
}