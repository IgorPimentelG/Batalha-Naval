package recursos.view;

// -- APIs --
import javax.swing.*;

// -- Pacotes --
import recursos.Imagens;

public class ScreenSetup extends JFrame {
    public ScreenSetup(String titulo, int width, int height, ImageIcon background) {
        // -- Configurações padrão para [JFrame] --
        setTitle(titulo);
        setSize(width, height);
        setLayout(null);
        setContentPane(new JLayeredPane());
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(Imagens.LOGO_SUPERIOR.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        adicionarBackground(width, height, background);
    }

    private void adicionarBackground(int width, int height, ImageIcon background) {
        JLabel lblBackground = new JLabel(background);
        lblBackground.setBounds(0, 0, width, height);
        add(lblBackground, 2);
    }
}
