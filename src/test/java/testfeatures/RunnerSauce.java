package testfeatures;

import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/testfeatures", glue = {"code"},
        tags = {"@CucumberPetStoreTests"},
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:", "json:output/cucumber-reports-jenkins/Cucumber.json"})

public class RunnerSauce {

    @BeforeClass
    public static void setuppre() {

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date date = new Date();
        String dateG = dateFormat.format(date);

        ExtentProperties extentProperties = ExtentProperties.INSTANCE;
        extentProperties.setReportPath("output/cucumber-extent/PetStore-" + dateG + ".html");
    }

    @AfterClass
    public static void setup() {
        Reporter.loadXMLConfig(new File("reports/extent-config.xml"));
    }

    public static void writeExtentReport() {
        System.setProperty("target/cucumber-reports/", "report_____" + ".html");
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setTestRunnerOutput("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

    }
}

