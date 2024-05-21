package com.ms.application.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationListingRespDTO extends BasePageableResponseDTO {

    private List<ApplicationDTO> applicationListing;
}
