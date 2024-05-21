package com.ms.application.services;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ms.common.enums.NameType;
import com.ms.common.utils.DateTimeGenerator;
import com.ms.common.utils.ReferenceNumberGenerator;
import com.ms.application.data.model.ApplicationListingRespDTO;
import com.ms.application.data.model.BasePageableResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.common.clientservices.ClientServices;
import com.ms.common.enums.ActionType;
import com.ms.application.data.entity.Application;
import com.ms.application.data.model.ApplicationDTO;
import com.ms.application.data.model.ApplicationSubmitRequestDTO;
import com.ms.application.repository.ApplicationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import scala.Tuple2;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationService {

    private final ObjectMapper objectMapper;
    private final ApplicationRepository applicationRepository;
    private final ClientServices clientServices;
    private final ReferenceNumberGenerator referenceNumberGenerator;
    private final ApplicationQueryService applicationQueryService;
    private final DateTimeGenerator dateTimeGenerator;

    public ApplicationDTO createApplication(ApplicationSubmitRequestDTO applicationSubmitRequestDTO, ActionType actionType) throws Exception {
        log.info("Create Application Payload: {}", objectMapper.writeValueAsString(applicationSubmitRequestDTO));
        if(ActionType.CREATE.equals(actionType)){
            Application application = ApplicationSubmitRequestDTO.populateNewApplication(applicationSubmitRequestDTO);
            application.setReferenceNum(referenceNumberGenerator.generateReferenceNum(applicationSubmitRequestDTO.getCountryCode()));

            String randomName = clientServices.getRandomName(NameType.FULLNAME.value, 1);
            application.setCreatedBy(randomName);
            application.setCreatedTime(dateTimeGenerator.generateLocalDateTime());

            Application res = applicationRepository.saveAndFlush(application);
            ApplicationDTO applicationDTO = objectMapper.convertValue(res, ApplicationDTO.class);
            log.info("Create Application Response: {}", objectMapper.writeValueAsString(applicationDTO));

            return applicationDTO;
        }
        return new ApplicationDTO();
    }

    public ApplicationListingRespDTO getApplicationListing(Pageable pageable, String sortBy) throws JsonProcessingException {
        Tuple2<Page<ApplicationDTO>, List<ApplicationDTO>> applicationDTO = applicationQueryService.getApplicationListing(pageable, sortBy);

        ApplicationListingRespDTO applicationListingRespDTO = new ApplicationListingRespDTO();
        List<ApplicationDTO> applicationDTOList = applicationDTO._2;
        Page<ApplicationDTO> applicationDTOPage = applicationDTO._1;

        BasePageableResponseDTO basePageableResponseDTO = new BasePageableResponseDTO(applicationDTOPage.getPageable(), applicationDTOPage.getNumber(), applicationDTOPage.getNumberOfElements(),applicationDTOPage.getTotalPages(), applicationDTOPage.isLast(), applicationDTOPage.getTotalElements());

        applicationListingRespDTO.setApplicationListing(applicationDTOList);
        applicationListingRespDTO.setPageable(basePageableResponseDTO.getPageable());
        applicationListingRespDTO.setMaxRecordReachFlag(basePageableResponseDTO.getMaxRecordReachFlag());
        log.info("Application Listing Response: {}", objectMapper.writeValueAsString(applicationListingRespDTO));

        return applicationListingRespDTO;
    }
}
