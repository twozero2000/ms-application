package com.ms.application.data.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ms.application.data.entity.Application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplicationSubmitRequestDTO {
    @NotNull
    @Size(max=50)
    private String name;
    @Size(max=50)
    private String addressLine1;
    @Size(max=50)
    private String addressLine2;
    @Size(max=20)
    private String state;
    @Size(max=5)
    private String postalCode;
    @NotNull
    @Size(max=5)
    private String countryCode;

    public static Application populateNewApplication(ApplicationSubmitRequestDTO applicationRequestDTO){
        Application application = new Application();
        application.setName(applicationRequestDTO.getName());
        application.setDetails(ApplicationDetailsDTO.constructApplicationDetail(applicationRequestDTO));
        application.setCreatedTime(null);

        return application;
    }
}


