
package es.lobosquadrugby;

import org.openqa.selenium.By;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PatrocinadoresPageTest {

    private WebDriver driver;
    private PatrocinadoresPage patrocinadoresPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        patrocinadoresPage = new PatrocinadoresPage(driver);
        patrocinadoresPage.abrir();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void shouldDisplayAllSponsorshipFormFields() {
        assertTrue(patrocinadoresPage.empresaEstaVisivel(),
                "O campo Empresa deveria estar visível.");

        assertTrue(patrocinadoresPage.contactoEstaVisivel(),
                "O campo Contacto deveria estar visível.");

        assertTrue(patrocinadoresPage.emailEstaVisivel(),
                "O campo Email deveria estar visível.");

        assertTrue(patrocinadoresPage.telefoneEstaVisivel(),
                "O campo Telefone deveria estar visível.");

        assertTrue(patrocinadoresPage.patrocinioEstaVisivel(),
                "O seletor de patrocínio deveria estar visível.");

        assertTrue(patrocinadoresPage.mensagemEstaVisivel(),
                "O campo Mensagem deveria estar visível.");
    }

    @Test
    void shouldAllowSelectingAllSponsorshipPlans() {
        patrocinadoresPage.selecionarPatrocinio("Platinum");
        assertTrue(
                patrocinadoresPage.obterPatrocinioSelecionado().equals("Platinum"),
                "Deveria ser possível selecionar o plano Platinum.");

        patrocinadoresPage.selecionarPatrocinio("Gold");
        assertTrue(
                patrocinadoresPage.obterPatrocinioSelecionado().equals("Gold"),
                "Deveria ser possível selecionar o plano Gold.");

        patrocinadoresPage.selecionarPatrocinio("Silver");
        assertTrue(
                patrocinadoresPage.obterPatrocinioSelecionado().equals("Silver"),
                "Deveria ser possível selecionar o plano Silver.");

        patrocinadoresPage.selecionarPatrocinio("Colabora");
        assertTrue(
                patrocinadoresPage.obterPatrocinioSelecionado().equals("Colabora"),
                "Deveria ser possível selecionar o plano Colabora.");
    }

    @Test
    void shouldMarkRequiredFieldsAsRequired() {
        assertTrue(
                patrocinadoresPage.campoEObrigatorio(By.id("companyName")),
                "O campo Empresa deve ser obrigatório.");

        assertTrue(
                patrocinadoresPage.campoEObrigatorio(By.id("contactName")),
                "O campo Contacto deve ser obrigatório.");

        assertTrue(
                patrocinadoresPage.campoEObrigatorio(By.id("email")),
                "O campo Email deve ser obrigatório.");

        assertTrue(
                patrocinadoresPage.campoEObrigatorio(By.id("sponsorshipType")),
                "O Tipo de patrocínio deve ser obrigatório.");
    }

    @Test
    void shouldFillSponsorshipFormFields() {
        patrocinadoresPage.preencherEmpresa("Empresa Teste");
        patrocinadoresPage.preencherContacto("João Silva");
        patrocinadoresPage.preencherEmail("joao.teste@example.com");
        patrocinadoresPage.preencherTelefone("912345678");
        patrocinadoresPage.selecionarPatrocinio("Gold");
        patrocinadoresPage.preencherMensagem("Gostaríamos de apoiar o clube.");

        assertTrue(
                patrocinadoresPage.obterPatrocinioSelecionado().equals("Gold"),
                "O plano Gold deveria estar selecionado.");
    }

    @Test
    void shouldPreventSubmissionWhenRequiredFieldsAreEmpty() {
        assertTrue(
                patrocinadoresPage.campoEObrigatorio(
                        org.openqa.selenium.By.id("companyName")),
                "Empresa deve ser obrigatória.");

        assertTrue(
                patrocinadoresPage.campoEObrigatorio(
                        org.openqa.selenium.By.id("contactName")),
                "Contacto deve ser obrigatório.");

        assertTrue(
                patrocinadoresPage.campoEObrigatorio(
                        org.openqa.selenium.By.id("email")),
                "Email deve ser obrigatório.");

        assertTrue(
                patrocinadoresPage.campoEObrigatorio(
                        org.openqa.selenium.By.id("sponsorshipType")),
                "Tipo de patrocínio deve ser obrigatório.");
    }

}
