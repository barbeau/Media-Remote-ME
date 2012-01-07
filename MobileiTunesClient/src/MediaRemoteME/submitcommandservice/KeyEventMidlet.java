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

package MediaRemoteME.submitcommandservice;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

/**
 * The key event demo shows the capture and display key code.
 * This demo catches key events and shows the name of the key and the event.
 * @author Sean J. Barbeau
 */
public class KeyEventMidlet extends MIDlet {
    
    public KeyEventMidlet() {
        // Create and activate the main canvas
        KeyEventCanvas keyEventCanvas = new KeyEventCanvas(this);
        Display.getDisplay(this).setCurrent(keyEventCanvas);
    }
    
     /**
     * Signals the MIDlet that it has entered the Active state
     * The method will only be called when the MIDlet is in the Paused state
     * Performs some initialization, places app in active state
     *@exception MIDletStateChangeException  is thrown if the MIDlet cannot
     * start now but might be able to start at a later time.
     */
    protected void startApp() throws MIDletStateChangeException{
    }
    
    /**
     * Signals the MIDlet to enter the Paused state.
     * In the Paused state the MIDlet must release shared resources.
     */
    protected void pauseApp(){
    }
   
    /**
     * Signals the MIDlet to terminate and enter the Destroyed state.
     * In the destroyed state the MIDlet must release all resources and
     * save any persistent state
     * @param unconditional If true when this method is called, the
     * MIDlet must cleanup and release all resources.  If false the
     * MIDlet may throw MIDletStateChangeException to indicate
     * it does not want to be destroyed at this time.
     */
    protected void destroyApp(boolean unconditional){
   }    
}