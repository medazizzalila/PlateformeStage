package tn.esprit.artifact.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;
import tn.esprit.artifact.entity.Offretravail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ScrapperService {

    public List<Offretravail> scraping() {
        List<Offretravail> scrapedData = new ArrayList<>();
        String url = "https://www.tanitjobs.com/jobs/?listing_type%5Bequal%5D=Job&action=search&keywords%5Ball_words%5D=ingenieur&GooglePlace%5Blocation%5D%5Bvalue%5D=&GooglePlace%5Blocation%5D%5Bradius%5D=50";

        // Set up Chrome driver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run Chrome in headless mode (without opening browser window)
        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            // Click on "Voir plus" button
            WebElement voirPlusButton = driver.findElement(By.cssSelector(".js-load-more"));
            voirPlusButton.click();

            // Wait for new content to load
            Thread.sleep(2000); // Adjust the sleep time according to the page loading time

            // Parse the updated page content with Jsoup
            Document doc = Jsoup.parse(driver.getPageSource());
            Elements jobs = doc.select(".media.well.listing-item.listing-item__jobs");

            // Extract data from the updated page
            for (Element j : jobs) {
                String titre = j.select(".media-heading.listing-item__title").text();
                String societe = j.select(".listing-item__info--item.listing-item__info--item-company").text();
                String localisation = j.select(".listing-item__info--item.listing-item__info--item-location").text();

                Offretravail offretravail = new Offretravail();
                offretravail.setTitre(titre);
                offretravail.setSociete(societe);
                offretravail.setLocalisation(localisation);
                scrapedData.add(offretravail);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Quit the WebDriver
            driver.quit();
        }

        return scrapedData;
    }
}
