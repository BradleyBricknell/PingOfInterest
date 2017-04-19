package com.reddigger.PingOfInterest.Mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.reddigger.PingOfInterest.Model.Ping;
import com.reddigger.PingOfInterest.Model.PingType;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;

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

    public List<Ping> FindSurroundingPings(double lati, double longi, int km){
        double lon1,lon2,lat1,lat2;

        double R = 6371; // earth radius in km 26.447521

        double radius = 0.75 * km; // km

        List<Ping> returnedPings = new ArrayList<>();

        lon1 =    (longi + Math.toDegrees(radius / R / Math.cos(Math.toRadians(lati))));
        lat1 =   (lati - Math.toDegrees(radius / R));

        lon2 =   (longi - Math.toDegrees(radius / R / Math.cos(Math.toRadians(lati))));
        lat2 =   (lati + Math.toDegrees(radius / R));


     ArrayList<Document> pings = (ArrayList<Document>) _pingCollection.find().filter(and(
                     gt("lat", lat1),
                     lt("lat", lat2),
                     lt("long", lon1),
                     gt("long", lon2)))
             .into(new ArrayList<Document>());

        for (Document p : pings )
           returnedPings.add(new Ping(PingType.valueOf(p.get("type").toString()),  p.getDouble("lat"), p.getDouble("long")));

        return returnedPings;
    }
}
