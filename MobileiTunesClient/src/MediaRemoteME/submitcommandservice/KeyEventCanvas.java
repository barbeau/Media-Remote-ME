/**
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

/*
 * KeyEventCanvas.java
 * 
 * Sanyo 7050 Key codes:
 *  -3=Left
 *   -4=Right
 *   -1=Up
 *   -2=Down
 *   -5=Select
 *   35=#
 *   42=*
 *   -10=Send
 *   92=Stop
 *   90=Play pause
 *   93=Track forward
 * 
 * Sprint System events detected on opening and closing phone:
 * Property = sprint.device.formfactor, Value = CLOSED
 * Property = sprint.device.formfactor, Value = OPEN

 *      Sanyo 7050 main display:
 *      240w x 261h
 *      Sanyo 7050 secondary display:
 *      96w x 64h
 */
package MediaRemoteME.submitcommandservice;

import com.sprintpcs.lcdui.ExternalCanvas;
import com.sprintpcs.util.SystemEventListener;
import com.sprintpcs.util.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

/**
 * The key event canvas catches key events and displays the name of the key and the event.
 * It can clean the screen on a shifting between primary and secondary LCD.
 * @author Sean J. Barbeau
 */
public class KeyEventCanvas extends ExternalCanvas implements CommandListener, SystemEventListener, Runnable, iTunesCommands, yahooLaunchCastCommands {

    private Command exitCmd = new Command("Exit", Command.EXIT, 1);
    private Display display;
    private MIDlet midlet;
    /** The despicable string initialization for the demo headline. */
    private String keyEventString = "Key code demo";
    /** When geting event to pring. */
    private boolean keyEvent = false;
    private int keyPressed = 0;  //variable to hold the key pressed
    //Variable that holds reference the current image to be displayed on screen
    private Image image = null;
    //Variables that hold reference to images used in different states of GUI
    private Image imageNormal = null;  //Image displayed when no button is being pressed
    private Image imageSel = null;  //Image displayed when the Select button is pressed
    private Image imageNext = null;  //Image displayed when the right arrow button is pressed
    private Image imagePrev = null;  //Image displayed when the left arrow button is pressed
    private Image imageVolUp = null;  //Image displayed when the up arrow button is pressed
    private Image imageVolDown = null;  //Image displayed when the down arrow button is pressed
    private Image imageExtNormal = null; //Image displayed on external when no button is pressed
    private Image imageExtNext = null;  //Image displayed on external when the right arrow button is pressed
    private Image imageExtPlayPause = null;  //Image displayed on external when the PlayPause button is pressed
    private Image imageExtStop = null;  //Image displayed on external when the Stop button is pressed
    
    //Constants for Sanyo 7050 key code mapped to iTunes functions
    private static final int ITUNES_UP = -1;
    private static final int ITUNES_DOWN = -2;
    private static final int ITUNES_LEFT = -3;
    private static final int ITUNES_RIGHT = -4;
    private static final int ITUNES_SELECT = -5;
    private static final int ITUNES_POUND = 35;
    private static final int ITUNES_STAR = 42;
    private static final int ITUNES_SEND = -10;
    private static final int ITUNES_STOP = 92;
    private static final int ITUNES_Play_pause = 90;
    private static final int ITUNES_Track_forward = 93;
    
    //Constants for Sanyo 7050 key code mapped to YahooLaunchCast functions
    private static final int YAHOO_LAUNCH_CAST_FIVE = 53;
    private static final int YAHOO_LAUNCH_CAST_SIX = 54;
    
    //Constant for Sprint phones used to detect when phone is flipped open and closed
    private static final String formFactor = "sprint.device.formfactor";
    private static final String OPEN = "OPEN";  //Constant value when phone has just been flipped open
    private static final String CLOSED = "CLOSED";  //Constant value when phone has just been flipped closed
    private boolean phoneIsOpen = true;  //Boolean value used to hold state of phone (either open or closed)

