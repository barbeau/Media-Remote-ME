/*
 * Copyright 2011 Sean J. Barbeau
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package MediaRemoteME.yahooLaunchCastController;

import java.awt.Robot;
import java.awt.*;
import java.awt.event.InputEvent;
import javax.swing.*;

/**
 * Controls the cursor to click on Yahoo LaunchCast controls in Internet Explorer Window
 * Uses the Java Robot feature to issue click commands.  Assumes that the Yahoo Launchcast window is 
 * positioned with the upper-left corner of window at the upper-left corner of computer display.
 * 
 * @author Sean Barbeau
 */
public class yahooLaunchCastTest implements yahooLaunchCastCommands {

    private static final int Yahoo_Next_Track_X = 68;
    private static final int Yahoo_Next_Track_Y = 400;
    private static final int Yahoo_Play_Pause_X = 35;
    private static final int Yahoo_Play_Pause_Y = 400;

    public yahooLaunchCastTest(int command) {
        
        try {
            Robot rob = new Robot();
            int input = YAHOO_NEXT_TRACK;  //Input for controller
            //rob.setAutoDelay(500); // 0.5s
            //for (int i = 0; i < 50; i++) {
             switch(command){
                case YAHOO_NEXT_TRACK:
                    input = InputEvent.BUTTON1_MASK;
                    rob.delay(2000);
                    rob.mouseMove(Yahoo_Next_Track_X, Yahoo_Next_Track_Y);            
                    System.out.println("Clicked NextTrack in YahooLaunchCast."); 
                    break;
                case YAHOO_PLAY_PAUSE:
                    input = InputEvent.BUTTON1_MASK;
                    rob.delay(2000);
                    rob.mouseMove(Yahoo_Play_Pause_X, Yahoo_Next_Track_Y);            
                    System.out.println("Clicked PlayPause in YahooLaunchCast."); 
                    break;
                default:  
                    //Default is YAHOO_NEXT_TRACK
                    input = InputEvent.BUTTON1_MASK;
                    rob.delay(2000);
                    rob.mouseMove(Yahoo_Next_Track_X, Yahoo_Next_Track_Y);            
                    System.out.println("Clicked NextTrack in YahooLaunchCast."); 
                    break;
             }
            rob.mousePress(input);
            rob.mouseRelease(input);
        } catch (Exception e) {
            System.out.println("Error in Yahoo constructor: " + e);
            e.printStackTrace();
        }

    }

    /**
     * Main method used to test the commands in this class
     * @param args
     * @throws AWTException 
     */
    public static void main(String[] args) throws AWTException {
        try {
            yahooLaunchCastTest controller = new yahooLaunchCastTest(YAHOO_PLAY_PAUSE);
        } catch (Exception e) {
            System.out.println("Error in Yahoo main: " + e);
            e.printStackTrace();
        }
    }
}
