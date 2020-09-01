package view;

// # Pacotes #
import recursos.Imagens;
import recursos.view.ScreenSetup;

// # APIs #
import java.awt.*;
import javax.swing.JLabel;

public class TelaSpash extends ScreenSetup {

    public TelaSpash() {
        super("World of Warships", 350, 350, Imagens.BACKGROUND_SPASH);
        
        setUndecorated(true);
        setBackground(new Color(0, 0,0, 0.0f));
        
        adicionarLabel();

        setVisible(true);
    }
    
    private void adicionarLabel() {
    	JLabel lblSobre = new JLabel("Versão 1.0");
    	lblSobre.setBounds(0, 235, 350, 80);
    	lblSobre.setHorizontalAlignment(JLabel.CENTER);
    	lblSobre.setForeground(Color.WHITE);
    	lblSobre.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
    	add(lblSobre, 0);
    }
}
