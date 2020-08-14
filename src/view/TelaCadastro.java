package view;

// -- APIs --
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// -- Pacotes --
import controller.PlayerControl;
import model.Player;
import persistencia.Persistencia;
import recursos.*;
import recursos.view.*;

public class TelaCadastro extends ScreenSetup {

    private JTextField inputNickname;
    private JTextField inputEmail;
    private JPasswordField inputPassword;
    private JPasswordField inputConfirmPassword;

    public TelaCadastro() {
        super("World of Warships - Sign Up", 800, 580, Imagens.BACKGROUN_SIGN_UP);

        // -- Adicionar Componentes a View --
        adicionarCard();
        adicionarLabels();
        adicionarInputs();
        adicionarButtons();

        setVisible(true);
    }

    // -- Componentes --
    private void adicionarCard() {
        Card card = new Card();

        JLabel cardSuperior = new JLabel(card.gerarCardSuperior(355, 60));
        cardSuperior.setBounds(220, 90, 355, 60);
        add(cardSuperior, 0);

        JLabel cardInferior = new JLabel(card.gerarCardInferior(355, 345));
        cardInferior.setBounds(220, 150, 355, 345);
        add(cardInferior, 0);

        JLabel lblLogo = new JLabel(Imagens.LOGO_SUPERIOR);
        lblLogo.setBounds(347, 40, 100, 100);
        add(lblLogo, 0);
    }

    private void adicionarLabels() {
        JLabel lblNickname = new JLabel("Nickname:");
        lblNickname.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        lblNickname.setBounds(240, 160, 100, 20);
        add(lblNickname, 0);

        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        lblEmail.setBounds(240, 232, 100, 20);
        add(lblEmail, 0);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        lblPassword.setBounds(240, 304, 100, 20);
        add(lblPassword, 0);

        JLabel lblConfirmPassword = new JLabel("Confirm Password:");
        lblConfirmPassword.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        lblConfirmPassword.setBounds(240, 376, 200, 20);
        add(lblConfirmPassword, 0);
    }

    private void adicionarInputs() {
        inputNickname = new ModInput(240, 182, 315);
        add(inputNickname, 0);

        inputEmail = new ModInput(240, 254, 315);
        add(inputEmail, 0);

        inputPassword = new JPasswordField();
        inputPassword.setHorizontalAlignment(JTextField.CENTER);
        inputPassword.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        inputPassword.setBounds(240, 325, 315, 35);
        add(inputPassword, 0);

        inputConfirmPassword = new JPasswordField();
        inputConfirmPassword.setHorizontalAlignment(JTextField.CENTER);
        inputConfirmPassword.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        inputConfirmPassword.setBounds(240, 398, 315, 35);
        add(inputConfirmPassword, 0);
    }

    private void adicionarButtons() {
        JButton btnCadastrar = new ModButton("REGISTRA-SE", 240, 445, 150);
        btnCadastrar.setBackground(Cores.COLOR_VERDE);
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {

                // Dados
                String nickname = inputNickname.getText();
                String email = inputEmail.getText().toLowerCase();
                String password = String.valueOf(inputPassword.getPassword());

                // Validação dos Dados
                if(!(nickname.isEmpty() && email.isEmpty() && password.isEmpty())) {
                    if(password.length() < 8) {
                        JOptionPane.showMessageDialog(null, "A SENHA NECESSITA NO MÁNIMO 8 CARACTERES!", "≋ SENHA CURTA! ≋", JOptionPane.WARNING_MESSAGE);
                        inputPassword.setText("");
                        inputConfirmPassword.setText("");
                    } else if(nickname.contains(" ")) {
                        JOptionPane.showMessageDialog(null, "NICKNAME NÃO PODE CONTER ESPAÇOS!", "≋ NICKNAME INVÁLIDO! ≋", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if(password.equals(String.valueOf(inputConfirmPassword.getPassword()))) {

                            PlayerControl playerControl = new Persistencia().recuperarController();

                            try {
                                Player player = playerControl.cadastrarUsuario(nickname, email, password);

                                JOptionPane.showMessageDialog(null, "CADASTRO REALIZADO COM SUCESSO!", "≋ SEJA BEM-VINDO ≋", JOptionPane.INFORMATION_MESSAGE);
                                dispose();
                                JOptionPane.showMessageDialog(null, "<html><center>AGORA VAMOS DEFINIR SUA FORMAÇÃOO PADRÃO PARA SUAS BATALHAS!"
                                        + "<br>ALTERE A QUALQUER MOMENTO EM [MENU - SETTINGS - EDITAR FORMAÇÃO]</center></html>", "≋ SEJA BEM-VINDO ≋", JOptionPane.INFORMATION_MESSAGE);
                                new TelaEditarFormacao(player);
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e.getMessage(), "≋ ATENÇÃO! ≋", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "SENHAS INCORRETAS!", "≋ ATENÇÃO! ≋", JOptionPane.ERROR_MESSAGE);
                            inputPassword.setText("");
                            inputConfirmPassword.setText("");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "PREENCHA TODOS OS DADOS!", "≋ ATENÇÃO! ≋", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        add(btnCadastrar, 0);

        JButton btnCancelar = new ModButton("⇖ CANCELAR", 405,  445, 150);
        btnCancelar.setBackground(Cores.COLOR_AZUL);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                dispose();
                new TelaLogin();
            }
        });
        add(btnCancelar, 0);
    }

}
