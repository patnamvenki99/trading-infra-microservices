package com.traidnginfra.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Position {
	@Id
	private String positionId;
	private String symbol;
	private String side;
	private Integer quantity;
	private Double price;
}
