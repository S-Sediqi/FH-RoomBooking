package com.logics;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginCheck {

    HashMap<String, String> login = new HashMap<String, String>();
    ArrayList<String> userIdList = new ArrayList<String>();
    ArrayList<String> userPassList = new ArrayList<String>();

    public LoginCheck() {
    }

    private void saveIDandPass(String userId, String userPass) {
        login.put(userId, userPass);
    }

    public HashMap<String, String> getLoginInfo() {
        return login;
    }

    public void setLoginInfo() {
        LogicAdministrator admin = new LogicAdministrator();
        userIdList = admin.getUserDataList(admin.USER_ID_INDEX);
        userPassList = admin.getUserDataList(admin.USER_PASS_INDEX);
            for (int i = 1; i < userIdList.size(); i++) {
                saveIDandPass(userIdList.get(i), userPassList.get(i));
            }
    }

}
