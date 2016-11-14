public class mainClass {
    public static void main(String[] args){
        Parser p = new Parser();
        myPanel panel = new myPanel();
        myFrame frame= new myFrame(panel);
        frame.setLocationRelativeTo(null);
    }
}


