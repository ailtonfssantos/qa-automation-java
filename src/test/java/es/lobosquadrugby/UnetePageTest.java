
package es.lobosquadrugby;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.extension.ExtendWith;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ScreenshotOnFailureExtension.class)
class UnetePageTest {

    private WebDriver driver;
    private UnetePage unetePage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        unetePage = new UnetePage(driver);
        unetePage.abrir();

        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(d -> !d.getCurrentUrl().isEmpty());

        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(d -> unetePage.obterFormulario().isDisplayed());
    }

    @Test
    @DisplayName("Verificar abertura da página e apresentação do formulário")
    @Feature("Formulário de inscrição")
    @Story("Carregamento da página")
    @Description("Verifica se a página de inscrição abre corretamente e apresenta o formulário.")
    void deveAbrirPaginaUneteEApresentarFormulario() {
        assertTrue(driver.getCurrentUrl().contains("/unete"));
        assertTrue(unetePage.obterFormulario().isDisplayed());
    }

    @Test
    @DisplayName("Identificar todos os campos obrigatórios")
    @Feature("Formulário de inscrição")
    @Story("Campos obrigatórios")
    @Description("Verifica se nome, data de nascimento, cidade, telefone, email e consentimento de privacidade são obrigatórios.")
    void deveIdentificarOsCamposObrigatorios() {
        assertTrue(
            unetePage.obterCampoNome().getAttribute("required") != null
        );
        assertTrue(
            unetePage.obterCampoDataNascimento().getAttribute("required") != null
        );
        assertTrue(
            unetePage.obterCampoCidade().getAttribute("required") != null
        );
        assertTrue(
            unetePage.obterCampoTelefone().getAttribute("required") != null
        );
        assertTrue(
            unetePage.obterCampoEmail().getAttribute("required") != null
        );
        assertTrue(
            unetePage.obterCheckboxPrivacidade().getAttribute("required") != null
        );
    }

    @Test
    @DisplayName("Impedir validação de formulário vazio")
    @Feature("Validação do formulário")
    @Story("Campos obrigatórios")
    @Description("Verifica se o formulário é inválido quando os campos obrigatórios estão vazios.")
    void deveImpedirFormularioVazio() {
        assertFalse(validarFormulario());

        assertTrue((Boolean) ((JavascriptExecutor) driver).executeScript(
            "return arguments[0].validity.valueMissing;",
            unetePage.obterCampoNome()
        ));
    }

    @Test
    @DisplayName("Rejeitar email com formato inválido")
    @Feature("Validação do formulário")
    @Story("Validação do email")
    @Description("Verifica se o navegador identifica um endereço de email com formato inválido.")
    void deveRejeitarEmailComFormatoInvalido() {
        unetePage.obterCampoEmail().sendKeys("email-invalido");

        assertTrue((Boolean) ((JavascriptExecutor) driver).executeScript(
            "return arguments[0].validity.typeMismatch;",
            unetePage.obterCampoEmail()
        ));
    }

    @Test
    @DisplayName("Preencher corretamente os campos obrigatórios")
    @Feature("Formulário de inscrição")
    @Story("Preenchimento dos dados")
    @Description("Verifica se os dados introduzidos são guardados corretamente nos campos do formulário.")
    void devePreencherCorretamenteOsCamposObrigatorios() {
        preencherCamposObrigatorios();

        assertEquals(
            "Ailton Santos",
            unetePage.obterCampoNome().getAttribute("value")
        );
        assertEquals(
            "Valencia",
            unetePage.obterCampoCidade().getAttribute("value")
        );
        assertEquals(
            "ailton@ailton.com",
            unetePage.obterCampoEmail().getAttribute("value")
        );
        assertEquals(
            "1995-06-15",
            unetePage.obterCampoDataNascimento().getAttribute("value")
        );
        assertEquals(
            "600123456",
            unetePage.obterCampoTelefone().getAttribute("value")
        );
    }

    @Test
    @DisplayName("Validar formulário com dados corretos e privacidade aceite")
    @Feature("Validação do formulário")
    @Story("Submissão válida")
    @Description("Verifica se os dados obrigatórios e o consentimento de privacidade tornam o formulário válido.")
    void deveValidarFormularioComDadosCorretosEPrivacidadeAceite() {
        preencherCamposObrigatorios();
        unetePage.aceitarPrivacidade();

        assertTrue(validarFormulario());
    }

    @Test
    @DisplayName("Verificar se o botão de envio está visível e ativo")
    @Feature("Formulário de inscrição")
    @Story("Botão de envio")
    @Description("Verifica a visibilidade, o estado ativo e o texto do botão de envio.")
    void deveApresentarBotaoDeEnvioVisivelEAtivo() {
        WebElement botao = unetePage.obterBotaoEnvio();

        assertTrue(botao.isDisplayed());
        assertTrue(botao.isEnabled());
        assertEquals("ENVIAR SOLICITUD", botao.getText().trim());
    }

    @Test
    @DisplayName("Impedir validação sem aceitar a política de privacidade")
    @Feature("Validação do formulário")
    @Story("Consentimento de privacidade")
    @Description("Verifica se o formulário permanece inválido quando a política de privacidade não é aceite.")
    void deveImpedirValidacaoSemAceitarPrivacidade() {
        preencherCamposObrigatorios();

        assertFalse(unetePage.obterCheckboxPrivacidade().isSelected());
        assertFalse(validarFormulario());
    }

    @Test
    @DisplayName("Impedir validação sem preencher o telefone")
    @Feature("Validação do formulário")
    @Story("Campos obrigatórios")
    @Description("Verifica se o telefone continua a ser obrigatório mesmo quando os restantes dados são válidos.")
    void deveImpedirValidacaoSemTelefone() {
        unetePage.preencherCamposObrigatorios(
            "Teste QA",
            "15061995",
            "Valencia",
            "",
            "teste@example.com"
        );

        unetePage.aceitarPrivacidade();

        assertFalse(validarFormulario());

        assertTrue((Boolean) ((JavascriptExecutor) driver).executeScript(
            "return arguments[0].validity.valueMissing;",
            unetePage.obterCampoTelefone()
        ));
    }

    private void preencherCamposObrigatorios() {
        unetePage.preencherCamposObrigatorios(
            "Ailton Santos",
            "15061995",
            "Valencia",
            "600123456",
            "ailton@ailton.com"
        );
    }

    private boolean validarFormulario() {
        return (Boolean) ((JavascriptExecutor) driver).executeScript(
            "return arguments[0].checkValidity();",
            unetePage.obterFormulario()
        );
    }

    WebDriver getDriver() {
        return driver;
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
