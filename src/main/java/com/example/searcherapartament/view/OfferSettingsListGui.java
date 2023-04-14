package com.example.searcherapartament.view;
import com.example.searcherapartament.domain.dao.OfferSettings;
import com.example.searcherapartament.service.OfferService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import lombok.RequiredArgsConstructor;

@Route("offer")
@RequiredArgsConstructor
public class OfferSettingsListGui extends Div implements AfterNavigationObserver {
    private final OfferService offerService;
    Grid<OfferSettings> offerSettingsGrid = new Grid<>(OfferSettings.class,false);

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        configureGrid();
        add(offerSettingsGrid);
    }
    public void configureGrid(){
        offerSettingsGrid.addColumn(OfferSettings::getPerfectLocation).setHeader("Perfect Location");
        offerSettingsGrid.addColumn(OfferSettings::getPriceFrom).setHeader("Price from");
        offerSettingsGrid.addColumn(OfferSettings::getPriceTo).setHeader("Price to");
        offerSettingsGrid.addColumn(OfferSettings::getCity).setHeader("City");
        offerSettingsGrid.addColumn(TemplateRenderer.<OfferSettings>of("<a href='offer/[[item.id]]'>[[item.id]]</a>")
                .withProperty("id",OfferSettings::getId)
        ).setHeader("Name").setWidth("15px");
        offerSettingsGrid.setItems(offerService.findAllOfferSettings());
        offerSettingsGrid.getColumns().forEach(offerSettingsColumn -> offerSettingsColumn.setAutoWidth(true));
    }
}
