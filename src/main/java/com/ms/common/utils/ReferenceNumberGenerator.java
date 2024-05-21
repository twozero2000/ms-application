package com.ms.common.utils;

import com.ms.application.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReferenceNumberGenerator {

    private final ApplicationRepository applicationRepository;

    @Value("${ref.num.date.format}")
    private String dateFormat;

    @Value("${ref.num.seq.format}")
    private String seqFormat;

    public String generateReferenceNum(String country){
        Integer runningNo = applicationRepository.getApplicationRunningNo();
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern(dateFormat));
        String refNum = country.concat(currentDate).concat(String.format(seqFormat, runningNo));

        return refNum;
    }
}
