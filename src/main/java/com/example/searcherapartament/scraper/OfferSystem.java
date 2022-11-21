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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
        int page = 1;
        do {
            String url = linkToAllOffersGenerator(offerSettings, page);
            Elements elements = getAllLinks(documentHelper.getDocument(url));
            Set<String> offerLinks = offerLinks(elements);
            for (String link : offerLinks) {

                if (link.contains("https://www.olx.pl/d/oferta/") && olx.isActiveOffer(link)) //olx
                {
                    System.out.println(link);
                    allOffer.add(getOfferfromOLX(link, offerSettings));
                }
            }
            page++;
        } while (page < offerSettings.getNumberOfPagesToScraping());
        return allOffer;
    }

    Offer getOfferfromOLX(String url, OfferSettings offerSettings) {
        return getOffer(url, offerSettings, olx.getAdditionalCost(url), olx.getBasicCost(url), olx.getLocation(url));
    }

    private Offer getOffer(String url, OfferSettings offerSettings, Long additionalCost, Long basicPrice, String location) {
        Offer offer = new Offer();
        offer.setAllCosts(additionalCost + basicPrice);
        offer.setLink(url);
        offer.setLocationName(location);
        String locationFormat = geocodingService.getLocationsForTravelTimeAPI(offer.getLocationName());
        offer.setTime(timeService.getTime(offerSettings.getTransportType(), locationFormat, offerSettings.getLatitudePerfectLocation(), offerSettings.getLongitudePerfectLocation()));

        return offer;
    }

    Elements getAllLinks(Document document) {
        return document.select("a[href]");

    }

    Set<String> offerLinks(Elements elements) {
        Set<String> links = new HashSet<>();
        for (Element element : elements) {
            String link = element.attr("href");
            if (link.contains("d/oferta")) {
                links.add(link);
            }
        }
        return links;
    }

    String linkToAllOffersGenerator(OfferSettings offerSettings, int page) {
        String city = offerSettings.getCity().toString();
        return "https://www.olx.pl/d/nieruchomosci/mieszkania/wynajem/" + city + "/?search%5Border%5D=created_at%3Adesc&page=" + page;
    }

}
