package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Start {

    WebDriver driver;

    public Start(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //General

    public WebDriver getDriver() {
        return driver;
    }

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Search']")
    public WebElement paso1;

    @FindBy(how = How.XPATH, using = "//i[@class='fa fa-search']")
    public WebElement paso2;
    public void clickpaso2() {
        paso2.click();
    }

    @FindBy(how = How.XPATH, using = "//img[@title='iPhone']")
    public WebElement searchP;
    public void clicksearchP() {
        searchP.click();
    }

    @FindBy(how = How.XPATH, using = "//button[@id='button-cart']")
    public WebElement producto;
    public void clickproducto() {
        producto.click();
    }

    @FindBy(how = How.XPATH, using = "//span[@id='cart-total']")
    public WebElement cartTotal;
    public void clickcartTotal() {
        cartTotal.click();
    }

    @FindBy(how = How.XPATH, using = "//strong[normalize-space()='View Cart']")
    public WebElement viewCart;
    public void clickviewCart() {
        viewCart.click();
    }

    @FindBy(how = How.XPATH, using = "//i[@class='fa fa-times-circle']")
    public WebElement times;
    public void clicktimes() {
        times.click();
    }



    @FindBy(how = How.XPATH, using = "//input[@id='SimpleSearchForm_SearchTerm']")
    private WebElement labelSearch;

    public WebElement getlabelSearch() {
        return labelSearch;
    }


    @FindBy(how = How.XPATH, using = "//input[@id='SimpleSearchForm_SearchTerm']")
    private WebElement btnSearch;

    public void clickbtnSearch() {
        btnSearch.click();
    }

    @FindBy(how = How.XPATH, using = "//a[@id='WC_CatalogEntryDBThumbnailDisplayJSPF_359511_link_9b']")
    private WebElement imgCamarote;

    public void clickimgCamarote() {
        imgCamarote.click();
    }

    ///////////////////////

    @FindBy(how = How.ID, using = "user-name")
    public WebElement labelUser;

    @FindBy(how = How.ID, using = "password")
    public WebElement labelPass;

    @FindBy(how = How.ID, using = "login-button")
    private WebElement buttonLogin;
    public void clickbtnLogin() {
        buttonLogin.click();
    }

    @FindBy(how = How.ID, using = "add-to-cart-sauce-labs-backpack")
    private WebElement prodOne;
    public void clickprodOne() {
        prodOne.click();
    }

    @FindBy(how = How.XPATH, using = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/a[1]")
    private WebElement carShop;
    public void clickcarShop() { carShop.click(); }

    @FindBy(how = How.ID, using = "checkout")
    private WebElement buttonCheckout;
    public void clickbtnCheckout() {
        buttonCheckout.click();
    }

    @FindBy(how = How.ID, using = "first-name")
    public WebElement labelFirst;

    @FindBy(how = How.ID, using = "last-name")
    public WebElement labelLast;

    @FindBy(how = How.ID, using = "postal-code")
    public WebElement labelPostal;

    @FindBy(how = How.ID, using = "continue")
    private WebElement buttonContinue;
    public void clickbtnContinue() {
        buttonContinue.click();
    }

    @FindBy(how = How.ID, using = "finish")
    private WebElement buttonFinish;
    public void clickbtnFinish() {
        buttonFinish.click();
    }

    @FindBy(how = How.ID, using = "back-to-products")
    private WebElement buttonbackHome;
    public void clickbtnbackHome() {
        buttonbackHome.click();
    }

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Sostenibilid")
    private WebElement buttonSos;
    public void clickbtnSos() {
        buttonSos.click();
    }

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Comunid")
    private WebElement buttonComu;
    public void clickbtnComu() {
        buttonComu.click();
    }














}



