/*
 * SubmitCommand.java
 *
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

package MediaRemoteME.iTunesServer;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import MediaRemoteME.itunescontroller.iTunesTest1;
import MediaRemoteME.itunescontroller.iTunesCommands;
import MediaRemoteME.yahooLaunchCastController.*;

/**
 * Defines the main web service methods that can be called from a web client (e.g., mobile device) to control
 * media controllers for media components such as iTunes or Yahoo Launchcast streaming music.
 * @author Sean Barbeau
 */
@WebService()
public class SubmitCommand implements iTunesCommands, yahooLaunchCastCommands {
    /**
     * Web service operation
     */
    @WebMethod
    public String SubmitCommand(@WebParam(name = "command") String command) {
        //System.out.println(new java.io.File(".").getAbsolutePath());
        
        try {                
            int command2 = Integer.parseInt(command);
            if(command2 >= 0){
                //Call iTunes controller
                iTunesTest1 test = new iTunesTest1(command2);
                System.out.println("Called SubmitCommand web service for iTunes");
            }else{
                //Call YahooLaunchCast controller
                yahooLaunchCastTest test1 = new yahooLaunchCastTest(command2);
                System.out.println("Called SubmitCommand web service for YahooLaunchCast");
            }          
            
        } catch(Exception e) {
            System.out.println("Error in Server.SubmitCommand web service: " + e);
            e.printStackTrace();
            return "Fail";
        }   
        
        return "Success";
    }
    
}
