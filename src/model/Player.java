package model;

import java.util.*;

public class Player implements Comparable<Player> {

    private String nickname;
    private String email;
    private String password;
    private int pontuacao;
    private List<List<String>> formacao = new ArrayList<List<String>>();
    private List<Historico> historicos = new ArrayList<Historico>();

    public Player(String nickname, String email, String password) {
        this.nickname 	= nickname;
        this.email 		= email;
        this.password 	= password;

        this.pontuacao = 0;
    }

    // Setters & Getters
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public List<List<String>> getFormacao() {
        return formacao;
    }

    public void setFormacao(List<List<String>> formacao) {
        this.formacao = formacao;
    }

    public void addHistorico(Historico historico) {
        historicos.add(historico);
    }

    public List<Historico> getHistorico() {
        return historicos;
    }

    @Override
    public int compareTo(Player nextPlayer) {

        /*
         * MÃ©todo utilizado para ordenar com base na pontuaÃ§Ã£o a lista atravÃ©s da chamada .sort()
         */

        if(this.getPontuacao() > nextPlayer.getPontuacao()) {
            return -1;
        } else if(this.getPontuacao() < nextPlayer.getPontuacao()) {
            return 1;
        } else {
            return 0;
        }
    }
}