import java.io.File;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
public class Parser extends DefaultHandler{
	
    private boolean authorbool = false,ignorebool=false,overall=false,articlebool, titlebool = false, yearbool = false, urlbool = false,volumebool=false,pagebool=false,journalbool=false;
    int c=0;
    private Data data;
    private JProgressBar bar;
    private JFrame loading;
    private ProgressBar pb;
    class ProgressBar
    {
        public ProgressBar()
        {
            loading= new JFrame();
            loading.setSize(600,40);
            bar = new JProgressBar(0, 1523384);
            bar.setValue(0);
            bar.setStringPainted(true);
            loading.add(bar);
            loading.setResizable(false);
            loading.setUndecorated(true);
            loading.setLocationRelativeTo(null);
            loading.setVisible(true);
        }
    }
    
    public Parser()
    {
        System.setProperty("jdk.xml.entityExpansionLimit", "0");
        pb= new ProgressBar();
        try {
            File inputFile = new File("test.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputFile, this);
        } catch (Exception f) {
        	System.out.println("file not there :(");
            f.printStackTrace();
        }    }


    @Override
    public void startElement(String uri,String localName, String qName, Attributes attributes)throws SAXException {
        if (qName.equalsIgnoreCase("article")) {
            articlebool = true;
        }else if (qName.equalsIgnoreCase("number")||qName.equalsIgnoreCase("ee")||qName.equalsIgnoreCase("crossref")||qName.equalsIgnoreCase("isbn")||qName.equalsIgnoreCase("publisher")||qName.equalsIgnoreCase("series")) {
            ignorebool = true;
        }
        else if (qName.equalsIgnoreCase("author")||qName.equalsIgnoreCase("editor")) {
            authorbool = true;
        } else if (qName.equalsIgnoreCase("title")||qName.equalsIgnoreCase("book")||qName.equalsIgnoreCase("www")||qName.equalsIgnoreCase("phdthesis")||qName.equalsIgnoreCase("inproceedings")||qName.equalsIgnoreCase("incollection")||qName.equalsIgnoreCase("proceedings")||qName.equalsIgnoreCase("mastersThesis")) {
            titlebool = true;
        }else if (qName.equalsIgnoreCase("pages")) {
            pagebool = true;
        }else if (qName.equalsIgnoreCase("year")) {
            yearbool = true;
        }else if (qName.equalsIgnoreCase("volume")) {
            volumebool = true;
        }else if (qName.equalsIgnoreCase("journal")||qName.equalsIgnoreCase("booktitle")) {
            journalbool = true;
        } else if (qName.equalsIgnoreCase("url")) {
            urlbool = true;
        }else if (qName.equalsIgnoreCase("dblp")) {
            //loading.setVisible(true);
        }
        else {
            ignorebool=true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("article")) {
            if(overall)
            {
                Database.allData.add(data);
                overall=false;
            }
            articlebool=false;
            ++c;
            if(c%10000==0)
            {
                bar.setValue(c);
                System.out.println((c/15233.94)+" %");
            }
        }
        if (qName.equalsIgnoreCase("dblp")) {
//        	bar.setValue(100);
            System.out.println("100 % "+Database.allData.size());
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (ignorebool)
        {
            ignorebool=false;
            return;
        }
        if (authorbool && articlebool) {
            String temp=new String(ch, start, length);
            overall = true;
            data=new Data();
            data.addAuthor(new String(ch, start, length));
            authorbool = false;
        } 
        else if (authorbool&& overall) {
            data.addAuthor(new String(ch, start, length));
            System.out.println("new author: " + new String(ch, start, length));
        }
        else if (titlebool&& overall) {
            titlebool = false;
            data.setTitle(new String(ch, start, length));
//             System.out.println("Title: " + new String(ch, start, length));
        }else if (pagebool&& overall) {
            pagebool = false;
            data.setPages(new String(ch, start, length));
//            System.out.println("pages: " + new String(ch, start, length));
        } else if (yearbool&& overall) {
            yearbool = false;
            try{
                data.setYear(Integer.parseInt(new String(ch, start, length)));
//                 System.out.println("Year: " + Integer.parseInt(new String(ch, start, length)));
            }catch (Exception e){};


        }else if (volumebool&& overall) {
            volumebool = false;
            data.setVolume(new String(ch, start, length));
//            System.out.println("volume: " + new String(ch, start, length));
        }else if (journalbool&& overall) {
            journalbool = false;
            data.setJournal_booktitle(new String(ch, start, length));
//            System.out.println("journal: " + new String(ch, start, length));
        } else if (urlbool&& overall) {
            urlbool = false;
            data.addUrl(new String(ch, start, length));
//            System.out.println("Url: " + new String(ch, start, length));
        }
    }
}
