package model;

// APIs
import java.util.*;

// Pacotes
import enums.Orientacao;
import view.*;

public class EditarFormacao {

    // Atributos
    private TelaEscolherEmbarcacoes telaEscolherEmbarcacoes;
    private TelaEditarFormacao telaEditarFormacao;

    private List<String> localizacaoDaEmbarcacao = new ArrayList<String>();

    private Player player;

    private int qntdEmbarcacoes2PDisponivel = 2;
    private int qntdEmbarcacoes3PDisponivel = 2;
    private int tamanhoEmbarcacaoAtual;

    // -------------------- Atributos de Validação da Embarcação Atual --------------------
    private List<List<String>> formacao = new ArrayList<List<String>>();
    private int indexReferenciaLinha;                         // Index da referência da linha contida em mapa
    private int indexPrimeiraCelulaSelecionada;               // Index do primeiro JButton selecionado em mapa
    private int indexUltimaCelulaSelecionada;                 // Index do último JButton selecionado em mapa
    private int indexReferenciaUltimaLinhaSelecionada;

    // Definir Orientação da Embarcação Atual
    private Orientacao orientacaoEmbarcacaoHorizontal = null;
    private Orientacao orientacaoEmbarcacaoVerical = null;

    // Setters
    public void setTelaEscolherEmbarcacoes(TelaEscolherEmbarcacoes telaEscolherEmbarcacoes) {
        this.telaEscolherEmbarcacoes = telaEscolherEmbarcacoes;
    }

    public void setTelaEditarFormacao(TelaEditarFormacao telaEditarFormacao) {
        this.telaEditarFormacao = telaEditarFormacao;
    }

    public void setLocalizacaoDaEmbarcacao(List<String> localizacaoDaEmbarcacao) {
        this.localizacaoDaEmbarcacao = localizacaoDaEmbarcacao;
    }

    public void setLocalizacaoDaEmbarcacao(String localizacaoDaEmbarcacao) {
        this.localizacaoDaEmbarcacao.add(localizacaoDaEmbarcacao);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setQntdEmbarcacoes2PDisponivel(int qntdEmbarcacoes2PDisponivel) {
        this.qntdEmbarcacoes2PDisponivel = qntdEmbarcacoes2PDisponivel;
    }

    public void setQntdEmbarcacoes3PDisponivel(int qntdEmbarcacoes3PDisponivel) {
        this.qntdEmbarcacoes3PDisponivel = qntdEmbarcacoes3PDisponivel;
    }

    public void reduzirTamanhoEmbarcacaoAtual() {
        this.tamanhoEmbarcacaoAtual--;
    }

    public void setTamanhoEmbarcacaoAtual(int tamanhoEmbarcacaoAtual) {
        this.tamanhoEmbarcacaoAtual = tamanhoEmbarcacaoAtual;

        if(tamanhoEmbarcacaoAtual == 2) {
            qntdEmbarcacoes2PDisponivel--;
        } else {
            qntdEmbarcacoes3PDisponivel--;
        }
    }

    public void setFormacao(List<List<String>> formacao) {
        this.formacao = formacao;
    }

    public void addFormacao(List<String> localizacaoDaEmbarcacao) {
        this.formacao.add(localizacaoDaEmbarcacao);
    }

    public void setIndexReferenciaLinha(int indexReferenciaLinha) {
        this.indexReferenciaLinha = indexReferenciaLinha;
    }

    public void setIndexPrimeiraCelulaSelecionada(int indexPrimeiraCelulaSelecionada) {
        this.indexPrimeiraCelulaSelecionada = indexPrimeiraCelulaSelecionada;
    }

    public void setIndexUltimaCelulaSelecionada(int indexUltimaCelulaSelecionada) {
        this.indexUltimaCelulaSelecionada = indexUltimaCelulaSelecionada;
    }

    public void setIndexReferenciaUltimaLinhaSelecionada(int indexReferenciaUltimaLinhaSelecionada) {
        this.indexReferenciaUltimaLinhaSelecionada = indexReferenciaUltimaLinhaSelecionada;
    }

    public void setOrientacaoEmbarcacaoHorizontal(Orientacao orientacaoEmbarcacaoHorizontal) {
        this.orientacaoEmbarcacaoHorizontal = orientacaoEmbarcacaoHorizontal;
    }

    public void setOrientacaoEmbarcacaoVertical(Orientacao orientacaoEmbarcacaoVertical) {
        this.orientacaoEmbarcacaoVerical = orientacaoEmbarcacaoVertical;
    }

    // Getters
    public TelaEscolherEmbarcacoes getTelaEscolherEmbarcacoes() {
        return telaEscolherEmbarcacoes;
    }

    public TelaEditarFormacao getTelaEditarFormacao() {
        return telaEditarFormacao;
    }

    public List<String> getLocalizacaoDaEmbarcacao() {
        return localizacaoDaEmbarcacao;
    }

    public Player getPlayer() {
        return player;
    }

    public int getQntdEmbarcacoes2PDisponivel() {
        return qntdEmbarcacoes2PDisponivel;
    }

    public int getQntdEmbarcacoes3PDisponivel() {
        return qntdEmbarcacoes3PDisponivel;
    }

    public int getTamanhoEmbarcacaoAtual() {
        return tamanhoEmbarcacaoAtual;
    }

    public List<List<String>> getFormacao() {
        return formacao;
    }

    public int getIndexReferenciaLinha() {
        return indexReferenciaLinha;
    }

    public int getIndexPrimeiraCelulaSelecionada() {
        return indexPrimeiraCelulaSelecionada;
    }

    public int getIndexUltimaCelulaSelecionada() {
        return indexUltimaCelulaSelecionada;
    }

    public int getIndexReferenciaUltimaLinhaSelecionada() {
        return indexReferenciaUltimaLinhaSelecionada;
    }

    public Orientacao getOrientacaoEmbarcacaoHorizontal() {
        return orientacaoEmbarcacaoHorizontal;
    }

    public Orientacao getOrientacaoEmbarcacaoVerical() {
        return orientacaoEmbarcacaoVerical;
    }
}