    /**
     * Constructor for the MIDlet,
     * initializations of the form.
     */
    public KeyEventCanvas(MIDlet midlet) {
        this.midlet = midlet;
        setCommandListener(this);
        com.sprintpcs.util.System.addSystemListener(this);

        // Sets the primary or the secondary lcd  auto.
        setLCD(AUTO_LCD);

        //Load image
        try {
            // Create immutable image
            image = Image.createImage("/iTunesScreen5.png");
            imageNormal = Image.createImage("/iTunesScreen5.png");
            imageSel = Image.createImage("/iTunesScreen5-sel.png");
            imageNext = Image.createImage("/iTunesScreen5-NextTrack.png");
            imagePrev = Image.createImage("/iTunesScreen5-PrevTrack.png");
            imageVolDown = Image.createImage("/iTunesScreen5-volDown.png");
            imageVolUp = Image.createImage("/iTunesScreen5-volUp.png");
            imageExtNormal = Image.createImage("/iTunesExt1.png");
            imageExtNext = Image.createImage("/iTunesExtNextTrack.png");
            imageExtPlayPause = Image.createImage("/iTunesExtPlayPause.png");
            imageExtStop = Image.createImage("/iTunesExtStop.png");            
        } catch (java.io.IOException e) {
            java.lang.System.err.println("Unable to locate or read .png file");
        }
        
        
        new Thread(this).start();
    }

    /**
     * Graphics obj paints the desplayable string.
     */
    public void paint(Graphics g) {
        g.setColor(0xffffff);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(0x000000);
        
        //draw image on screen
        if (image != null){
            g.drawImage(image, 0, 0, Graphics.LEFT | Graphics.TOP);
        }

        // Display the key event strong or clean if it is null - draw string
        if (keyEventString != null) {
            //g.drawString(keyEventString, 0, 0, Graphics.TOP | Graphics.LEFT);
        }
    }

    /**
     * Respond to changes, and brings  the property and its new value.
     */
    public void systemEvent(java.lang.String property, java.lang.String value) {
        java.lang.System.out.println("System event detected.  Property = " + property + ", Value = " + value);
        //Detect when phone is flipped open and closed
        if(property.equals(formFactor)){
            if(value.equals(OPEN)){
                this.setPhoneIsOpen(true);
            }else{
                if(value.equals(CLOSED)){
                    this.setPhoneIsOpen(false);                    
                }
            }
        }
        // Clean the screan.
        synchronized (this) {
            notify();
        }
    }

    /**
     * Able to exit form the program.
     */
    public void commandAction(Command c, Displayable d) {
        if (c == exitCmd) {
            midlet.notifyDestroyed();
        }
    }

