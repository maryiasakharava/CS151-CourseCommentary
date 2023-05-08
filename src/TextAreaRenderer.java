import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;

//text renderer class makes it so that the jtable which is used for displaying the reviews wraps texts
public class TextAreaRenderer extends DefaultTableCellRenderer {
    private final JTextArea area = new JTextArea();

    public TextAreaRenderer() {
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
    }

    //the values which are put into the jtable will be wrapped and the row and column heights will be adjusted in order to accommodate the data
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        area.setText((String) value);
        int rowHeight = table.getRowHeight(row);
        int colWidth = table.getColumnModel().getColumn(column).getWidth();
        area.setSize(colWidth, Short.MAX_VALUE);
        int preferredHeight = area.getPreferredSize().height;
        if (preferredHeight > rowHeight) {
            table.setRowHeight(row, preferredHeight);
        }
        return area;
    }
}
