package main;

import recursos.Cores;
import recursos.Imagens;
import recursos.view.Card;
import recursos.view.ModButton;
import recursos.view.ScreenSetup;

import javax.swing.*;
import java.awt.*;

public class TelaTeste extends ScreenSetup {
    public TelaTeste() {
        super("World of Warships - Resultado", 533, 340, Imagens.BACKGROUND_RESULTADO);

        adicionarCard();
        adicionarLabels();
        adicionarImagens();
        adicionarButtons();

        setVisible(true);
    }

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
