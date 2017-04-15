package com.reddigger.PingOfInterest.Mongo;


import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoControllers
{
    private MongoClient _mongoClient;
    private MongoCollection _collection;

    public MongoControllers()
    {
        _mongoClient = new MongoClient("mongodb://>@ds031903.mlab.com:31903/db_amid");
        String dbName =  _mongoClient.listDatabaseNames().first();
        MongoDatabase mongoDatabase  = _mongoClient.getDatabase(dbName);

       _collection = mongoDatabase.getCollection("pings");

    }

    public FindIterable FindPings(long lon, long lat) // radius from position?
    {
        return null;
    }

    public void InsertPing(long lon, long lat, String type)
    {
    }




}
