package objects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import java.text.MessageFormat;
import java.util.NoSuchElementException;

public class PrimaryNavigation {
    public PrimaryNavigation (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//i[@aria-label='Search']/../../button")
    private WebElement button_Search;

    @FindBy(how = How.XPATH, using = "//input[@type='text' and contains(@aria-label,'Search')]")
    private WebElement textBox_Search;

    public void click_Search(){
        button_Search.click();

        try { Thread.sleep(3000);}
        catch (InterruptedException e) {}

        System.out.println("Clicked on Search button in Primary Navigation");
    }

    public void enter_SearchText(String s){
        textBox_Search.sendKeys(s);
        textBox_Search.sendKeys(Keys.ENTER);

        try { Thread.sleep(3000);}
        catch (InterruptedException e) {}

        System.out.println(MessageFormat.format("Entered text \"{0}\" into Search field",s));
    }

    public void searchFor(String keyword){
        click_Search();
        enter_SearchText(keyword);
    }
}
