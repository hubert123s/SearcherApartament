package com.example.searcherapartament.view;

import com.example.searcherapartament.domain.dao.Offer;
import com.example.searcherapartament.domain.dao.OfferSettings;
import com.example.searcherapartament.service.OfferService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;

import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.*;
import lombok.RequiredArgsConstructor;



@Route("offer/:id")
@RequiredArgsConstructor
public class OfferGui  extends Div implements BeforeEnterObserver, AfterNavigationObserver {
    private Long id;
    private final OfferService offerService;
    Grid<Offer> offerGrid =new Grid<>(Offer.class,false);
    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        id = beforeEnterEvent.getRouteParameters().getLong("id").get();
    }
    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        configureGrid();
        Text text =new Text("You are checking the offer with the number "+id);
        add(text,offerGrid);
    }
    public void configureGrid(){
        offerGrid.setItems(offerService.selectedOffer(id));
        offerGrid.addColumn(Offer::getId).setHeader("Id").setWidth("10px").setKey("id");
        offerGrid.addColumn(Offer::getLocationName).setHeader("Location Name").setAutoWidth(true).setKey("locationname");
        offerGrid.addColumn(Offer::getTime).setHeader("Time").setKey("time");
        offerGrid.addColumn(Offer::getAllCosts).setHeader("All costs").setKey("allcosts");
        offerGrid.addColumn(new ComponentRenderer<>(item -> {
            Image image = new Image(item.getImageLink(),"Image description");
            image.setWidth("216px");
            image.setHeight("152px");
            return image;
        })).setHeader("Image").setAutoWidth(true);
        offerGrid.addColumn(TemplateRenderer .<Offer>of("<a href='[[item.link]]'>Link</a>")
                .withProperty("link", Offer::getLink)
        ).setHeader("Link to Offer");
        offerGrid.getColumns().forEach(offerColumn -> offerColumn.setAutoWidth(true));
        offerGrid.setSortableColumns("id","locationname","time","allcosts");
        offerGrid.setMultiSort(true);
    }
}
