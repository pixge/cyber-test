package it.andreamugellini.manager.srvs;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import it.andreamugellini.manager.rps.RepoCalculation;
import it.andreamugellini.manager.rps.entity.EntCalculation;

@Service
public class SrvManager {
	
	@Autowired
	private  KafkaTemplate<String, EntCalculation> kafka;
	
	@Autowired
	private  RepoCalculation repoCalculation;
	
	

	public EntCalculation create(EntCalculation calculation) {
		
		EntCalculation c = this.repoCalculation.save(calculation);
		kafka.send("evaluate-calc", calculation);
		return c;
	}

	public EntCalculation get(Long id) {
		Optional<EntCalculation> c = this.repoCalculation.findById(id);
		return c.get();		
	}

	public EntCalculation update(Long id, EntCalculation calculation) {
		this.repoCalculation.save(calculation);
		kafka.send("calculate", calculation);
		return calculation;
	}

	public void delete(Long id) {
		this.repoCalculation.deleteById(id);
	}

}
