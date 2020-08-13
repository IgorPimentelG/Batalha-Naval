package controller;

import model.Historico;
import model.Player;
import recursos.pdf.CreatorPDF;

import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;

public class HistoricoControl {

    private List<Historico> partidasGanhas = new ArrayList<Historico>();
    private List<Historico> partidasPerdidas = new ArrayList<Historico>();

    private void gerarListas(List<Historico> historicos, Player player) {
        for(Historico historicoSalvo : historicos) {
            if(historicoSalvo.getVencedor().equals(player.getNickname())) {
                partidasGanhas.add(historicoSalvo);
            } else {
                partidasPerdidas.add(historicoSalvo);
            }
        }
    }

    public void gerarRelatorioPartidasGanhas(List<Historico> historicos, Player player) throws Exception {

        gerarListas(historicos, player);

        if(partidasGanhas.size() == 0) {
            throw new Exception("NÃO HÁ PARTIDAS GANHAS");
        } else {
            new CreatorPDF(partidasGanhas, "PARTIDAS GANHAS").creatPDF();
        }
    }

    public void gerarRelatorioPartidasPerdidas(List<Historico> historicos, Player player) throws Exception {

        gerarListas(historicos, player);

        if(partidasPerdidas.size() == 0) {
            throw new Exception("NÃO HÁ PARTIDAS PERDIDAS");
        } else {
            new CreatorPDF(partidasPerdidas, "PARTIDAS PERDIDAS");
        }

    }
}
