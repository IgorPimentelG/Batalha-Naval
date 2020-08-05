package model;

// -- APIs --
import java.util.*;

// -- Pacotes --
import enums.Orientacao;

public class Computador {

    // -- Atributos --
    private List<List<String>> formacao= new ArrayList<List<String>>();
    private List<List<String>> matriz = new ArrayList<List<String>>();

    // -- Construtor --
    public Computador() {
        setFormacao();
    }

    // -- Métodos --
    private void setFormacao() {

        gerarMatriz();

        int embarcacao2PartesDisponiveis = 2;
        int embarcacao3PartesDisponiveis = 2;

        boolean flagEmbarcacao3Partes = false;

        while(embarcacao2PartesDisponiveis > 0 || embarcacao3PartesDisponiveis > 0) {

            List<String> embarcaoAtual = new ArrayList<String>();
            Orientacao orientacao = sortearOrientacao();

            // SORTEAR PRIMEIRA POSIÃ‡AO
            int indexLinha = (int) (Math.random() * matriz.size());
            int indexCelula = (int) (Math.random() * matriz.get(indexLinha).size());

            embarcaoAtual.add(matriz.get(indexLinha).get(indexCelula));				// Salvar primeira posição seleionada

            while(embarcacao2PartesDisponiveis > 0) {
                if(orientacao == Orientacao.VERTICAL) {
                    if(indexCelula == 4) {
                        embarcaoAtual.add(matriz.get(indexLinha).get(indexCelula - 1));
                    } else {
                        embarcaoAtual.add(matriz.get(indexLinha).get(indexCelula + 1));
                    }
                } else if(orientacao == Orientacao.HORIZONTAL) {
                    if(indexLinha == 4) {
                        embarcaoAtual.add(matriz.get(indexLinha - 1).get(indexCelula));
                    } else {
                        embarcaoAtual.add(matriz.get(indexLinha + 1).get(indexCelula));
                    }
                }
                break;
            }

            while(flagEmbarcacao3Partes) {
                if(orientacao == Orientacao.VERTICAL) {
                    if(indexCelula >= 3) {
                        embarcaoAtual.add(matriz.get(indexLinha).get(indexCelula - 1));
                        embarcaoAtual.add(matriz.get(indexLinha).get(indexCelula - 2));
                    } else {
                        embarcaoAtual.add(matriz.get(indexLinha).get(indexCelula + 1));
                        embarcaoAtual.add(matriz.get(indexLinha).get(indexCelula + 2));
                    }
                } else if(orientacao == Orientacao.HORIZONTAL) {
                    if(indexLinha >= 3) {
                        embarcaoAtual.add(matriz.get(indexLinha - 1).get(indexCelula));
                        embarcaoAtual.add(matriz.get(indexLinha - 2).get(indexCelula));
                    } else {
                        embarcaoAtual.add(matriz.get(indexLinha + 1).get(indexCelula));
                        embarcaoAtual.add(matriz.get(indexLinha + 2).get(indexCelula));
                    }
                }
                break;
            }

            if(formacao.isEmpty()) {
                formacao.add(embarcaoAtual);
                embarcacao2PartesDisponiveis--;
            } else {
                boolean flag = false;

                for(List<String> embarcaoSalva : formacao) {
                    for(String posicao : embarcaoAtual) {
                        if(embarcaoSalva.contains(posicao)) {
                            flag = true;
                        }
                    }
                }

                if(!flag) {
                    formacao.add(embarcaoAtual);

                    if(embarcaoAtual.size() == 2) {
                        embarcacao2PartesDisponiveis--;
                    } else {
                        embarcacao3PartesDisponiveis--;
                    }
                }
            }

            if(embarcacao2PartesDisponiveis == 0) {
                flagEmbarcacao3Partes = true;
            }
        }
    }

    private void gerarMatriz() {
        int contador = 1;
        String posicao = "";

        for(int i = 1; i < 6; i++) {

            List<String> linha = new ArrayList<String>();

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
                linha.add(posicao);
            }
            matriz.add(linha);
        }
    }

    private Orientacao sortearOrientacao() {
        int index = (int) (Math.random() * Orientacao.values().length);
        return Orientacao.values()[index];
    }

    public List<List<String>> getFormacao() {
        return formacao;
    }
}
