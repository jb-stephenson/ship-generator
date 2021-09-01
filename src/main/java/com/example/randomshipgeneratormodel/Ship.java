package com.example.randomshipgeneratormodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.core.io.ClassPathResource;

import java.beans.Transient;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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

    public Ship() {
        this.random = new Random();
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
        HashMap<Integer, String> prefixes = generateStruct("prefixes.txt");
        int randPrefix = this.getRandom().nextInt(100)+1;
        String prefix = prefixes.get(randPrefix);
        this.setPrefix(prefix);
    }

    public static HashMap<Integer, String> generateStruct(String fileName){
        HashMap<Integer, String> mappings = new HashMap<>();

        try{
            File resource = new ClassPathResource(fileName).getFile();
            //String text = new String(Files.readAllBytes(resource.toPath()));
            List<String> lines = Files.readAllLines(resource.toPath(), StandardCharsets.UTF_8);

            for(String line: lines){
                int startVal=0;
                int endVal=0;
                if(line.contains("-")){ //value is possible over a range
                    String range = line.substring(0, line.indexOf(":"));
                    String start = range.substring(0, range.indexOf("-"));
                    startVal = Integer.parseInt(start);
                    String end = range.substring(range.indexOf("-")+1);
                    endVal = Integer.parseInt(end);

                    //get connected value
                    String value = line.substring(line.indexOf(":")+1);

                    for(int i=startVal; i < endVal+1; i++){
                        mappings.put(i, value);
                    }
                }
                else{ //value is possible for only 1 number
                    String start = line.substring(0,line.indexOf(":"));
                    startVal = Integer.parseInt(start);
                    String value = line.substring(line.indexOf(":")+1);
                    mappings.put(startVal, value);
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return mappings;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void generateSuffix(){
        HashMap<Integer, String> prefixes = generateStruct("suffixes.txt");
        int randSuffix = this.getRandom().nextInt(100)+1;
        String suffix = prefixes.get(randSuffix);
        this.setSuffix(suffix);
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public void generateShipType(){
        HashMap<Integer, String> shipTypes = generateStruct("ship types.txt");
        int randShipType = this.getRandom().nextInt(100)+1;
        String shipType = shipTypes.get(randShipType);
        this.setShipType(shipType);
    }

    public String getShipFunction() {
        return shipFunction;
    }

    public void setShipFunction(String shipFunction) {
        this.shipFunction = shipFunction;
    }

    public void generateShipFunction(){
        HashMap<Integer, String> shipFunctions = generateStruct("ship functions.txt");
        int randShipFunction = this.getRandom().nextInt(100)+1;
        String shipFunction = shipFunctions.get(randShipFunction);
        this.setShipFunction(shipFunction);
    }

    public String getShipCrew() {
        return shipCrew;
    }

    public void setShipCrew(String shipCrew) {
        this.shipCrew = shipCrew;
    }

    public void generateShipCrew(){
        HashMap<Integer, String> shipCrews = generateStruct("ship crews.txt");
        int randShipCrew = this.getRandom().nextInt(100)+1;
        String shipCrew = shipCrews.get(randShipCrew);
        this.setShipCrew(shipCrew);
    }

    public String getTreasure() {
        return treasure;
    }

    public void setTreasure(String treasure) {
        this.treasure = treasure;
    }

    public void generateTreasure(){
        HashMap<Integer, String> treasures = generateStruct("treasures.txt");
        int randTreasure = this.getRandom().nextInt(100)+1;
        String treasure = treasures.get(randTreasure);
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
        HashMap<Integer, String> weapons = generateStruct("weapons.txt");

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
            String weapon = weapons.get(randWeapon);

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

    public String toString(){
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
