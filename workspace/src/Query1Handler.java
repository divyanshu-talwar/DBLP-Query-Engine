
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Query1Handler {

	private int sortby, from, to;
	private String name_title;
	private ArrayList<Data> list = new ArrayList<>();

	public Query1Handler(String _name_title, int _sortby, int _from, int _to) {
		sortby = _sortby;
		from = _from;
		to = _to;
		name_title = _name_title;
	}

	// sort==1 for year and sort==2 for relevance

	/**
	 * 
	 */
	public void doWork(boolean searchBy) {
		if(searchBy){
			for (int i = 0; i < Database.allData.size(); i++) {
				// System.out.println("sup");
				Data tmpData = Database.allData.get(i);
				Author tmpAuthor = Database.authors.get(i);
				if (tmpData.searchAuthor(name_title) && tmpData.getYear() >= from && tmpData.getYear() <= to) {
					list.add(Database.allData.get(i));
				}
			}
		}
		else{
			for (int i = 0; i < Database.allData.size(); i++) {
				// System.out.println("sup");
				Data tmpData = Database.allData.get(i);
				if (tmpData.getTitle().equals(name_title) && tmpData.getYear() >= from && tmpData.getYear() <= to) {
					list.add(Database.allData.get(i));
				}
			}
		}

		sort();
		print();
		showResult();
	}

	public void sort() {
		Collections.sort(list);
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
//    public void findSimilarNames() {
//        Iterator it = Database.allData.iterator();
//        Data tmp;
//
//        while (it.hasNext()) {
//            tmp = (Data) it.next();
//            ArrayList<String> auth= tmp.getRawAuthor();
//            for (int i=0; i<auth.size(); i++) {
//                    if (auth.get(i).contains(name_title))
//                        list.add(tmp);
//            }
//        }
//    }
    
	void showResult() {
		Object[][] temp = new Object[list.size()][7];
		for (int i = 0; i < list.size(); i++) {
			temp[i][0] = list.get(i).getTitle();
			temp[i][1] = list.get(i).getAuthor();
			temp[i][2] = list.get(i).getYear();
			temp[i][3] = list.get(i).getVolume();
			temp[i][4] = list.get(i).getPages();
			temp[i][5] = list.get(i).getJournal_booktitle();
			temp[i][6] = list.get(i).getUrl();
		}
		String columnNames[] = { "title", "author", "year", "volume", "pages", "journal/booktitle", "url" };
		ResultPanel.updateData(temp, columnNames);
		ResultPanel.updateTable();
	}
}