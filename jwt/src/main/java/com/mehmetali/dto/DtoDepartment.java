package com.mehmetali.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoDepartment {

	private Long id;
	
	private String location;
	
	private String name;
	
}
