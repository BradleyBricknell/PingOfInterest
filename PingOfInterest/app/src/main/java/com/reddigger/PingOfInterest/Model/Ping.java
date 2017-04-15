package com.reddigger.PingOfInterest.Model;

import java.util.Enumeration;

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
    public PingType Type;

    public double Longitude;

    public double Latitude;
}
