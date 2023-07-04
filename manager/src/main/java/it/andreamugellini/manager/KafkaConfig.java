package it.andreamugellini.manager;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import it.andreamugellini.manager.rps.entity.EntCalculation;

@Configuration
public class KafkaConfig {

	@Value("${kafka.ip}")
	String kafkaIp;
	
    @Bean
    public ProducerFactory<String,EntCalculation> producerFactory(){
        Map<String,Object> config = new HashMap<String, Object>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaIp);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<String,EntCalculation>(config);
    }

    @Bean
    public KafkaTemplate<String, EntCalculation> kafkaTemplate(){
        return new KafkaTemplate<String, EntCalculation>(producerFactory());
    }
}