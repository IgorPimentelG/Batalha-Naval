package recursos.email;

import javax.mail.*;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

import controller.PlayerControl;
import exceptions.DadosInvalidosException;
import model.Player;
import persistencia.Persistencia;

public class RecuperarConta extends EmailSetup {
    public void enviarEmail(String email) throws DadosInvalidosException, MessagingException {
        Persistencia persistencia = new Persistencia();
        PlayerControl controllerPlayer = persistencia.recuperarController();

        // VALIDAR PLAYER
        Player player = controllerPlayer.autenticarEmail(email);

        String novaSenha = gerarNovaSenha();

        // CRIAR MENSAGEM
        Message mensagem = super.setup();
        mensagem.setFrom(new InternetAddress(super.getRemetente()));
        mensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse(player.getEmail()));
        mensagem.setSubject("Batalha Naval - RecuperaÃ§Ã£o");
        mensagem.setText("OlÃ¡ " + player.getNickname() + "\n\nEsqueceu sua senha? Uma nova senha foi gerada." +
                "\nUtilize a senha abaixo para acessar sua conta." +
                "\nSua nova senha: " + novaSenha);

        Transport.send(mensagem);

        player.setPassword(novaSenha);
        persistencia.salvarController(controllerPlayer);
    }

    private String gerarNovaSenha() {
        String novaSenha = "";

        String[] caracteres = {
                "A", "B", "C", "D", "E", "Z", "H", "I", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "V", "X",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "-", ".", "_"
        };

        for(int i = 0; i < 8; i++) {
            novaSenha += caracteres[((int) (Math.random() * caracteres.length))];
        }

        return novaSenha;
    }
}
