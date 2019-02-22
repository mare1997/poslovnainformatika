package com.pi.PoslovnaInformatika.reports;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class ReportGenerator {

	public static String OUT_PUT = "/reports/piReport.docx";
	public static String REPORT = "/reports/finalpiv6.jrprint";

	public void generateReport(String reportPath,
	        Map<String, Object> map, Connection con) {
	    try {

	        JasperReport jr = JasperCompileManager.compileReport(ClassLoader.getSystemResourceAsStream(reportPath));
	        JasperPrint jp = JasperFillManager.fillReport(jr, map, con);
	        JRDocxExporter export = new JRDocxExporter();
	    export.setExporterInput(new SimpleExporterInput(jp));
	    export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(OUT_PUT)));
	    SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();
	    export.setConfiguration(config);
	    export.exportReport();
	    } catch (JRException ex) {
	        ex.printStackTrace();   
	    }
	} 
	public void exportToPdf(){
		try {
			Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/poslovnainformatika?useSSL=false", "root", "root");
			System.out.println(dbConnection.isValid(0));
			Map<String, Object> map = new HashMap<>();
	        map.put("id_fakture", 2);//parameter name should be like it was named inside your report.
	        
			JasperPrint jp = JasperFillManager.fillReport(getClass().getResource("/reports/finalpiv6.jasper").openStream(),map, dbConnection);
			//eksport
			File pdf = File.createTempFile("output.", ".pdf");
			JasperExportManager.exportReportToPdfStream(jp, new FileOutputStream(pdf));
		}catch (Exception ex) {
				ex.printStackTrace();
			}
		}

}