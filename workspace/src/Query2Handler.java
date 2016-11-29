<<<<<<< HEAD
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class Query2Handler
{
    private int limit;
    private HashMap<String,Integer> map = new HashMap<>();
    private int c=0;
    ArrayList <String> authorAlias;

    public Query2Handler(int _k)
    {
        limit=_k;
        doWork();
    }

	public boolean isPresent(ArrayList<String> arr,String name_title){
		for(String a : arr){
			if(a.equalsIgnoreCase(name_title)){
				return true;
			}
		}
		return false;
	}
	
	public void searchSimilarAuthor(String name_title){
		boolean flag= true;
		for(Author a : Database.authors){
			if(isPresent(a.getAlias(),name_title)){
				authorAlias = a.getAlias();
				flag = false;
			}
		}
		if(flag){
			authorAlias = new ArrayList<String>();
			authorAlias.add(name_title);
		}
		
	}
	
    public void doWork()
    {
        for(Data d : Database.allData)
        {
            for(String a : d.getRawAuthor())
            {
            	searchSimilarAuthor(a);
                map.put(authorAlias.get(0),0);
            }
        }
        System.out.println(map.size());
        for(Data d : Database.allData)
        {
            for(String a : d.getRawAuthor())
            {
            	searchSimilarAuthor(a);
                map.put(authorAlias.get(0), map.get(authorAlias.get(0)) + 1);
            }
        }
        for(String s: map.keySet())
        {
            if(map.get(s)>limit)
            {
                c++;
            }
        }
        showResult();
    }

    void showResult()
    {
        Object[][] temp= new Object[c][2];
        int i=0;
        for(String s: map.keySet())
        {
            if(map.get(s)>limit)
            {
                temp[i][0]=s;
                temp[i++][1]=map.get(s);
            }
        }
        String columnNames[] = { "Author","No. of publishes" };
        ResultPanel.updateData(temp,columnNames);
        ResultPanel.updateTable();
    }
	@Override
	public int compare(Comparable[] c1, Comparable[] c2) {
		int result = c1[iColumn].compareTo(c2[iColumn]);
		return order == SortingOrder.ASCENDING ? result : -result;
	}
}
