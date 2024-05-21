package com.ms.places.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.common.utils.DateTimeGenerator;
import com.ms.places.data.entity.Places;
import com.ms.places.data.model.PlacesReqDTO;
import com.ms.places.data.model.PlacesRespDTO;
import com.ms.places.repository.PlacesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PlacesService {

    private final ObjectMapper objectMapper;
    private final PlacesRepository placesRepository;
    private final DateTimeGenerator dateTimeGenerator;

    public PlacesRespDTO saveFavouritePlaces(PlacesReqDTO placesReqDTO) throws Exception {
        log.info("Save Favourite Places Payload: {}", objectMapper.writeValueAsString(placesReqDTO));
        Places places = PlacesReqDTO.populateNewPlaces(placesReqDTO);
        places.setCreatedTime(dateTimeGenerator.generateLocalDateTime());

        Places res = placesRepository.saveAndFlush(places);
        PlacesRespDTO placesRespDTO = objectMapper.convertValue(res, PlacesRespDTO.class);
        log.info("Save Favourite Places Response: {}", objectMapper.writeValueAsString(placesRespDTO));

        return placesRespDTO;
    }
}
