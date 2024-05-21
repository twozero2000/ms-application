package com.ms.common.constant;

public class ApplicationSqlConstant {

    private static final String PAGEABLE = "OFFSET :OFFSET " +
            "LIMIT :LIMIT";

    public static final String APPLICATION_LISTING_SQL =
            "SELECT count(*) over() AS " + ApplicationConstants.TOTAL + " , " +
                    "id AS " + ApplicationConstants.ID + " , " +
                    "name AS " + ApplicationConstants.NAME + " , " +
                    "reference_num AS " + ApplicationConstants.REFERENCE_NUMBER + " , " +
                    "detail AS " + ApplicationConstants.DETAILS + " , " +
                    "crt_by AS " + ApplicationConstants.CREATED_BY + " , " +
                    "crt_time AS " + ApplicationConstants.CREATED_TIME + " " +
                    "FROM t_application " +
                    "ORDER BY :ORDER " +
                    PAGEABLE;
}
