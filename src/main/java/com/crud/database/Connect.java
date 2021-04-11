package com.crud.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import java.io.IOException;
import java.util.Properties;

public class Connect implements AutoCloseable {

    private static MongoClient mongoClient;

    public static MongoClient getClient() {

        final var properties = Connect.getProperties();

        final var mongoClientURI = new MongoClientURI(properties.getProperty("db.mongo.cloud.url"));

        mongoClient = new MongoClient(mongoClientURI);

        return mongoClient;
    }

    public static MongoDatabase toDB(String databaseName) {

        final var database = (databaseName != null) ? databaseName : Connect.getProperties()
                .getProperty("db.mongo.cloud.database");

        final var mongoDatabase = Connect.getClient()
                .getDatabase(database);

        return mongoDatabase;
    }

    private static Properties getProperties() {

        final var resource = new Connect().getClass()
                .getClassLoader()
                .getResourceAsStream("database.properties");

        final var properties = new Properties();

        try {
            properties.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    @Override
    public void close() throws Exception {

        if (mongoClient != null)
            mongoClient.close();
    }
}
