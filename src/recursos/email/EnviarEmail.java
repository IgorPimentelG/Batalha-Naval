package recursos.email;

import controller.HistoricoControl;
import controller.PlayerControl;
import exceptions.DadosInvalidosException;
import model.Player;
import persistencia.Persistencia;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EnviarEmail extends EmailSetup {

    private Persistencia persistencia       = new Persistencia();
    private PlayerControl controllerPlayer  = persistencia.recuperarController();

    private String enderecoEmail;

    private Message mensagem;

    public EnviarEmail(String enderecoEmail) {
        this.enderecoEmail = enderecoEmail;
        mensagem = super.setup();
    }

    public void dadosConta(Player player) throws Exception {
        HistoricoControl historicoControl = new HistoricoControl();

        criarMensagem("World of Warships - Conta Excluída", "Olá, sua conta foi desativada permanentemente :( " +
                "\nEm anexo contém arquivos com seus históricos enquanto sua conta foi ativa." + "\n\nEsperamos você de volta!!",
                historicoControl.getArquivoPartidasGanhas(player),
                historicoControl.getArquivoPartidasPerdidas(player));

        Transport.send(mensagem);
    }

    public void recuperacaoConta() throws DadosInvalidosException, MessagingException {

        Player player = controllerPlayer.autenticarEmail(enderecoEmail);
        String novaSenha = gerarNovaSenha();

        criarMensagem("World of Warships - Recuperação", "Olá, " + player.getNickname() + "\n\nEsqueceu sua senha? Uma nova senha foi gerada." +
                "\nUtilize a senha abaixo para acessar sua conta." +
                "\nSua nova senha: " + novaSenha, null, null);

        player.setPassword(novaSenha);
        persistencia.salvarController(controllerPlayer);

        Transport.send(mensagem);
    }

    private void criarMensagem(String titulo, String texto, FileDataSource anexo1, FileDataSource anexo2) throws MessagingException {
        mensagem.setFrom(new InternetAddress(super.getRemetente()));
        mensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse(enderecoEmail));
        mensagem.setSubject(titulo);

        if(anexo1 != null && anexo2 != null) {
            MimeBodyPart corpoTexto = new MimeBodyPart();
            corpoTexto.setText(texto);

            MimeBodyPart arquivoAnexo1 = new MimeBodyPart();
            arquivoAnexo1.setDataHandler(new DataHandler(anexo1));
            arquivoAnexo1.setFileName(anexo1.getName());

            MimeBodyPart arquivoAnexo2 = new MimeBodyPart();
            arquivoAnexo2.setDataHandler(new DataHandler(anexo2));
            arquivoAnexo2.setFileName(anexo2.getName());

            Multipart mp = new MimeMultipart();
            mp.addBodyPart(corpoTexto);
            mp.addBodyPart(arquivoAnexo1);
            mp.addBodyPart(arquivoAnexo2);

            mensagem.setContent(mp);
        } else {
            mensagem.setText(texto);
        }
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
