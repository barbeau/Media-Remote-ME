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
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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

 */
package MediaRemoteME.submitcommandservice;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import com.sprintpcs.util.SystemEventListener;
import com.sprintpcs.util.System;
import com.sprintpcs.lcdui.ExternalCanvas;
import javax.microedition.lcdui.*;

/**
 * This class is a test MIDlet used to enter media commands and see the output.
 * 
 * @author Sean Barbeau
 */
public class VisualMIDlet extends MIDlet implements CommandListener, Runnable, SystemEventListener {

    private boolean midletPaused = false;
    
    /* String for web service
     http://97.97.166.100:8220/iTunesServer/servlet/MediaRemoteME.iTunesServer.webservlet6.WebToMobileServlet2
     */ 

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Form form;
    private TextField textField;
    private Command okCommand;
    private Command exitCommand;
    private Command okCommand1;
    private Command exitCommand1;
    //</editor-fold>//GEN-END:|fields|0|
    /**
     * The VisualMIDlet constructor.
     */
    public VisualMIDlet() {
        //Add key listener
        com.sprintpcs.util.System.addSystemListener(this);
        
        // Sets the primary or the secondary lcd  auto.
        //setLCD(AUTO_LCD);
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
    // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
    // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, getForm());//GEN-LINE:|3-startMIDlet|1|3-postAction
    // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
    // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
    // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
    // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == form) {//GEN-BEGIN:|7-commandAction|1|28-preAction
            if (command == exitCommand1) {//GEN-END:|7-commandAction|1|28-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|2|28-postAction
            // write post-action user code here
            } else if (command == okCommand1) {//GEN-LINE:|7-commandAction|3|26-preAction
              
                
//GEN-LINE:|7-commandAction|4|26-postAction
                // Call Web service to active iTunes function via thread
                Thread thread = new Thread(this);
                thread.start();                
                
            }//GEN-BEGIN:|7-commandAction|5|7-postCommandAction
        }//GEN-END:|7-commandAction|5|7-postCommandAction
    // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|6|
    //</editor-fold>//GEN-END:|7-commandAction|6|
    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|19-getter|0|19-preInit
    /**
     * Returns an initiliazed instance of okCommand component.
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {//GEN-END:|19-getter|0|19-preInit
            // write pre-init user code here
            okCommand = new Command("Ok", Command.OK, 0);//GEN-LINE:|19-getter|1|19-postInit
        // write post-init user code here
        }//GEN-BEGIN:|19-getter|2|
        return okCommand;
    }
    //</editor-fold>//GEN-END:|19-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|21-getter|0|21-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|21-getter|0|21-preInit
            // write pre-init user code here
            exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|21-getter|1|21-postInit
        // write post-init user code here
        }//GEN-BEGIN:|21-getter|2|
        return exitCommand;
    }
    //</editor-fold>//GEN-END:|21-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: form ">//GEN-BEGIN:|23-getter|0|23-preInit
    /**
     * Returns an initiliazed instance of form component.
     * @return the initialized component instance
     */
    public Form getForm() {
        if (form == null) {//GEN-END:|23-getter|0|23-preInit
            // write pre-init user code here
            form = new Form("form", new Item[] { getTextField() });//GEN-BEGIN:|23-getter|1|23-postInit
            form.addCommand(getOkCommand1());
            form.addCommand(getExitCommand1());
            form.setCommandListener(this);//GEN-END:|23-getter|1|23-postInit
        // write post-init user code here
        }//GEN-BEGIN:|23-getter|2|
        return form;
    }
    //</editor-fold>//GEN-END:|23-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField ">//GEN-BEGIN:|30-getter|0|30-preInit
    /**
     * Returns an initiliazed instance of textField component.
     * @return the initialized component instance
     */
    public TextField getTextField() {
        if (textField == null) {//GEN-END:|30-getter|0|30-preInit
            // write pre-init user code here
            textField = new TextField("Enter input:", "", 32, TextField.ANY);//GEN-LINE:|30-getter|1|30-postInit
        // write post-init user code here
        }//GEN-BEGIN:|30-getter|2|
        return textField;
    }
    //</editor-fold>//GEN-END:|30-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand1 ">//GEN-BEGIN:|25-getter|0|25-preInit
    /**
     * Returns an initiliazed instance of okCommand1 component.
     * @return the initialized component instance
     */
    public Command getOkCommand1() {
        if (okCommand1 == null) {//GEN-END:|25-getter|0|25-preInit
            // write pre-init user code here
            okCommand1 = new Command("Ok", Command.OK, 0);//GEN-LINE:|25-getter|1|25-postInit
        // write post-init user code here
            
        }//GEN-BEGIN:|25-getter|2|
        return okCommand1;
    }
    //</editor-fold>//GEN-END:|25-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand1 ">//GEN-BEGIN:|27-getter|0|27-preInit
    /**
     * Returns an initiliazed instance of exitCommand1 component.
     * @return the initialized component instance
     */
    public Command getExitCommand1() {
        if (exitCommand1 == null) {//GEN-END:|27-getter|0|27-preInit
            // write pre-init user code here
            exitCommand1 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|27-getter|1|27-postInit
        // write post-init user code here
        }//GEN-BEGIN:|27-getter|2|
        return exitCommand1;
    }
    //</editor-fold>//GEN-END:|27-getter|2|
    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable(null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet();
        } else {
            initialize();
            startMIDlet();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }

    /*
     * Method calls Web service to execute iTunes function via thread
     */
    public void run() {
        try {
                    WebToMobileClient wmc = new WebToMobileClient();
                    wmc.SubmitCommand(this.textField.getString());
                } catch (Exception e) {
                    java.lang.System.out.println(e);
                }
    }

    public void systemEvent(String arg0, String arg1) {
        java.lang.System.out.println("systemEvent " + arg0 + ", " + arg1 + " not supported yet.");
    }
    
    /**
     * Check the key pressed event and repaint the screen.
     *
     * @param keyCode the key code of the key that was pressed
     */
    protected void keyPressed(int keyCode){
        java.lang.System.out.println(keyCode);
        /*
        java.lang.System.out.println(keyCode + "=" + getKeyName(keyCode));
         keyEventString = getKeyName(keyCode) + " Pressed";
        synchronized(this){
            keyEvent = true;
            notify();
        }
         */
    }
    
    /**
     * Check the key released event and repaint the screen.
     *
     * @param keyCode the key code of the key that was released
     */
    protected void keyReleased(int keyCode){
        /*
         keyEventString =  getKeyName(keyCode) + " Released";
         synchronized (this){
            keyEvent = true;
            notify();
        }
         */
    }
    
    /**
     * Check the key repeated event and repaint the screen.
     *
     * @param keyCode the key code of the key that was repeated
     */
    protected void keyRepeated(int keyCode){
        /*
        keyEventString =  getKeyName(keyCode) + " Repeated";
        synchronized(this){
            keyEvent = true;
            notify();
        }
         */ 
    }
}
