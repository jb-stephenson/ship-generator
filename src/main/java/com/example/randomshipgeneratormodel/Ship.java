package com.example.randomshipgeneratormodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import static com.example.randomshipgeneratormodel.PropertiesLoader.loadProperties;

public class Ship {

    private String prefix;
    private String suffix;
    private String shipType;
    private String shipFunction;
    private String shipCrew;
    private String treasure;
    private String weaponry;

    @JsonIgnore
    private Random random;

    @JsonIgnore
    private Properties configuration;

    public Ship() {
        this.random = new Random();

        try {
            this.configuration = loadProperties("application.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.generateShip();
    }

    public void generateShip() {
        this.generatePrefix();
        this.generateSuffix();
        this.generateShipType();
        this.generateShipFunction();
        this.generateShipCrew();
        this.generateTreasure();
        this.generateWeaponry(this.getShipType());
    }

    public Random getRandom() {
        return this.random;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void generatePrefix() {
        int randPrefix = this.getRandom().nextInt(100)+1;
        String prefix = configuration.getProperty("p" + randPrefix);
        this.setPrefix(prefix);
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void generateSuffix(){
        int randSuffix = this.getRandom().nextInt(100)+1;
        String suffix = configuration.getProperty("s" + randSuffix);
        this.setSuffix(suffix);
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public void generateShipType(){
        int randShipType = this.getRandom().nextInt(100)+1;
        String shipType = configuration.getProperty("st" + randShipType);
        this.setShipType(shipType);
    }

    public String getShipFunction() {
        return shipFunction;
    }

    public void setShipFunction(String shipFunction) {
        this.shipFunction = shipFunction;
    }

    public void generateShipFunction(){
        int randShipFunction = this.getRandom().nextInt(100)+1;
        String shipFunction = configuration.getProperty("sf" + randShipFunction);
        this.setShipFunction(shipFunction);
    }

    public String getShipCrew() {
        return shipCrew;
    }

    public void setShipCrew(String shipCrew) {
        this.shipCrew = shipCrew;
    }

    public void generateShipCrew(){
        int randShipCrew = this.getRandom().nextInt(100)+1;
        String shipCrew = configuration.getProperty("sc" + randShipCrew);
        this.setShipCrew(shipCrew);
    }

    public String getTreasure() {
        return treasure;
    }

    public void setTreasure(String treasure) {
        this.treasure = treasure;
    }

    public void generateTreasure(){
        int randTreasure = this.getRandom().nextInt(100)+1;
        String treasure = configuration.getProperty("t" + randTreasure);
        this.setTreasure(treasure);
    }

    public String getWeaponry() {
        return weaponry;
    }

    public void setWeaponry(String weaponry) {
        this.weaponry = weaponry;
    }

    public void generateWeaponry(String shipType) {
        int numWeapons = 0;

        if(shipType.equals("Coaster")) {
            numWeapons = 1;
        } else if(shipType.equals("Keelboat") || shipType.equals("Longship")) {
            numWeapons = 2;
        } else if(shipType.equals("Caravel") || shipType.equals("Sailing Ship")) {
            numWeapons = 3;
        } else if(shipType.equals("Warship")) {
            numWeapons = 10;
        }

        int ballista = 0, cannon = 0, mangonel = 0, ram = 0, scorpio = 0, sideShears = 0;
        for(int i=0; i<numWeapons; i++){
            int randWeapon = this.getRandom().nextInt(100)+1;
            String weapon = configuration.getProperty("w" + randWeapon);

            switch(weapon) {
                case "ballista": ballista++;
                    break;
                case "cannon": cannon++;
                    break;
                case "mangonel": mangonel++;
                    break;
                case "ram": ram++;
                    break;
                case "scorpio": scorpio++;
                    break;
                case "side shears": sideShears++;
                    break;
            }
        }

        String result = (ballista == 0) ? "" : (ballista + " ballista ");
        result += (cannon == 0) ? "" : (cannon + " cannon ");
        result += (mangonel == 0) ? "" : (mangonel + " mangonel ");
        result += (ram == 0) ? "" : (ram + " ram ");
        result += (scorpio == 0) ? "" : (scorpio + " scorpio ");
        result += (sideShears == 0) ? "" : (sideShears + " side-shears");

        this.setWeaponry(result);
    }

    public String toString() {
        String result = "\nThe ";
        String prefix = this.getPrefix();
        String suffix = this.getSuffix();
        String shipType = this.getShipType();
        String shipFunction = this.getShipFunction();
        String shipCrew = this.getShipCrew();
        String treasure = this.getTreasure();
        String weaponry = this.getWeaponry();
        result += prefix.equals("*") ? "" : prefix + " ";
        result += suffix + "\n--------------------" +
                "\nShip Type: " + shipType +
                "\nShip Function: " + shipFunction +
                "\nShip Crew: " + shipCrew +
                "\nTreasure: " + treasure +
                "\nWeaponry: " + weaponry +
                "\n--------------------";
        return result;
    }
}

class PropertiesLoader {

    public static Properties loadProperties(String resourceFileName) throws IOException, IOException {
        Properties configuration = new Properties();
        InputStream inputStream = PropertiesLoader.class
                .getClassLoader()
                .getResourceAsStream(resourceFileName);
        configuration.load(inputStream);
        inputStream.close();
        return configuration;
    }
}
