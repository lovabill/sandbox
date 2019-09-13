package com.lovatsis.sandbox.examples.mongodb;

import com.mongodb.*;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

/**
 * MongoDB driver demo. Download mongodb community edition as zip, extract it,
 * run it from mongo/bin/mongod.exe --dbpath="/path/to/data/directory"
 */
public class DriverDemo {

    @Test
    public void testMongoDBDriver() {
        MongoClient mongoClient = null;
        System.out.print("Connecting...");
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        System.out.println("OK");

        DB database = mongoClient.getDB("tutorial");

        DBCollection contacts = database.getCollection("Contacts");

        DBObject personD = new BasicDBObject("_id", UUID.randomUUID().toString())
                .append("name", "John Doe")
                .append("mobile", "123456789");

        contacts.insert(personD);

        List<DBObject> dbObjects = contacts.find(new BasicDBObject("name", "John Doe")).toArray();
        dbObjects.forEach(d -> System.out.println(d));

        contacts.remove(new BasicDBObject("name", "John Doe"));
    }
}
