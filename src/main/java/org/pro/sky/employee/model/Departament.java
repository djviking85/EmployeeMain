package org.pro.sky.employee.model;

import java.util.HashMap;
import java.util.Map;

public class Departament {
    private int id;
    private String name;

    public Departament(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static final Map<Integer, Departament> DEPARTAMENT_MAP_ID;
    static {
        DEPARTAMENT_MAP_ID = new HashMap<>();
        DEPARTAMENT_MAP_ID.put(1, new Departament(1, "It"));
        DEPARTAMENT_MAP_ID.put(2, new Departament(2, "Buchgaltery"));
        DEPARTAMENT_MAP_ID.put(3, new Departament(3, "managers"));
    }

}
