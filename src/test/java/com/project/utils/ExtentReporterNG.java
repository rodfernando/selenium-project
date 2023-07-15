package com.project.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.BeforeEach;

public class ExtentReporterNG {


    public static ExtentReports getReportObject() {
        String path = System.getProperty("user.dir") + "\\reports\\index.html"; //Definição do local dos reports (pasta do projeto)
        //ExtentSpark é responsável por atrelar o path e realizar configurações:
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Tests Results");

        //Extentsreports é responsável pelo report em sim, pegando as configs do ExtentSpark
        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("QA", "Rodrigo Oliveira");
        return extentReports;
    }
}
