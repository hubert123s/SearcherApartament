package com.example.searcherapartament.offer.model;

import lombok.Getter;

@Getter
public enum OfferType {
    ROOM("POKOJ"),APARTAMENT("MIESZKANIE");
    private String displayName;
    OfferType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return name();
    }
}
