package com.example.searcherapartament.scraper;

import com.example.searcherapartament.domain.dao.Offer;
import com.example.searcherapartament.domain.dao.OfferSettings;
import com.example.searcherapartament.helper.DocumentHelper;
import com.example.searcherapartament.service.GeocodingService;
import com.example.searcherapartament.service.TimeService;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.*;


@Component
@RequiredArgsConstructor
public class OfferSystem {
    private final Olx olx;

    private final GeocodingService geocodingService;
    private final TimeService timeService;
    private final DocumentHelper documentHelper;
    private static final String olxSettings = "?search%5Border%5D=created_at%3Adesc";// the latest offers

public List<Offer> getOffer(OfferSettings offerSettings) {
    List<Offer> allOffer = new ArrayList<>();
    Set<String> offerLinks= new TreeSet<>();
    int page = 1;
    do {
        String url = linkToAllOffersGenerator(offerSettings, page);
        Elements elements = getAllLinks(documentHelper.getDocument(url));
        offerLinks.addAll(offerLinks(elements));
        page++;
    } while (page <= offerSettings.getNumberOfPagesToScraping());

    ExecutorService executorService = Executors.newCachedThreadPool();
    List<Future<Offer>> futures = new ArrayList<>();

    for (String link : offerLinks) {
        futures.add(executorService.submit(() -> {
            if (link.contains("https://www.olx.pl/d/oferta/") && olx.isActiveOffer(link)) {
                System.out.println(link);
                return getOfferfromOLX(link, offerSettings);
            } else if (link.contains("/d/oferta/")) {
                String newLink = "https://www.olx.pl" + link;
                System.out.println(newLink);
                return getOfferfromOLX(newLink, offerSettings);
            }
            return null;
        }));
    }

    for (Future<Offer> future : futures) {
        try {
            Offer offer = future.get();
            if (offer != null) {
                allOffer.add(offer);
            }
        } catch (InterruptedException | ExecutionException e) {
        }
    }
    executorService.shutdown();

    return allOffer;
}
    public Offer getOfferfromOLX(String url, OfferSettings offerSettings) {
        return getOffer(url, offerSettings, olx.getAdditionalCost(url), olx.getBasicCost(url), olx.getLocation(url),olx.getImageLink(url));
    }

    public Offer getOffer(String url, OfferSettings offerSettings, Long additionalCost, Long basicPrice, String location,String imageLink) {
        Offer offer = new Offer();
        offer.setAllCosts(additionalCost + basicPrice);
        offer.setLink(url);
        offer.setImageLink(imageLink);
        offer.setLocationName(location);
        String locationFormat = geocodingService.getLocationsForTravelTimeAPI(offer.getLocationName());
        offer.setTime(timeService.getTime(offerSettings.getTransportType(), locationFormat, offerSettings.getLatitudePerfectLocation(), offerSettings.getLongitudePerfectLocation()));
        return offer;
    }

   public Elements getAllLinks(Document document) {
        return document.select("a[href]");
    }

    public  Set<String> offerLinks(Elements elements) {
        Set<String> links = new HashSet<>();
        for (Element element : elements) {
            String link = element.attr("href");
            if (link.contains("d/oferta")) {
                links.add(link);
            }
        }
        return links;
    }

    public String linkToAllOffersGenerator(OfferSettings offerSettings, int page) {
        String city = offerSettings.getCity().toString();
        //return "https://www.olx.pl/d/nieruchomosci/mieszkania/wynajem/wroclaw/?search%5Border%5D=created_at%3Adesc&page=1";
        return "https://www.olx.pl/d/nieruchomosci/"+city+"/?page="+page+"&search%5Bfilter_float_price%3Afrom%5D=600&search%5Bfilter_float_price%3Ato%5D=1300&search%5Border%5D=created_at%3Adesc";

    }

}
