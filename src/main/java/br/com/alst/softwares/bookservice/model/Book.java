package br.com.alst.softwares.bookservice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Generated
@Entity(name = "book")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernate_lazy_initializer", "handler"})
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5325321483644907142L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length=180)
	private String author;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date launchDate;
	
	@Column(nullable=false)
	private BigDecimal price;
	
	@Column(nullable=false, length=250)
	private String title;
	
	@Transient
	private String currency;
	
	@Transient
	private String environment;
}
