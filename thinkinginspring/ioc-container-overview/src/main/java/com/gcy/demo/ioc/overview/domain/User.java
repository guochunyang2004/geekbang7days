package com.gcy.demo.ioc.overview.domain;

import com.gcy.demo.ioc.overview.enums.City;
import org.springframework.core.io.Resource;

import java.util.Arrays;
import java.util.List;


/**
 * 用户类
 */
public class User {
    private  Long Id;


    private String name;
    private City city;
    private City[] workCities;

    private List<City> lifeCites;
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City[] getWorkCities() {
        return workCities;
    }

    public void setWorkCities(City[] workCities) {
        this.workCities = workCities;
    }

    public Resource getConfigFileLocation() {
        return configFileLocation;
    }

    public void setConfigFileLocation(Resource configFileLocation) {
        this.configFileLocation = configFileLocation;
    }

    private Resource configFileLocation;

    public static User createUser(){
        User user = new User();
        user.setId(1L);
        user.setName("张三");
        return user;
    }

    public List<City> getLifeCites() {
        return lifeCites;
    }

    public void setLifeCites(List<City> lifeCites) {
        this.lifeCites = lifeCites;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", workCities=" + Arrays.toString(workCities) +
                ", lifeCites=" + lifeCites +
                ", configFileLocation=" + configFileLocation +
                '}';
    }
}
