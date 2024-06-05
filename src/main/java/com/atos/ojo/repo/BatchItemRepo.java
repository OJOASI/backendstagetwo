package com.atos.ojo.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.atos.ojo.model.batch.BatchItem;

@Repository
public interface BatchItemRepo extends CrudRepository<BatchItem, Long> {

}
