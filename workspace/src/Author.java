
/**
 * Stores details of authors with their aliases
 * @author Mridul Gupta | Divyanshu Talwar
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Author {

	private ArrayList<String> alias; /**< ArrayList of aliases. */ 

	/**
	 * Author Constructor Details.
	 */
	Author() {
		alias = new ArrayList<String>();
	}

	/**
	 * Alias Getter Function.
	 * @return ArrayList<String> of aliases
	 */
	public ArrayList<String> getAlias() {
		return alias;
	}

	/**
	 * Author Name Getter Function.
	 * @return String AuthorName
	 */
	public String getAuthor() {
		return alias.get(0);
	}

	/**
	 * Alias setter Function.
	 * @param a String value
	 * 
	 */
	public void addAlias(String name) {
		this.alias.add(name);
	}
}