    /**
     * Check the key pressed event and repaint the screen.
     *
     * @param keyCode the key code of the key that was pressed
     */
    protected void keyPressed(int keyCode) {
        java.lang.System.out.println(keyCode + "=" + getKeyName(keyCode));
        keyEventString = getKeyName(keyCode) + " Pressed";
        synchronized (this) {
            keyEvent = true;
            notify();
        }

        int command;
        switch (keyCode) {
            case ITUNES_UP:
                command = VOLUME_UP;
                keyPressed = VOLUME_UP;
                if(this.getPhoneIsOpen() == true){
                    image = imageVolUp;
                }                
                java.lang.System.out.println("VolumnUp");
                break;
            case ITUNES_DOWN:
                command = VOLUME_DOWN;
                keyPressed = VOLUME_DOWN;
                if(this.getPhoneIsOpen() == true){
                    image = imageVolDown;
                }
                java.lang.System.out.println("VolumeDown");
                break;
            case ITUNES_LEFT:
                command = PREVIOUS_TRACK;
                keyPressed = PREVIOUS_TRACK;
                if(this.getPhoneIsOpen() == true){
                    image = imagePrev;
                }
                java.lang.System.out.println("PrevTrack");
                break;
            case ITUNES_RIGHT:
                command = NEXT_TRACK;
                keyPressed = NEXT_TRACK;
                if(this.getPhoneIsOpen() == true){
                    image = imageNext;
                }else{
                    image = imageExtNext;
                }
                java.lang.System.out.println("NextTrack");
                break;
            case ITUNES_SELECT:
                command = PLAY_PAUSE;
                keyPressed = PLAY_PAUSE;
                if(this.getPhoneIsOpen() == true){
                    image = imageSel;
                }else{
                    image = imageExtPlayPause;
                }
                java.lang.System.out.println("PlayPause");
                break;
            case ITUNES_STAR:
                command = REWIND;
                keyPressed = REWIND;
                java.lang.System.out.println("Rewind");
                break;
            case ITUNES_POUND:
                command = FAST_FORWARD;
                keyPressed = FAST_FORWARD;
                java.lang.System.out.println("FastForward");
                break;
            case ITUNES_Play_pause:
                command = PLAY_PAUSE;
                keyPressed = PLAY_PAUSE;
                if(this.getPhoneIsOpen() == true){
                    image = imageSel;
                }else{
                    image = imageExtPlayPause;
                }
                java.lang.System.out.println("PlayPause-external");
                break;
            case ITUNES_Track_forward:
                command = NEXT_TRACK;
                keyPressed = NEXT_TRACK;
                if(this.getPhoneIsOpen() == true){
                    image = imageNext;
                }else{
                    image = imageExtNext;
                }
                java.lang.System.out.println("NextTrack-external");
                break;
            case ITUNES_STOP:
                command = STOP;
                keyPressed = STOP;
                if(this.getPhoneIsOpen() == false){
                    image = imageExtStop;
                }
                java.lang.System.out.println("Stopped-external");
                break;
            case YAHOO_LAUNCH_CAST_FIVE:
                command = YAHOO_PLAY_PAUSE;
                keyPressed = YAHOO_PLAY_PAUSE;
                if(this.getPhoneIsOpen() == false){
                    //image = imageExtStop;  //need to insert yahoo images
                }
                java.lang.System.out.println("YAHOO_PLAY_PAUSE");
                break;
            case YAHOO_LAUNCH_CAST_SIX:
                command = YAHOO_NEXT_TRACK;
                keyPressed = YAHOO_NEXT_TRACK;
                if(this.getPhoneIsOpen() == false){
                    //image = imageExtStop;  //need to insert yahoo images
                }
                java.lang.System.out.println("YAHOO_NEXT_TRACK");
                break;                
            default:
                command = PLAY_PAUSE;
                keyPressed = PLAY_PAUSE;
                if(this.getPhoneIsOpen() == true){
                    image = imageSel;
                }else{
                    image = imageExtPlayPause;
                }
                java.lang.System.out.println("PlayPause");
            }
        this.callWebService(command);
    }

    /**
     * Check the key released event and repaint the screen.
     *
     * @param keyCode the key code of the key that was released
     */
    protected void keyReleased(int keyCode) {
        keyEventString = getKeyName(keyCode) + " Released";
        synchronized (this) {
            keyEvent = true;
            if(this.getPhoneIsOpen() == true){
                image = imageNormal;
            }else{
                image = imageExtNormal;
            }
            notify();
        }
    }

    /**
     * Check the key repeated event and repaint the screen.
     *
     * @param keyCode the key code of the key that was repeated
     */
    protected void keyRepeated(int keyCode) {
        keyEventString = getKeyName(keyCode) + " Repeated";
        synchronized (this) {
            keyEvent = true;
            notify();
        }
    }

    /**
     * Repain the screen
     */
    public void run() {
        // Loop for repint.
        while (true) {
            keyEvent = false;
            // Get event from the keys or every second.
            synchronized (this) {
                try {
                    wait(1000);
                } catch (InterruptedException ex) {
                }
            }
            // Repaint
            repaint();
            // For time out event clean the screen.
            if (!keyEvent) {
                keyEventString = null;
                if(this.getPhoneIsOpen() == true){
                    image = imageNormal;
                }else{
                    image = imageExtNormal;
                }
                
            }
        }
    }

    public void callWebService(final int command) {
        //Launch web service to submit iTunes command
        new Thread(Integer.toString(command)) {

            public void run() {
                try {
                    WebToMobileClient wmc = new WebToMobileClient();
                    wmc.SubmitCommand(Integer.toString(command));
                    java.lang.System.out.println("WS called.  Input:" + command);
                } catch (Exception e) {
                    java.lang.System.out.println(e);
                }
            }
        }.start();
    }
    
    public synchronized void setPhoneIsOpen(boolean value){
        this.phoneIsOpen = value;
    }
    
    public synchronized boolean getPhoneIsOpen(){
        return this.phoneIsOpen;
    }
    
}

