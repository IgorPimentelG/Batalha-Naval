package main;

import view.TelaLogin;
import view.TelaSpash;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        TelaSpash telaSpash = new TelaSpash();

        try {
            Thread.sleep(5000);
            telaSpash.dispose();
            new TelaLogin();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,
                    "<html><center>HOUVE UM ERRO AO INICIALIZAR O JOGO! <br>REINICIE NOVAMENTE O JOGO<center></html>",
                    "≋ ATENÇÃO! ≋", JOptionPane.ERROR_MESSAGE);
        }
    }
}