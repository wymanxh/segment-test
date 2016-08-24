package com.wyman.query.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories
public class MongodbConfig extends AbstractMongoConfiguration {

	@Override
	public MappingMongoConverter mappingMongoConverter() throws Exception {
		DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
		MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext());
		converter.setCustomConversions(customConversions());
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
		return converter;
	}

	@Override
	protected String getDatabaseName() {
		return "segment_test";
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient("127.0.0.1", 27017);
	}

	@Override
	protected String getMappingBasePackage() {
		return "com.wyman.query.mongo.repository";
	}

}
