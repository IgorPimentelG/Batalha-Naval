package view;

// -- APIs --
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// -- Pacotes --
import controller.PlayerControl;
import model.*;
import persistencia.Persistencia;
import recursos.view.*;
import recursos.*;

public class TelaPartida extends ScreenSetup {

    private Player desafiante;
    private Player desafiado;
    private Partida partida;
    private JLabel lblTitulo;
    private Mapa mapa;

    public TelaPartida(Player desafiante) {
        super("World of Warships - Batalhe!", 585, 505, Imagens.BACKGROUND_GAME);

        this.desafiante = desafiante;

        partida = new Partida(desafiante, this);

        configuracaoScreen();
    }

    public TelaPartida(Player desafiante, Player desafiado) {
        super("World of Warships - Batalhe!", 585, 505, Imagens.BACKGROUND_GAME);

        this.desafiante = desafiante;
        this.desafiado  = desafiado;

        partida = new Partida(desafiante, desafiado, this);

        configuracaoScreen();
    }

    private void configuracaoScreen() {
        // Adicionar Componentes a View
        adicionarCard();
        adicionarButton();
        adicionarMapa();
        adicionarLabel();

        setUndecorated(true);           // Remover barra de titulo

        setVisible(true);
    }

    // -- Componentes --
    private void adicionarCard() {
        Card card = new Card();

        JLabel cardViewSuperior = new JLabel(card.gerarCardSuperior(380, 60));
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
        lblTitulo = new JLabel("〘 BATTLE 〙");
        lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        lblTitulo.setBounds(100, 50, 380, 20);
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        add(lblTitulo, 0);
    }

    private void adicionarButton() {
        JButton btnDesistir = new JButton("✖ DESISTIR ✖");
        btnDesistir.setBackground(Cores.COLOR_LARANJA);
        btnDesistir.setForeground(Color.WHITE);
        btnDesistir.setBounds(220, 80, 150, 25);
        btnDesistir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                PlayerControl control = new Persistencia().recuperarController();

                String vencedor = "";

                dispose();

                control.salvarPontuacaoPerda(desafiante, 5);

                if(desafiado == null) {
                    vencedor = "COMPUTADOR";
                } else {
                   vencedor = desafiado.getNickname();
                   control.salvarPontuacaoGanha(desafiado, 2);
                }

                new TelaResultado(vencedor, "VOCÊ PERDEU -5 PONTOS!", desafiante);
            }
        });
        add(btnDesistir, 0);
    }

    private void adicionarMapa() {
        mapa = new Mapa();
        mapa.setPartida(partida);
        add(mapa.gerarMapa(), 0);
    }

    // -- Getters --
    public Mapa getMapa() {
        return mapa;
    }
}