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

    private List<List<JButton>> matriz = new ArrayList<List<JButton>>();

    private JButton celula;

    private EditarFormacao editarFormacao;
    private Partida partida;
    private TelaPartida telaPartida;

    // -- Construtor | Mapa Editar Formação --
    public Mapa(TelaEscolherEmbarcacoes telaEscolherEmbarcacoes, TelaEditarFormacao telaEditarFormacao) {
        editarFormacao = new EditarFormacao();

        editarFormacao.setTelaEscolherEmbarcacoes(telaEscolherEmbarcacoes);
        editarFormacao.setTelaEditarFormacao(telaEditarFormacao);
    }

    // -- Construtor | Mapa Partida --
    public Mapa(Partida partida, TelaPartida telaPartida) {
        this.partida = partida;
        this.telaPartida = telaPartida;
    }

    public Mapa() {

    }

    // -- Métodos --
    public JPanel gerarMapa() {
        JPanel painel = new JPanel();

        painel.setLayout(new GridLayout(5, 5, 0, 0));
        painel.setBounds(115, 130, 360, 310);

        int contador = 1;
        String posicao = "";

        for(int i = 1; i < 6; i++) {

            List<JButton> linha = new ArrayList<JButton>();

            for(int j = 1; j < 6; j++) {

                switch(contador) {
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

                if(j == 5) {
                    contador++;
                }

                celula = new JButton(posicao);
                celula.setBackground(new Color(217,217,217));
                celula.setBorder(BorderFactory.createLineBorder(new Color(102,102,102)));
                celula.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        celula.setBackground(Cores.COLOR_VERDE);
                        celula.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        celula.setBackground(new Color(217,217,217));
                    }

                });

                if(editarFormacao != null) {
                    celula.addActionListener(new VerificarFormacaoListener());
                } else {
                    celula.addActionListener(new startPlayerVsComputador());
                }

                linha.add(celula);
                painel.add(celula);
            }
            matriz.add(linha);
        }
        return painel;
    }

    public List<List<JButton>> getMatriz() {

        if(matriz.isEmpty()) {
            gerarMapa();
        }

        return matriz;
    }

    // -- Getters --
    public EditarFormacao getEditarFormacao() {
        return editarFormacao;
    }

    // -- Listeners --
    private class VerificarFormacaoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            JButton celula = (JButton) (e.getSource());		// Obter JButton selecionado no mapa

            try {
                verificarPosicao(celula);

                // ----------- Configurar JButton -----------
                celula.setBackground(Cores.COLOR_LARANJA);
                celula.setText("");
                celula.setIcon(Imagens.ICON_PIRATA);
                celula.setEnabled(false);
                // ----------------------------------------

                editarFormacao.reduzirTamanhoEmbarcacaoAtual();

                // -- Verificar se todas as partes da embaração foi posicionado --
                if(editarFormacao.getTamanhoEmbarcacaoAtual() == 0) {
                    // -- Configurar nova embarcação --
                    editarFormacao.setOrientacaoEmbarcacaoHorizontal(null);
                    editarFormacao.setOrientacaoEmbarcacaoVertical(null);

                    // -- Salvar embarcação e limpar lista --
                    editarFormacao.addFormacao(editarFormacao.getLocalizacaoDaEmbarcacao());
                    editarFormacao.setLocalizacaoDaEmbarcacao(new ArrayList<String>());

                    if(editarFormacao.getQntdEmbarcacoes2PDisponivel() != 0 || editarFormacao.getQntdEmbarcacoes3PDisponivel() != 0) {
                        editarFormacao.setTelaEscolherEmbarcacoes(new TelaEscolherEmbarcacoes(
                                editarFormacao.getTelaEditarFormacao(), String.valueOf(editarFormacao.getQntdEmbarcacoes2PDisponivel()),
                                String.valueOf(editarFormacao.getQntdEmbarcacoes3PDisponivel())));
                    } else {
                        for(List<JButton> linha : matriz) {
                            List<JButton> celulas = linha;
                            for(JButton posicao : celulas) {
                                posicao.setEnabled(false);
                            }
                        }
                    }
                }
            } catch(Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage(), "ATENÇÃO!", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void verificarPosicao(JButton celula) throws Exception {
            if(editarFormacao.getLocalizacaoDaEmbarcacao().isEmpty()) {

                editarFormacao.setIndexReferenciaLinha(obterReferenciaLinha(celula.getText().charAt(0)));
                editarFormacao.setIndexPrimeiraCelulaSelecionada(matriz.get(editarFormacao.getIndexReferenciaLinha()).indexOf(celula));
                editarFormacao.setIndexUltimaCelulaSelecionada(matriz.get(editarFormacao.getIndexReferenciaLinha()).indexOf(celula));

                editarFormacao.setLocalizacaoDaEmbarcacao(celula.getText());

            } else {
                // -- Verificar Orientação Vertical --
                if(obterReferenciaLinha(celula.getText().charAt(0)) == editarFormacao.getIndexReferenciaLinha() && editarFormacao.getOrientacaoEmbarcacaoHorizontal() == null) {

                    int indexCelulaSelecionada = matriz.get(editarFormacao.getIndexReferenciaLinha()).indexOf(celula);

                    if((editarFormacao.getIndexUltimaCelulaSelecionada() - 1) == indexCelulaSelecionada ||
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
                    int indexCelulaSelecionada = matriz.get(indexReferenciaLinhaSelecionada).indexOf(celula);

                    if(((editarFormacao.getIndexReferenciaLinha() - 1) == indexReferenciaLinhaSelecionada ||
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

    private class startPlayerVsComputador implements ActionListener {
        public void actionPerformed(ActionEvent evento) {
            JButton celula = (JButton) evento.getSource();

            for(List<String> linha : partida.getFormacaoDesafiado()) {
                if(linha.contains(celula.getText())) {
                    partida.getDesafiante().setPontuacao(partida.getDesafiante().getPontuacao() + 2);
                    telaPartida.atualizarPlacar();
                    celula.setIcon(Imagens.FOGO);
                    return;
                }
            }

            celula.setIcon(Imagens.AGUA);
        }
    }
}