package com.example.searcherapartament.repository;

import com.example.searcherapartament.domain.dao.OfferSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<OfferSettings,Long> {
}
