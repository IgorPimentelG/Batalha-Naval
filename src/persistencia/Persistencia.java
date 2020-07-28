package persistencia;

import java.io.*;

import javax.swing.JOptionPane;

import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.DomDriver;

import controller.PlayerControl;
import exceptions.DadosInvalidosException;

public class Persistencia {

    private XStream xStream = new XStream(new DomDriver("utf-8"));
    private File dadosUsuario = new File("dados.xml");

    public void salvarController(PlayerControl playerControl) {
        try {

            String dados = xStream.toXML(playerControl);

            dadosUsuario.createNewFile();

            PrintWriter gravarDados = new PrintWriter(dadosUsuario);
            gravarDados.print(dados);
            gravarDados.close();

        } catch(Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO AO SALVAR DADOS", JOptionPane.ERROR_MESSAGE);
        }
    }

    public PlayerControl recuperarController() {
        try {
            if(dadosUsuario.exists()) {
                FileInputStream dados = new FileInputStream(dadosUsuario);
                return (PlayerControl) xStream.fromXML(dados);
            }
        } catch(Exception erro) {
            System.out.println(erro.getMessage());
            JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO AO RECUPERAR DADOS", JOptionPane.ERROR_MESSAGE);
        }

        return new PlayerControl();
    }
}