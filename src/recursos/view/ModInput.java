package recursos.view;

// -- APIs --
import java.awt.Font;
import javax.swing.JTextField;

public class ModInput extends JTextField {
    public ModInput(int x, int y, int width) {
        // -- Configurações Padrão [JTextField] --
        setBounds(x, y, width, 35);
        setHorizontalAlignment(JTextField.CENTER);
        setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
    }
}