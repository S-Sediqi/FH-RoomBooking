package com.logics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class RoomSorting { 

    private final static String COMMA_DELIMITER = ",";
    private final static int ROOM_STATUS_INDEX = 0;
    private final static int ROOM_NUMBER_INDEX = 1;
    private final static int ROOM_NAME_INDEX = 2;
    private final static int ROOM_LOCATION_INDEX = 3;
    private final static int ROOM_CAPACITY_INDEX = 4;
    private final static int ROOM_RESOURCES_INDEX = 5;

    HashMap<String, String> roomMap = new HashMap<String, String>();
    ArrayList<String> rStatus = new ArrayList<String>();
    ArrayList<String> rNumber = new ArrayList<String>();
    ArrayList<String> rName = new ArrayList<String>();
    ArrayList<String> rLocation = new ArrayList<String>();
    ArrayList<String> rCapacity = new ArrayList<String>();
    ArrayList<String> rResources = new ArrayList<String>();

    ArrayList<String> rKey_rNum = new ArrayList<String>();
    ArrayList<String> rDetails = new ArrayList<String>();

    protected RoomSorting() {
    }

    // This method saves the given Strings into HashMap as Key and Value
    private void saveRoomNumAndRoomDetails(String roomNo, String roomDetails) {
        roomMap.put(roomNo, roomDetails);
    }

    // 
    protected HashMap<String, String> getRoomInfo() {
        return roomMap;
    }

    // This method first gets all room details each in an ArrayList
    // then separates the room number as one String and all other room details in one String 
    // the two String are added to ArrayLists and then put into HashMap (saveRoomNumAndRoomDetails())
    private void setRoomInfo() {
        LogicAdministrator admin = new LogicAdministrator();
        rStatus    = admin.getRoomDataList(ROOM_STATUS_INDEX);
        rNumber    = admin.getRoomDataList(ROOM_NUMBER_INDEX);
        rName      = admin.getRoomDataList(ROOM_NAME_INDEX);
        rLocation  = admin.getRoomDataList(ROOM_LOCATION_INDEX);
        rCapacity  = admin.getRoomDataList(ROOM_CAPACITY_INDEX);
        rResources = admin.getRoomDataList(ROOM_RESOURCES_INDEX);
        // adds the room numbers each as an element into an ArrayList
        for (int i = 1; i < rNumber.size(); i++) {
            rKey_rNum.add(rNumber.get(i));
        }
        // adds all room details each as an element into an ArrayList
        for (int i = 1; i < rStatus.size(); i++) {
            String roomDetails =","+rStatus.get(i)+","+rName.get(i)+","+rLocation.get(i)+","+rCapacity.get(i)+","+rResources.get(i);
            rDetails.add(roomDetails);
        }
        // saves the room number as key and the room details(all room data in one String) as values in HashMap
        for (int i = 0; i < rKey_rNum.size(); i++) {
            saveRoomNumAndRoomDetails(rKey_rNum.get(i), rDetails.get(i));
        }
    }

    // This method returns the sorted room numbers (only room numbers) in an ArrayList
    private ArrayList<String> getSortedRoomNumber() {
        LogicAdministrator admin = new LogicAdministrator();
        ArrayList<String> sortNum = new ArrayList<>();
        sortNum = admin.getRoomDataList(ROOM_NUMBER_INDEX);
        sortNum.remove(0);
        Collections.sort(sortNum);
        return sortNum;
    }

    // This method simply receives a String and two Integer (elementOneIndex and elementTwoIndex)
    // Then returns the String after it swaps the elements with given indexes
    // Here it gets a String form of (RoomNo,Status,RoomName,Location,Capacity,Resources)
    // after rearranging it returns  (Status,RoomNo,RoomName,Location,Capacity,Resources)
    private String getRearrangedString(String mixedString, int elementOneIndex, int elementTwoIndex) {
        String arrangedString = "";
        ArrayList<String> rearrange = new ArrayList<>();
        String[] rDataArray = mixedString.split(COMMA_DELIMITER);
            for (int j = 0; j < rDataArray.length; j++) {
                rearrange.add(rDataArray[j]);
            }
            // saves (element 0 = str0) and (element 1 = str1)
            String str0 = rearrange.get(elementOneIndex);    
            String str1 = rearrange.get(elementTwoIndex);
            // sets (element 0 = str1) and (element 1 = str0)
            rearrange.set(elementOneIndex, str1);
            rearrange.set(elementTwoIndex, str0);
            arrangedString  =   rearrange.get(0)+","+rearrange.get(1)+","+rearrange.get(2)+","+
                                rearrange.get(3)+","+rearrange.get(4)+","+rearrange.get(5);
        return arrangedString;
    }

    // This method first calls the (setRoomInfo()) method and gets a sorted ArrayList of room numbers 
    // then, calls back the room detail from the HashMap with (getRoomInfo) according to sorted room number
    // at the end it returns the sorted rooms with their details in an ArrayList.
    protected ArrayList<String> getRoomSortedByNumber() {
        ArrayList<String> answer = new ArrayList<>();
        setRoomInfo();
        ArrayList<String> sortedNum = new ArrayList<>();
        sortedNum = getSortedRoomNumber();

        for (int i = 0; i < sortedNum.size(); i++) {
            if (roomMap.containsKey(sortedNum.get(i))) {
                String roomData = ""+sortedNum.get(i)+roomMap.get(sortedNum.get(i))+"";
                String sortedData = getRearrangedString(roomData, 0, 1);
                answer.add(sortedData);            
            }
        }
        return answer;
    }
}