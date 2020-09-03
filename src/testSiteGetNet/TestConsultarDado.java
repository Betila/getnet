package testSiteGetNet;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestConsultarDado {
	
	WebDriver driver;
	
	private String URL = "https://site.getnet.com.br/";
	
	private final String CAMPOPROCURADO = "superget";
	
	@Before
	public void inicio() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "/Users/beti_/bin/chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.get(URL);			
		this.driver.manage().window().maximize();

		Thread.sleep(4000);	
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testPesquisar() throws Throwable {
		try {
			String TituloTela;
			String TituloValidacao1 = "Como faço a portabilidade da minha maquininha?";
			String TituloValidacao2 = "Como acesso a minha conta SuperGet?";
			
			
			this.driver.findElement(By.id("search-trigger")).click();
			
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("global-search-input")));
			
			this.driver.findElement(By.id("global-search-input")).sendKeys(CAMPOPROCURADO);
			this.driver.findElement(By.xpath("/html/body/section/div/div/div/form/button")).click();
			
			List<WebElement> elements = this.driver.findElements(By.xpath("//h3[contains(.,'como faço a portabilidade da minha maquininha')]"));
			
			if(!elements.isEmpty()){
				
				this.driver.findElement(By.xpath("//h3[contains(.,'como faço a portabilidade da minha maquininha')]")).click();
				
				WebDriverWait wait1 = new WebDriverWait(driver, 10);
				WebElement element1 = wait1.until(ExpectedConditions.presenceOfElementLocated(By.className("is-modal-open")));			
				TituloTela = this.driver.findElement(By.className("is-modal-open")).findElement(By.className("o-modal__title")).getText();
				
				Thread.sleep(4000);	
				assertEquals(TituloValidacao1, TituloTela);	
				
			}else {
				
				this.driver.findElement(By.xpath("//h3[contains(.,'Como acesso a minha conta SuperGet?')]")).click();
				
				WebDriverWait wait1 = new WebDriverWait(driver, 10);
				WebElement element1 = wait1.until(ExpectedConditions.presenceOfElementLocated(By.className("is-modal-open")));			
				TituloTela = this.driver.findElement(By.className("is-modal-open")).findElement(By.className("o-modal__title")).getText();	
				
				Thread.sleep(4000);	
				assertEquals(TituloValidacao2, TituloTela);
				
			}
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	


	@After
	public void fim() {
		this.driver.quit();
		
	}
	
}
