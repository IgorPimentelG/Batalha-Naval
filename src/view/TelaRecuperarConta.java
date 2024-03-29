package view;

// -- APIs --
import java.awt.*;
import java.awt.event.*;
import javax.mail.MessagingException;
import javax.swing.*;

// -- Pacotes --
import exceptions.DadosInvalidosException;
import recursos.*;
import recursos.email.EnviarEmail;
import recursos.view.*;

public class TelaRecuperarConta extends ScreenSetup {

    private JTextField inputEmail;

    public TelaRecuperarConta() {
        super("Recover Password", 600, 415, Imagens.BACKGROUN_RECUPERAR_PASSWORD);

        // -- Adicionar Componentes a View --
        adicionarCard();
        adicionarLabels();
        adicionarButtons();
        adicionarInput();

        setVisible(true);
    }

    // -- Componentes --
    private void adicionarCard() {
        Card card = new Card();

        JLabel cardSuperior = card.getCardSuperior(125, 90, 355, 60);
        add(cardSuperior, 0);

        JLabel cardInferior = card.getCardInferior(125, 150, 355, 185);
        add(cardInferior, 0);

        JLabel lblLogo = card.getIcone(125, 40, 355);
        add(lblLogo, 0);
    }

    private void adicionarLabels() {
        JLabel lblTitulo = new JLabel("♔   RECUPERE SUA CONTA ♔");
        lblTitulo.setBounds(125, 180, 355, 20);
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        add(lblTitulo, 0);

        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setBounds(145, 210, 100, 20);
        add(lblEmail, 0);
    }

    private void adicionarButtons() {
        JButton btnConfirmar = new ModButton("CONFIRMAR", 145, 280, 150);
        btnConfirmar.setBackground(new Color(102, 255, 153));
        btnConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new EnviarEmail(inputEmail.getText()).recuperacaoConta();

                    JOptionPane.showMessageDialog(null,
                            "SENHA ALTERADA COM SUCESSO! VERIFIQUE SEU E-MAIL.",
                            "♔ SUCESSO!♔", JOptionPane.WARNING_MESSAGE);

                    dispose();
                    new TelaLogin();
                } catch (DadosInvalidosException dadosInvalidosException) {
                    JOptionPane.showMessageDialog(null,
                            dadosInvalidosException.getMessage(),
                            "♔ OPERAÇÃO INVÁLIDA!♔", JOptionPane.ERROR_MESSAGE);
                } catch (MessagingException messagingException) {
                    messagingException.printStackTrace();
                }
            }
        });
        add(btnConfirmar, 0);

        JButton btnCancelar = new ModButton("⇖ CANCELAR", 310, 280, 150);
        btnCancelar.setBackground(new Color(5, 17, 41));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TelaLogin();
            }
        });
        add(btnCancelar, 0);
    }

    private void adicionarInput() {
        inputEmail = new ModInput(145, 232, 315);
        add(inputEmail, 0);
    }

}