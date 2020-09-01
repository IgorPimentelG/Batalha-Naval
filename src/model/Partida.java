package model;

// # APIs #
import javax.swing.*;
import java.util.*;

// # Pacotes #
import controller.PlayerControl;
import enums.*;
import persistencia.Persistencia;
import recursos.Imagens;
import view.*;

public class Partida {

    // # Atributos #
    private Player desafiante;
    private Player desafiado;
    private Computador computador;
    private List<List<String>> formacaoDesafiante;
    private List<List<String>> formacaoDesafiado;
    private int pontuacaoDesafiante = 0;
    private int pontuacaoDesafiado = 0;

    // # Histórico da Partida #
    private Historico historico = new Historico();

    // # Atributos | Verificação #
    private TelaPartida telaPartida;
    private List<String> hitsComputador = new ArrayList<String>();
    private boolean hitComputador = false;
    private int linhaSorteadaComputador;
    private int posicaoCelulaSorteadaComputador;

    PlayerControl control = new Persistencia().recuperarController();

    // # PLAYER VS PLAYER #
    public Partida(Player desafiante, Player desafiado, TelaPartida telaPartida) {
        this.desafiante = desafiante;
        this.desafiado = desafiado;
        this.formacaoDesafiante = desafiante.getFormacao();
        this.formacaoDesafiado = desafiado.getFormacao();
        this.telaPartida = telaPartida;

        this.historico.setDesafiado(desafiado.getNickname());
        salvarFormacaoHistorico();
    }

    // # PLAYER VS COMPUTADOR #
    public Partida(Player desafiante, TelaPartida telaPartida) {
        this.desafiante = desafiante;
        this.computador = new Computador();

        // # Recuperar formações #
        this.formacaoDesafiante = desafiante.getFormacao();
        this.formacaoDesafiado = computador.getFormacao();

        this.telaPartida = telaPartida;

        this.historico.setDesafiado("COMPUTADOR");
        salvarFormacaoHistorico();
    }

