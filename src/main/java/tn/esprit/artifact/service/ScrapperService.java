package tn.esprit.artifact.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import tn.esprit.artifact.entity.Offretravail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScrapperService {
    public List<Offretravail> scraping() {
        List<Offretravail> scrapedData = new ArrayList<>();
        String url = "https://www.tanitjobs.com/jobs/?listing_type%5Bequal%5D=Job&action=search&keywords%5Ball_words%5D=ingenieur&GooglePlace%5Blocation%5D%5Bvalue%5D=&GooglePlace%5Blocation%5D%5Bradius%5D=50";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements jobs = doc.select(".media.well.listing-item.listing-item__jobs");

            // Limit to the first 10 elements
            int count = 0;
            for (Element j : jobs) {
                if (count >= 3) {
                    break; // Stop scraping after 10 elements
                }
                String titre = j.select(".media-heading.listing-item__title").text();
                String societe = j.select(".listing-item__info--item.listing-item__info--item-company").text();
                String description = j.select(".listing-item__desc.hidden-sm.hidden-xs").text();
                String localisation = j.select(".listing-item__info--item.listing-item__info--item-location").text();


                Offretravail offretravail = new Offretravail();
                offretravail.setTitre(titre);
                offretravail.setSociete(societe);
                offretravail.setDescrip(description);
                offretravail.setLocalisation(localisation);
                scrapedData.add(offretravail);

                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scrapedData;
    }
}
