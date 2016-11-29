import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ResultPanel
{
    private JScrollPane pane;
    private static JTable table;


    private static Object[][] rowData={
            {" "," "," "," "," "," "," "," "}
    };

    private static String columnNames[] = { "S.No.","Title","Author(s)" ,"Year", "Volume","Pages","Journal/Booktitle","Url" };

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
        String columnNames[] = { "S.No.","Title","Author(s)" ,"Year", "Volume","Pages","Journal/Booktitle","Url"  };
        table=new JTable(rowData,columnNames);
        table.setRowHeight(27);
        pane=new JScrollPane(table);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    }



}
