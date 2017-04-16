package com.reddigger.PingOfInterest.PintIt;

import com.reddigger.PingOfInterest.Model.Ping;
import com.reddigger.PingOfInterest.Model.PingType;
import com.reddigger.PingOfInterest.Mongo.MongoController;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    public void assertSinglePingCanBeFoundInCollection() throws Exception
    {

    }

    @After
    public void TearDown()
    {
     _mongoController.DeletePing(_ping);
    }
}