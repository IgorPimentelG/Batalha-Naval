package recursos.view;

// -- APIs --
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

// -- Pacotes --
import recursos.Cores;

public class Card {
    public ImageIcon gerarCardSuperior(int width, int height) {
        // -- Instância do Buffer da Imagem --
        BufferedImage cardSuperior = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics drawCardSuperior = cardSuperior.createGraphics();

        // -- Gerar Forma do Retângulo --
        drawCardSuperior.setColor(Cores.COLOR_AZUL);
        drawCardSuperior.fillRect(0, 0, width, height);

        return new ImageIcon(cardSuperior);
    }

    public ImageIcon gerarCardInferior(int width, int height) {
        // -- Instância do Buffer da Imagem --
        BufferedImage cardInferior = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics drawCardInferior = cardInferior.createGraphics();

        // -- Gerar Forma do Retângulo --
        drawCardInferior.setColor(Color.WHITE);
        drawCardInferior.fillRect(0, 0, width, height);

        return new ImageIcon(cardInferior);
    }
}
