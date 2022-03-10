package objects;

        import java.text.MessageFormat;

        import org.openqa.selenium.*;
        import org.openqa.selenium.support.FindBy;
        import org.openqa.selenium.support.How;
        import org.openqa.selenium.support.PageFactory;

public class Pagination {
    private final WebDriver driver;

    public Pagination(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//button[@class='is-current pagination-link']")
    private WebElement button_CurrentPage;

    @FindBy(how = How.XPATH, using = "//button[@aria-label='Goto next page']")
    private WebElement button_NextPageLink;

    @FindBy(how = How.XPATH, using = "//button[@aria-label='Goto next page']/i")
    private WebElement link_NextPageLink;

    public int get_ActivePageNumber(){
        int pageNumber = 0;
        try {
            pageNumber = Integer.parseInt(button_CurrentPage.getText());
        } catch (AssertionError e) {
            throw new AssertionError(
                    "No results are displayed on Search Results page."
            );
        }
        return pageNumber;
    }

    public boolean gotoNextPage(){
        String isDisabled = button_NextPageLink.getAttribute("disabled");
        if (isDisabled != null) {
            return false;
        }else{
//            WebElement nextPageLink = button_NextPageLink.findElement(By.xpath("./i"));
            ((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView(true);", button_NextPageLink);

            try { Thread.sleep(3000);}
            catch (InterruptedException e) {}

            try {
                button_NextPageLink.click();
            } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView(true);", button_NextPageLink);
                button_NextPageLink.click();
            }

            try { Thread.sleep(3000);}
            catch (InterruptedException e) {}

            return true;
        }
    }
}