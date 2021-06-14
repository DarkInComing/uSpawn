package it.darksolutions.uspawn.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public enum TypeSerializations {
    FIRSTSPAWN("firstspawn", "FirstSpawn type"), SPAWN("spawn", "Normal spawn type");


    private String dirFile;
    private String name;

}
