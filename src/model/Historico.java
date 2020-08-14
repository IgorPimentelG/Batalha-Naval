package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Historico {

    private String vencedor;
    private String desafiado;
    private int pontuacao;
    private LocalDateTime dataDaPartida;
    private List<List<String>> formacaoDesafiante;
    private List<List<String>> formacaoDesafiado;
    private List<String> plays = new ArrayList<String>();

    public Historico() {
        // -- Formatar Data --
        this.dataDaPartida = LocalDateTime.now();
    }

    // Setters
    public void setVencedor(String vencedor) {
        this.vencedor = vencedor;
    }

    public void setDesafiado(String desafiado) {
        this.desafiado = desafiado;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public void setFormacaoDesafiante(List<List<String>> formacaoDesafiante) {
        this.formacaoDesafiante = formacaoDesafiante;
    }

    public void setFormacaoDesafiado(List<List<String>> formacaoDesafiado) {
        this.formacaoDesafiado = formacaoDesafiado;
    }

    public void addPlays(String move) {
        plays.add(move);
    }

    // Getters
    public String getVencedor() {
        return vencedor;
    }

    public String getDesafiado() {
        return desafiado;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public String getDataDaPartida() {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");

        String data = formatoData.format(this.dataDaPartida);
        String hora = formatoHora.format(this.dataDaPartida);

        return data + " " + hora;
    }

    public List<List<String>> getFormacaoDesafiante() {
        return formacaoDesafiante;
    }

    public List<List<String>> getFormacaoDesafiado() {
        return formacaoDesafiado;
    }

    public List<String> getPlays() {
        return plays;
    }
}
