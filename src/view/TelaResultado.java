package view;

// -- APIs --
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

        // -- Adicionar componentes a view --
        adicionarCard();
        adicionarLabels(vencedor, pontuacao);
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

    private void adicionarLabels(String vencedor, String pontuacao) {
        JLabel lblTitulo = new JLabel("RESULTADO");
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setBounds(0, 130, 533, 20);
        lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        add(lblTitulo, 0);

        JLabel lblVencedor = new JLabel("VENCEDOR");
        lblVencedor.setHorizontalAlignment(JLabel.CENTER);
        lblVencedor.setBounds(0, 155, 533, 20);
        add(lblVencedor, 0);

        JLabel lblPlayer = new JLabel(vencedor);
        lblPlayer.setHorizontalAlignment(JLabel.CENTER);
        lblPlayer.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        lblPlayer.setBounds(0, 173, 533, 20);
        lblPlayer.setForeground(Color.GREEN);
        add(lblPlayer, 0);

        JLabel lblPontuacao = new JLabel(pontuacao);
        lblPontuacao.setHorizontalAlignment(JLabel.CENTER);
        lblPontuacao.setBounds(0, 195, 533, 20);
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
}