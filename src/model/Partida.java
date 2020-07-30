package model;

// APIs
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// -- Pacotes --
import recursos.Imagens;
import view.TelaMenu;
import view.TelaPartida;

public class Partida {

    // -- Atributos --
    private Player desafiante;
    private Player desafiado;
    private Computador computador;
    private List<List<String>> formacaoDesafiante;
    private List<List<String>> formacaoDesafiado;
    private String vencedor;

    // -- Atributos | Verificação --
    private TelaPartida telaPartida;
    private Mapa mapa;
    private List<String> hitsComputador = new ArrayList<String>();
    private List<String> hitsPlayer     = new ArrayList<String>();

    // -- PLAYER VS PLAYER --
    public Partida(Player desafiante, Player desafiado) {
        this.desafiante = desafiante;
        this.desafiado 	= desafiado;

        this.formacaoDesafiante = desafiante.getFormacao();
        this.formacaoDesafiado = desafiado.getFormacao();
    }

    // -- PLAYER VS COMPUTADOR --
    public Partida(Player desafiante, TelaPartida telaPartida, Mapa mapa) {
        this.desafiante = desafiante;
        this.computador = new Computador();

        // -- Recuperar formações ==
        this.formacaoDesafiante = desafiante.getFormacao();
        this.formacaoDesafiado 	= computador.getFormacao();

        this.telaPartida  = telaPartida;
        this.mapa         = mapa;
    }

    public void PlayerVsComputador(JButton celula) {

        boolean flag = false;

        for (List<String> linha : formacaoDesafiado) {
            if (linha.contains(celula.getText())) {
                desafiante.setPontuacao(2);
                telaPartida.atualizarPlacar();
                flag = true;
            }
        }

        if(flag) {
            celula.setIcon(Imagens.FOGO);
        } else {
            celula.setIcon(Imagens.AGUA);
        }

        if(desafiante.getPontuacao() == 20) {
            for(List<JButton> linha : mapa.getMatriz()) {
                for(JButton posicao : linha) {
                    posicao.setEnabled(false);
                    posicao.setBackground(Color.BLACK);
                   // telaPartida.dispose();
                   // new TelaMenu(desafiante);
                }
            }
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

    public List<List<String>> getFormacaoDesafiante() {
        return formacaoDesafiante;
    }

    public List<List<String>> getFormacaoDesafiado() {
        return formacaoDesafiado;
    }
}