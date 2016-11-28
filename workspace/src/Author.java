import java.util.ArrayList;
import java.util.HashMap;

public class Author {
	private ArrayList<String> alias;
	Author(){
		alias = new ArrayList<String>();
	}
	
	public ArrayList<String> getAlias(){
		return alias;
	}
	
	public String getAuthor(){
		return alias.get(0);
	}
	
	public void addAlias(String name){
		this.alias.add(name);
	}
}
