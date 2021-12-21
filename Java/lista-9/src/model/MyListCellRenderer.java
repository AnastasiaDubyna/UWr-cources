package model;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MyListCellRenderer implements ListCellRenderer {

//    protected static TitledBorder focusBorder = new TitledBorder(LineBorder.createGrayLineBorder(), "title");
    protected static Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
    Month month;

    public MyListCellRenderer(Month month) {
        this.month = month;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        list.setVisibleRowCount(month.getRowsCount()+1);
        renderer.setBorder(border);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        renderer.setForeground(Color.black);
        if ((index + 1) % 7 == 0) {
            renderer.setForeground(Color.red);
        }
        return renderer;
    }

}
