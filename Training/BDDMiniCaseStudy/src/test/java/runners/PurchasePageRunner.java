package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src//test//resources//features//PurchasePage.feature",
		dryRun=true,
		glue= {"stepDefs"},
		monochrome = true,
		plugin= {"pretty",
		  "html:target//Reports//HtmlReport.html",
		 //"json:target//Reports//jsonReport.json",
		 //"usage:target//Reports//UsageReport",
		 //"rerun:target//failedScenarios.txt",
		 }
		
		)

public class PurchasePageRunner extends AbstractTestNGCucumberTests{
        
}


