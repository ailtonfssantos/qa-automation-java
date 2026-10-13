package es.lobosquadrugby;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UnetePage {

    private final WebDriver driver;

    private final By fullName = By.id("fullName");
    private final By birthDate = By.id("birthDate");
    private final By city = By.id("city");
    private final By phone = By.id("phone");
    private final By email = By.id("email");

    private final By privacyAccepted =
        By.cssSelector("input[name='privacyAccepted']");

    private final By form = By.tagName("form");

    private final By submitButton =
        By.cssSelector("form button[type='submit']");

    public UnetePage(WebDriver driver) {
        this.driver = driver;
    }

    public void abrir() {
        driver.get(
            "https://lobos-quad-rugby.vercel.app/unete"
        );
    }

    public WebElement obterCampoNome() {
        return driver.findElement(fullName);
    }

    public WebElement obterCampoDataNascimento() {
        return driver.findElement(birthDate);
    }

    public WebElement obterCampoCidade() {
        return driver.findElement(city);
    }

    public WebElement obterCampoTelefone() {
        return driver.findElement(phone);
    }

    public WebElement obterCampoEmail() {
        return driver.findElement(email);
    }

    public WebElement obterCheckboxPrivacidade() {
        return driver.findElement(privacyAccepted);
    }

    public WebElement obterFormulario() {
        return driver.findElement(form);
    }

    public WebElement obterBotaoEnvio() {
        return driver.findElement(submitButton);
    }

    public void preencherCamposObrigatorios(
        String nome,
        String dataNascimento,
        String cidade,
        String telefone,
        String email
    ) {
        obterCampoNome().sendKeys(nome);
        obterCampoDataNascimento().sendKeys(dataNascimento);
        obterCampoCidade().sendKeys(cidade);
        obterCampoTelefone().sendKeys(telefone);
        obterCampoEmail().sendKeys(email);
    }

    public void aceitarPrivacidade() {
        WebElement checkbox = obterCheckboxPrivacidade();

        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }
}