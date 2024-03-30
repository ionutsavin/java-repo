package org.example.commands;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.example.repository.Repository;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class ReportCommand implements Command {

    private final Repository repository;

    public ReportCommand(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        String htmlReport = generateHTMLReport();
        if (htmlReport != null) {
            File reportFile = writeHTMLReportToFile(htmlReport);
            if (reportFile != null) {
                openHTMLReport(reportFile);
            }
        }
    }

    private String generateHTMLReport() {
        try {
            VelocityEngine velocityEngine = new VelocityEngine();
            velocityEngine.init();
            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("repository", repository);
            Template template = velocityEngine.getTemplate("report_template.vm");
            StringWriter writer = new StringWriter();
            template.merge(velocityContext, writer);
            return writer.toString();
        } catch (Exception e) {
            System.out.println("Error generating HTML report: " + e.getMessage());
            return null;
        }
    }


    private File writeHTMLReportToFile(String htmlReport) {
        try {
            File reportFile = new File("repository_report.html");
            FileWriter writer = new FileWriter(reportFile);
            writer.write(htmlReport);
            writer.close();
            return reportFile;
        } catch (IOException e) {
            System.out.println("Error writing HTML report to file: " + e.getMessage());
            return null;
        }
    }

    private void openHTMLReport(File reportFile) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(reportFile);
            } else {
                System.out.println("Desktop is not supported on this platform.");
            }
        } catch (IOException e) {
            System.out.println("Error opening HTML report: " + e.getMessage());
        }
    }
}
