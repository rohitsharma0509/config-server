package com.scb.rider.configserver.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PropertyDto {
    private Long id;
    private String application;
    private String key;
    private String value;
    private String label;
    private String profile;
}
