public class mainClass {
    public static void main(String[] args){
        Parser p = new Parser();
        ParseEntityResolution per = new ParseEntityResolution();
        per.printData();
		System.out.println("hijhuhi");

        myPanel panel = new myPanel();
        myFrame frame= new myFrame(panel);
        frame.setLocationRelativeTo(null);
    }
}


