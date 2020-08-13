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

    public CreatorPDF(List<Historico> historicos, String tipo) {
        this.historicos = historicos;
        this.tipo = tipo;
    }

    public void creatPDF() throws Exception {

        // Configuração PDF
        Rectangle pageSize = new Rectangle(PageSize.A4);
        pageSize.setBackgroundColor(new BaseColor(5, 17, 41));
        Document pdf = new Document(pageSize);
        PdfWriter.getInstance(pdf, new FileOutputStream("relatorios/World_of_Warships_Relatório.pdf"));
        pdf.open();

        
        // -- LOGO --
        Image logo = Image.getInstance("images/logo.png");
        logo.setAbsolutePosition(150, 580);
        pdf.add(logo);

        // -- FONTE --
        Font fonteTitulo = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, new BaseColor(BaseColor.WHITE.getRGB()));
        Font fonteTexto = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(BaseColor.WHITE.getRGB()));

        paragraph = new Paragraph("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        pdf.add(paragraph);

        paragraph = new Paragraph("RELATÓRIO DAS " + tipo, fonteTitulo);
        paragraph.setAlignment(1);              // -- Centralizar Texto --
        pdf.add(paragraph);

        for(int i = 0; i < historicos.size(); i++) {
            paragraph = new Paragraph("\n\n -------------- [ " + (i + 1) + "ª ] -------------- \n", fonteTexto);
            paragraph.setAlignment(1);
            pdf.add(paragraph);
            paragraph = new Paragraph("Player Desafiado: ", fonteTexto);
            paragraph.setAlignment(1);
            pdf.add(paragraph);
            paragraph = new Paragraph("Formação Desafiante: " + historicos.get(i).getFormacaoDesafiante(), fonteTexto);
            paragraph.setAlignment(1);
            pdf.add(paragraph);
            paragraph = new Paragraph("Formação Desafiado: " + historicos.get(i).getFormacaoDesafiado(), fonteTexto);
            paragraph.setAlignment(1);
            pdf.add(paragraph);
        }

        pdf.close();
    }
}
