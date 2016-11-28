import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.File;
import java.util.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ParseEntityResolution extends DefaultHandler{
	boolean bwww = false;
	boolean bAuthor = false;

	ArrayList<String> partAuthor = new ArrayList<String>();

	Author Author;

	public ParseEntityResolution() {
		System.setProperty("jdk.xml.entityExpansionLimit", "0");
		try {
			File inputFile = new File("test.xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(inputFile, this);
		} catch (Exception f) {
			System.out.println("file not there :(");
			f.printStackTrace();
		}
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("www")) {
			bwww = true;
			Author = new Author();
			
		}
		else if (qName.equalsIgnoreCase("author") && bwww == true) {
			partAuthor = new ArrayList<String>();
			bAuthor = true;
		}
	}

	public void characters(char ch[], int start, int length) throws SAXException {
		if (bAuthor) {
			partAuthor.add(new String(ch, start, length));
		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("www")) {
			bAuthor = false;
			bwww = false;
			Database.authors.add(Author);
			Author = new Author();
		}

		else if (qName.equalsIgnoreCase("author") && bwww == true) {
			StringBuilder listString = new StringBuilder();

			for (String s : partAuthor){
     			listString.append(s);
			}
			
			bAuthor = false;

			Author.getAlias().add(listString.toString());


		}
	}

	public void printData() {
		System.out.println("hi : ");
		for (Author x : Database.authors) {
			System.out.println("next");
			for (String y : x.getAlias()) {
				System.out.println("hi : " + y);
			}
		}

	}
}