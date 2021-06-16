
/**
 * Parsing For Entity Resolution
 * @author Mridul Gupta | Divyanshu Talwar
 */

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.File;
import java.util.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ParseEntityResolution extends DefaultHandler {
	boolean bwww = false; /**< WWW Boolean. */ 
	boolean bAuthor = false; /**< Author boolean. */ 

	ArrayList<String> partAuthor; /**< ArrayList of String of Author parts . */ 

	Author Author; /**< Author type. */ 
	
	/**
     * ParseEntityResolution Constructor.
     * Parses the data file
     */
	public ParseEntityResolution() {
		System.setProperty("jdk.xml.entityExpansionLimit", "0");
		try {
			File inputFile = new File("dblp.xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(inputFile, this);
		} catch (Exception f) {
			System.out.println("file not here :(" + f);
			f.printStackTrace();
		}
	}
	
	/**
     * Start Element method
     * @param String url
     * @param String localName
     * @param String qName
     * @param Attributes
     * @throws SAXException
     */
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("www")) {
			bwww = true;
			Author = new Author();

		} else if (qName.equalsIgnoreCase("author") && bwww == true) {
			partAuthor = new ArrayList<String>();
			bAuthor = true;
		}
	}
	
	/**
     * characters method
     * @param char[] ch
     * @param int start
     * @param int length
     * @throws SAXException
     */
	public void characters(char ch[], int start, int length) throws SAXException {
		if (bAuthor) {
			partAuthor.add(new String(ch, start, length));
		}
	}
	
	/**
     * End Element method
     * @param String url
     * @param String localName
     * @param String qName
     * @throws SAXException
     */
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("www")) {
			bAuthor = false;
			bwww = false;
			Database.authors.add(Author);
			if(Author!=null && Author.getAlias()!=null &&  !Author.getAlias().isEmpty()){
				for (int i=1;i<Author.getAlias().size();i++){
					if( Author.getAlias().get(i) != null && !(Author.getAlias().get(i).equals("")) && Database.map.containsKey(Author.getAlias().get(i)) && Author.getAlias().get(0) != null && !(Author.getAlias().get(0).equals("")) && Database.map.containsKey(Author.getAlias().get(0))){
						int addsum = ((int)Database.map.get(Author.getAlias().get(i)) + Database.map.get(Author.getAlias().get(0)));
						Database.map.put(Author.getAlias().get(0),addsum);
						Database.map.remove(Author.getAlias().get(i));
					}
				}
			}
//			Author = new Author();
		}

		else if (qName.equalsIgnoreCase("author") && bwww == true) {
			StringBuilder listString = new StringBuilder();

			for (String s : partAuthor) {
				listString.append(s);
			}

			bAuthor = false;

			Author.getAlias().add(listString.toString());

		}
	}
	
	/**
     * Prints Data
     * 
     */
	public void printData() {
		// System.out.println("hi : ");
		for (Author x : Database.authors) {
			// System.out.println("next");
			for (String y : x.getAlias()) {
//				System.out.println("hi : " + y);
			}
		}

	}
}