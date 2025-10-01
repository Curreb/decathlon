Basics för playwright:
Klicka på knapp
page.click("");
Skriva text
page.fill("", "");
Välja från dropdown
page.selectOption("", "");
Checkbox
page.check("");
Vänta på element
page.waitForSelector("");
 
1. Kontrollera URL
Assertions.assertEquals("https://din-webbplats.se/dashboard", page.url());
Assertions.assertTrue(page.url().contains("/dashboard"));
2. Kontrollera sidtitel
Assertions.assertTrue(page.title().contains("Dashboard"));
3. Kontrollera text på sidan
Assertions.assertTrue(page.textContent("h1").contains("Välkommen"));
4. Verifiera att element finns/är synligt
Assertions.assertTrue(page.isVisible("text=Logga ut"));
5. Kontrollera attribut
String placeholder = page.getAttribute("input[name='username']", "placeholder");
Assertions.assertEquals("Användarnamn", placeholder);
6. Kontrollera värde i ett fält
page.fill("#search", "Playwright");
Assertions.assertEquals("Playwright", page.inputValue("#search"));
7. Kontrollera att något INTE syns
Assertions.assertFalse(page.isVisible("#errorMessage"));
 
