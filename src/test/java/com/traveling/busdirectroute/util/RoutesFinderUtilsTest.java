package com.traveling.busdirectroute.util;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import com.traveling.busdirectroute.service.FileCacheService;
import com.traveling.busdirectroute.service.impl.BusRoutesServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

@RunWith(DataProviderRunner.class)
public class RoutesFinderUtilsTest {

    @DataProvider
    public static Object[][] routeFilesDataProvider() {
        final String path1 = "/routes-1.txt";
        final String path2 = "/routes-2.txt";
        return new Object[][]{
                {path1, 1, 2, true},
                {path1, 1, 4, true},
                {path1, 3, 5, true},
                {path1, 0, 6, true},
                {path1, 0, 4, true},
                {path1, 0, 10, false},
                {path1, 10, 12, false},
                {path1, 2, 6, false},
                {path1, 5, 3, true},
                {path1, 5, 6, true},
                {path2, 153, 24, true},
                {path2, 24, 153, true},
                {path2, 1, 24, false},
                {path2, 150, 5, true},
                {path2, 14, 114, false}
        };
    }

    @Test
    @UseDataProvider("routeFilesDataProvider")
    public void busDirectRouteFinder(final String relatedFileDataPath, final int depSid, final int arrSid, final boolean expected) {
        final String path = RoutesFinderUtilsTest.class.getResource(relatedFileDataPath).getPath();
        final FileCacheService fileCacheService = Mockito.mock(FileCacheService.class);
        Mockito.when(fileCacheService.loadBusRoutes()).thenAnswer(on -> RoutesFinderUtils.lookForDirectRoute(path));

        final boolean actual = new BusRoutesServiceImpl(fileCacheService).lookForDirectRoute(depSid, arrSid).isDirectBusRoute();
        Assert.assertEquals("Unexpected route response", expected, actual);
    }

}
