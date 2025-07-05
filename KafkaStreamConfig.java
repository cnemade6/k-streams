package com.example.kafkaStreamProducer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import com.example.kafkaStreamProducer.service.KStreamProcessor;
import com.example.kafkaStreamProducer.util.Handset;
import com.example.kafkaStreamProducer.util.HandsetSerde;

@EnableKafka
@EnableKafkaStreams
@Configuration
public class KafkaStreamConfig {

	@Autowired
	  private KStreamProcessor kStreamProcessor ;
	
	@Bean
	(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
	 public KafkaStreamsConfiguration kStreamsConfig() {
	         Map<String, Object> props = new HashMap<>();
	        // props.put("APPLICATION_ID_CONFIG", "my-streams-app");
	         props.put(StreamsConfig.APPLICATION_ID_CONFIG, "my-streams-app");
	         props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
	         props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
	         props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Double().getClass().getName());
	         props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); 

	         return new KafkaStreamsConfiguration(props);
	  }
	
	@Bean
	  public ConsumerFactory<String, Handset> consumerFactory() {
	         Map<String, Object> props = new HashMap<>();
	         props.put(
	           ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, 
	           "127.0.0.1:9092");
	         props.put(
	           ConsumerConfig.GROUP_ID_CONFIG, 
	           "demo-1");
	         props.put(
	           ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
	           StringDeserializer.class);
	         props.put(
	           ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
	           StringDeserializer.class);
	         return new DefaultKafkaConsumerFactory<>(props);
	     }
	
	@Bean
    public ConcurrentKafkaListenerContainerFactory<String, Handset> kafkaListenerContainerFactory() {
   
        ConcurrentKafkaListenerContainerFactory<String, Handset> factory =
          new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
	
	@Bean
    public KStream<String, Handset> kStream(StreamsBuilder kStreamBuilder) {
     
        KStream<String, Handset> stream = kStreamBuilder.stream("sourceTopic", Consumed.with(Serdes.String(), new HandsetSerde()));
	System.out.println("source data stream consumed by KStream.......");
        
        this.kStreamProcessor.process(stream);
	
	
	  return stream;
	 
}
}