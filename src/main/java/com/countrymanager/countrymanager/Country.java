package com.countrymanager.countrymanager;

public class Country {
    private String _name;
    private String _countryCode;
    private boolean _visited;

    public Country (String name, String countryCode, boolean visited) {
        _name = name;
        _countryCode = countryCode;
        _visited = visited;
    }
    
    public Country (String name, String countryCode) {
        _name = name;
        _countryCode = countryCode;
    }

    @Override
    public String toString() {
        return _name + " " + _countryCode + " " + _visited; 
    } 

    public String getName() {
        return _name;
    }

    public boolean visited() {
        return _visited;
    }

    public void setVisited(boolean visited) {
        _visited = visited;
    }

    public String getCountryCode() {
        return _countryCode;
    }
}
