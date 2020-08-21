package recursos.view;

import javax.swing.*;
import java.awt.*;

public class ModRotulo extends JLabel {

    public ModRotulo(String texto, int x, int y, int width, int height) {
        setText(texto);
        setBounds(x, y, width, height);
        setForeground(Color.WHITE);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
    }
}
