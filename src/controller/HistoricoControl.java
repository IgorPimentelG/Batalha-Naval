package controller;

import model.Historico;
import model.Player;
import recursos.pdf.CreatorPDF;

import javax.activation.FileDataSource;
import java.io.FileOutputStream;
import java.rmi.server.ExportException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HistoricoControl {

    private List<Historico> partidasGanhas = new ArrayList<Historico>();
    private List<Historico> partidasPerdidas = new ArrayList<Historico>();

    private String nomeDoArquivoPartidasGanhas;
    private String nomeDoArquivoPartidasPerdidas;

    private void gerarListas(List<Historico> historicos, String nickName) {
        for(Historico historicoSalvo : historicos) {
            if(historicoSalvo.getVencedor().equals(nickName)) {
                partidasGanhas.add(historicoSalvo);
            } else {
                partidasPerdidas.add(historicoSalvo);
            }
        }
    }

    public void gerarRelatorioPartidasGanhas(Player player) throws Exception {

        gerarListas(player.getHistorico(), player.getNickname());

        nomeDoArquivoPartidasGanhas = "world_of_warships_partidas_ganhas_" + getDataHora() + "_" + player.getNickname();

        if(partidasGanhas.size() == 0) {
            throw new Exception("NÃO HÁ PARTIDAS GANHAS");
        } else {
            new CreatorPDF(partidasGanhas, "PARTIDAS GANHAS", nomeDoArquivoPartidasGanhas).creatPDF();
        }
    }

    public void gerarRelatorioPartidasPerdidas(Player player) throws Exception {

        gerarListas(player.getHistorico(), player.getNickname());

        nomeDoArquivoPartidasPerdidas = "world_of_warships_partidas_perdidas_" + getDataHora() + "_" + player.getNickname();

        if(partidasPerdidas.size() == 0) {
            throw new Exception("NÃO HÁ PARTIDAS PERDIDAS");
        } else {
            new CreatorPDF(partidasPerdidas, "PARTIDAS PERDIDAS", nomeDoArquivoPartidasPerdidas).creatPDF();
        }

    }

    public FileDataSource getArquivoPartidasGanhas(Player player) throws Exception {
        gerarRelatorioPartidasGanhas(player);
        return new FileDataSource("relatorios/" + nomeDoArquivoPartidasGanhas + ".pdf");
    }

    public FileDataSource getArquivoPartidasPerdidas(Player player) throws Exception{
        gerarRelatorioPartidasPerdidas(player);
        return new FileDataSource("relatorios/" + nomeDoArquivoPartidasPerdidas + ".pdf");
    }

    public String getDataHora() {
        LocalDateTime dataAtual = LocalDateTime.now();
        return dataAtual.toString();
    }
}
