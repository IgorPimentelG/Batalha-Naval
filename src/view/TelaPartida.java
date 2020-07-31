package view;

// -- APIs --
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// -- Pacotes --
import model.*;
import recursos.view.*;
import recursos.*;

public class TelaPartida extends ScreenSetup {

    private Player player;
    private Partida partida;

    private  JLabel lblTitulo;

    public TelaPartida(Player player) {
        super("World of Warships - Batalhe!", 585, 505, Imagens.BACKGROUND_GAME);
        setUndecorated(true);           // Remover barra de titulo

        this.player = player;

        // Adicionar Componentes a View
        adicionarCard();
        adicionarButton();
        adicionarMapa();
        adicionarLabel();

        new TelaResultado();

      //  setVisible(true);
    }

    // -- Componentes --
    private void adicionarCard() {
        Card card = new Card();

        JLabel cardViewSuperior = new JLabel(card.gerarCardSuperior(380,60));
        cardViewSuperior.setBounds(100, 30, 380, 60);
        add(cardViewSuperior, 0);

        // Logo
        JLabel lblLogoEsquerdo = new JLabel(Imagens.LOGO_SUPERIOR);
        lblLogoEsquerdo.setBounds(25, 15, 100, 100);
        add(lblLogoEsquerdo, 0);

        JLabel lblLogoDireito = new JLabel(Imagens.LOGO_SUPERIOR);
        lblLogoDireito.setBounds(460, 15, 100, 100);
        add(lblLogoDireito, 0);
    }

    private void adicionarLabel() {

        lblTitulo = new JLabel(partida.getDesafiante().getNickname() + " [" + partida.getDesafiante().getPontuacao()
                + "] x [" + partida.getComputador().getPontuacao() + "]" + "Computador");

        lblTitulo.setBounds(100, 50, 380, 20);
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        add(lblTitulo, 0);
    }

    public void atualizarPlacar() {
        remove(lblTitulo);
        adicionarLabel();
    }

    private void adicionarButton() {
        JButton btnDesistir = new JButton("✖ DESISTIR ✖");
        btnDesistir.setBackground(Cores.COLOR_LARANJA);
        btnDesistir.setForeground(Color.WHITE);
        btnDesistir.setBounds(220, 80, 150, 25);
        btnDesistir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                
            }
        });
        add(btnDesistir, 0);
    }

    private void adicionarMapa() {
        Mapa mapa = new Mapa();
        partida = new Partida(player, this, mapa);
        mapa.setPartida(partida);
        add(mapa.gerarMapa(), 0);
    }

    // -- Inner Class --
    private class TelaResultado extends ScreenSetup {

        public TelaResultado() {
            super("World of Warships - Resultado", 533, 340, Imagens.BACKGROUND_RESULTADO);

            // -- Adicionar componentes a view --
            adicionarCard();
            adicionarLabels();
            adicionarImagens();
            adicionarButtons();

            setVisible(true);
        }

        // -- Configuração componentes --
        private void adicionarCard() {
            Card card = new Card();

            JLabel cardSuperior = new JLabel(card.gerarCardSuperior(350, 50));
            cardSuperior.setBounds(90, 70, 350, 50);
            add(cardSuperior, 0);

            JLabel cardInferior = new JLabel(card.gerarCardInferior(350, 150));
            cardInferior.setBounds(90, 120, 350, 150);
            add(cardInferior, 0);

            JLabel lblLogo = new JLabel(Imagens.LOGO_SUPERIOR);
            lblLogo.setBounds(215, 10, 100, 100);
            add(lblLogo, 0);
        }

        private void adicionarLabels() {
            JLabel lblTitulo = new JLabel("RESULTADO");
            lblTitulo.setHorizontalAlignment(JLabel.CENTER);
            lblTitulo.setBounds(0, 130, 533, 20);
            lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
            add(lblTitulo, 0);

            JLabel lblVencedor = new JLabel("VENCEDOR");
            lblVencedor.setHorizontalAlignment(JLabel.CENTER);
            lblVencedor.setBounds(0, 170, 533, 20);
            add(lblVencedor, 0);

            JLabel lblPlayer = new JLabel("igor-pimentel");
            lblPlayer.setHorizontalAlignment(JLabel.CENTER);
            lblPlayer.setBounds(0, 185, 533, 20);
            lblPlayer.setForeground(Color.GREEN);
            add(lblPlayer, 0);
        }

        private void adicionarImagens() {
            JLabel lblEspadaEsquerda = new JLabel(Imagens.ICON_ESPADA);
            lblEspadaEsquerda.setBounds(120, 170, 50, 50);
            add(lblEspadaEsquerda, 0);

            JLabel lblEspadaDireito = new JLabel(Imagens.ICON_ESPADA);
            lblEspadaDireito.setBounds(360,170, 50, 50);
            add(lblEspadaDireito, 0);
        }

        private void adicionarButtons() {
            JButton btnContinuar = new ModButton("CONTINUAR", 190, 220, 150, 30);
            add(btnContinuar, 0);
        }

    }

}