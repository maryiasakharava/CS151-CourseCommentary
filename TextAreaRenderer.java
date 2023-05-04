import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;

public class TextAreaRenderer extends DefaultTableCellRenderer {
    private final JTextArea area = new JTextArea();

    public TextAreaRenderer() {
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
    }

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