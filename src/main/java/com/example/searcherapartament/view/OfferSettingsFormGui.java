package com.example.searcherapartament.view;

import com.example.searcherapartament.domain.dao.City;
import com.example.searcherapartament.domain.dao.OfferSettings;
import com.example.searcherapartament.domain.dao.TransportType;
import com.example.searcherapartament.service.OfferService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Searcher Offer")
@Route("hello")
public class OfferSettingsFormGui extends VerticalLayout {
    MenuBar menuBar = new MenuBar();
    TextField textFieldLocation = new TextField("Perfect Location"); // dodac example Wroclaw pl. Grunwaldzki
    NumberField numberFieldPriceFrom = new NumberField("Price from");
    NumberField numberFieldPriceTo = new NumberField("Price to");
    TextField textFieldNumberOfPagesToScraping = new TextField("Number Of Pages To Scraping");
    ComboBox<TransportType> comboboxTransportType = new ComboBox<>("Transport Type",TransportType.values());
    ComboBox<City> comboboxCity = new ComboBox<>("City",City.values());
    Button button = new Button("Check selected offers");
    public OfferSettingsFormGui(OfferService offerService){
        addClassName("offersettings"); // do CSS
        setSizeFull();
        menuBar.addItem("List of offer settings", e -> {
            UI.getCurrent().getPage().setLocation("/offer");
        });
        menuBar.addItem("Set offer settings", e -> {
            UI.getCurrent().getPage().setLocation("/hello");
        });
        button.addClickListener(buttonClickEvent -> {
           OfferSettings offerSettings1= offerService.searchOffer(getOfferSettings());
            UI.getCurrent().getPage().setLocation("/offer/"+offerSettings1.getId());
        });
        add(menuBar,textFieldLocation,numberFieldPriceFrom,numberFieldPriceTo,textFieldNumberOfPagesToScraping,comboboxTransportType,comboboxCity,button);
    }
    OfferSettings getOfferSettings(){
        OfferSettings offerSettings = new OfferSettings();
        offerSettings.setPerfectLocation(textFieldLocation.getValue());
        offerSettings.setPriceTo(numberFieldPriceTo.getValue().longValue());
        offerSettings.setPriceFrom(numberFieldPriceFrom.getValue().longValue());
        offerSettings.setTransportType(comboboxTransportType.getValue());
        offerSettings.setCity(comboboxCity.getValue());
        return offerSettings;
    }
}
