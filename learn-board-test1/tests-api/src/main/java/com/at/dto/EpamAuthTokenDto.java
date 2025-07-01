package com.at.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EpamAuthTokenDto {

    String access_token;
    Integer expires_in;
    Integer refresh_expires_in;
    String refresh_token;
    String token_type;
    // TODO: Think about using of ObjectMapper or something else or remove completely this parameter.
    // Integer not-before-policy;
    String session_state;
    String scope;
}
