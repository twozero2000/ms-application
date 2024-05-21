package com.ms.places.data.model;

import com.ms.places.data.entity.Places;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlacesReqDTO {
    @NotNull
    @Size(max=255)
    private String placeId;
    @NotNull
    @Size(max=255)
    private String address;
    @NotNull
    private BigDecimal latitude;
    @NotNull
    private BigDecimal longitude;

    public static Places populateNewPlaces(PlacesReqDTO placesReqDTO){
        Places places = new Places();
        places.setPlaceId(placesReqDTO.getPlaceId());
        places.setAddress(placesReqDTO.getAddress());
        places.setLatitude(placesReqDTO.getLatitude());
        places.setLongitude(placesReqDTO.getLongitude());

        return places;
    }
}


