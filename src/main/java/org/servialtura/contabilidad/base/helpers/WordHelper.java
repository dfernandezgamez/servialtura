package org.servialtura.contabilidad.base.helpers;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.context.FacesContext;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.servialtura.contabilidad.base.model.Presupuesto;


public class WordHelper {

	public static XWPFDocument getPresupuesto(Presupuesto presupuesto){
		XWPFDocument presupuestoDoc = new XWPFDocument();
		crearPortada(presupuestoDoc);
		crearCuerpo(presupuestoDoc);
		crearCondicionesLegales(presupuestoDoc);
		return presupuestoDoc;
		
	}

	private static void crearCondicionesLegales(XWPFDocument presupuesto) {
		XWPFParagraph tituloCondiciones = presupuesto.createParagraph();
		XWPFRun run=tituloCondiciones.createRun();
		run.addBreak(BreakType.PAGE);
		run.setBold(true);
		run.setFontSize(12);
		run.setText("CONDICIONES GENERALES");
		run.setFontFamily("Helvetica");
		XWPFParagraph parrafoCondiciones = presupuesto.createParagraph();
		parrafoCondiciones.setIndentFromLeft(800);
		XWPFRun run2=parrafoCondiciones.createRun();
		run2.addBreak();
		run2.setFontSize(10);
		run2.setText("Este presupuesto Este presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuest");
		run2.setFontFamily("Helvetica");
		
		
		
		XWPFParagraph tituloFormas = presupuesto.createParagraph();
		XWPFRun run3=tituloFormas.createRun();
		run3.setBold(true);
		run3.setFontSize(12);
		run3.setText("FORMA DE PAGO");
		run3.setFontFamily("Helvetica");
		XWPFParagraph parrafoFormaPago = presupuesto.createParagraph();
		parrafoFormaPago.setIndentFromLeft(800);
		XWPFRun run4=parrafoFormaPago.createRun();
		run4.addBreak();
		run4.setFontSize(10);
		run4.setText("Este presupuesto Este presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuestEste presupuest");
		run4.setFontFamily("Helvetica");
		
		

		
	}

	private static void crearCuerpo(XWPFDocument presupuesto) {
		addParrafoImagen(presupuesto,297/2,210/2,ParagraphAlignment.LEFT);


		XWPFParagraph parrafo3 = presupuesto.createParagraph();
		parrafo3.setVerticalAlignment(TextAlignment.TOP);
		parrafo3.setAlignment(ParagraphAlignment.RIGHT);
		XWPFRun r3 = parrafo3.createRun();
		r3.setBold(true);
		r3.setFontSize(12);
		r3.setText("COMUNIDAD DE PROPIETARIOS DE LA CALLE DE A TOMAR POR CULO NUMERO 5 PERO MUY JRTO TODO");
		r3.setFontFamily("Helvetica");
		r3.addBreak();

		XWPFParagraph parrafo = presupuesto.createParagraph();
		parrafo.setVerticalAlignment(TextAlignment.TOP);
		parrafo.setAlignment(ParagraphAlignment.LEFT);
		XWPFRun r1 = parrafo.createRun();
		r1.setFontSize(10);
		r1.setText("Atendiendo a su solicitud, les presentamos nuestra mejor oferta, según detallamos a continuación:");
		r1.setFontFamily("Helvetica");
		r1.addBreak();

		XWPFParagraph parrafo2 = presupuesto.createParagraph();
		parrafo2.setVerticalAlignment(TextAlignment.TOP);
		parrafo2.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r2 = parrafo2.createRun();
		r2.setBold(true);
		r2.setFontSize(12);
		r2.setUnderline(UnderlinePatterns.SINGLE);
		r2.setText("ANCLAJE Y REJUNTEO DE CHAPADO DE PIEDRA NATURAL");
		r2.setFontFamily("Helvetica");
		r2.addBreak();

		
		addListaTrabajos(presupuesto);

	}

	private static void addListaTrabajos(XWPFDocument presupuesto) {
		for(int i=1;i<10;i++){
			XWPFParagraph para = presupuesto.createParagraph();
			para.setVerticalAlignment(TextAlignment.TOP);
			para.setAlignment(ParagraphAlignment.CENTER);
			para.setNumID(BigInteger.ONE);
			XWPFRun run=para.createRun();
			run.setText( "Elemento: "+String.valueOf(i));

		}
	}

	private static void guardarPresupuesto(XWPFDocument presupuesto) throws FileNotFoundException, IOException {
		FileOutputStream ficheroSalida = new FileOutputStream("presupuesto_servialtura.docx");
		presupuesto.write(ficheroSalida);
		ficheroSalida.close();
	}

	private static void crearPortada(XWPFDocument presupuesto) {
		addParrafoImagen(presupuesto,297,210,ParagraphAlignment.CENTER);
		addParrafoInformacionObra(presupuesto);
		addPiePagina(presupuesto);

	}

	private static void addParrafoInformacionObra(XWPFDocument presupuesto) {
		XWPFParagraph parrafo = presupuesto.createParagraph();
		parrafo.setVerticalAlignment(TextAlignment.TOP);
		parrafo.setAlignment(ParagraphAlignment.CENTER);

		XWPFRun r1 = parrafo.createRun();
		r1.setBold(true);
		r1.setFontSize(20);
		r1.setText("PRESUPUESTO Nº: 213-10");
		r1.setFontFamily("Helvetica");
		r1.addBreak();


		XWPFParagraph parrafo2 = presupuesto.createParagraph();
		parrafo2.setVerticalAlignment(TextAlignment.TOP);
		parrafo2.setAlignment(ParagraphAlignment.CENTER);

		XWPFRun r2 = parrafo2.createRun();
		r2.setBold(true);
		r2.setFontSize(10);
		r2.setText("CONCEJO DE ILLANO Y CCONCEJO DE ILLANO Y ");
		r2.setFontFamily("Helvetica");
		r2.addBreak(BreakType.PAGE);
	}

	private static void addPiePagina(XWPFDocument presupuesto) {
		CTSectPr sectPr = presupuesto.getDocument().getBody().addNewSectPr();
		XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(presupuesto, sectPr);
		CTP ctpFooter = CTP.Factory.newInstance();
		XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, presupuesto);
		footerParagraph.setAlignment(ParagraphAlignment.LEFT);
		XWPFRun r1 = footerParagraph.createRun();
		r1.setFontSize(6);
		r1.setText("SERVIALTURA S.L.L.");
		r1.addBreak();
		r1.setText("CIF: B52506664");
		r1.addBreak();
		r1.setText("Tlfs: 666 63 07 86  665 38 37 07 984 06 73 75");
		r1.addBreak();
		r1.setText("Correo electrónico: servialtura@gmail.com");


		XWPFParagraph[] parsFooter = new XWPFParagraph[1];
		parsFooter[0] = footerParagraph;
		try {
			policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void addParrafoImagen(XWPFDocument presupuesto,int ancho,int alto, ParagraphAlignment alineado) {
		XWPFParagraph parrafoImagen = presupuesto.createParagraph();
		XWPFRun r = parrafoImagen.createRun();
		try {
			
			InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/images/servialtura.png");
			r.addPicture(stream, XWPFDocument.PICTURE_TYPE_PNG, "servialtura.png", Units.toEMU(ancho), Units.toEMU(alto));
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 200x200 pixels
		r.addBreak(BreakType.TEXT_WRAPPING);
		parrafoImagen.setAlignment(alineado);
	}
}
