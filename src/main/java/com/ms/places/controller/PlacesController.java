package com.ms.places.controller;

import com.ms.common.constant.ResourcePath;
import com.ms.places.data.model.PlacesReqDTO;
import com.ms.places.data.model.PlacesRespDTO;
import com.ms.places.services.PlacesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(ResourcePath.version + ResourcePath.places)
public class PlacesController {

    private final PlacesService placesService;

    @PostMapping(ResourcePath.favourite)
    public ResponseEntity<PlacesRespDTO> processFavouritePlaces (
        @Valid @RequestBody PlacesReqDTO placesReqDTO
    ) throws Exception {
        PlacesRespDTO placesRespDTO = placesService.saveFavouritePlaces(placesReqDTO);
        return new ResponseEntity<>(placesRespDTO, HttpStatus.OK);
    }
}
