package com.crypto.assignment.apiessentials;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApiCurrencyPojo {

    private Long id;
    private String name;
    private String symbol;
    private String slug;
    private Integer is_active;
    private Long rank;
    private String first_historical_data;
    private String last_historical_data;
    private PlatformPojo platform;
}
