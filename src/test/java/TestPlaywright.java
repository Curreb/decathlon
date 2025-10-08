import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

@Disabled

public class TestPlaywright {
    static Playwright pw;
    static Browser browser;
    Page page;

    @BeforeAll
    static void setup() {
        pw = Playwright.create();
        browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterAll
    static void teardown() {
        browser.close();
        pw.close();
    }

    @BeforeEach
    void createContextAndPage() {
        page = browser.newPage();
    }

    @AfterEach
    void closeContext() {
        page.context().close();
    }

    //Test for 100 meter Decathlon, assertion for right values saved
    @Test
    void test100M() throws InterruptedException {
        page.navigate("http://localhost:8080/");
        page.fill("#name2", "Test Person1");
        // Thread.sleep(2000);
        page.selectOption("#event", "100m");
        page.fill("[data-testid='rawInput']", "9.8");
        page.click("[data-testid='saveScoreBtn']");

        page.fill("#name2", "Test Testsson");
        page.selectOption("#event", "100m");
        page.fill("#raw", "11.36");
        page.click("#save");
        Thread.sleep(2000);

        //Locator table = page.locator("[data-testid='standingsTable']");
        Locator table = page.getByTestId("standingsTable");

        Locator row1=table.locator("tr").nth(0);
        Assertions.assertEquals("Test Person1", row1.locator("td").nth(0).textContent().trim());
        Assertions.assertEquals("1146", row1.locator("td").nth(1).textContent().trim());
        Assertions.assertEquals("", row1.locator("td").nth(2).textContent().trim());
        Assertions.assertEquals("", row1.locator("td").nth(3).textContent().trim());
        Assertions.assertEquals("", row1.locator("td").nth(4).textContent().trim());
        Assertions.assertEquals("1146", row1.locator("td").nth(5).textContent().trim());

        Locator row2=table.locator("tr").nth(1);
        Assertions.assertEquals("Test Testsson", row2.locator("td").nth(0).textContent().trim());
        Assertions.assertEquals("782", row2.locator("td").nth(1).textContent().trim());
        Assertions.assertEquals("", row2.locator("td").nth(2).textContent().trim());
        Assertions.assertEquals("", row2.locator("td").nth(3).textContent().trim());
        Assertions.assertEquals("", row2.locator("td").nth(4).textContent().trim());
        Assertions.assertEquals("782", row2.locator("td").nth(5).textContent().trim());
    }

//    @Test
//    void test100MDecimaltest() throws InterruptedException {
//        page.navigate("http://localhost:8080/");
//        page.fill("#name2", "Test Testsson");
//        page.selectOption("#event", "100m");
//        page.fill("#raw", "11.36");
//        page.click("#save");
//        Thread.sleep(2000);
//
//        Locator table = page.getByTestId("standingsTable");
//
//        Assertions.assertEquals("Test Testsson", table.locator("td").nth(0).textContent().trim());
//        Assertions.assertEquals("782", table.locator("td").nth(1).textContent().trim());
//        Assertions.assertEquals("", table.locator("td").nth(2).textContent().trim());
//        Assertions.assertEquals("", table.locator("td").nth(3).textContent().trim());
//        Assertions.assertEquals("", table.locator("td").nth(4).textContent().trim());
//        Assertions.assertEquals("782", table.locator("td").nth(5).textContent().trim());
//
//    }

}
