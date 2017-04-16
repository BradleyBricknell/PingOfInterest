package com.reddigger.PingOfInterest.Model;

/**
 * Created by Doggo Puter on 15/04/2017.
 */

public class Ping
{
    public Ping(PingType type, double longitude, double latitude)
    {
        Type = type;
        Longitude = longitude;
        Latitude = latitude;
    }

    public Ping(PingType type, double longitude, double latitude, boolean validated)
    {
        Type = type;
        Longitude = longitude;
        Latitude = latitude;
        Validated = validated;
    }

    public PingType Type;

    public double Longitude;

    public double Latitude;

    public boolean Validated;
}
