package view;

// -- APIs --
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// -- Pacotes --
import recursos.*;
import recursos.view.*;

public class TelaHistorico extends ScreenSetup {

    public TelaHistorico() {
        super("World of Warships - Histórico", 505, 580, Imagens.BACKGROUND_HISTORICO);

        // -- Adicionar Componentes a View --
        adicionarCard();
        adicionarLabels();
        adicionarButtons();
        adicionarTabela();

        setVisible(true);
    }

    // -- Componentes  --
    private void adicionarCard() {
        Card card = new Card();

        JLabel cardSuperiorHistorico = new JLabel(card.gerarCardSuperior(300, 60));
        cardSuperiorHistorico.setBounds(90, 50, 300, 60);
        add(cardSuperiorHistorico, 0);

        JLabel logoCardHistoricoEsquerdo = new JLabel(Imagens.LOGO_SUPERIOR);
        logoCardHistoricoEsquerdo.setBounds(30, 35, 100, 100);
        add(logoCardHistoricoEsquerdo, 0);

        JLabel logoCardHistoricoDireito = new JLabel(Imagens.LOGO_SUPERIOR);
        logoCardHistoricoDireito.setBounds(370, 35, 100, 100);
        add(logoCardHistoricoDireito, 0);
    }

    private void adicionarLabels() {
        JLabel lblHistico = new JLabel("HISTÓRICO");
        lblHistico.setBounds(210, 70, 100, 20);
        lblHistico.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        lblHistico.setForeground(Color.WHITE);
        add(lblHistico, 0);
    }

    private void adicionarButtons() {
        JButton btnDetalhar = new ModButton("DETALHAR", 100, 500, 140);
        btnDetalhar.setBackground(Cores.COLOR_VERDE);
        add(btnDetalhar, 0);

        JButton btnVoltar = new ModButton("⇖ VOLTAR", 260, 500, 140);
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setBackground(Cores.COLOR_LARANJA);
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                //new TelaSettings();
            }
        });
        add(btnVoltar, 0);
    }

    private void adicionarTabela() {
        DefaultTableModel modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Data");
        modeloTabela.addColumn("Adversário");
        modeloTabela.addColumn("Resumo");

        JTable tabelaHistorico = new JTable(modeloTabela);

        JScrollPane painelTabelaHistorico = new JScrollPane(tabelaHistorico);
        painelTabelaHistorico.setBounds(80, 150, 340, 330);
        add(painelTabelaHistorico, 0);
    }
}

