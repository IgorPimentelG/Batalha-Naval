package view;

// -- APIs --
import java.awt.Color;
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
        setUndecorated(true);

        this.player = player;

        // Adicionar Componentes a View
        adicionarCard();
        adicionarButton();
        adicionarMapa();
        adicionarLabel();

        setVisible(true);
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
        add(btnDesistir, 0);
    }

    private void adicionarMapa() {
        Mapa mapa = new Mapa();
        partida = new Partida(player, this, mapa);
        mapa.setPartida(partida);
        add(mapa.gerarMapa(), 0);
    }
}
