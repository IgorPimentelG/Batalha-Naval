package view;

// -- APIs --
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

// -- Pacotes --
import controller.PlayerControl;
import model.Partida;
import model.Player;
import persistencia.Persistencia;
import recursos.*;
import recursos.view.*;

public class TelaEnviarConvite extends ScreenSetup {

    private JTextField inputBuscarJogador;
    private Player player;

    public TelaEnviarConvite(Player player) {
        super("World of Warships - Desafiar", 850, 520, Imagens.BACKGROUND_CONVITE);
        this.player = player;

        // -- Adicionar Componentes a View --
        adicionarCard();
        adicionarLabels();
        adicionarButtons();
        adicionarInputs();
        adicionarTabela();

        setVisible(true);
    }

    // -- Componentes --
    private void adicionarCard()  {
        Card card = new Card();

        JLabel cardSuperiorConvite = new JLabel(card.gerarCardSuperior(300, 60));
        cardSuperiorConvite.setBounds(60, 170, 300, 60);
        add(cardSuperiorConvite, 0);

        JLabel cardInferiorConvite = new JLabel(card.gerarCardInferior(300, 210));
        cardInferiorConvite.setBounds(60, 230, 300, 210);
        add(cardInferiorConvite, 0);

        JLabel logoConvite = new JLabel(Imagens.LOGO_SUPERIOR);
        logoConvite.setBounds(160, 120, 100, 100);
        add(logoConvite, 0);

        // --- Ranking ---
        JLabel cardSuperiorRanking = new JLabel(card.gerarCardSuperior(350, 60));
        cardSuperiorRanking.setBounds(430, 110, 350, 60);
        add(cardSuperiorRanking, 0);

        JLabel logoRankingEsquerdo = new JLabel(Imagens.LOGO_SUPERIOR);
        logoRankingEsquerdo.setBounds(555, 35, 100, 100);
        add(logoRankingEsquerdo, 0);
    }

    private void adicionarLabels() {
        JLabel lblEnviarConvite = new JLabel("ENVIAR CONVITE");
        lblEnviarConvite.setBounds(60, 245, 300, 20);
        lblEnviarConvite.setHorizontalAlignment(JLabel.CENTER);
        lblEnviarConvite.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        add(lblEnviarConvite, 0);

        JLabel lblRanking = new JLabel("R A N K I N G");
        lblRanking.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        lblRanking.setBounds(430, 135, 350, 20);
        lblRanking.setHorizontalAlignment(JLabel.CENTER);
        lblRanking.setForeground(Color.WHITE);
        add(lblRanking, 0);

        JLabel lblNickname = new JLabel("Nickname:");
        lblNickname.setBounds(70, 277, 100, 20);
        add(lblNickname, 0);
    }

    private void adicionarInputs() {
        inputBuscarJogador = new ModInput(70, 300, 280);
        add(inputBuscarJogador, 0);
    }

    private void adicionarButtons() {
        JButton btnPesquisar = new ModButton("DESAFIAR", 70, 345, 280);
        btnPesquisar.setBackground(Cores.COLOR_VERDE);
        btnPesquisar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // -- Recuperar player selecionado --
                PlayerControl control = new Persistencia().recuperarController();
                Player desafiado = control.pesquisarNickName(inputBuscarJogador.getText());

                if(desafiado == null) {
                    JOptionPane.showMessageDialog(null, "PLAYER NÃO ENCONTRADO! TENTE NOVAMENTE.", "≋ - ATENÇÃO! - ≋", JOptionPane.ERROR_MESSAGE);
                } else {
                    dispose();
                    JOptionPane.showMessageDialog(null, "A PARTIDA IRÁ COMEÇAR EM BREVE...", "≋ - BATALHE! - ≋", JOptionPane.WARNING_MESSAGE);
                    new TelaPartida(player, desafiado);
                }

            }
        });
        add(btnPesquisar, 0);

        JButton btnVoltar = new ModButton("⇖ VOLTAR", 70, 390, 280);
        btnVoltar.setBackground(Cores.COLOR_LARANJA);
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TelaMenu(player);
            }
        });
        add(btnVoltar, 0);
    }

    private void adicionarTabela() {
        DefaultTableModel modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Posição");
        modeloTabela.addColumn("Nickname");
        modeloTabela.addColumn("Pontuação");

        JTable tabelaRanking = new TableMod(modeloTabela, 3);
        tabelaRanking.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                // -- Configurar JTextField de pesquisa com o nickname selecionado na tabela
                if(!tabelaRanking.getValueAt(tabelaRanking.getSelectedRow(), 1).equals(player.getNickname())) {
                    inputBuscarJogador.setText(tabelaRanking.getValueAt(tabelaRanking.getSelectedRow(), 1).toString());
                    repaint();
                } else {
                    inputBuscarJogador.setText("");
                }
            }
        });

        Persistencia persistencia = new Persistencia();
        PlayerControl playerControl = persistencia.recuperarController();

        List<Player> playersCadastrados = playerControl.getUsuariosCadastrado();

        for(int i = 0; i < playersCadastrados.size(); i++) {
            Object[] dados = {(i + 1), playersCadastrados.get(i).getNickname(), playersCadastrados.get(i).getPontuacao()};
            modeloTabela.addRow(dados);
        }

        JScrollPane painelTabela = new JScrollPane(tabelaRanking);
        painelTabela.setBounds(430, 170, 350, 270);
        add(painelTabela, 0);
    }
}
