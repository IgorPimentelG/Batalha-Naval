package exceptions;

public class EmailInvalidoException extends Exception {
    public EmailInvalidoException() {
        super("<html><center>ENDERE�O DE E-MAIL N�O � V�LIDO. <br/>POR FAVOR INSIRA NOVAMENTE PARA COMPLETAR O CADASTRO.</center></html>");
    }
}