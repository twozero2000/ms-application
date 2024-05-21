package com.ms.application.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.common.constant.ApplicationConstants;
import com.ms.application.data.model.ApplicationDTO;
import com.ms.application.data.model.ApplicationDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@AllArgsConstructor
public class ApplicationListingRowMapper implements RowMapper<ApplicationDTO> {
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public ApplicationDTO mapRow(ResultSet rs, int rowNum){
        ApplicationDTO applicationDTO = new ApplicationDTO();
        applicationDTO.setId(UUID.fromString(rs.getString(ApplicationConstants.ID)));
        applicationDTO.setName(rs.getString(ApplicationConstants.NAME));
        applicationDTO.setReferenceNum(rs.getString(ApplicationConstants.REFERENCE_NUMBER));
        applicationDTO.setCreatedBy(rs.getString(ApplicationConstants.CREATED_BY));
        applicationDTO.setDetails(objectMapper.readValue(rs.getString(ApplicationConstants.DETAILS), ApplicationDetailsDTO.class));
        applicationDTO.setCreatedTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(Timestamp.valueOf(String.valueOf(rs.getString(ApplicationConstants.CREATED_TIME))).getTime()), ZoneId.of("UTC")));
        applicationDTO.setTotal(rs.getInt(ApplicationConstants.TOTAL));

        return applicationDTO;
    }
}
