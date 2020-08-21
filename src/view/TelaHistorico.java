package view;

// -- APIs --
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;

// -- Pacotes --
import controller.*;
import model.*;
import persistencia.Persistencia;
import recursos.*;
import recursos.view.*;

public class TelaHistorico extends ScreenSetup {

    private Player player;
    private List<Historico> historicos;
    private TelaHistorico telaHistorico;

    private JTable tabelaHistorico;

    public TelaHistorico(Player player) {
        super("World of Warships - Histórico", 585, 505, Imagens.BACKGROUND_GAME);

        PlayerControl playerControl = new Persistencia().recuperarController();

        this.player = playerControl.pesquisarPlayer(player);
        this.telaHistorico = this;

        historicos  = this.player.getHistorico();

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

        JLabel cardSuperiorHistorico = card.getCardSuperior(135, 50, 310, 60);
        add(cardSuperiorHistorico, 0);

        JLabel logoCardHistoricoEsquerdo = new JLabel(Imagens.LOGO_SUPERIOR);
        logoCardHistoricoEsquerdo.setBounds(55, 35, 100, 100);
        add(logoCardHistoricoEsquerdo, 0);

        JLabel logoCardHistoricoDireito = new JLabel(Imagens.LOGO_SUPERIOR);
        logoCardHistoricoDireito.setBounds(425, 35, 100, 100);
        add(logoCardHistoricoDireito, 0);
    }

    private void adicionarLabels() {
        JLabel lblHistico = new ModRotulo("HISTÓRICO", 135, 70, 310, 20);
        lblHistico.setHorizontalAlignment(JLabel.CENTER);
        add(lblHistico, 0);
    }

