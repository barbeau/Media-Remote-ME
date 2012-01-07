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
 * 
 **/

/**
 * For references on iTunes Java interface, see:
 *  http://www.workingwith.me.uk/blog/apple/itunes/java_and_the_itunes_com_interf
 * 
 * Some working commands not yet implemented:
 *  
 * PlayFile(filePath)
 * Resume
 * 
 */

package MediaRemoteME.itunescontroller;

import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.activeX.ActiveXComponent;

/**
 * This class implements the functionality that interfaces with the iTunes COM API in order to control
 * iTunes.  It can be called as a library function or via the main method in this class.
 * 
 * @author Sean Barbeau
 */
public class iTunesTest1 implements iTunesCommands{
    
    /**
     * Creates a new instance of iTunesTest1
     */
    public iTunesTest1(int command) {
        
        try{            
            ComThread.InitMTA(true);
            
            ActiveXComponent iTunesCom = new ActiveXComponent("iTunes.Application");
            
            Dispatch iTunesController = (Dispatch)iTunesCom.getObject();
            
            String input = "";
            int current_volume;
            int new_volume;
            
            switch(command){
                case PLAY_PAUSE: 
                                input = "PlayPause"; 
                                Dispatch.call(iTunesController, input);
                                System.out.println("Toggled play/pause in iTunes."); 
                                break;
                case NEXT_TRACK: 
                                input = "NextTrack"; 
                                Dispatch.call(iTunesController, input);
                                System.out.println("Skipped to NextTrack in iTunes."); 
                                break;       
                case PREVIOUS_TRACK: 
                                input = "PreviousTrack"; 
                                Dispatch.call(iTunesController, input);
                                System.out.println("Skipped to PrevioustTrack in iTunes."); 
                                break;       
                case FAST_FORWARD: 
                                input = "FastForward"; 
                                Dispatch.call(iTunesController, input);
                                System.out.println("FastForwarded in iTunes."); 
                                break;
                case REWIND: 
                                input = "Rewind"; 
                                Dispatch.call(iTunesController, input);
                                System.out.println("Rewinded in iTunes."); 
                                break;
                case VOLUME_UP: 
                                input = "SoundVolume"; 
                                current_volume = (int) Dispatch.get(iTunesController, "SoundVolume").getInt(); //Get current volume
                                new_volume = current_volume + 5;
                                if(new_volume > 100){
                                    new_volume = 100;
                                }
                                Dispatch.put(iTunesController, "SoundVolume", new_volume);  //Set new volume
                                System.out.println("Volumn Up in iTunes."); 
                                break;       
                case VOLUME_DOWN: 
                                input = "SoundVolume"; 
                                current_volume = (int) Dispatch.get(iTunesController, "SoundVolume").getInt(); //Get current volume
                                new_volume = current_volume - 5;
                                if(new_volume < 0){
                                      new_volume = 0;
                                }
                                Dispatch.put(iTunesController, "SoundVolume", new_volume);  //Set new volume
                                System.out.println("Volumn Down in iTunes."); 
                                break;  
               case STOP: 
                                input = "Stop"; 
                                Dispatch.call(iTunesController, input);
                                System.out.println("Stopped in iTunes."); 
                                break;
                default:  
                                input = "PlayPause";
                                Dispatch.call(iTunesController, input);  
                                System.out.println("Toggled play/pause in iTunes."); 
            }
            
            //System.out.println(Dispatch.get(iTunesController, "Album"));
            
            //ComThread.Release();  Moved to 'finally' clause
            
            //System.exit(0);  This line is commented out since if its present, only one command works then it becomes inactive
        }catch(Exception e){
            System.out.println("Error: " + e);
            e.printStackTrace();            
        }finally{      
            //TO DO - Set iTunesController = null
            ComThread.Release();
        }
    }
    
    /**
     * Main method used to test the commands in this class
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {                
            iTunesTest1 test = new iTunesTest1(NEXT_TRACK);
        } catch(Exception e) {
            System.out.println("Error in main: " + e);
            e.printStackTrace();
        }   
        
    }
    
}
