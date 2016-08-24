package com.wyman;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import com.wyman.query.mongo.dto.Customer;
import com.wyman.query.mongo.dto.DateProperty;
import com.wyman.query.mongo.dto.MultiSetProperty;
import com.wyman.query.mongo.dto.NumberProperty;
import com.wyman.query.mongo.dto.SetProperty;
import com.wyman.query.mongo.dto.TextProperty;
import com.wyman.segment.EmailDomainField;
import com.wyman.segment.Group;
import com.wyman.segment.Matcher;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SegmentTestApplicationTests {

	private static final String I0K = "10k";
	private static final String I00K = "100k";
	private static final String I000K = "1000k";
	private static final String I0000K = "10000k";

	@Autowired
	private MongoTemplate template;

	@After
	public void tearDown() {
		// template.getDb().dropDatabase();
	}

//	@Test
	public void query() throws InterruptedException {
		DBCollection collection = template.getCollection(template.getCollectionName(Customer.class));
		BasicDBObject query = new BasicDBObject();
		BasicDBObject fields = new BasicDBObject();
		fields.put("textProps", 0);
		fields.put("numberProps", 0);
		fields.put("setProps", 0);
		fields.put("multisetProps", 0);
		fields.put("dataProps", 0);
		query.append("dbId", I000K);
		int size = 1;
		CountDownLatch cdl = new CountDownLatch(size);
		ExecutorService pool = Executors.newFixedThreadPool(size);

		for (int i = 0; i < size; i++) {
			pool.execute(() -> {
				DBCursor cursor = collection.find(query, fields);

				long start = System.currentTimeMillis();
				int count = 0;
				while (cursor.hasNext()) {
					DBObject next = cursor.next();
					Customer customer = template.getConverter().read(Customer.class, next);
					// System.out.println(customer.getTextProps());
					Matcher matcher = new Matcher();
					if (matcher.include(Group.andGroup(EmailDomainField.equal("example.com"))).match(customer)) {
						count++;
					}
				}
				cursor.close();
				System.out.println("count: " + count);
				System.out.println((System.currentTimeMillis() - start) + " ms");
				cdl.countDown();
			});
		}
		cdl.await();
	}

	// @Test
	public void contextLoads() {
		Lorem lorem = LoremIpsum.getInstance();
		template.setWriteConcern(WriteConcern.UNACKNOWLEDGED);
		for (int j = 0; j < 10000000; j++) {
			Customer customer = new Customer();
			customer.setDbId(I000K);
			customer.setExternalId(ObjectId.get().toString());
			customer.setEmail(lorem.getEmail());
			customer.setFirstName(lorem.getFirstName());
			customer.setLastName(lorem.getLastName());
			for (int i = 0; i < 10; i++) {
				customer.addTextProperties(new TextProperty(ObjectId.get().toString(), lorem.getWords(5)));
				customer.addDateProperties(new DateProperty(ObjectId.get().toString(), new Date()));
				customer.addNumberProperties(new NumberProperty(ObjectId.get().toString(), Math.random()));
				customer.addSetProperties(new SetProperty(ObjectId.get().toString(), lorem.getWords(2)));
				customer.addMultiSetProperties(new MultiSetProperty(ObjectId.get().toString(), lorem.getWords(2), lorem.getWords(2), lorem.getWords(2), lorem
						.getWords(2), lorem.getWords(2)));

			}
			template.insert(customer);
		}
	}

}
