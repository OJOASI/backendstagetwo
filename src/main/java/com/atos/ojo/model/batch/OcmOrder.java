package com.atos.ojo.model.batch;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class OcmOrder {
	@Id
	private Long id;
	private Integer chunkSize;
}