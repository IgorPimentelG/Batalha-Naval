package recursos.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import model.Historico;
import recursos.Cores;
import recursos.Imagens;

import java.awt.*;
import java.io.FileOutputStream;
import java.util.List;

public class CreatorPDF {

    private Paragraph paragraph;
    private List<Historico> historicos;
    private String tipo;
    private String nomeDoArquivo;

    public CreatorPDF(List<Historico> historicos, String tipo, String nomeDoArquivo) {
        this.historicos     = historicos;
        this.tipo           = tipo;
        this.nomeDoArquivo  = nomeDoArquivo;
    }

    public void creatPDF() throws Exception {

        // Configura√ß√£o PDF
        Rectangle pageSize = new Rectangle(PageSize.A4);
        pageSize.setBackgroundColor(new BaseColor(5, 17, 41));

        Document pdf = new Document(pageSize);
        PdfWriter.getInstance(pdf, new FileOutputStream("relatorios/" + nomeDoArquivo + ".pdf"));

        pdf.open();

        // -- LOGO --
        Image logo = Image.getInstance("images/logo.png");
        logo.setAbsolutePosition(150, 580);
        pdf.add(logo);

        // -- FONTE --
        Font fonteTitulo  = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, new BaseColor(BaseColor.WHITE.getRGB()));
        Font fonteTexto   = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(BaseColor.WHITE.getRGB()));

        paragraph = new Paragraph("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        pdf.add(paragraph);

        paragraph = new Paragraph("RELAT”RIO DAS " + tipo, fonteTitulo);
        paragraph.setAlignment(1);              // -- Centralizar Texto --
        pdf.add(paragraph);

        for(int i = 0; i < historicos.size(); i++) {
            paragraph = new Paragraph("\n\n ------------------------------------------- [ " + (i + 1) + "™ ] ------------------------------------------- \n", fonteTexto);
            paragraph.setAlignment(1);
            pdf.add(paragraph);
            paragraph = new Paragraph("Player Desafiado: " + historicos.get(i).getDesafiado(), fonteTexto);
            paragraph.setAlignment(1);
            pdf.add(paragraph);
            paragraph = new Paragraph("PontuaÁ„o: " + historicos.get(i).getPontuacao(), fonteTexto);
            paragraph.setAlignment(1);
            pdf.add(paragraph);
            paragraph = new Paragraph("FormaÁ„o Desafiante: " + historicos.get(i).getFormacaoDesafiante(), fonteTexto);
            paragraph.setAlignment(1);
            pdf.add(paragraph);
            paragraph = new Paragraph("FormaÁ„o Desafiado: " + historicos.get(i).getFormacaoDesafiado(), fonteTexto);
            paragraph.setAlignment(1);
            pdf.add(paragraph);
        }
        pdf.close();
    }
}
