package model;

// APIs
import java.util.List;

public class Partida {

    // -- Atributos --
    private Player desafiante;
    private Player desafiado;
    private Computador computador;
    private List<List<String>> formacaoDesafiante;
    private List<List<String>> formacaoDesafiado;
    private String vencedor;

    // -- PLAYER VS PLAYER --
    public Partida(Player desafiante, Player desafiado) {
        this.desafiante = desafiante;
        this.desafiado 	= desafiado;

        this.formacaoDesafiante = desafiante.getFormacao();
        this.formacaoDesafiado = desafiado.getFormacao();
    }

    // -- PLAYER VS COMPUTADOR --
    public Partida(Player desafiante) {
        this.desafiante = desafiante;
        this.desafiado  = null;
        this.computador = new Computador();

        // -- Recuperar formações ==
        this.formacaoDesafiante = desafiante.getFormacao();
        this.formacaoDesafiado 	= computador.getFormacao();
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