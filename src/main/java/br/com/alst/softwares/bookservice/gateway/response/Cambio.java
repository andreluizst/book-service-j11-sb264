package br.com.alst.softwares.bookservice.gateway.response;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cambio implements Serializable{
	
	@Transient
	private static final long serialVersionUID = -358249817844671675L;
	
	private Long id;
	private String from;
	private String to;
	private BigDecimal conversionFactor;
	private BigDecimal convertedValue;
	private String environment;
}
