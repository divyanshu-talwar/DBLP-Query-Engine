import java.util.ArrayList;

import entity.Jaccard;

public class Data implements Comparable {
	private String title;
	private int year;
	private String volume;
	private String pages;
	private String journal_booktitle;
	private ArrayList<String> authors;
	private ArrayList<String> url;
	
	private Double relevance;
	
	public void setRelevance(Double s){
		relevance=s;
	}
	
	public Double getRelevance(){
		return relevance;
	}

	public Data() {
		title = "NA";
		volume = "NA";
		pages = "NA";
		year = 0;
		journal_booktitle = "NA";
		authors = new ArrayList<String>();
		url = new ArrayList<String>();
		relevance = (double) 0;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public void setJournal_booktitle(String journal_booktitle) {
		this.journal_booktitle = journal_booktitle;
	}

	public void addAuthor(String _author) {
//		System.out.println(_author);
		authors.add(_author);
	}

	public void addUrl(String _url) {
		url.add(_url);
	}

	public boolean searchAuthor(String name) {
//		System.out.println("Size is " + authors.size());
		for (int i = 0; i < authors.size(); i++) {
			if (authors.get(i).equalsIgnoreCase(name))
				return true;
		}
		return false;
	}

	public String getAuthor() {
		StringBuilder temp = new StringBuilder();
		temp.append(authors.get(0));
		for (int i = 1; i < authors.size(); i++)
		{
			temp.append(", ");
		    temp.append(authors.get(i));
		}
		return temp.toString();

	}

	public String getUrl() {
		try {
			StringBuilder temp = new StringBuilder();
			temp.append(url.get(0));
			if (temp.toString() == null) {
				return "NA";
			}
			for (int i = 1; i < url.size(); i++)
			{
				temp.append(",");
			    temp.append(url.get(i));
			}
			return temp.toString();
		} catch (Exception e) {
			return "NA";
		}
	}

	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	}

	public String getVolume() {
		return volume;
	}

	public String getPages() {
		return pages;
	}

	public String getJournal_booktitle() {
		return journal_booktitle;
	}

	public ArrayList<String> getRawAuthor() {
		return authors;
	}

	@Override
	public int compareTo(Object o) {
		int temp = ((Data) o).year;
		return temp - this.year;
	}

	public String toString() {

		return authors + " " + title + " " + pages + " " + volume + " " + journal_booktitle + " " + year + " " + url;
	}

	/**
	 * @param name_title
	 * @return
	 */
	public boolean searchRelAuthor(String name_title) {
		for (int i = 0; i < authors.size(); i++) {
			String s1=authors.get(i).toLowerCase(),s2=name_title.toLowerCase();
			Jaccard J = new Jaccard(2);
			Double tolerance = 0.4;
			if (J.similarity(s1, s2)>=tolerance)
				return true;
		}
		return false;
	}
}
