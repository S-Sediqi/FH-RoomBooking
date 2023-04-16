package com.validation;

import java.io.*;
import java.util.*;

import com.logics.LogicAdministrator;

public class RoomDataValidation {

    private final static String COMMA_DELIMITER = ",";

    public RoomDataValidation () {
    }

    // The method gets the room number as input
    // it runs the readCSV_Room() then calls the room number list (rNo_List)
    // then compares the input to existing room numbers, if it exist or not
    public boolean doesRoomExist(String roomNo) {
        boolean b = false;
        ArrayList<String> rNumList = new ArrayList<>();

        LogicAdministrator admin = new LogicAdministrator();
        rNumList = admin.getRoomDataList(admin.ROOM_NUMBER_INDEX);
        for (int i = 1; i < rNumList.size(); i++) {
            if ( rNumList.get(i).equals(roomNo) ) {
                b = true;
            }
        }
        return b;
    }

    // the method gets the room number and checks if the room already exist in the list or not
    public boolean isRoomListed(String roomNo, String filePath) {
        boolean b = false;
        String path = filePath;
        ArrayList<String> roomNo_List = new ArrayList<>();
        try {
            BufferedReader file_out = new BufferedReader(new FileReader(path));
            String csvLine = "";
            while ( (csvLine = file_out.readLine()) != null ) {
                String[] dataLine = csvLine.split(COMMA_DELIMITER);
                roomNo_List.add(dataLine[0]);
            }
            file_out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < roomNo_List.size(); i++) {
            //int n = Integer.parseInt(roomNo_List.get(i));
            if (roomNo.equals(roomNo_List.get(i))) {
                b = true;
            }
        }
        return b;
    }

    // checks if the room name input is correct
    // Room Name: only letters are allowed (no number, no space, no symbol) 
    public boolean isRoomName_Valid (String roomName) {
        boolean b = false;
        int count = 0;
        for (int i = 0; i < roomName.length(); i++) {
            // checks if the user ID contains only numbers
            if ( !Character.isLetter(roomName.charAt(i)) ) {
                count++;
            }
        }
        if (count == 0) {
            b = true;
        }
        if (count > 0) {
            b = false;
        }
        return b;
    }

    // checks if the room location input is correct
    // Room Location: space and comma (,) is not allowed 
    public boolean isRoomLocation_Valid (String roomLocation) {
        boolean b = false;
        int count = 0;
        for (int i = 0; i < roomLocation.length(); i++) {
            // checks if the user ID contains only numbers
            if ( Character.isWhitespace(roomLocation.charAt(i)) || roomLocation.contains(",") ) {
                count++;
            }
        }
        if (count == 0) {
            b = true;
        }
        if (count > 0) {
            b = false;
        }
        return b;
    }

    // checks if the room resource input is correct
    // Room Resources: space, comma (,) and hyphen (-) are not allowed 
    public boolean isRoomResources_Valid (String roomResources) {
        boolean b = false;
        int count = 0;
        for (int i = 0; i < roomResources.length(); i++) {
            // checks if the user ID contains only numbers
            if ( Character.isWhitespace(roomResources.charAt(i)) || roomResources.contains(",") ) {
                count++;
            }
        }
        if (count == 0) {
            b = true;
        }
        if (count > 0) {
            b = false;
        }
        return b;
    }
}