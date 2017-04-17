package com.reddigger.PingOfInterest.PintIt;

import com.reddigger.PingOfInterest.Model.Ping;
import com.reddigger.PingOfInterest.Model.PingType;
import com.reddigger.PingOfInterest.Mongo.MongoController;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MongoTests
{
    private MongoController _mongoController;

    private Ping _ping;

    @Before
    public void Setup()
    {
        _mongoController = new MongoController();
    }

    @Test
    public void assertPingCollectionIsExists() throws Exception
    {
        assertNotNull(_mongoController.GetPingCollection());
    }

    @Test
    public void assertSinglePingCanBeInserted() throws Exception
    {
        assertEquals(_mongoController.PingCount(), 0);
        _ping = new Ping(PingType.ATM, 50.8280890, -0.131488);

        _mongoController.InsertPing(_ping);

        assertEquals(_mongoController.PingCount(), 1);
    }

    @Test
    public void assertLongLatOneKilometerBoundingBoxIsAccurate()
    {
        Ping pingCloseBy = new Ping(PingType.LIBRARY, 50.822793748758566, -0.1301);

        _mongoController.InsertPing(new Ping(PingType.BAR, 50.802440, -0.131123213));
        _mongoController.InsertPing(new Ping(PingType.CAFE, 50.890023, -0.13411111));
        _mongoController.InsertPing(new Ping(PingType.ATM, 50.086023, -1.134));
        _mongoController.InsertPing(new Ping(PingType.PUBLICTOILET, 50.822893748758566, -0.5401));
        _mongoController.InsertPing(pingCloseBy);

        List<Ping> returnedPings = _mongoController.FindSurroundingPings(50.828089, -0.131488, 1);

        assertTrue(returnedPings.size() == 1);
        Ping validPing = returnedPings.get(0);
        assertTrue(SamePings(pingCloseBy, validPing));
    }

    @After
    public void TearDown()
    {
        if(_mongoController.PingCount() > 0)
            _mongoController.GetPingCollection().deleteMany(new Document());

    }


    private boolean SamePings(Ping ping1, Ping ping2)
    {
        return (ping1.Type.equals(ping2.Type)) && (ping1.Latitude == ping2.Latitude) && (ping1.Longitude == ping2.Longitude);
    }
}

