
/**
 * Format for storing publication details
 * @author Mridul Gupta | Divyanshu Talwar
 */
import java.util.ArrayList;
import entity.Jaccard;

public class Data implements Comparable {
	private String title; /**< Publication Title. */ 
	private int year; /**< Year of Publication. */ 
	private String volume; /**< Publication Volume. */ 
	private String pages; /**< Number of Pages. */ 
	private String journal_booktitle; /**< Journal/Book Title. */ 
	private ArrayList<String> authors; /**< ArrayList of Author Aliases. */ 
	private ArrayList<String> url; /**< ArrayList of URLs. */ 

	private Double relevance; /**< Store value of relevance with another string. */ 
	
	/**
     * Data constructor.
     * Sets default for the data fields
     */
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
	
	/**
	 * Relevance setter Function.
	 * @param a Double value
	 * 
	 */
	public void setRelevance(Double s) {
		relevance = s;
	}
	
	/**
	 * Relevance Getter Function. Returns the Jaccard similarity value stored
	 * @return Double relevance
	 */
	public Double getRelevance() {
		return relevance;
	}
	
	/**
	 * Title setter function
	 * @param String value
	 * 
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Year setter function
	 * @param int value
	 * 
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * Volume setter function
	 * @param String value
	 * 
	 */
	public void setVolume(String volume) {
		this.volume = volume;
	}
	
	/**
	 * Pages setter function
	 * @param String value
	 * 
	 */
	public void setPages(String pages) {
		this.pages = pages;
	}
	
	/**
	 * Book/Journal Title setter function
	 * @param String value
	 * 
	 */
	public void setJournal_booktitle(String journal_booktitle) {
		this.journal_booktitle = journal_booktitle;
	}
	
	/**
	 * Add Author function to list
	 * @param String value
	 * 
	 */
	public void addAuthor(String _author) {
		// System.out.println(_author);
		authors.add(_author);
	}
	
	/**
	 * Add URL function to list
	 * @param String value
	 * 
	 */
	public void addUrl(String _url) {
		url.add(_url);
	}
	
	/**
	 * Search for an Author. Hard Search
	 * @param String value
	 * @return Boolean value of author found.
	 * 
	 */
	public boolean searchAuthor(String name) {
		// System.out.println("Size is " + authors.size());
		for (int i = 0; i < authors.size(); i++) {
			if (authors.get(i).equalsIgnoreCase(name))
				return true;
		}
		return false;
	}
	
	/**
	 * Returns an author
	 * @return String value of all authors of Data
	 * 
	 */
	public String getAuthor() {
		StringBuilder temp = new StringBuilder();
		temp.append(authors.get(0));
		for (int i = 1; i < authors.size(); i++) {
			temp.append(", ");
			temp.append(authors.get(i));
		}
		return temp.toString();

	}
	
	/**
	 * Returns the URL
	 * @return String URL
	 * 
	 */
	public String getUrl() {
		try {
			StringBuilder temp = new StringBuilder();
			temp.append(url.get(0));
			if (temp.toString() == null) {
				return "NA";
			}
			for (int i = 1; i < url.size(); i++) {
				temp.append(",");
				temp.append(url.get(i));
			}
			return temp.toString();
		} catch (Exception e) {
			return "NA";
		}
	}
	
	/**
	 * Returns the Title
	 * @return String Title
	 * 
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Returns the Publication Year
	 * @return int Year
	 * 
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Returns the Volume
	 * @return String Volume
	 * 
	 */
	public String getVolume() {
		return volume;
	}
	
	/**
	 * Returns the Pages
	 * @return String Pages
	 * 
	 */
	public String getPages() {
		return pages;
	}
	
	/**
	 * Returns the Journal/Book Title
	 * @return String journal_booktitle
	 * 
	 */
	public String getJournal_booktitle() {
		return journal_booktitle;
	}
	
	/**
	 * Returns the Author List
	 * @return ArrayList<String> authors
	 * 
	 */
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
	 * @return boolean if string are related
	 */
	public boolean searchRelAuthor(String name_title) {
		for (int i = 0; i < authors.size(); i++) {
			String s1 = authors.get(i), s2 = name_title;
			Jaccard J = new Jaccard(2);
			Double tolerance = 0.4;
			if (J.similarity(s1, s2) >= tolerance)
				return true;
		}
		return false;
	}
}
