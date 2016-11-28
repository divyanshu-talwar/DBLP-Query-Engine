import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ResultPanel
{
    private JScrollPane pane;
    private static JTable table;


    private static Object[][] rowData={
            {" "," "," "," "," "," "," "}
    };

    private static String columnNames[] = { "title","author" ,"year", "volume","pages","journal/booktitle","url" };

    public ResultPanel()
    {
        buildGui();
    }

    public JScrollPane getPane()
    {
        return pane;
    }

    public static void updateData(Object[][] _data,String[] colData)
    {
        columnNames=colData;
        rowData=_data;
    }

    public static void updateTable()
    {

        DefaultTableModel tm = new DefaultTableModel(rowData, columnNames);
        table.setModel(tm);
    }

    private void buildGui()
    {
        String columnNames[] = { "title","author" ,"year", "volume","pages","journal/booktitle","url" };
        table=new JTable(rowData,columnNames);
        pane=new JScrollPane(table);
    }



}
