package recursos.view;

import recursos.Cores;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TableMod extends JTable {
    public TableMod(DefaultTableModel modeloTabela, int qntdColuna) {
        super(modeloTabela);

        DefaultTableCellRenderer centralizar = new DefaultTableCellRenderer();
        centralizar.setHorizontalAlignment(SwingConstants.CENTER);

        setRowHeight(20);
        setBackground(Cores.COLOR_AZUL_CLARO);
        setForeground(Color.WHITE);
        setSelectionBackground(Cores.COLOR_AZUL);
        setSelectionForeground(Color.WHITE);

        for(int i = 0; i < qntdColuna; i++) {
            getColumnModel().getColumn(i).setCellRenderer(centralizar);
        }
    }
}
