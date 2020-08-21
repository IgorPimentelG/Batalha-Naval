package view;

// -- APIs --
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

// -- Pacotes --
import controller.PlayerControl;
import model.*;
import persistencia.Persistencia;
import recursos.Imagens;
import recursos.view.*;

public class TelaMenu extends ScreenSetup {

    private Player player;

    public TelaMenu(Player player) {
        super("World of Warships - Menu", 550, 470, Imagens.BACKGROUN_MENU);

        this.player = player;

        // -- Adicionar Componentes a View --
        adicionarCard();
        adicionarButtons();
        adicionarLabels();

        setVisible(true);
    }

    // -- Componentes --
    private void adicionarCard() {
        Card card = new Card();

        JLabel cardSuperior = card.getCardSuperior(100, 30, 350, 60);
        add(cardSuperior, 0);

        JLabel lblLogoEsquerdo = card.getIconeLateral(25, 15);
        add(lblLogoEsquerdo, 0);

        JLabel lblLogoDireito = card.getIconeLateral(430, 15);
        add(lblLogoDireito, 0);
    }

    private void adicionarButtons() {
        JButton btnPlayerVsPlayer = new ModButton("PLAYER VS PLAYER", 130, 110, 300, 45);
        btnPlayerVsPlayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new TelaEnviarConvite(player);
                dispose();
            }
        });
        add(btnPlayerVsPlayer, 0);

        JButton btnPlayerVsComputador = new ModButton("PLAYER VS COMPUTADOR", 130, 175, 300, 45);
        btnPlayerVsComputador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "A PARTIDA IRÁ COMEÇAR EM BREVE...", "≋ BATALHE! ≋", JOptionPane.WARNING_MESSAGE);
                new TelaPartida(player);
                dispose();
            }
        });
        add(btnPlayerVsComputador,0);

        JButton btnEditarFormacao = new ModButton("EDITAR FORMAÇÃO", 130, 240, 300, 45);
        btnEditarFormacao.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TelaEditarFormacao(player);
            }
        });
        add(btnEditarFormacao, 0);

        JButton btnHistico = new ModButton("HISTÓRICO", 130, 305, 300, 45);
        btnHistico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TelaHistorico(player);
            }
        });
        add(btnHistico,0);

        JButton btnExcluirConta = new ModButton("EXCLUIR CONTA", 130, 370, 300, 45);
        btnExcluirConta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlayerControl playerControl = new Persistencia().recuperarController();

                try {
                    playerControl.excluirConta(player);
                    JOptionPane.showMessageDialog(null, "SUA CONTA FOI EXCLUÍDA COM SUCESSO!", "≋ ATÉ LOGO! ≋", JOptionPane.WARNING_MESSAGE);
                    dispose();
                    new TelaLogin();
                } catch (Exception exception) {
                    System.out.println(Arrays.toString(exception.getStackTrace()));
                    JOptionPane.showMessageDialog(null, "NÃO FOI POSSÍVEL EXCLUÍR SUA CONTA. TENTE NOVAMENTE.", "≋ OPERAÇÃO INVÁLIDA! ≋", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        add(btnExcluirConta, 0);
    }

    private void adicionarLabels() {
        JLabel lblTitulo = new ModRotulo("MENU", 250, 50, 100, 20);
        add(lblTitulo, 0);
    }
}

