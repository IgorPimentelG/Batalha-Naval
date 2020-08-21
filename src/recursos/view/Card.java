package recursos.view;

// -- APIs --
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

// -- Pacotes --
import recursos.Cores;
import recursos.Imagens;

public class Card {
    private ImageIcon gerarCardSuperior(int width, int height) {
        // -- Instância do Buffer da Imagem --
        BufferedImage cardSuperior = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics drawCardSuperior = cardSuperior.createGraphics();

        // -- Gerar Forma do Retângulo --
        drawCardSuperior.setColor(Cores.COLOR_AZUL);
        drawCardSuperior.fillRect(0, 0, width, height);

        return new ImageIcon(cardSuperior);
    }

    private ImageIcon gerarCardInferior(int width, int height) {
        // -- Instância do Buffer da Imagem --
        BufferedImage cardInferior = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics drawCardInferior = cardInferior.createGraphics();

        // -- Gerar Forma do Retângulo --
        drawCardInferior.setColor(Color.WHITE);
        drawCardInferior.fillRect(0, 0, width, height);

        return new ImageIcon(cardInferior);
    }

    public JLabel getCardSuperior(int x, int y, int width, int height) {
        JLabel cardSuperior = new JLabel(gerarCardSuperior(width, height));
        cardSuperior.setBounds(x, y, width, height);
        return cardSuperior;
    }

    public JLabel getCardInferior(int x, int y, int width, int height) {
        JLabel cardInferior = new JLabel(gerarCardInferior(width, height));
        cardInferior.setBounds(x, y, width, height);
        return cardInferior;
    }

    public JLabel getIcone(int x, int y, int width) {
        JLabel icone = new JLabel(Imagens.LOGO_SUPERIOR);
        icone.setBounds(x, y, width, 100);
        icone.setHorizontalAlignment(JLabel.CENTER);
        return icone;
    }

    public JLabel getIconeLateral(int x, int y) {
        JLabel icone = new JLabel(Imagens.LOGO_SUPERIOR);
        icone.setBounds(x, y, 100, 100);
        return icone;
    }
}
