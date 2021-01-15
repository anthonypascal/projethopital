package com.company.rooms;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Room {
    private static final Map<Integer, Boolean> rooms = new HashMap<>();

    public static void initRooms() {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        //System.out.println(imgName);
        int floor = 5; //LIRE CONFIG.YML
        int floorRoom = 12; //LIRE CONFIG.YML
        int baseFloor = 0; //LIRE CONFIG.YML

        for (int i = baseFloor; i <= floor; i++) {
            for (int j = 1; j <= floorRoom; j++) {
               int value = i * 100 + j ;
               if (!rooms.containsKey(value)) {
                   rooms.put(value, false);
               }
            }
        }
    }

    public static Map<Integer, Boolean> getRooms() {
        return rooms;
    }
}
