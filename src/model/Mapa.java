package model;

// -- APIs --
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

// Pacotes
import enums.Orientacao;
import recursos.*;
import view.*;

public class Mapa {

    private List<List<JButton>> matrizButton = new ArrayList<List<JButton>>();
    private List<List<String>> matrizString = new ArrayList<List<String>>();

    private JButton celula;

    private EditarFormacao editarFormacao;
    private Partida partida;

    // -- Construtor | Mapa Editar Formação --
    public Mapa(TelaEscolherEmbarcacoes telaEscolherEmbarcacoes, TelaEditarFormacao telaEditarFormacao) {
        editarFormacao = new EditarFormacao();

        editarFormacao.setTelaEscolherEmbarcacoes(telaEscolherEmbarcacoes);
        editarFormacao.setTelaEditarFormacao(telaEditarFormacao);
    }

    // -- Construtor | Mapa Partida --
    public Mapa() {

    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    // -- Métodos --
    public JPanel gerarMapa() {
        JPanel painel = new JPanel();

        painel.setLayout(new GridLayout(5, 5, 0, 0));
        painel.setBounds(115, 130, 360, 310);

        int contador = 1;
        String posicao = "";

        for (int i = 1; i < 6; i++) {

            List<JButton> linhaButton   = new ArrayList<JButton>();
            List<String> linhaString    = new ArrayList<String>();
            for (int j = 1; j < 6; j++) {

                switch (contador) {
                    case 1:
                        posicao = "A" + j;
                        break;
                    case 2:
                        posicao = "B" + j;
                        break;
                    case 3:
                        posicao = "C" + j;
                        break;
                    case 4:
                        posicao = "D" + j;
                        break;
                    case 5:
                        posicao = "E" + j;
                        break;
                }

                if (j == 5) {
                    contador++;
                }

                celula = new JButton(posicao);
                celula.setBackground(new Color(217, 217, 217));
                celula.setBorder(BorderFactory.createLineBorder(new Color(102, 102, 102)));

                if (editarFormacao != null) {
                    celula.addActionListener(new VerificarFormacaoListener());
                } else {
                    celula.addActionListener(new startPartida());
                }

                linhaButton.add(celula);
                linhaString.add(posicao);
                painel.add(celula);
            }
            matrizString.add(linhaString);
            matrizButton.add(linhaButton);
        }
        return painel;
    }

    public List<List<JButton>> getMatrizButton() {

        if (matrizButton.isEmpty()) {
            gerarMapa();
        }

        return matrizButton;
    }

    public List<List<String>> getMatrizString() {

        if(matrizString.isEmpty()) {
            gerarMapa();
        }

        return matrizString;
    }

    // -- Getters --
    public EditarFormacao getEditarFormacao() {
        return editarFormacao;
    }

    // -- Listeners --
    private class VerificarFormacaoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            JButton celula = (JButton) (e.getSource());        // Obter JButton selecionado no mapa

            if(!celula.getText().isEmpty()) {
                try {
                    verificarPosicao(celula);

                    // ----------- Configurar JButton -----------
                    celula.setBackground(Cores.COLOR_LARANJA);
                    celula.setText("");
                    celula.setIcon(Imagens.ICON_PIRATA);
                    // ----------------------------------------

                    editarFormacao.reduzirTamanhoEmbarcacaoAtual();

                    // -- Verificar se todas as partes da embaração foi posicionado --
                    if (editarFormacao.getTamanhoEmbarcacaoAtual() == 0) {
                        // -- Configurar nova embarcação --
                        editarFormacao.setOrientacaoEmbarcacaoHorizontal(null);
                        editarFormacao.setOrientacaoEmbarcacaoVertical(null);

                        // -- Salvar embarcação e limpar lista --
                        editarFormacao.addFormacao(editarFormacao.getLocalizacaoDaEmbarcacao());
                        editarFormacao.setLocalizacaoDaEmbarcacao(new ArrayList<String>());

                        if (editarFormacao.getQntdEmbarcacoes2PDisponivel() != 0 || editarFormacao.getQntdEmbarcacoes3PDisponivel() != 0) {
                            editarFormacao.setTelaEscolherEmbarcacoes(new TelaEscolherEmbarcacoes(
                                    editarFormacao.getTelaEditarFormacao(), String.valueOf(editarFormacao.getQntdEmbarcacoes2PDisponivel()),
                                    String.valueOf(editarFormacao.getQntdEmbarcacoes3PDisponivel())));
                        } else {
                            for (List<JButton> linha : matrizButton) {
                                List<JButton> celulas = linha;
                                for (JButton posicao : celulas) {
                                    posicao.setEnabled(false);
                                }
                            }
                        }
                    }
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, erro.getMessage(), "≋ ATENÇÃO! ≋", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "POSIÇÃO JÁ FOI SELECIONADA!", "≋ ATENÇÃO! ≋", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void verificarPosicao(JButton celula) throws Exception {
            if (editarFormacao.getLocalizacaoDaEmbarcacao().isEmpty()) {

                editarFormacao.setIndexReferenciaLinha(obterReferenciaLinha(celula.getText().charAt(0)));
                editarFormacao.setIndexPrimeiraCelulaSelecionada(matrizButton.get(editarFormacao.getIndexReferenciaLinha()).indexOf(celula));
                editarFormacao.setIndexUltimaCelulaSelecionada(matrizButton.get(editarFormacao.getIndexReferenciaLinha()).indexOf(celula));

                editarFormacao.setLocalizacaoDaEmbarcacao(celula.getText());

            } else {
                // -- Verificar Orientação Vertical --
                if (obterReferenciaLinha(celula.getText().charAt(0)) == editarFormacao.getIndexReferenciaLinha() && editarFormacao.getOrientacaoEmbarcacaoHorizontal() == null) {

                    int indexCelulaSelecionada = matrizButton.get(editarFormacao.getIndexReferenciaLinha()).indexOf(celula);

                    if ((editarFormacao.getIndexUltimaCelulaSelecionada() - 1) == indexCelulaSelecionada ||
                            (editarFormacao.getIndexUltimaCelulaSelecionada() + 1) == indexCelulaSelecionada ||
                            (editarFormacao.getIndexPrimeiraCelulaSelecionada() - 1) == indexCelulaSelecionada ||
                            (editarFormacao.getIndexPrimeiraCelulaSelecionada() + 1) == indexCelulaSelecionada) {

                        editarFormacao.setIndexUltimaCelulaSelecionada(indexCelulaSelecionada);
                        editarFormacao.setOrientacaoEmbarcacaoVertical(Orientacao.VERTICAL);
                        editarFormacao.setLocalizacaoDaEmbarcacao(celula.getText());
                    } else {
                        throw new Exception("POSIÇÃO INVÁLIDA!");
                    }
                } else {

                    // -- Verificar Orientação Horizontal --

                    int indexReferenciaLinhaSelecionada = obterReferenciaLinha(celula.getText().charAt(0));
                    int indexCelulaSelecionada = matrizButton.get(indexReferenciaLinhaSelecionada).indexOf(celula);

                    if (((editarFormacao.getIndexReferenciaLinha() - 1) == indexReferenciaLinhaSelecionada ||
                            (editarFormacao.getIndexReferenciaLinha() + 1) == indexReferenciaLinhaSelecionada ||
                            (editarFormacao.getIndexReferenciaUltimaLinhaSelecionada() - 1) == indexReferenciaLinhaSelecionada ||
                            (editarFormacao.getIndexReferenciaUltimaLinhaSelecionada() + 1) == indexReferenciaLinhaSelecionada) &&
                            editarFormacao.getIndexPrimeiraCelulaSelecionada() == indexCelulaSelecionada &&
                            editarFormacao.getOrientacaoEmbarcacaoVerical() == null) {

                        editarFormacao.setOrientacaoEmbarcacaoHorizontal(Orientacao.HORIZONTAL);
                        editarFormacao.setIndexReferenciaUltimaLinhaSelecionada(indexReferenciaLinhaSelecionada);
                        editarFormacao.setLocalizacaoDaEmbarcacao(celula.getText());
                    } else {
                        throw new Exception("POSIÇÃO INVÁLIDA!");
                    }
                }
            }
        }

        private int obterReferenciaLinha(char letraReferencia) {
            /*
             * Obter index da linha do JButton selecionado
             * através do texto do JButton
             */

            int indexLinhaMapa = 0;

            switch (letraReferencia) {
                case 'A':
                    indexLinhaMapa = 0;
                    break;
                case 'B':
                    indexLinhaMapa = 1;
                    break;
                case 'C':
                    indexLinhaMapa = 2;
                    break;
                case 'D':
                    indexLinhaMapa = 3;
                    break;
                case 'E':
                    indexLinhaMapa = 4;
                    break;
            }
            return indexLinhaMapa;
        }
    }

    private class startPartida implements ActionListener {
        public void actionPerformed(ActionEvent evento) {
            partida.checkMove((JButton) evento.getSource());
        }
    }
}