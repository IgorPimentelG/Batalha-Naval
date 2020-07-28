package view;

// -- APIs --
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// -- Pacotes --
import model.*;
import recursos.Imagens;
import recursos.view.*;

public class TelaMenu extends ScreenSetup {

    private Player player;

    public TelaMenu(Player player) {
        super("World of Warships - Menu", 550, 405, Imagens.BACKGROUN_SIGN_MENU);

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

        JLabel cardSuperior = new JLabel(card.gerarCardSuperior(350, 60));
        cardSuperior.setBounds(100, 30, 350, 60);
        add(cardSuperior, 0);

        JLabel lblLogoEsquerdo = new JLabel(Imagens.LOGO_SUPERIOR);
        lblLogoEsquerdo.setBounds(25, 15, 100, 100);
        add(lblLogoEsquerdo, 0);

        JLabel lblLogoDireito = new JLabel(Imagens.LOGO_SUPERIOR);
        lblLogoDireito.setBounds(430, 15, 100, 100);
        add(lblLogoDireito, 0);
    }

    private void adicionarButtons() {
        JButton btnPlayerVsPlayer = new ModButton("PLAYER VS PLAYER", 130, 110, 300, 45);
        add(btnPlayerVsPlayer, 0);

        JButton btnPlayerVsComputador = new ModButton("PLAYER VS COMPUTADOR", 130, 175, 300, 45);
        btnPlayerVsComputador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TelaPartida(new Partida(player));
                dispose();
            }
        });
        add(btnPlayerVsComputador,0);

        JButton btnEnviarConvite = new ModButton("ENVIAR CONVITE", 130, 240, 300, 45);
        btnEnviarConvite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TelaEnviarConvite(player);
            }
        });
        add(btnEnviarConvite, 0);

        JButton btnSettings = new ModButton("SETTINGS", 130, 305, 300, 45);
        btnSettings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                new TelaSettings(player);
            }
        });
        add(btnSettings, 0);
    }

    private void adicionarLabels() {
        JLabel lblTitulo = new JLabel("MENU");
        lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        lblTitulo.setBounds(250, 50, 100, 20);
        lblTitulo.setForeground(Color.WHITE);
        add(lblTitulo, 0);
    }


    // -- INNER CLASS --
    private class TelaSettings extends ScreenSetup {

        private Player player;

        public TelaSettings(Player player) {
            super("World of Warships - Settings", 550, 405, Imagens.BACKGROUN_SIGN_MENU);

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

            JLabel cardSuperior = new JLabel(card.gerarCardSuperior(350, 60));
            cardSuperior.setBounds(100, 30, 350, 60);
            add(cardSuperior, 0);

            JLabel lblLogoEsquerdo = new JLabel(Imagens.LOGO_SUPERIOR);
            lblLogoEsquerdo.setBounds(25, 15, 100, 100);
            add(lblLogoEsquerdo, 0);

            JLabel lblLogoDireito = new JLabel(Imagens.LOGO_SUPERIOR);
            lblLogoDireito.setBounds(430, 15, 100, 100);
            add(lblLogoDireito, 0);
        }

        private void adicionarButtons() {
            JButton btnEditarFormacao = new ModButton("EDITAR FORMAÇÃO", 130, 110, 300, 45);
            btnEditarFormacao.addActionListener(new ActionListener()  {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new TelaEditarFormacao(player);
                }
            });
            add(btnEditarFormacao, 0);

            JButton btnHistico = new ModButton("HISTÓRICO", 130, 175, 300, 45);
            btnHistico.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new TelaHistorico();
                }
            });
            add(btnHistico,0);

            JButton btnExcluirConta = new ModButton("EXCLUIR CONTA", 130, 240, 300, 45);
            add(btnExcluirConta, 0);

            JButton btnVoltar = new ModButton("⇖ VOLTAR", 130, 305, 300, 45);
            btnVoltar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new TelaMenu(player);
                }
            });
            add(btnVoltar, 0);
        }

        private void adicionarLabels() {
            JLabel lblTitulo = new JLabel("SETTINGS");
            lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            lblTitulo.setBounds(240, 50, 100, 20);
            lblTitulo.setForeground(Color.WHITE);
            add(lblTitulo, 0);
        }
    }
}

