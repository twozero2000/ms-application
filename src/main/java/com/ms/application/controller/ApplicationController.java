package com.ms.application.controller;

import com.ms.application.data.model.ApplicationListingRespDTO;
import com.ms.application.services.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ms.common.constant.ResourcePath;
import com.ms.common.enums.ActionType;
import com.ms.application.data.model.ApplicationDTO;
import com.ms.application.data.model.ApplicationSubmitRequestDTO;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(ResourcePath.version + ResourcePath.application)
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping(ResourcePath.action)
    public ResponseEntity<ApplicationDTO> processApplication (
        @PathVariable("actionType") ActionType actionType,
        @Valid @RequestBody ApplicationSubmitRequestDTO applicationReqDTO
    ) throws Exception {
        ApplicationDTO applicationDTO = applicationService.createApplication(applicationReqDTO, actionType);
        return new ResponseEntity<>(applicationDTO, HttpStatus.OK);
    }

    @GetMapping(ResourcePath.listing)
    public ResponseEntity<ApplicationListingRespDTO> applicationListing (
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int limit,
            @RequestParam(required = false, defaultValue = "referenceNum") String sortBy,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortOrder
    ) throws Exception {
        Pageable pageable = PageRequest.of(page - 1, limit, sortOrder, sortBy);
        ApplicationListingRespDTO applListng = applicationService.getApplicationListing(pageable, sortBy);
        return new ResponseEntity<>(applListng, HttpStatus.OK);
    }
}
