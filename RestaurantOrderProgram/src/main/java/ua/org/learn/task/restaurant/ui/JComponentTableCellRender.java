package ua.org.learn.task.restaurant.ui;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class JComponentTableCellRender implements TableCellRenderer {
    private TableCellRenderer defaultRenderer;
    public JComponentTableCellRender(TableCellRenderer renderer) {
        defaultRenderer = renderer;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(value instanceof Component)
            return (Component)value;
        return defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
