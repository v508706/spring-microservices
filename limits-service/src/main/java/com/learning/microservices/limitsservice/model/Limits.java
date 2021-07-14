package com.learning.microservices.limitsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Limits {

	private int minimum;
	private int maximum;
}
