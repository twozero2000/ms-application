package com.ms.application.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.common.constant.ApplicationConstants;
import com.ms.common.constant.ApplicationSqlConstant;
import com.ms.application.data.model.ApplicationDTO;
import com.ms.application.mapper.ApplicationListingRowMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import scala.Tuple2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationQueryService {

    private final ObjectMapper objectMapper;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Tuple2<Page<ApplicationDTO>, List<ApplicationDTO>> getApplicationListing(Pageable pageable, String sortBy){
        Map<String, Object> params = new HashMap<>();
        params.put(ApplicationConstants.OFFSET, pageable.getOffset());
        params.put(ApplicationConstants.LIMIT, pageable.getPageSize());
        params.put(ApplicationConstants.ORDER, sortBy);

        List<ApplicationDTO> applicationDTOList = namedParameterJdbcTemplate.query(ApplicationSqlConstant.APPLICATION_LISTING_SQL, params, new ApplicationListingRowMapper(objectMapper));

        int total = !CollectionUtils.isEmpty(applicationDTOList) ? applicationDTOList.get(0).getTotal() : 0;
        return new Tuple2<>(new PageImpl<>(applicationDTOList, pageable, total), applicationDTOList);
    }
}
