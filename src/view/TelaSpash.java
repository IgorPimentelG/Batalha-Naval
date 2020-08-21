package view;

// -- APIs --
import javax.swing.*;

// -- Pacotes --
import recursos.Imagens;
import recursos.view.ScreenSetup;

import java.awt.*;

public class TelaSpash extends ScreenSetup {

    public TelaSpash() {
        super("World of Warships", 350, 281, Imagens.BACKGROUND_SPASH);
        setUndecorated(true);
        super.setBackground(new Color(0, 0,0, 0.0f));

        setVisible(true);
    }
}
