package controller;

// -- APIS --
import java.util.*;
import java.util.regex.*;

// -- Pacotes --
import exceptions.*;
import model.Historico;
import model.Player;
import persistencia.Persistencia;

public class PlayerControl {

    private List<Player> usuariosCadastrados = new ArrayList<Player>();

    public Player cadastrarUsuario(String nickname, String email, String password) throws Exception, EmailInvalidoException {
        Player player = new Player(nickname, email, password);

        if(validarEmail(email) == false) {
            throw new EmailInvalidoException();
        } else if(pesquisarPlayer(player) == null) {
            usuariosCadastrados.add(player);
        } else {
            throw new Exception("DADOS JÁ CADASTRADO!");
        }

        salvar();
        return player;
    }

    public Player pesquisarPlayer(Player player) {
        for(Player playerCadastrado : usuariosCadastrados)  {
            if(playerCadastrado.getEmail().contentEquals(player.getEmail()) && playerCadastrado.getNickname().equals(player.getNickname())) {
                return playerCadastrado;
            }
        }
        return null;
    }

    public Player pesquisarNickName(String nickname) {
        for(Player playerCadastrado : usuariosCadastrados) {
            if(playerCadastrado.getNickname().equals(nickname)) {
                return playerCadastrado;
            }
        }
        return null;
    }

    public Player autenticar(String email, String password) throws DadosInvalidosException {
        for(Player playerCadastrado : usuariosCadastrados) {
            if(playerCadastrado.getEmail().equals(email) && playerCadastrado.getPassword().contentEquals(password)) {
                return playerCadastrado;
            }
        }
        throw new DadosInvalidosException();
    }

    public Player autenticarEmail(String email) throws DadosInvalidosException {
        for(Player playerCadastrado : usuariosCadastrados) {
            if(playerCadastrado.getEmail().equals(email)) {
                return playerCadastrado;
            }
        }
        throw new DadosInvalidosException();
    }

    public boolean validarEmail(String email) {
        boolean emailValido = false;

        if(email != null && email.length() > 0) {
            String caracteresValidos = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(caracteresValidos, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);

            if(matcher.matches()) {
                emailValido = true;
            }
        }
        return emailValido;
    }

    public void salvarFormacao(Player player, List<List<String>> formacao) {
        Player playerCadastrado = pesquisarPlayer(player);
        playerCadastrado.setFormacao(formacao);
        salvar();
    }

    public void salvarPontuacaoGanha(Player player, int pontuacao) {
        Player playerCadastrado = pesquisarPlayer(player);
        playerCadastrado.setPontuacao(player.getPontuacao() + pontuacao);
        salvar();
    }

    public void salvarPontuacaoPerda(Player player, int pontuacao) {
        Player playerCadastrado = pesquisarPlayer(player);
        playerCadastrado.setPontuacao(player.getPontuacao() - pontuacao);
        salvar();
    }

    public void salvarHistorico(Player player, Historico historico) {
        Player playerCadastrado = pesquisarPlayer(player);
        playerCadastrado.addHistorico(historico);
        salvar();
    }

    private void salvar() {
        new Persistencia().salvarController(this);
    }

    public List<Player> getUsuariosCadastrado() {
        Collections.sort(this.usuariosCadastrados);
        return usuariosCadastrados;
    }
}
