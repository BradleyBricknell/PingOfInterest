package com.reddigger.PingOfInterest.Mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.reddigger.PingOfInterest.Model.Ping;

import org.bson.Document;

public class MongoController
{
    private MongoClient _mongoClient;
    private MongoCollection _pingCollection;

    public MongoController()
    {
        MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017/");
        _mongoClient = new MongoClient(uri);
        MongoDatabase mongoDatabase = _mongoClient.getDatabase("admin");
        _pingCollection = mongoDatabase.getCollection("pings");
    }

    public MongoCollection GetPingCollection()
    {
        return _pingCollection;
    }


    public FindIterable FindPings(long lon, long lat) // radius from position?
    {
        return null;
    }

    public void InsertPing(Ping ping)
    {
        _pingCollection.insertOne(PingToDocument(ping));
    }

    public void DeletePing(Ping ping)
    {
        _pingCollection.deleteOne(PingToDocument(ping));
    }

    private Document PingToDocument(Ping ping)
    {
        Document pingAsDocument = new Document();
        pingAsDocument.put("type", ping.Type.toString());
        pingAsDocument.put("lat", ping.Latitude);
        pingAsDocument.put("long", ping.Longitude);

        return pingAsDocument;
    }

    public int PingCount()
    {
        return (int)_pingCollection.count();
    }
}
