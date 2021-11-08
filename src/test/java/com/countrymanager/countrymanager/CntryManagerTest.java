package com.countrymanager.countrymanager;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Test;

public class CntryManagerTest {

    @Test
    public void writeJsonTest() throws IOException, ParseException {
        CntryManager CM = new CntryManager();
        CM.writeJSONVisited();
    }

    @Test
    public void testUpdateVisited() throws IOException, ParseException {
        CntryManager CM = new CntryManager();
        try {
        CM.writeJSONVisited();
        Country country = new Country("Bulgaria", "BG", false);
        CM.updateCountryVisited(country, true);

        assertTrue(CM.getVisitedCountries().size() != 0);
        } finally {
            CM.writeJSONVisited();
        }
    }

    @Test
    public void testLoadCountries() throws IOException, ParseException {
        CntryManager CM = new CntryManager();
        assertTrue(CM.getCountries().size() > 0);
    }

    @Test
    public void testUpdateVisited2() throws IOException, ParseException {
        CntryManager CM = new CntryManager();
        // try {
        CM.writeJSONVisited();
        Country country = new Country("Bulgaria", "BG");
        CM.updateCountryVisited(country, true);
        Country country2 = new Country("Antarctica", "AQ");
        CM.updateCountryVisited(country2, true);

        CM.getVisitedCountries().stream().forEach(c -> System.out.println(c.getName()));

        assertTrue(CM.getVisitedCountries().size() > 0);
        // } finally {
        //     CM.writeJSON();
        // }
    }
}
