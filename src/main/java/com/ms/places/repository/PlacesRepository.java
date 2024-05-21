package com.ms.places.repository;

import com.ms.places.data.entity.Places;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlacesRepository extends JpaRepository<Places, Long> {

}
