/**
 * The Main working class of the project. Enjoy!
 * @author Mridul Gupta | Divyanshu Talwar
 */

public class mainClass {
	
	/**
	 * Main working class
	 * @param String[] args
	 */
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


