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

public class TelaLogin extends ScreenSetup {

    private JTextField inputEmail;
    private JPasswordField inputPassword;

    public TelaLogin() {
        super("World of Warships - Sign In", 800, 565, Imagens.BACKGROUN_SIGN_IN);

        // -- Adicionar Componentes a View --
        adicionarCard();
        adicionarInputs();
        adicionarLabels();
        adicionarButtons();
        adicionarSeparador();
        adicionarLink();

        setVisible(true);
    }

    // -- Componentes --
    private void adicionarCard() {
        Card card = new Card();

        JLabel cardSuperior = new JLabel(card.gerarCardSuperior(355, 60));
        cardSuperior.setBounds(220, 90, 355, 60);
        add(cardSuperior, 0);

        JLabel cardInferior = new JLabel(card.gerarCardInferior(355, 330));
        cardInferior.setBounds(220, 150, 355, 330);
        add(cardInferior, 0);

        JLabel lblLogo = new JLabel(Imagens.LOGO_SUPERIOR);
        lblLogo.setBounds(347, 40, 100, 100);
        add(lblLogo, 0);
    }

    private void adicionarInputs() {
        inputEmail = new ModInput(240, 193, 315);
        add(inputEmail, 0);

        inputPassword = new JPasswordField();
        inputPassword.setHorizontalAlignment(JPasswordField.CENTER);
        inputPassword.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        inputPassword.setBounds(240, 265, 315, 35);
        add(inputPassword, 0);
    }

    private void adicionarLabels() {
        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setBounds(240, 170, 100, 20);
        lblEmail.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        add(lblEmail, 0);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(240, 243, 100, 20);
        lblPassword.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        add(lblPassword, 0);

        JLabel lblDivisor = new JLabel("OU");
        lblDivisor.setBounds(387, 355, 40, 20);
        add(lblDivisor, 0);
    }

    private void adicionarButtons() {
        JButton btnEntrar = new ModButton("ENTRAR", 240, 315, 315);
        btnEntrar.setBackground(Cores.COLOR_VERDE);
        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = inputEmail.getText().toLowerCase();
                String password = String.valueOf(inputPassword.getPassword());

                if(email.isEmpty() && password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "PREENCHA TODOS OS DADOS!", "≋ ATENÇÃO! ≋", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        Persistencia persistencia = new Persistencia();
                        PlayerControl playerControl = persistencia.recuperarController();

                        Player player = playerControl.autenticar(email, password);
                        dispose();
                        new TelaMenu(player);
                    } catch(Exception erro) {
                        JOptionPane.showMessageDialog(null, erro.getMessage(), "≋ ATENÇÃO! ≋", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });
        add(btnEntrar, 0);

        JButton btnCadastrar = new ModButton("CRIAR CONTA", 240, 380, 315);
        btnCadastrar.setBackground(Cores.COLOR_AZUL);
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TelaCadastro();
            }
        });
        add(btnCadastrar, 0);
    }

    private void adicionarSeparador() {
        JSeparator separadorHorizontal = new JSeparator(JSeparator.HORIZONTAL);
        separadorHorizontal.setBounds(240, 430, 315, 1);
        separadorHorizontal.setForeground(Color.GRAY);
        add(separadorHorizontal, 0);
    }

    private void adicionarLink() {
        JLabel lblLinkRecuperarPassword = new JLabel("NÃO CONSEGUIU ENTRAR?");
        lblLinkRecuperarPassword.setBounds(220, 445, 355, 20);
        lblLinkRecuperarPassword.setHorizontalAlignment(JLabel.CENTER);
        lblLinkRecuperarPassword.setForeground(Cores.COLOR_AZUL);
        lblLinkRecuperarPassword.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new TelaRecuperarConta();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblLinkRecuperarPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
                lblLinkRecuperarPassword.setForeground(Cores.COLOR_LARANJA);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblLinkRecuperarPassword.setForeground(new Color(5, 17, 41));
            }

        });
        add(lblLinkRecuperarPassword, 0);
    }
}
