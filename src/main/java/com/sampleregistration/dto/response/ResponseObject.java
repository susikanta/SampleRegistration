package com.sampleregistration.dto.response;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ResponseObject {
    private String link;
    private String id;
    private String imgType;
    private String deletehash;
    private boolean success;
    private int statusCode;
}
