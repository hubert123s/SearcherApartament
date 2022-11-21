package com.example.searcherapartament.domain.dao;

public enum TransportType {
    DRIVING("driving"), CYCLING("cycling");
    private String displayName;
    TransportType(String displayName) {
        this.displayName = displayName;
    }
    @Override
    public String toString() {
        return displayName;
    }
}
