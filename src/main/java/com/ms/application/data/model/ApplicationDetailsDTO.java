package com.ms.application.data.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDetailsDTO implements Serializable {
    private String addressLine1;
    private String addressLine2;
    private String state;
    private String postalCode;
    private String countryCode;

    public static ApplicationDetailsDTO constructApplicationDetail(ApplicationSubmitRequestDTO applicationRequestDTO){
        ApplicationDetailsDTO applicationDetailsDTO = new ApplicationDetailsDTO();
        applicationDetailsDTO.setAddressLine1(applicationRequestDTO.getAddressLine1());
        applicationDetailsDTO.setAddressLine2(applicationRequestDTO.getAddressLine2());
        applicationDetailsDTO.setCountryCode(applicationRequestDTO.getCountryCode());
        applicationDetailsDTO.setState(applicationRequestDTO.getState());
        applicationDetailsDTO.setPostalCode(applicationRequestDTO.getPostalCode());

        return applicationDetailsDTO;
    }
}
