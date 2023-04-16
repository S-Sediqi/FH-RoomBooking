package com.logics;

import java.io.*;
import java.util.*;

import com.validation.RoomDataValidation;

public class Room_Data_RW {

    private static final String COMMA_DELIMITER = ",";
    private static final int ROOM_STATUS_INDEX = 0;
    private static final int ROOM_NUMBER_INDEX = 1;
    private static final int ROOM_NAME_INDEX = 2;
    private static final int ROOM_LOCATION_INDEX = 3;
    private static final int ROOM_CAPACITY_INDEX = 4;
    private static final int ROOM_RESOURCES_INDEX = 5;

    private static final String STATUS_ROOM_OPEN = "OPEN";
    private static final String STATUS_ROOM_CLOSE = "CLOSE";

    private ArrayList<ArrayList<String>> roomList = new ArrayList<>();
    private ArrayList<String> rStatus_List = new ArrayList<>();
    private ArrayList<String> rNo_List = new ArrayList<>();
    private ArrayList<String> rName_List = new ArrayList<>();
    private ArrayList<String> rLocation_List = new ArrayList<>();
    private ArrayList<String> rCapacity_List = new ArrayList<>();
    private ArrayList<String> rResources_List = new ArrayList<>();

    protected Room_Data_RW() {
    }

