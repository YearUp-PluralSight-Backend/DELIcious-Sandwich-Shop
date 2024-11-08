package com.pluralsight.gui;

import com.pluralsight.utils.Utility;

public class HomeGUI {

    private  static HomeGUI homeGUI;

    private HomeGUI() {

    }

    public static HomeGUI getInstance() {
        if (homeGUI == null) {
            homeGUI = new HomeGUI();
            return  homeGUI;
        }
        return homeGUI;
    }

    public void HomeScreen() {

        boolean runningApplication = true;
        while (runningApplication)  {

                Utility.InputForString("");


        }
    }


    public void PrintHomeScreenMenu() {

        boolean runningApplication = true;
        while (runningApplication)  {



        }
    }
}
