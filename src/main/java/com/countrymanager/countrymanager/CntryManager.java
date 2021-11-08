package com.countrymanager.countrymanager;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.util.ResourceUtils;



public class CntryManager {
    
    private Set<Country> _countries = new HashSet<>(); 

    private File _file = ResourceUtils.getFile("classpath:countries.json");
    

    public CntryManager() throws IOException, ParseException {
        loadCountries();
    }

    private void loadCountries() throws IOException, ParseException {
        Reader in = new FileReader(_file);

        JSONObject jsonObject = (JSONObject) JSONValue.parseWithException(in);
        JSONArray rows = (JSONArray) jsonObject.get("countries");

        for (int i = 0; i < rows.size(); i++) {
            JSONObject row = (JSONObject) rows.get(i);
            _countries.add(new Country(row.get("name").toString(), row.get("code").toString(), (boolean) row.get("visited")));    
        }
    }

    public void writeJSONVisited() throws IOException, ParseException {
        Reader in = new FileReader(_file);

        JSONObject jsonObject = (JSONObject) JSONValue.parseWithException(in);
        JSONArray rows = (JSONArray) jsonObject.get("countries");

        for (int i = 0; i < rows.size(); i++) {
            JSONObject row = (JSONObject) rows.get(i);
            row.put("visited",false);
        }
    
        try (Writer out = new FileWriter(_file)) {
          JSONValue.writeJSONString(jsonObject, out);
        }
    }

    public void updateCountryVisited(Country country, boolean visited) throws IOException, ParseException {
        _countries.stream().filter(c -> c.getCountryCode().equals(country.getCountryCode())).forEach(c -> c.setVisited(true));
        updateJSONCountryVisited(country, visited);
    }

    private void updateJSONCountryVisited(Country country, boolean visited) throws IOException, ParseException {
        Reader in = new FileReader(_file);

        JSONObject jsonObject = (JSONObject) JSONValue.parseWithException(in);
        JSONArray rows = (JSONArray) jsonObject.get("countries");

        for (int i = 0; i < rows.size(); i++) {
            JSONObject row = (JSONObject) rows.get(i);
            if (country.getCountryCode().equals(row.get("code").toString())) {
                row.put("visited", visited);
            }
        }
    
        try (Writer out = new FileWriter(_file)) {
          JSONValue.writeJSONString(jsonObject, out);
        }
    }

    public Set<Country> getCountries() {
        return _countries;
    }

    public Set<Country> getVisitedCountries() {
        return _countries.stream().filter(c -> c.visited()).collect(Collectors.toSet());
    }
}
