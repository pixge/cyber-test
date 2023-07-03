package it.andreamugellini.manager.rps;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import it.andreamugellini.manager.rps.entity.EntCalculation;


@Repository
public interface RepoCalculation extends MongoRepository<EntCalculation, Long> {

}
