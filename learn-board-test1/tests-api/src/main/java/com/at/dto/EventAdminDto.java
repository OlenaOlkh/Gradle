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
public class EventAdminDto {

    String id;
    String objectId;
    String email;
    String description;
    String displayName;
    String upsaId;
    EventAdminRoleDto role;
}
