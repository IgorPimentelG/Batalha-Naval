package view;

// -- APIs --
import javax.swing.*;

// -- Pacotes --
import recursos.Imagens;
import recursos.view.ScreenSetup;

public class TelaSpash extends ScreenSetup {

    public TelaSpash() {
        super("World of Warships", 500, 281, Imagens.BACKGROUND_SPASH);
        setUndecorated(true);

        // -- Adicionar Logo --
        adicionarImagem();

        setVisible(true);
    }

    // -- Configuração Logo --
    public void adicionarImagem() {
        JLabel lblLogo = new JLabel(Imagens.LOGO);
        lblLogo.setBounds(0, 0, 500, 281);
        lblLogo.setHorizontalAlignment(JLabel.CENTER);
        lblLogo.setVerticalAlignment(JLabel.CENTER);
        add(lblLogo, 0);

    }

}
