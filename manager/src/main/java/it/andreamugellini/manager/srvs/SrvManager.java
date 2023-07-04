package it.andreamugellini.manager.srvs;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import it.andreamugellini.manager.ctrls.dto.DTOCalculation;
import it.andreamugellini.manager.exc.ExCalculationNotFound;
import it.andreamugellini.manager.rps.RepoCalculation;
import it.andreamugellini.manager.rps.entity.EntCalculation;

@Service
public class SrvManager {

	@Autowired
	private KafkaTemplate<String, EntCalculation> kafka;

	@Autowired
	private RepoCalculation repoCalculation;

	public DTOCalculation create(DTOCalculation dto) {

		EntCalculation c = new EntCalculation();
		c.setOperation(dto.getOperation());
		this.repoCalculation.save(c);
		kafka.send("evaluate-calc", c);
		dto.setId(c.getId());

		return dto;
	}

	public DTOCalculation get(String id) {
		Optional<EntCalculation> c = this.repoCalculation.findById(id);
		if (!c.isPresent())
			throw new ExCalculationNotFound(id);

		DTOCalculation dto = new DTOCalculation();
		dto.setId(c.get().getId());
		dto.setOperation(c.get().getOperation());
		dto.setResult(c.get().getResult());

		return dto;

	}

	public DTOCalculation update(String id, DTOCalculation dto) {

		Optional<EntCalculation> c = this.repoCalculation.findById(id);
		if (!c.isPresent())
			throw new ExCalculationNotFound(id);

		c.get().setOperation(dto.getOperation());

		this.repoCalculation.save(c.get());

		kafka.send("evaluate-calc", c.get());

		dto.setId(id);
		return dto;
	}

	public void delete(String id) {
		Optional<EntCalculation> c = this.repoCalculation.findById(id);
		if(!c.isPresent()) throw new ExCalculationNotFound(id);
		this.repoCalculation.deleteById(id);

	}

}
