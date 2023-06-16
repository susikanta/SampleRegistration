package com.sampleregistration.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {

	private Long id;
	private String title;
    private String description;
	private String dateTime;  
    private String type;
    boolean animated;
	private String link;
	private String deletehash;
}
