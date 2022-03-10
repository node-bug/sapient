package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import objects.Pagination;
import objects.PrimaryNavigation;
import objects.SearchResultsPage;
import org.openqa.selenium.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.text.MessageFormat;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import utils.PropertyProvider;
import utils.WebDriverSetup;


public class SearchSteps {

	private Properties properties;
	private WebDriver driver;

	@Before()
	public void beforeActivities() {
		properties = new PropertyProvider().getPropertyFile();
		driver = new WebDriverSetup().getDriver(properties);
	}

	@Given("I opened the Search page")
	public void iOpenedTheSearchPage() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get(properties.getProperty("url.search"));
	}

	@When("I search for {string} via the navigation bar")
	public void i_search_for_via_the_primary_navigation(String string) throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		PrimaryNavigation p = new PrimaryNavigation(driver);
		p.searchFor(string);
	}

	@Then("the Search Results page is opened")
	public void the_Search_Results_page_is_opened() {
		// Write code here that turns the phrase above into concrete actions
		SearchResultsPage s = new SearchResultsPage(driver);
		s.assert_PageTitle();
		s.assert_ResultsHeader();
	}

	@Then("search results are shown")
	public void search_results_are_shown() {
		// Write code here that turns the phrase above into concrete actions
		SearchResultsPage s = new SearchResultsPage(driver);
		s.assert_ResultsCount();
		s.assert_Results();
	}

	@When("I search for {string} via the search results page")
	public void i_search_for_via_the_search_results_page(String string) {
		// Write code here that turns the phrase above into concrete actions
		SearchResultsPage s = new SearchResultsPage(driver);
		s.searchFor(string);
	}

	@Then("the results match the search term {string}")
	public void the_results_match_the_search_term(String string){
		// Write code here that turns the phrase above into concrete actions
		Pagination p = new Pagination(driver);
		SearchResultsPage s = new SearchResultsPage(driver);

		boolean continueVerification = true;
		do {
			int pageNumber = p.get_ActivePageNumber();
			if(pageNumber == 0){
				throw new AssertionError(
						"No search results pages were found or pagination is missing. FAIL."
				);
			}
			System.out.println(MessageFormat.format("Current page number is {0}",pageNumber));
			s.assert_ResultsContainText(string);
			continueVerification = p.gotoNextPage();
		} while(continueVerification);
	}

	@Then("the error message {string} is shown")
	public void the_error_message_is_shown(String string) {
		// Write code here that turns the phrase above into concrete actions
		SearchResultsPage s = new SearchResultsPage(driver);
		String actualMessage = s.getErrorMessage();

		try {
			assertEquals(actualMessage, string);
		} catch (AssertionError e) {
			throw new AssertionError(
					MessageFormat.format("Error message on page \"{0}\" does not match expected message \"{1}\".", actualMessage, string)
			);
		}
	}

	@After()
	public void afterActivities(Scenario scenario) {
		Status status = scenario.getStatus();
		if(status.name() != "PASSED"){
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png", "Screenshot");
		}
		driver.close();
	}

}
