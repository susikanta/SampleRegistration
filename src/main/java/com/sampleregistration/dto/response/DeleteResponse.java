package com.sampleregistration.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteResponse {
	private String data;
	private boolean success;
	private int statusCode;
}
