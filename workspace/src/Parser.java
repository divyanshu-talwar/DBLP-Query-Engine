
/**
 * This is the main SAX parser, parsing the dblp.xml file.
 * @author Mridul Gupta | Divyanshu Talwar
 */

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Parser extends DefaultHandler {

	private boolean authorbool = false; /**< Author boolean. */ 
	private boolean ignorebool = false; /**< Ignore  boolean. */ 
	private boolean articlebool = false; /**< Article  boolean. */ 
	private boolean titlebool = false; /**< Title  boolean. */ 
	private boolean yearbool = false; /**< Year  boolean. */ 
	private boolean urlbool = false; /**< URL boolean. */ 
	private boolean volumebool = false; /**< Volume boolean. */ 
	private boolean pagebool = false; /**< Page boolean. */ 
	private boolean journalbool = false; /**< Journal boolean. */ 
	private boolean wwwbool = false; /**< WWW boolean. */ 
	int c = 0;
	private Data data; /**< Data store. */ 
	private JProgressBar bar; /**< GUI Progress Bar. */ 
	private JFrame loading; /**< Loading Frame. */ 
	private ProgressBar pb; /**< Progress bar. */ 
	private ArrayList<String> authorName; /**< List of authors. */ 
	private ArrayList<String> titleName; /**< List of Titles. */ 
	
	/**
	 * Progressbar Class
	 * 
	 */
	class ProgressBar {
		public ProgressBar() {
			loading = new JFrame();
			loading.setSize(600, 40);
			bar = new JProgressBar(0, 1529443);
			bar.setValue(0);
			bar.setStringPainted(true);
			loading.add(bar);
			loading.setResizable(false);
			loading.setUndecorated(true);
			loading.setLocationRelativeTo(null);
			loading.setVisible(true);
		}
	}
	
	/**
	 * Constructor for Parser
	 * 
	 */
	public Parser() {
		System.setProperty("jdk.xml.entityExpansionLimit", "0");
		pb = new ProgressBar();
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
	
	/**
     * Start Element method
     * @param String url
     * @param String localName
     * @param String qName
     * @param Attributes
     * @throws SAXException
     */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("article")) {
			articlebool = true;
			data = new Data();
		} else if (qName.equalsIgnoreCase("author") || qName.equalsIgnoreCase("editor")) {
			authorbool = true;
			authorName = new ArrayList<String>();
		} else if (qName.equalsIgnoreCase("title") || qName.equalsIgnoreCase("book")
				|| qName.equalsIgnoreCase("phdthesis") || qName.equalsIgnoreCase("inproceedings")
				|| qName.equalsIgnoreCase("incollection") || qName.equalsIgnoreCase("proceedings")
				|| qName.equalsIgnoreCase("mastersThesis")) {
			titlebool = true;
			titleName = new ArrayList<String>();
		} else if (qName.equalsIgnoreCase("www")) {
			wwwbool = true;
		} else if (qName.equalsIgnoreCase("pages")) {
			pagebool = true;
		} else if (qName.equalsIgnoreCase("year")) {
			yearbool = true;
		} else if (qName.equalsIgnoreCase("volume")) {
			volumebool = true;
		} else if (qName.equalsIgnoreCase("journal") || qName.equalsIgnoreCase("booktitle")) {
			journalbool = true;
		} else if (qName.equalsIgnoreCase("url")) {
			urlbool = true;
		} else if (qName.equalsIgnoreCase("dblp")) {
		} else {
			ignorebool = true;
		}
	}
	
	/**
     * End Element method
     * @param String url
     * @param String localName
     * @param String qName
     * @throws SAXException
     */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("article")) {

			Database.allData.add(data);
			articlebool = false;

			++c;
			if (c % 10000 == 0) {
				bar.setValue(c);
				System.out.println((c / 15294.43) + " %");
			}
		} else if (qName.equalsIgnoreCase("author") && !wwwbool) {
			authorbool = false;
			StringBuilder name = new StringBuilder();
			for (String s : authorName) {
				name.append(s);
			}
			data.addAuthor(name.toString());
		} else if (qName.equalsIgnoreCase("title") && !wwwbool) {
			titlebool = false;
			StringBuilder tname = new StringBuilder();
			for (String s : titleName) {
				tname.append(s);
			}
			data.setTitle(tname.toString());
		} else if (qName.equalsIgnoreCase("www")) {
			wwwbool = false;
		} else if (qName.equalsIgnoreCase("dblp")) {
			System.out.println("100 % " + Database.allData.size());
		}

	}
	
	/**
     * characters method
     * @param char[] ch
     * @param int start
     * @param int length
     * @throws SAXException
     */
	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		if (ignorebool) {
			ignorebool = false;
			return;
		}
		if (authorbool && !wwwbool) {
			String temp = new String(ch, start, length);
			authorName.add(temp);
		} else if (titlebool && !wwwbool) {
			titleName.add(new String(ch, start, length));
		} else if (pagebool) {
			pagebool = false;
			data.setPages(new String(ch, start, length));
		} else if (yearbool) {
			yearbool = false;
			try {
				data.setYear(Integer.parseInt(new String(ch, start, length)));
			} catch (Exception e) {
			}
		} else if (volumebool) {
			volumebool = false;
			data.setVolume(new String(ch, start, length));
			// System.out.println("volume: " + new String(ch, start, length));
		} else if (journalbool) {
			journalbool = false;
			data.setJournal_booktitle(new String(ch, start, length));
			// System.out.println("journal: " + new String(ch, start, length));
		} else if (urlbool) {
			urlbool = false;
			data.addUrl(new String(ch, start, length));
			// System.out.println("Url: " + new String(ch, start, length));
		}
	}
}
