package exceptions;

public class EmailInvalidoException extends Exception {
    public EmailInvalidoException() {
        super("<html><center>ENDEREÇO DE E-MAIL NÃO É VÁLIDO. <br/>POR FAVOR INSIRA NOVAMENTE PARA COMPLETAR O CADASTRO.</center></html>");
    }
}