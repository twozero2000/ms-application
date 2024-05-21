package com.ms.application.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasePageableResponseDTO implements Serializable {

    private Boolean maxRecordReachFlag;
    private PageableDTO pageable;


    public BasePageableResponseDTO(Pageable pageable, int pageNumber, int size, long total, boolean isLast, long totalElements){
        int currentPage = pageNumber + 1;
        PageableDTO pageableDTO = PageableDTO.builder()
                .offset(pageable.getOffset())
                .page(pageable.isPaged() ? currentPage : 0)
                .size(size)
                .unpaged(pageable.isUnpaged())
                .paged(pageable.isPaged())
                .total(total)
                .totalRecords(totalElements)
                .build();

        this.maxRecordReachFlag = isLast;
        this.pageable = pageableDTO;
    }
}