    // This method saves the room data into ROOM CSV file
    protected void writeToRoomDataCSV(String room_Input) {
        LogicAdministrator admin = new LogicAdministrator();
        String path = admin.getRoomDBpath();
        try {
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(path, true));
            bWriter.write("\n"+room_Input);
            bWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method reads the data from the RoomData CSV file
    // it creates Arraylists for each room data and adds each data to its corresponding ArrayList
    // then it adds all ArrayLists to one 2D ArrayList (roomList)
    private void readRoomDataFile() {
        LogicAdministrator admin = new LogicAdministrator();
        String path = admin.getRoomDBpath();
        try {
            BufferedReader bReader = new BufferedReader(new FileReader(path));
            
            String csvLine = "";
            while ( (csvLine = bReader.readLine()) != null ) {
                String[] dataLine = csvLine.split(COMMA_DELIMITER);
                
                // saving each data of room into a separate ArrayList
                rStatus_List.add(dataLine[0]);
                rNo_List.add(dataLine[1]);
                rName_List.add(dataLine[2]);
                rLocation_List.add(dataLine[3]);
                rCapacity_List.add(dataLine[4]);
                rResources_List.add(dataLine[5]);
            }
            // adding all ArrayLists to the one ArrayList 
            // the new/big ArrayList will save other ArrayLists as its elements
            roomList.add(rStatus_List);
            roomList.add(rNo_List);
            roomList.add(rName_List);
            roomList.add(rLocation_List);
            roomList.add(rCapacity_List);
            roomList.add(rResources_List);

            bReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // The method gets requested Room Data as Integer
    // it looks through each data list
    // then returns the requested data as an ArrayList 
    protected ArrayList<String> getOneDataListOfRooms(int req_RoomDataIndex) {
        ArrayList<String> roomData = new ArrayList<>();
        readRoomDataFile();
        switch (req_RoomDataIndex) {
            case (ROOM_STATUS_INDEX): roomData = roomList.get(ROOM_STATUS_INDEX);
                break;
            case (ROOM_NUMBER_INDEX): roomData = roomList.get(ROOM_NUMBER_INDEX);
                break;    
            case (ROOM_NAME_INDEX): roomData = roomList.get(ROOM_NAME_INDEX);
                break;
            case (ROOM_LOCATION_INDEX): roomData = roomList.get(ROOM_LOCATION_INDEX);
                break;
            case (ROOM_CAPACITY_INDEX): roomData = roomList.get(ROOM_CAPACITY_INDEX);
                break;
            case (ROOM_RESOURCES_INDEX): roomData = roomList.get(ROOM_RESOURCES_INDEX);
                break;
            default:
                break;
        }
        return roomData;
    }

    // The method returns a 2D Array of room data for the JTable in the UI
    protected String[][] getRoomDataIn2DArray(){
        readRoomDataFile();
        int colSize = roomList.size();
        int rowSize = roomList.get(0).size();
        String[][] arrData = new String[rowSize][colSize];

        for (int i = 0; i < arrData.length; i++) {
            for (int j = 0; j < arrData[i].length; j++) {
                arrData[i][j] = roomList.get(j).get(i);
            }
        }
        return arrData;
    }

    // This method returns an array of column headers from room data file for the UI
    protected String[] getRoomDataHeaderList() {
        readRoomDataFile();
        int arrSize = roomList.size();
        String[] arrHeader = new String[arrSize];
        for (int i = 0; i < arrHeader.length; i++) {
            arrHeader[i] = roomList.get(i).get(0);
        }
        return arrHeader;
    }
/* 
    // This method returns an array of column headers from room data file for the UI
    protected String[] getRoomNumberList() {
        readRoomDataFile();
        int arrSize = roomList.get(ROOM_NUMBER_INDEX).size();
        String[] arrNum = new String[arrSize];
        for (int i = 0; i < arrNum.length; i++) {
            arrNum[i] = roomList.get(ROOM_NUMBER_INDEX).get(i);
        }
        return arrNum;
    }
*/
    // The method returns the data of a requested room as String
    protected String getOneRoomData(String roomNum) {
        RoomDataValidation rDataVal = new RoomDataValidation();
        boolean b = rDataVal.doesRoomExist(roomNum);

        LogicAdministrator admin = new LogicAdministrator();
        String path = admin.getRoomDBpath();
        String roomInfo = "";
        if (!b) {
            roomInfo = "Room No. "+roomNum+" does not Exit !!!";
        } else {
            try {
                BufferedReader bReader = new BufferedReader(new FileReader(path));
                String csvLine = "";
                while ( (csvLine = bReader.readLine()) != null ) {
                    String[] dataLine = csvLine.split(COMMA_DELIMITER);
                    if (roomNum.equals(dataLine[1])) {
                        roomInfo =  "Requested room data:" + "\n" + "\n" +
                                    "Status: " + dataLine[ROOM_STATUS_INDEX] + "\n" +
                                    "Number: " + dataLine[ROOM_NUMBER_INDEX] + "\n" +
                                    "Name: " + dataLine[ROOM_NAME_INDEX] + "\n" +
                                    "Location: " + dataLine[ROOM_LOCATION_INDEX] + "\n" +
                                    "Capacity: " + dataLine[ROOM_CAPACITY_INDEX] + "\n" +
                                    "Resources: " + dataLine[ROOM_RESOURCES_INDEX] + "\n";
                    }
                }
                bReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return roomInfo;
    }

    // This method changes the status of a room (open/close)
    // if OPEN the room will be available foe booking
    // if CLOSE the room will be not available for booking
    protected String changeRoomStatus(String roomNo, String roomStatus) {
        String answer = "";
        LogicAdministrator admin = new LogicAdministrator();
        String currentPath = admin.getRoomDBpath();

        DataBases roomDB = new DataBases();
        roomDB.createRoom_EmptyDatabase();

        try {
            BufferedReader file_out = new BufferedReader(new FileReader(currentPath));
            String csvLine = "";
            while ( (csvLine = file_out.readLine()) != null ) {
                String[] dataLine = csvLine.split(COMMA_DELIMITER);
                if (!dataLine[0].equalsIgnoreCase("Status")) {
                    if (!dataLine[1].equals(roomNo)) {
                    String room_Input = csvLine;
                    writeToRoomDataCSV(room_Input);
                    }
                    if (dataLine[ROOM_NUMBER_INDEX].equals(roomNo)) {
                        String room_Input  = checkAndChangeStatus(csvLine, roomStatus);
                        writeToRoomDataCSV(room_Input);
                    }
                }
            }
            file_out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        roomDB.deleteDatabase(currentPath);
        answer += "\n"+"Room "+roomNo+" is now "+roomStatus+" for Booking!";
        return answer;
    }

    //This method returns the room data after it changes the status(open/close) of the given room data line  
    private String checkAndChangeStatus(String roomData, String roomStatus) {
        String answer = "";
        String[] dataSplit = roomData.split(COMMA_DELIMITER);
        if (roomStatus.equalsIgnoreCase(STATUS_ROOM_CLOSE)) {
            dataSplit[ROOM_STATUS_INDEX] = STATUS_ROOM_CLOSE;
        } else if (roomStatus.equalsIgnoreCase(STATUS_ROOM_OPEN)) {
            dataSplit[ROOM_STATUS_INDEX] = STATUS_ROOM_OPEN;
        }
        answer = String.join(COMMA_DELIMITER, dataSplit);
        return answer;
    }

    // This method returns the status(open/close) of a given room (number)
    protected String getStatusOfARoom(String roomNum) {
        String answer = "";
        ArrayList<String> statusList = new ArrayList<>();
        statusList = getOneDataListOfRooms(ROOM_STATUS_INDEX);
        ArrayList<String> numberList = new ArrayList<>();
        numberList =  getOneDataListOfRooms(ROOM_NUMBER_INDEX);
        for (int i = 0; i < numberList.size(); i++) {
            if (numberList.get(i).equals(roomNum)) {
                answer = statusList.get(i);
            }
        }
        return answer;
    }

    // This method first gets the (userId) as input
    // it reads the current existing (UserData) file and copies the data to new (UserData) file
    // when it comes to given user id it ignores the line and thus in the new file the user is no longer exist
    // afterwards the old file is deleted by (deleteDatabase) method 
    protected String deleteRoom(String roomNo) {
        String answer = "";
        LogicAdministrator admin = new LogicAdministrator();
        String currentPath = admin.getRoomDBpath();

        DataBases roomDB = new DataBases();
        roomDB.createRoom_EmptyDatabase();

        try {
            BufferedReader file_out = new BufferedReader(new FileReader(currentPath));
            String csvLine = "";
            while ( (csvLine = file_out.readLine()) != null ) {
                String[] dataLine = csvLine.split(COMMA_DELIMITER);
                // ignores the first line (column headers) and the line with the given room number
                if (!dataLine[ROOM_NUMBER_INDEX].equals(roomNo) && !dataLine[ROOM_NUMBER_INDEX].equals("RoomNo")) {
                    String room_Input = csvLine;
                    writeToRoomDataCSV(room_Input);
                }
            }
            file_out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        answer += roomDB.deleteDatabase(currentPath);
        answer += "\n"+"Room "+roomNo+" Successfully deleted !";
        admin.deleteNonExistingRooms();
        return answer;
    }

    // This method returns the room number list as an Array with out title (RoomNo)
    protected String[] getRoomNumbersArray() {
        ArrayList<String> rNumList = new ArrayList<>();
        rNumList = getOneDataListOfRooms(ROOM_NUMBER_INDEX);
        String[] rNumArray = new String[rNumList.size()-1];
            for (int i = 0; i < rNumArray.length; i++) {
                rNumArray[i] = rNumList.get(i+1);
            }
        return rNumArray;
    }

    // This method rewrites the (RoomData) file after sorting it ascending by the room number
    protected String sortRoomDataByNumberAscending() {
        String msg = "";
        LogicAdministrator admin = new LogicAdministrator();
        String currentPath = admin.getRoomDBpath();
        // calls the sorting method and gets the sorted data as an ArrayList
        RoomSorting rSorting = new RoomSorting();
		ArrayList<String> sortedRoomList = rSorting.getRoomSortedByNumber();
        // creates a new Room Database
        DataBases roomDB = new DataBases();
        roomDB.createRoom_EmptyDatabase();
        // writes the sorted data into the new Room Database file
            for (int i = 0; i < sortedRoomList.size(); i++) {
                String rooms = sortedRoomList.get(i);
                writeToRoomDataCSV(rooms);
            }
        // the old (unsorted) file gets deleted after new file is created
        roomDB.deleteDatabase(currentPath);
        msg = "Rooms successfully sorted ascending by number!";
        return msg;
    }
}