    private void adicionarButtons() {
        JButton btnDetalhar = new ModButton("DETALHAR", 70, 415, 135, 30);
        btnDetalhar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Historico historicoDetalhado = null;

                if(tabelaHistorico.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "NENHUAM PARTIDA FOI SELECIONADA!", "≋ ATENÇÃO! ≋", JOptionPane.WARNING_MESSAGE);
                } else {
                    String dataDoHistorico = historicos.get(tabelaHistorico.getSelectedRow()).getDataDaPartida();

                    for(Historico historicoSalvo : historicos) {
                        if(historicoSalvo.getDataDaPartida().equals(dataDoHistorico)) {
                            historicoDetalhado = historicoSalvo;
                            break;
                        }
                    }
                    setVisible(false);
                    new TelaDetalhar(historicoDetalhado, telaHistorico);
                }
            }
        });
        add(btnDetalhar, 0);

        JButton btnRelatorio = new ModButton("RELATÓRIO", 225, 415, 135, 30);
        btnRelatorio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                setVisible(false);
                new TelaGerarRelatorio(telaHistorico);
            }
        });
        add(btnRelatorio, 0);

        JButton btnVoltar = new ModButton("⇖ VOLTAR", 375, 415, 135, 30);
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
        modeloTabela.addColumn("Data");
        modeloTabela.addColumn("Adversário");
        modeloTabela.addColumn("Resumo");

        tabelaHistorico = new TableMod(modeloTabela, 3);

        for(Historico historico : historicos) {

            String resumo = "GANHOU!";

            if(!historico.getVencedor().equals(player.getNickname())) {
                resumo = "PERDEU!";
            }

            Object[] dados = {
                    historico.getDataDaPartida(),
                    historico.getDesafiado(),
                    resumo
            };
            modeloTabela.addRow(dados);
        }

        JScrollPane painelTabelaHistorico = new JScrollPane(tabelaHistorico);
        painelTabelaHistorico.setBounds(70, 140, 440, 255);
        add(painelTabelaHistorico, 0);
    }

    // -- Inner Class Relatório --
    private class TelaGerarRelatorio extends ScreenSetup {

        private TelaHistorico telaHistorico;

        private TelaGerarRelatorio(TelaHistorico telaHistorico) {
            super("≋ Relatório ≋", 255, 255, Imagens.BACKGROUN_MENU);

            this.telaHistorico = telaHistorico;

            adicionarLabel();
            adicionarButtons();

            setVisible(true);
        }

        private void adicionarLabel() {
            JLabel lblTitulo = new JLabel("GERAR RELATÓRIO");
            lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
            lblTitulo.setBounds(0, 20, 255, 18);
            lblTitulo.setHorizontalAlignment(JLabel.CENTER);
            lblTitulo.setForeground(Color.WHITE);
            add(lblTitulo, 0);
        }

        private void adicionarButtons() {
            JButton btnRelatorioPartidasGanhas = new ModButton("PARTIDAS GANHAS", 35, 60, 185, 35);
            btnRelatorioPartidasGanhas.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    try {
                        new HistoricoControl().gerarRelatorioPartidasGanhas(player);
                        JOptionPane.showMessageDialog(null, "RELATÓRIO GERADO COM SUCESSO!", "≋ RELATÓRIO ≋", JOptionPane.INFORMATION_MESSAGE);
                    } catch(Exception erro) {
                        JOptionPane.showMessageDialog(null, "ACONTECEU UM ERRO AO GERAR O RELATÓRIO!", "≋ ATENÇÃO! ≋", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(null, erro.getMessage(), "≋ ATENÇÃO! ≋", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            add(btnRelatorioPartidasGanhas, 0);

            JButton btnRelatorioPartidasPerdidas = new ModButton("PARTIDAS PERDIDAS", 35, 110, 185, 35);
            btnRelatorioPartidasPerdidas.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    try {
                        new HistoricoControl().gerarRelatorioPartidasPerdidas(player);
                        JOptionPane.showMessageDialog(null, "RELATÓRIO GERADO COM SUCESSO!", "≋ RELATÓRIO ≋", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, "ACONTECEU UM ERRO AO GERAR O RELATÓRIO!", "≋ ATENÇÃO! ≋", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(null, erro.getMessage(), "≋ ATENÇÃO! ≋", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            add(btnRelatorioPartidasPerdidas, 0);

            JButton btnVoltar = new ModButton("⇖ VOLTAR", 35, 160, 185, 35);
            btnVoltar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    dispose();
                    telaHistorico.setVisible(true);
                }
            });
            add(btnVoltar, 0);

        }
    }

    private class TelaDetalhar extends ScreenSetup {

        private Historico historico;
        private TelaHistorico telaHistorico;

        private TelaDetalhar(Historico historico, TelaHistorico telaHistorico) {
            super("TELA DETALHAR", 470, 525, Imagens.BACKGROUND_DETALHAR);

            this.historico      = historico;
            this.telaHistorico  = telaHistorico;

            adicionarCard();
            adicionarSeparador();
            adicionarLabels();
            adicionarAreaTexto();
            adicionarButton();

            setVisible(true);
        }

        private void adicionarLabels() {
            JLabel lblData = new JLabel("Data e Hora: " + historico.getDataDaPartida());
            lblData.setBounds(50, 135, 350, 20);
            lblData.setHorizontalAlignment(JLabel.CENTER);
            add(lblData, 0);

            JLabel lblVencedor = new JLabel("Vencedor: " + historico.getVencedor());
            lblVencedor.setBounds(50, 153, 350, 20);
            lblVencedor.setHorizontalAlignment(JLabel.CENTER);
            add(lblVencedor, 0);

            JLabel lblFormacao = new JLabel("Formações");
            lblFormacao.setHorizontalAlignment(JLabel.CENTER);
            lblFormacao.setBounds(50, 185, 350, 20);
            add(lblFormacao, 0);

            JLabel lblFormacaoDesafiante = new JLabel("Desafiante: " + historico.getFormacaoDesafiante().toString());
            lblFormacaoDesafiante.setBounds(50, 200, 350, 40);
            lblFormacaoDesafiante.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 11));
            lblFormacaoDesafiante.setHorizontalAlignment(JLabel.CENTER);
            add(lblFormacaoDesafiante, 0);

            JLabel lblFormacaoDesafiado =  new JLabel("Desafiado: " + historico.getFormacaoDesafiado());
            lblFormacaoDesafiado.setBounds(50, 225, 350, 20);
            lblFormacaoDesafiado.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 11));
            lblFormacaoDesafiado.setHorizontalAlignment(JLabel.CENTER);
            add(lblFormacaoDesafiado, 0 );

            JLabel lblResumo = new JLabel("Resumo");
            lblResumo.setBounds(50, 257, 350, 20);
            lblResumo.setHorizontalAlignment(JLabel.CENTER);
            add(lblResumo, 0);
        }

        private void adicionarCard() {
            Card card = new Card();

            JLabel cardSuperior = card.getCardSuperior(50, 80,350, 50);
            add(cardSuperior, 0);

            JLabel cardInferior = card.getCardInferior(50, 130, 350, 300);
            add(cardInferior, 0);

            JLabel lblLogo = card.getIcone(50, 20, 350);
            add(lblLogo, 0);
        }

        private void adicionarSeparador() {
            JSeparator primeiroSeparador = new JSeparator(JSeparator.HORIZONTAL);
            primeiroSeparador.setBounds(55, 180, 340, 2);
            primeiroSeparador.setForeground(Color.GRAY);
            add(primeiroSeparador, 0);

            JSeparator segundoSeparador = new JSeparator(JSeparator.HORIZONTAL);
            segundoSeparador.setBounds(55, 252,340, 2);
            segundoSeparador.setForeground(Color.GRAY);
            add(segundoSeparador, 0);
        }

        private void adicionarAreaTexto() {
            JTextPane textHistorico = new JTextPane();
            textHistorico.setText(combinarTexto());
            textHistorico.setAutoscrolls(true);
            textHistorico.setEnabled(false);
            textHistorico.setForeground(Color.WHITE);
            textHistorico.setBackground(Cores.COLOR_AZUL);
            textHistorico.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));

            StyledDocument styleDoc = textHistorico.getStyledDocument();
            SimpleAttributeSet centralizar = new SimpleAttributeSet();
            StyleConstants.setAlignment(centralizar, StyleConstants.ALIGN_CENTER);
            styleDoc.setParagraphAttributes(0, styleDoc.getLength(), centralizar, false);

            JScrollPane caixaScrollBar = new JScrollPane(textHistorico, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            caixaScrollBar.setBounds(80, 280, 290, 150);
            add(caixaScrollBar, 0);
        }

        private void adicionarButton() {
            JButton btnVoltar = new ModButton("⇖ VOLTAR", 50, 440, 350, 30);
            btnVoltar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    dispose();
                    telaHistorico.setVisible(true);
                }
            });
            add(btnVoltar, 0);
        }

        private String combinarTexto() {
            String texto = "";

            for(String jogada : historico.getPlays()) {
                texto += jogada + "\n";
            }

            return texto;
        }
    }
}