    // # Verificar Jogada & Jogada Computador #
    public void checkMove(JButton celula) {

        if (!celula.getText().isEmpty()) {

            boolean flag = false;
            String hit = "não";

            for (List<String> linha : formacaoDesafiado) {
                if (linha.contains(celula.getText())) {
                    pontuacaoDesafiante++;
                    hit = "sim";
                    flag = true;
                }
            }

            historico.addPlays("Player: " + desafiante.getNickname() + " | Move: [" + celula.getText() + "] | Hit: " + hit);
            celula.setText("");

            if (flag) {
                celula.setIcon(Imagens.FOGO);
                JOptionPane.showMessageDialog(null, "VOCÊ ACERTOU UMA EMBARCAÇÃO!", ":)", JOptionPane.WARNING_MESSAGE);
            } else {
                celula.setIcon(Imagens.AGUA);
                JOptionPane.showMessageDialog(null, "VOCÊ ERROU!", ":(", JOptionPane.WARNING_MESSAGE);
            }

            if (!checkVencedor()) {
                // # COMPUTADOR #

                if (!hitComputador) {
                    boolean flagControleComputador = true;

                    while (flagControleComputador) {

                        // # Sortear posição #
                        linhaSorteadaComputador = new Random().nextInt(5);
                        posicaoCelulaSorteadaComputador = new Random().nextInt(5);

                        String celulaSorteadaComputador = telaPartida.getMapa().getMatrizString().get(linhaSorteadaComputador).get(posicaoCelulaSorteadaComputador);
                      
                        if(realizarJogadaComputador(celulaSorteadaComputador)) {

                            flagControleComputador = false;

                            verificarAcertoComputador();
                            hitsComputador.add(celulaSorteadaComputador);
                            checkVencedor();
                        }
                    }
                } else {

                    Lado lado = null;
                    boolean flagControleComputadorDefinirPosicao = true;

                    while(flagControleComputadorDefinirPosicao) {

                        // # Sortear Orientação #
                        int indexSorteadoOrientacao = new Random().nextInt(Orientacao.values().length);
                        Orientacao orientacaoSorteada = Orientacao.values()[indexSorteadoOrientacao];

                        if (orientacaoSorteada == Orientacao.HORIZONTAL) {
                            if (linhaSorteadaComputador == 0) {
                                lado = Lado.TOP;
                            } else if (linhaSorteadaComputador == 4) {
                                lado = Lado.BOTTOM;
                            } else {
                                lado = Lado.values()[new Random().nextInt((Lado.values().length) - 2)];
                            }
                        } else if (orientacaoSorteada == Orientacao.VERTICAL) {
                            if (posicaoCelulaSorteadaComputador == 0) {
                                lado = Lado.RIGHT;
                            } else if (posicaoCelulaSorteadaComputador == 4) {
                                lado = Lado.LEFT;
                            } else {
                                lado = Lado.values()[new Random().nextInt(((Lado.values().length - 1) + 1 - 2)) + 2];
                            }
                        }

                        if(interligenciaProximaJogada(lado)) {
                        	flagControleComputadorDefinirPosicao = false;
                        }
                   }
                   verificarAcertoComputador(); 
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "POSIÇÃO JÁ FOI SELECIONADA", "♔ATENÇÃO!♔", JOptionPane.ERROR_MESSAGE);
        }
    }

    // # Métodos Auxiliares #
    public boolean checkFormacaoDesafiado() {

        boolean formacaoValida = true;

        List<Historico> historicoDesafiante = desafiante.getHistorico();
        List<Historico> partidasComDesafiado = new ArrayList<Historico>();


        for (Historico historico : historicoDesafiante) {
            if (historico.getDesafiado().equals(desafiado.getNickname())) {
                partidasComDesafiado.add(historico);
            }
        }

        partidasComDesafiado.get(partidasComDesafiado.size() - 1).getFormacaoDesafiado().removeAll(formacaoDesafiado);

        if (partidasComDesafiado.get(partidasComDesafiado.size() - 1).getFormacaoDesafiado().size() == 0) {
            formacaoValida = false;
        }

        return formacaoValida;
    }

    private boolean checkVencedor() {

        boolean finalizarPartida = false;
        String vencedor = "";
        String pontuacao = "";

        if (pontuacaoDesafiante == 10) {

            finalizarPartida = true;
            vencedor = desafiante.getNickname();
            pontuacao = "VOCÊ GANHOU +10 PONTOS";

            control.salvarPontuacaoGanha(desafiante, 10);
            historico.setVencedor(desafiante.getNickname());
            historico.setPontuacao(10);

        } else if (pontuacaoDesafiado == 10) {
            finalizarPartida = true;
            pontuacao = "VOCÊ PERDEU -5 PONTOS";

            if (desafiado != null) {
                vencedor = desafiado.getNickname();
                control.salvarPontuacaoGanha(desafiado, 2);
            } else {
                vencedor = "COMPUTADOR";
            }

            control.salvarPontuacaoPerda(desafiante, 5);
            historico.setVencedor(vencedor);
            historico.setPontuacao(-5);
        }

        if (finalizarPartida) {
            salvarHistorico();
            telaPartida.dispose();
            new TelaResultado(vencedor, pontuacao, desafiante);
        }
        return finalizarPartida;
    }

    private boolean interligenciaProximaJogada(Lado lado) {

        switch(lado) {
            case TOP:
                linhaSorteadaComputador++;
                break;
            case BOTTOM:
                linhaSorteadaComputador--;
                break;
            case RIGHT:
                posicaoCelulaSorteadaComputador++;
                break;
            case LEFT:
                posicaoCelulaSorteadaComputador--;
                break;
        }

        String proximaJogada = telaPartida.getMapa().getMatrizString().get(linhaSorteadaComputador).get(posicaoCelulaSorteadaComputador);
        
        return realizarJogadaComputador(proximaJogada);
    }

    private boolean realizarJogadaComputador(String celula) {
    
        boolean jogadaRealizada = false;

        System.out.println(celula);
        
        if (!hitsComputador.contains(celula)) {
            jogadaRealizada = true;
            for (List<String> linhaDaFormacao : formacaoDesafiante) {
                if (linhaDaFormacao.contains(celula)) {
                    pontuacaoDesafiado++;
                    hitComputador = true;
                    break;
                }
                hitComputador = false;
            }
        }
        return jogadaRealizada;
    }

    private void verificarAcertoComputador() {
        if (hitComputador) {
            JOptionPane.showMessageDialog(null, "O OPONENTE ACERTOU UMA EMBARCAÇÃO!", ":(", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "O OPONENTE ERROU!", ":)", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void salvarHistorico() {
        if(desafiado != null) {
            control.salvarHistoricoPlayerVsPlayer(desafiante, desafiado, historico);
        } else {
            control.salvarHistorico(desafiante, historico);
        }
    }

    // # Getters #
    public Computador getComputador() {
        return computador;
    }
    
    private void salvarFormacaoHistorico() {
        historico.setFormacaoDesafiante(formacaoDesafiante);
        historico.setFormacaoDesafiado(formacaoDesafiado);
    }
}