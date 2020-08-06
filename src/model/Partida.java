package model;

// APIs
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// -- Pacotes --
import controller.PlayerControl;
import persistencia.Persistencia;
import recursos.Imagens;
import view.*;

public class Partida {

    // -- Atributos --
    private Player desafiante;
    private Player desafiado;
    private Computador computador;
    private List<List<String>> formacaoDesafiante;
    private List<List<String>> formacaoDesafiado;
    private int pontuacaoDesafiante = 0;
    private int pontuacaoDesafiado  = 0;

    // -- Histórico da Partida --
    private Historico historico = new Historico();

    // -- Atributos | Verificação --
    private TelaPartida telaPartida;
    private List<String> hitsComputador = new ArrayList<String>();

    PlayerControl control = new Persistencia().recuperarController();

    // -- PLAYER VS PLAYER --
    public Partida(Player desafiante, Player desafiado, TelaPartida telaPartida) {
        this.desafiante          = desafiante;
        this.desafiado 	         = desafiado;
        this.formacaoDesafiante  = desafiante.getFormacao();
        this.formacaoDesafiado   = desafiado.getFormacao();
        this.telaPartida         = telaPartida;

        this.historico.setDesafiado(desafiado.getNickname());
        salvarFormacaoHistorico();
    }

    // -- PLAYER VS COMPUTADOR --
    public Partida(Player desafiante, TelaPartida telaPartida) {
        this.desafiante = desafiante;
        this.computador = new Computador();

        // -- Recuperar formações ==
        this.formacaoDesafiante = desafiante.getFormacao();
        this.formacaoDesafiado 	= computador.getFormacao();

        this.telaPartida  = telaPartida;

        this.historico.setDesafiado("COMPUTADOR");
        salvarFormacaoHistorico();
    }

    public void checkMove(JButton celula) {

        if(!celula.getText().isEmpty()) {
            boolean flag = false;
            String hit = "não";

            for (List<String> linha : formacaoDesafiado) {
                if (linha.contains(celula.getText())) {
                    pontuacaoDesafiante += 5;
                    hit = "sim";
                    flag = true;
                }
            }

            historico.addPlays("Player: " + desafiante.getNickname() + " | Move: [" + celula.getText() + "] | Hit: " + hit);
            celula.setText("");

            if (flag) {
                celula.setIcon(Imagens.FOGO);
                JOptionPane.showMessageDialog(null, "VOCÊ ACERTOU UMA EMBARCAÇÃO!", "ϟ :) ϟ", JOptionPane.WARNING_MESSAGE);
            } else {
                celula.setIcon(Imagens.AGUA);
                JOptionPane.showMessageDialog(null, "VOCÊ ERROU!", "ϟ :( ϟ", JOptionPane.WARNING_MESSAGE);
            }



            if (pontuacaoDesafiante == 50) {
                for (List<JButton> linha : telaPartida.getMapa().getMatriz()) {

                    for (JButton posicao : linha) {
                        posicao.setEnabled(false);
                        posicao.setBackground(Color.BLACK);
                    }

                    control.salvarPontuacaoGanha(desafiante, 10);
                    historico.setVencedor(desafiante.getNickname());
                    historico.setPontuacao(10);
                    telaPartida.dispose();
                }

                control.salvarHistorico(desafiante, historico);
                new TelaResultado(desafiante.getNickname(), "VOCÊ GANHOU +10 PONTOS",desafiante);

            } else {

                // -- COMPUTADOR --
                boolean flagControleComputador = true;
                boolean hitComputador = false;
                String hitPC = "não";

                while (flagControleComputador) {

                    // Sortear posição
                    int linhaSorteadaComputador = new Random().nextInt(5);
                    int posicaoCelulaSorteadaComputador = new Random().nextInt(5);

                    if (!hitsComputador.contains(telaPartida.getMapa().getMatriz().get(linhaSorteadaComputador).get(posicaoCelulaSorteadaComputador).getText())) {

                        flagControleComputador = false;

                        for(List<String> linhaDaFormacao : formacaoDesafiante) {
                            if (linhaDaFormacao.contains(telaPartida.getMapa().getMatriz().get(linhaSorteadaComputador).get(posicaoCelulaSorteadaComputador).getText())) {
                                pontuacaoDesafiado += 5;
                                hitComputador = true;
                                break;
                            }
                        }

                        if(hitComputador) {
                            JOptionPane.showMessageDialog(null, "O OPONENTE ACERTOU UMA EMBARCAÇÃO!", "ϟ :( ϟ", JOptionPane.WARNING_MESSAGE);
                            hitPC = "sim";
                        } else {
                            JOptionPane.showMessageDialog(null, "O OPONENTE ERROU!", "ϟ :) ϟ", JOptionPane.WARNING_MESSAGE);
                        }
                        hitsComputador.add(telaPartida.getMapa().getMatriz().get(linhaSorteadaComputador).get(posicaoCelulaSorteadaComputador).getText());
                    }

                    if(desafiado == null) {
                        historico.addPlays("Player: COMPUTADOR" + " | Move: [" + telaPartida.getMapa().getMatriz().get(linhaSorteadaComputador).get(posicaoCelulaSorteadaComputador).getText()
                                + "] | Hit: " + hitPC);
                    } else {
                        historico.addPlays("Player: " + desafiado.getNickname() + " | Move: [" + telaPartida.getMapa().getMatriz().get(linhaSorteadaComputador).get(posicaoCelulaSorteadaComputador).getText()
                                + "] | Hit: " + hitPC);
                    }

                    if (pontuacaoDesafiado == 50) {
                        telaPartida.dispose();

                        String vencedor = "";

                        if(desafiado != null) {
                            vencedor = desafiado.getNickname();
                            control.salvarPontuacaoGanha(desafiado, 2);
                            control.salvarPontuacaoPerda(desafiante, 5);
                        } else {
                            vencedor = "COMPUTADOR";
                            control.salvarPontuacaoPerda(desafiante, 5);
                        }

                        historico.setVencedor(vencedor);
                        historico.setPontuacao(-5);
                        control.salvarHistorico(desafiante, historico);
                        new TelaResultado(vencedor, "VOCÊ PERDEU -5 PONTOS", desafiante);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "POSIÇÃO JÁ FOI SELECIONADA", "ϟ ATENÇÃO! ϟ", JOptionPane.ERROR_MESSAGE);
        }
    }

    // -- Getters --
    public Player getDesafiante() {
        return desafiante;
    }

    public Player getDesafiado() {
        return desafiante;
    }

    public Computador getComputador() {
        return computador;
    }

    public int getPontuacaoDesafiante() {
        return pontuacaoDesafiante;
    }

    public int getPontuacaoDesafiado() {
        return pontuacaoDesafiado;
    }

    public void setPontuacaoDesafiante(int pontuacaoDesafiante) {
        this.pontuacaoDesafiante = pontuacaoDesafiante;
    }

    public void setPontuacaoDesafiado(int pontuacaoDesafiado) {
        this.pontuacaoDesafiado = pontuacaoDesafiado;
    }

    public List<List<String>> getFormacaoDesafiante() {
        return formacaoDesafiante;
    }

    public List<List<String>> getFormacaoDesafiado() {
        return formacaoDesafiado;
    }

    private void salvarFormacaoHistorico() {
        historico.setFormacaoDesafiante(formacaoDesafiante);
        historico.setFormacaoDesafiado(formacaoDesafiado);
    }
}
