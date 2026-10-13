
package es.lobosquadrugby;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PatrocinadoresPage {

    private static final String URL_PATROCINADORES = "https://lobos-quad-rugby.vercel.app/patrocinadores";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By campoEmpresa = By.id("companyName");
    private final By campoContacto = By.id("contactName");
    private final By campoEmail = By.id("email");
    private final By campoTelefone = By.id("phone");
    private final By seletorPatrocinio = By.id("sponsorshipType");
    private final By campoMensagem = By.id("message");
    private final By botaoEnviar = By.cssSelector("button[type='submit']");

    public PatrocinadoresPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void abrir() {
        driver.get(URL_PATROCINADORES);
        wait.until(d -> d.findElement(campoEmpresa).isDisplayed());
    }

    public void preencherEmpresa(String empresa) {
        driver.findElement(campoEmpresa).sendKeys(empresa);
    }

    public void preencherContacto(String contacto) {
        driver.findElement(campoContacto).sendKeys(contacto);
    }

    public void preencherEmail(String email) {
        driver.findElement(campoEmail).sendKeys(email);
    }

    public void preencherTelefone(String telefone) {
        driver.findElement(campoTelefone).sendKeys(telefone);
    }

    public void selecionarPatrocinio(String plano) {
        Select select = new Select(driver.findElement(seletorPatrocinio));
        select.selectByValue(plano);
    }

    public void preencherMensagem(String mensagem) {
        driver.findElement(campoMensagem).sendKeys(mensagem);
    }

    public void enviarSolicitacao() {
        wait.until(d -> d.findElement(botaoEnviar).isEnabled());
        driver.findElement(botaoEnviar).click();
    }

    public boolean empresaEstaVisivel() {
        return driver.findElement(campoEmpresa).isDisplayed();
    }

    public boolean contactoEstaVisivel() {
        return driver.findElement(campoContacto).isDisplayed();
    }

    public boolean emailEstaVisivel() {
        return driver.findElement(campoEmail).isDisplayed();
    }

    public boolean telefoneEstaVisivel() {
        return driver.findElement(campoTelefone).isDisplayed();
    }

    public boolean patrocinioEstaVisivel() {
        return driver.findElement(seletorPatrocinio).isDisplayed();
    }

    public boolean mensagemEstaVisivel() {
        return driver.findElement(campoMensagem).isDisplayed();
    }

    public String obterPatrocinioSelecionado() {
        Select select = new Select(driver.findElement(seletorPatrocinio));
        return select.getFirstSelectedOption().getAttribute("value");
    }
    
    public boolean campoEObrigatorio(By campo) {
        return driver.findElement(campo)
                .getAttribute("required") != null;
    }
}

