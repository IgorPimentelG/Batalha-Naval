package view;

// # APIs #
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

// Pacotes
import controller.PlayerControl;
import model.*;
import persistencia.Persistencia;
import recursos.*;
import recursos.view.*;

public class TelaEditarFormacao extends ScreenSetup {

    private TelaEscolherEmbarcacoes telaEscolherEmbarcacoes;
    private TelaEditarFormacao telaEditarFormacao = this;

    private Player player;
    private Mapa mapa;

    public TelaEditarFormacao(Player player)  {
        super("Editar Formação", 585, 505, Imagens.BACKGROUND_GAME);
        setUndecorated(true);

        mapa = new Mapa(telaEscolherEmbarcacoes, telaEditarFormacao);
        this.player = player;

        // # Adicionar Componentes a View #
        adicionarCard();
        adicionarLabel();
        adicionarButtons();
        adicionarMapa();

        // # Configurar Mapa Caso já exista uma Formação salva #
        if(player.getFormacao().size() > 0) {
            mapa.getEditarFormacao().setFormacao(player.getFormacao());
            configurarMapa();

            mapa.getEditarFormacao().setQntdEmbarcacoes2PDisponivel(0);
            mapa.getEditarFormacao().setQntdEmbarcacoes3PDisponivel(0);

            setVisible(true);
        } else {
            telaEscolherEmbarcacoes = new TelaEscolherEmbarcacoes(this,
                    String.valueOf(mapa.getEditarFormacao().getQntdEmbarcacoes2PDisponivel()),
                    String.valueOf(mapa.getEditarFormacao().getQntdEmbarcacoes2PDisponivel()));
        }
    }

    // # Componentes #
    private void adicionarCard() {
        Card card = new Card();

        JLabel cardViewSuperior = card.getCardSuperior(100, 30,380,60);
        add(cardViewSuperior, 0);

        // # Logo #
        JLabel lblLogoEsquerdo = card.getIconeLateral(25, 15);
        add(lblLogoEsquerdo, 0);

        JLabel lblLogoDireito = card.getIconeLateral(460, 15);
        add(lblLogoDireito, 0);
    }

    private void adicionarLabel() {
        JLabel lblTitulo = new ModRotulo("FORMAÇÃO PADRÃO", 100, 50, 380, 20);
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitulo, 0);
    }

    private void adicionarButtons() {
        JButton btnSalvar = new JButton("SALVAR");
        btnSalvar.setBackground(Cores.COLOR_LARANJA);
        btnSalvar.setBounds(140, 80, 150, 25);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(mapa.getEditarFormacao().getQntdEmbarcacoes2PDisponivel() == 0 && mapa.getEditarFormacao().getQntdEmbarcacoes3PDisponivel() == 0) {
                    Persistencia persistencia = new Persistencia();
                    PlayerControl playerControl = persistencia.recuperarController();

                    playerControl.salvarFormacao(player, mapa.getEditarFormacao().getFormacao());

                    JOptionPane.showMessageDialog(null, "FORMAÇÃO SALVA COM SUCESSO!", "♔ AVISO♔", JOptionPane.INFORMATION_MESSAGE);

                    dispose();

                    new TelaMenu(playerControl.pesquisarPlayer(player));
                } else {
                    JOptionPane.showMessageDialog(null, "ADICIONE TODAS AS EMBARCACÕES DISPONÍVEIS!", "♔ ATENÇÃO!♔", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        add(btnSalvar, 0);

        JButton btnLimpar = new JButton("LIMPAR");
        btnLimpar.setBackground(Cores.COLOR_VERDE);
        btnLimpar.setBounds(297, 80, 150, 25);
        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mapa.getEditarFormacao().setFormacao(new ArrayList<List<String>>());
                player.setFormacao(mapa.getEditarFormacao().getFormacao());
                dispose();
                new TelaEditarFormacao(player);
            }
        });
        add(btnLimpar, 0);
    }

    private void adicionarMapa() {
        add(mapa.gerarMapa(), 0);
    }

    private void configurarMapa() {
        for(List<JButton> linha : mapa.getMatrizButton()) {
            List<JButton> celulas = linha;

            for(JButton celula : celulas) {
                celula.setEnabled(false);

                for(List<String> embarcacoes : mapa.getEditarFormacao().getFormacao()) {
                    List<String> embarcacao = embarcacoes;

                    for(String parte : embarcacao) {
                        if(celula.getText().equals(parte)) {
                            celula.setText("");
                            celula.setIcon(Imagens.ICON_PIRATA);
                        }
                    }
                }
            }
        }
    }

    public void setTamanhoEmbarcacaoAtual(int tamanhoEmbarcaoAtual) {
        mapa.getEditarFormacao().setTamanhoEmbarcacaoAtual(tamanhoEmbarcaoAtual);
    }
}
