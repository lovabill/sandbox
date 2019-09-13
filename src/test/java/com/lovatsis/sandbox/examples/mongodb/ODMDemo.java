package com.lovatsis.sandbox.examples.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import org.junit.Test;

import java.util.List;

/**
 * MongoDB mapping to POJO (Object Document Mapping, similar to Object Relational Mapping).
 * Library used: Morphia.
 */
public class ODMDemo {

    @Test
    public void testObjectDocumentMapping() {
        Morphia morphia = new Morphia();
        morphia.mapPackage("com.baeldung.morphia");
        Datastore datastore = null;
        System.out.print("Connecting...");
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        datastore = morphia.createDatastore(mongoClient, "tutorial");
        System.out.println("OK");
        datastore.ensureIndexes();

        Contact contact = new Contact("Jenny Joe", "000000000");
        datastore.save(contact);

        List<Contact> contacts = datastore.createQuery(Contact.class)
                .field("name")
                .equal("Jenny Joe")
                .find()
                .toList();
        contacts.forEach(d -> System.out.println(d));
        datastore.delete(contact);
    }
}

