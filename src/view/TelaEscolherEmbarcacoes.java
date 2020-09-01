package view;

// # APIs #
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// # Pacotes #
import recursos.Imagens;
import recursos.view.*;

public class TelaEscolherEmbarcacoes extends ScreenSetup {

    private TelaEditarFormacao telaEditarFormacao;
    private String qntdEmbarcacoes2PDisponiveis;
    private String qntdEmbarcacoes3PDisponiveis;

    public TelaEscolherEmbarcacoes(TelaEditarFormacao telaEditarFormacao, String qntdEmbarcacoes2PDisponiveis, String qntdEmbarcacoes3PDisponiveis) {

        super("Embarcações Disponíveis", 290, 285, Imagens.BACKGROUND_EMBARCACOES);

        setUndecorated(true);

        this.telaEditarFormacao = telaEditarFormacao;
        this.qntdEmbarcacoes2PDisponiveis = qntdEmbarcacoes2PDisponiveis;
        this.qntdEmbarcacoes3PDisponiveis = qntdEmbarcacoes3PDisponiveis;

        telaEditarFormacao.setVisible(false);

        // # Adicionar Componentes a View #
        adicionarCard();
        adicionarLabels();
        adicionarButtons();

        setVisible(true);
    }

    // # Componentes #
    private void adicionarCard() {
        Card card = new Card();

        JLabel cardSuperiorTitulo = card.getCardSuperior(35, 25,225, 50);
        add(cardSuperiorTitulo, 0);

        JLabel cardSuperiorBarco1P = card.getCardSuperior(35, 210, 100, 60);
        add(cardSuperiorBarco1P, 0);

        JLabel cardSuperiorBarco2P = card.getCardSuperior(158, 210, 100,60);
        add(cardSuperiorBarco2P, 0);
    }

    private void adicionarLabels() {
        JLabel lblEmbarcacoes = new JLabel("EMBARCAÇÕES");
        lblEmbarcacoes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        lblEmbarcacoes.setForeground(Color.WHITE);
        lblEmbarcacoes.setBounds(35, 40, 225, 20);
        lblEmbarcacoes.setHorizontalAlignment(JLabel.CENTER);
        add(lblEmbarcacoes, 0);

        JLabel lblQntdBarco2P = new ModLabel(this.qntdEmbarcacoes2PDisponiveis, 35, 215);
        add(lblQntdBarco2P, 0);

        JLabel lblDisponiveisB2P = new ModLabel("DISPONÍVEIS", 35, 232);
        add(lblDisponiveisB2P, 0);

        JLabel lblQntdBarco3P = new ModLabel(this.qntdEmbarcacoes3PDisponiveis, 158, 215);
        add(lblQntdBarco3P, 0);

        JLabel lblDisponiveisB3P = new ModLabel("DISPONÍVEIS", 158, 232);
        add(lblDisponiveisB3P, 0);
    }

    private void adicionarButtons() {
        JButton btnBarco2P = new JButton(Imagens.BARCO_2_PARTES);
        btnBarco2P.setOpaque(true);
        btnBarco2P.setBounds(35, 95, 100, 100);

        if(Integer.parseInt(qntdEmbarcacoes2PDisponiveis) == 0) {
            btnBarco2P.setEnabled(false);
        }

        btnBarco2P.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaEditarFormacao.setTamanhoEmbarcacaoAtual(2);
                telaEditarFormacao.setVisible(true);
                dispose();
            }
        });
        add(btnBarco2P, 0);

        JButton btnBarco3P = new JButton(Imagens.BARCO_3_PARTES);
        btnBarco3P.setOpaque(true);
        btnBarco3P.setBounds(158, 95, 100, 100);

        if(Integer.parseInt(qntdEmbarcacoes3PDisponiveis) == 0) {
            btnBarco3P.setEnabled(false);
        }

        btnBarco3P.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaEditarFormacao.setTamanhoEmbarcacaoAtual(3);
                telaEditarFormacao.setVisible(true);
                dispose();
            }
        });
        add(btnBarco3P, 0);
    }

    private class ModLabel extends JLabel {
    	public ModLabel(String texto, int x, int y) {
    		super(texto);
    		setForeground(Color.WHITE);
    		setHorizontalAlignment(JLabel.CENTER);
    		setBounds(x, y, 100, 20);
    	}
    	
    }
}
