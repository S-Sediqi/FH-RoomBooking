package com.logics;

import java.util.ArrayList;

import com.validation.RoomDataValidation;

public class Room_Input_Check {
    
    private static final String HYPHEN_DELIMITER = "-";

    private int numberCheck = 0,
                nameCheck = 0,
                nameEmptyCheck = 0,
                locationCheck = 0,
                locationEmptyCheck = 0,
                resourceCheck = 0,
                resourceBox = 0;
    private String  numMsg = "", 
                    nameMsg = "",
                    nameEmptyMsg = "", 
                    locationMsg = "", 
                    locationEmptyMsg = "",
                    resourceMsg = "", 
                    resourceBoxMsg = "";
    private String roomData = "";

    protected Room_Input_Check () {
    }

    protected String checkRoomInputData (
        String status, String number, String name, String location, 
        String capacity, String resource, boolean resourceSelection) {

        String answer = "";
        if (name.isEmpty()) {
            nameEmptyCheck = 1;
            nameEmptyMsg = "Name field can't be Empty";
        }
        if (location.isEmpty()) {
            locationEmptyCheck = 1;
            locationEmptyMsg = "Location field can't be Empty";
        }
        // creates a constructor of class (RoomDataValidation) to use the functions for validating the input data
        RoomDataValidation rDataVal = new RoomDataValidation();    
        if (rDataVal.doesRoomExist(number)) {
            numberCheck = 1;
            numMsg = "Room number already exist !";
        }
        if (!rDataVal.isRoomName_Valid(name)) {
            nameCheck = 1;
            nameMsg = "Name can only contain letters !";
        }
        if (!rDataVal.isRoomLocation_Valid(location)) {
            locationCheck = 1;
            locationMsg = "Location can't contain space or comma !";
        }
        if (!rDataVal.isRoomResources_Valid(resource)) {
            resourceCheck = 1;
            resourceMsg = "Resources can't contain space or comma !";
        }
        if (resourceSelection) {
            resourceBox = 1;
            resourceBoxMsg = "Minimum one resource should be selected";
        }

        // adds all int after running all data validation functions for input fields 
        // if all data is correct (sumOfValidation = 0)
        // if any data is wrong/empty (sumOfValidation > 0 )            
        int sumOfValidation = numberCheck + nameCheck + locationCheck + resourceCheck
                            + nameEmptyCheck + locationEmptyCheck + resourceBox;

        // checks if all room data are valid, then saves it in database
        if ( sumOfValidation == 0 ) {
            // stores all the room data into a Sting separated with comma for CSV file
            roomData = status+","+number+","+name+","+location+ ","+capacity+","+getFullResourceName(resource);
            // creates a room data entry and saves the input data into the (ROOM_DATA.csv) file
            LogicAdministrator admin = new LogicAdministrator();
            admin.writeToRoomDB(roomData);
            answer += "\n" + "\n" + "------ !!! Congrats !!! ------";
            answer += "\n" + "Room created Successfully!";
        }
        // if entered data is wrong, prints the corresponding statement 
        else if ( sumOfValidation != 0 ){
            answer += "\n" + "\n" + "------ !!! ERROR !!! ------";
            if (numberCheck != 0) { // if room already exist in the database
                answer += "\n" + numMsg;
            }
            if (nameCheck != 0) { // if entered name is invalid (contains: number, symbol, space)
                answer += "\n" + nameMsg;
            }
            if (nameEmptyCheck != 0) {
                answer += "\n" + nameEmptyMsg;
            }
            if (locationCheck != 0) { // if entered location is invalid (contains: space or comma)
                answer += "\n" + locationMsg;
            }
            if (locationEmptyCheck != 0) {
                answer += "\n" + locationEmptyMsg;
            }
            if (resourceCheck != 0) { // if entered resources are invalid (contains: space or comma)
                answer += "\n" + resourceMsg;
            }
            if (resourceBox != 0) { // if no resources are selected (minimum 1 resource must be selected)
                answer += "\n" + resourceBoxMsg;
            }
        }
        return answer;
    }

    // This method gets the resource list from the room registration UI
    // because no space or comma is allowed this method replaces the short name
    // with the full name after the resource input is validated
    private String getFullResourceName(String roomResource) {
        String answer = "";
        String[] resArray = roomResource.split(HYPHEN_DELIMITER);
        ArrayList<String> resList = new ArrayList<>();
            for (int i = 0; i < resArray.length; i++) {
                resList.add(resArray[i]);
            }
            // first adds the resource added by the user through text field input and WiFi
            // index 0 (user input), index 1 (WiFi)
            if (resList.size() == 2) {
                answer += resList.get(0);
                answer += "-";
            }
            if (resList.size() >= 3) {
                answer += resList.get(0);
                answer += "-";
                answer += resList.get(1);
            }
            
            // adds the rest of the resources with their full names and descriptions
            for (int j = 2; j < resList.size(); j++) {
                String resName = resList.get(j);
                switch (resName) {
                    case ("wbm"): answer += "-white board mounted";
                        break;
                    case ("wbp"): answer += "-white board portable";
                        break;    
                    case ("cbm"): answer += "-chalk board mounted";
                        break;
                    case ("cbp"): answer += "-chalk board portable";
                        break;
                    case ("ttv"): answer += "-touch screen TV";
                        break;
                    case ("tv"):  answer += "-TV";
                        break;
                    case ("sb"):  answer += "-smart board";
                        break;
                    case ("pj"):  answer += "-projector";
                        break;
                    case ("sp"):  answer += "-speaker";
                        break;
                    case ("sk"):  answer += "-sink";
                        break;
                    default:
                        break;
                }
            }
        return answer;
    }
}
