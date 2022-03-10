package objects;

import java.text.MessageFormat;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchResultsPage {
    private final WebDriver driver;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//input[@type='text' and contains(@aria-label,'Search')]")
    private WebElement textBox_Search;

    @FindBy(how = How.XPATH, using = "//h1[.='Search results']")
    private WebElement header_SearchResults;

    @FindBy(how = How.XPATH, using = "//div[@class='total-results-top']")
    private WebElement header_ResultsCount;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'no-search-results')]//h3")
    private WebElement header_NoResults;

    @FindAll(@FindBy(how = How.XPATH, using = "//div[@class='solarsearch-searchresults-list']/div"))
    private List<WebElement> article_SearchResults;

    private String pageTitle_Search = "Search | Publicis Sapient";

    public void assert_PageTitle(){
        String currentPageTitle= this.driver.getTitle();
        try {
            assertEquals(currentPageTitle, pageTitle_Search);
        } catch (AssertionError e) {
            throw new AssertionError(
                    MessageFormat.format("Current page title is \"{0}\". Expected page with title \"{1}\"",
                            currentPageTitle, pageTitle_Search)
            );
        }

        System.out.println(MessageFormat.format("Current page title is \"{0}\". PASS",currentPageTitle));
    }

    public void assert_ResultsHeader(){
        try {
            assertTrue(header_SearchResults.isDisplayed());
        } catch (AssertionError e) {
            throw new AssertionError(
                    "Search Results header is not displayed."
            );
        }

        System.out.println("Search Results header is displayed. PASS");
    }

    public void assert_ResultsCount(){
        String headerText;
        try {
            headerText = header_ResultsCount.getText();
            assertTrue(headerText.matches("\\d+—\\d+ of \\d+ search results"));
        } catch (AssertionError e) {
            throw new AssertionError(
                    "Search Results header is not displayed."
            );
        }

        System.out.println(MessageFormat.format("Search results count \"{0}\" is displayed. PASS", headerText));
    }

    public void assert_Results(){
        List<WebElement> resultsOnPage = article_SearchResults;
        try {
            assertTrue(resultsOnPage.size() > 0);
        } catch (AssertionError e) {
            throw new AssertionError(
                    "No results are displayed on Search Results page."
            );
        }

        System.out.println(MessageFormat.format("\"{0}\" results are displayed on Search Results page. PASS", resultsOnPage.size()));
    }

    public void enter_SearchText(String s){
        textBox_Search.sendKeys(s);
        textBox_Search.sendKeys(Keys.ENTER);

        try { Thread.sleep(3000);}
        catch (InterruptedException e) {}

        System.out.println(MessageFormat.format("Entered text\"{0}\" into Search field",s));
    }

    public void assert_ResultsContainText(String keyword){
        List<WebElement> resultsOnPage = article_SearchResults;
        for (int i = 0; i < resultsOnPage.size(); i++) {
            try {
                assertTrue(resultsOnPage.get(i).getText().toLowerCase().contains(keyword.toLowerCase()));
            } catch (AssertionError e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", resultsOnPage.get(i));
                throw new AssertionError(
                        MessageFormat.format("Result at index \"{0}\" on page does not contain the string \"{1}\".", i + 1, keyword)
                );
            }
        }
        System.out.println(MessageFormat.format("All results on page have the keyword \"{0}\".", keyword));
    }

    public void searchFor(String keyword){
        enter_SearchText(keyword);
    }

    public String getErrorMessage() throws NoSuchElementException {
        String message;
        try{
            message = header_NoResults.getText();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(
                    "Error Message header element (header_NoResults) was not displayed on the Search Results page.\n"+e.getMessage()
            );
        }
        return message;
    }
}
