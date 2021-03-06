package org.servialtura.contabilidad.base.helpers;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import org.apache.commons.lang3.text.WordUtils;
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
import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.servialtura.contabilidad.base.model.Partida;
import org.servialtura.contabilidad.base.model.Presupuesto;


public class WordHelper {
	
	private static ResourceBundle myResource =     ResourceBundle.getBundle("org.servialtura.contabilidad.i18n.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());

	public static XWPFDocument getPresupuesto(Presupuesto presupuesto){
		XWPFDocument presupuestoDoc = new XWPFDocument();
		crearPortada(presupuestoDoc,presupuesto);
		crearCuerpo(presupuestoDoc,presupuesto);
		crearCondicionesLegales(presupuestoDoc);
		return presupuestoDoc;

	}


	private static void crearCondicionesLegales(XWPFDocument presupuesto) {
		DateTime hoy = new DateTime();
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
		run2.setText(myResource.getString("condicionesLegales1"));
		run2.addBreak();
		run2.setFontFamily("Helvetica");
		XWPFRun run22=parrafoCondiciones.createRun();
		run22.addBreak();
		run22.setFontSize(10);
		run22.setText(myResource.getString("condicionesLegales2"));
		run22.addBreak();
		run22.setFontFamily("Helvetica");
		XWPFRun run33=parrafoCondiciones.createRun();
		run33.addBreak();
		run33.setFontSize(10);
		run33.setText(myResource.getString("condicionesLegales3"));
		run33.addBreak();
		run33.setFontFamily("Helvetica");



		XWPFParagraph tituloFormas = presupuesto.createParagraph();
		XWPFRun run3=tituloFormas.createRun();
		run3.setBold(true);
		run3.setFontSize(12);
		run3.setText("FORMA DE PAGO");
		run3.setFontFamily("Helvetica");
		XWPFParagraph parrafoFormaPago = presupuesto.createParagraph();
		parrafoFormaPago.setIndentFromLeft(800);
		XWPFRun run4=parrafoFormaPago.createRun();
		run4.setFontSize(10);
		run4.addBreak();
		run4.setText(myResource.getString("formaPago1"));
		run4.addBreak();
		run4.setFontFamily("Helvetica");
		XWPFRun run44=parrafoFormaPago.createRun();
		run44.setFontSize(10);
		run44.addBreak();
		run44.setText(myResource.getString("formaPago2"));
		run44.setFontFamily("Helvetica");
		run44.addBreak();
		XWPFRun run55=parrafoFormaPago.createRun();
		run55.setFontSize(10);
		run55.addBreak();
		run55.setText(myResource.getString("formaPago3"));
		run55.setFontFamily("Helvetica");
		run55.addBreak();
		
		
		XWPFParagraph tituloValidez = presupuesto.createParagraph();
		XWPFRun run77=tituloValidez.createRun();
		run77.setBold(true);
		run77.setFontSize(12);
		run77.setText("VALIDEZ");
		run77.setFontFamily("Helvetica");
		XWPFParagraph parrafoValidez = presupuesto.createParagraph();
		parrafoValidez.setIndentFromLeft(800);
		XWPFRun run88=parrafoValidez.createRun();
		run88.addBreak();
		run88.setFontSize(10);
		run88.setText(myResource.getString("validez"));
		run88.setFontFamily("Helvetica");
		run88.addBreak();


		XWPFParagraph fecha = presupuesto.createParagraph();
		fecha.setAlignment(ParagraphAlignment.RIGHT);
		XWPFRun fechaRun=fecha.createRun();
		fechaRun.setBold(true);
		fechaRun.setFontSize(12);
		fechaRun.setFontFamily("Helvetica");
		fechaRun.addBreak();
		fechaRun.setText("Gijón a "+hoy.getDayOfMonth()+ " de "+ WordUtils.capitalize(hoy.toString("MMMM",Locale.getDefault())) + " de "+ hoy.getYear() );

	}

	private static void crearCuerpo(XWPFDocument presupuestoDoc, Presupuesto presupuesto) {
		addParrafoImagen(presupuestoDoc,297/2,210/2,ParagraphAlignment.LEFT);


		XWPFParagraph parrafo3 = presupuestoDoc.createParagraph();
		parrafo3.setVerticalAlignment(TextAlignment.TOP);
		parrafo3.setAlignment(ParagraphAlignment.RIGHT);
		XWPFRun r3 = parrafo3.createRun();
		r3.setBold(true);
		r3.setFontSize(12);
		r3.setText(presupuesto.getPersonaContacto());
		r3.setFontFamily("Helvetica");
		r3.addBreak();

		XWPFParagraph parrafo = presupuestoDoc.createParagraph();
		parrafo.setVerticalAlignment(TextAlignment.TOP);
		parrafo.setAlignment(ParagraphAlignment.LEFT);
		XWPFRun r1 = parrafo.createRun();
		r1.setFontSize(10);
		r1.setText("Atendiendo a su solicitud, les presentamos nuestra mejor oferta, según detallamos a continuación:");
		r1.setFontFamily("Helvetica");
		r1.addBreak();


		addListaTrabajos(presupuestoDoc,presupuesto);

	}

	private static void addListaTrabajos(XWPFDocument presupuestoDoc, Presupuesto presupuesto) {
		for(Partida partida:presupuesto.getPartidas()){
			XWPFParagraph parrafo2 = presupuestoDoc.createParagraph();
			parrafo2.setVerticalAlignment(TextAlignment.TOP);
			parrafo2.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun r2 = parrafo2.createRun();
			r2.setBold(true);
			r2.setFontSize(12);
			r2.setUnderline(UnderlinePatterns.SINGLE);
			r2.setText(partida.getNombrePartida()+" "+partida.getImportePartida().toString()+" €");
			r2.setFontFamily("Helvetica");
			r2.addBreak();
			
			XWPFParagraph parrafo3 = presupuestoDoc.createParagraph();
			parrafo3.setAlignment(ParagraphAlignment.LEFT);
			XWPFRun r3 = parrafo3.createRun();
			r3.setText(partida.getDescripcionPartida());
			r3.addBreak();
		}
		
		XWPFParagraph importe = presupuestoDoc.createParagraph();
		importe.setVerticalAlignment(TextAlignment.TOP);
		importe.setAlignment(ParagraphAlignment.LEFT);
		XWPFRun run=importe.createRun();
		run.setBold(true);
		run.addBreak();
		run.addBreak();
		run.setText(myResource.getString("importeObra"));
	}

	private static void guardarPresupuesto(XWPFDocument presupuesto) throws FileNotFoundException, IOException {
		FileOutputStream ficheroSalida = new FileOutputStream("presupuesto_servialtura.docx");
		presupuesto.write(ficheroSalida);
		ficheroSalida.close();
	}

	private static void crearPortada(XWPFDocument presupuestoDoc, Presupuesto presupuesto) {
		addParrafoImagen(presupuestoDoc,297,210,ParagraphAlignment.CENTER);
		addParrafoInformacionObra(presupuestoDoc,presupuesto);
		addPiePagina(presupuestoDoc);

	}

	private static void addParrafoInformacionObra(XWPFDocument presupuestoDoc, Presupuesto presupuesto) {
		XWPFParagraph parrafo = presupuestoDoc.createParagraph();
		parrafo.setVerticalAlignment(TextAlignment.TOP);
		parrafo.setAlignment(ParagraphAlignment.CENTER);

		XWPFRun r1 = parrafo.createRun();
		r1.setBold(true);
		r1.setFontSize(20);
		r1.setText("PRESUPUESTO Nº:"+ presupuesto.getNumeroPresupuesto());
		r1.setFontFamily("Helvetica");
		r1.addBreak();


		XWPFParagraph parrafo2 = presupuestoDoc.createParagraph();
		parrafo2.setVerticalAlignment(TextAlignment.TOP);
		parrafo2.setAlignment(ParagraphAlignment.CENTER);

		XWPFRun r2 = parrafo2.createRun();
		r2.setBold(true);
		r2.setFontSize(10);
		r2.setText(presupuesto.getDescripcionSolicitud());
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
		r1.setText("D'ALTURA S.L.");
		r1.addBreak();
		r1.setText("CIF: xxxx");
		r1.addBreak();
		r1.setText("Teléfonos:");
		r1.addBreak();
		r1.setText("Oficina:+34 984067375 // +34 616598617");
		r1.addBreak();
		r1.setText("Obra: +34 665383707");
		r1.addBreak();
		r1.setText("Correo electrónico: renedaltura@gmail.com");


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

			InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/images/daltura.jpg");
			r.addPicture(stream, XWPFDocument.PICTURE_TYPE_JPEG, "daltura.jpg", Units.toEMU(ancho), Units.toEMU(alto));
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
