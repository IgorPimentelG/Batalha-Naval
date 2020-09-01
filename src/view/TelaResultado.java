package view;

// # APIs #
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// -- Pacotes --
import model.Player;
import recursos.Imagens;
import recursos.view.*;

public class TelaResultado extends ScreenSetup {

    private Player player;

    public TelaResultado(String vencedor, String pontuacao, Player player) {
        super("World of Warships - Resultado", 533, 340, Imagens.BACKGROUND_RESULTADO);

        this.player = player;

        // # Adicionar componentes a view #
        adicionarCard();
        adicionarLabels(vencedor, pontuacao);
        adicionarImagens();
        adicionarButtons();

        setVisible(true);
    }

    // # Configuração componentes #
    private void adicionarCard() {
        Card card = new Card();

        JLabel cardSuperior = card.getCardSuperior(90, 70,350, 50);
        add(cardSuperior, 0);

        JLabel cardInferior = card.getCardInferior(90, 120, 350, 150);
        add(cardInferior, 0);

        JLabel lblLogo = card.getIcone(90, 10, 350);
        add(lblLogo, 0);
    }

    private void adicionarLabels(String vencedor, String pontuacao) {
        JLabel lblTitulo = new ModRotulo("RESULTADO", 0, 130, 533, 20);
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        add(lblTitulo, 0);

        JLabel lblVencedor = new ModRotulo("VENCEDOR", 0, 155, 533, 20);
        lblVencedor.setHorizontalAlignment(JLabel.CENTER);
        add(lblVencedor, 0);

        JLabel lblPlayer = new ModRotulo(vencedor, 0, 173, 533, 14);
        lblPlayer.setHorizontalAlignment(JLabel.CENTER);
        lblPlayer.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        lblPlayer.setForeground(Color.GREEN);
        add(lblPlayer, 0);

        JLabel lblPontuacao = new ModRotulo(pontuacao, 0, 195, 533, 20);
        lblPontuacao.setHorizontalAlignment(JLabel.CENTER);
        add(lblPontuacao, 0);
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
        JButton btnContinuar = new ModButton("CONTINUAR", 190, 223, 150, 30);
        btnContinuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
                new TelaMenu(player);
            }
        });
        add(btnContinuar, 0);
    }

    // -- Inner Class --
    private class ModRotulo extends JLabel {
        private ModRotulo(String texto, int x, int y, int width, int height) {
            setText(texto);
            setBounds(x, y, width, height);
        }
    }
}