package recursos.view;

// -- APIs --
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// -- Pacotes --
import recursos.Cores;

public class ModButton extends JButton {
    // -- Configuração Padrão [JButton] Simples --
    public ModButton(String texto, int x, int y, int width) {
        super(texto);
        setBounds(x, y, width, 35);
        setupCursor();
    }

    // -- Configuraçao Padrão [JButton] Azul --
    public ModButton(String texto, int x, int y, int width, int height) {
        super(texto);
        setBounds(x, y, width, height);
        setBackground(Cores.COLOR_AZUL);
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.WHITE));
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setBackground(Cores.COLOR_AZUL_CLARO);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Cores.COLOR_AZUL_CLARO);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Cores.COLOR_AZUL);
            }
        });
        setupCursor();
    }

    // -- Configuração Padrão [Cursor] do Mouse --
    private void setupCursor() {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}