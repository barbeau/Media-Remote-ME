package MediaRemoteME.iTunesServer.webtomobileservletsupport;

import MediaRemoteME.iTunesServer.SubmitCommand;

import java.io.*;
import javax.servlet.http.HttpSession;

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
 *
 **/
 
/**
 * Mobile/server communication source code
 * @author Sean J. Barbeau
 */
public class EndToEndGateways {
    
    /**
     *  This class implements the application server connectivity specific to the needs.
     */
    public static class MediaRemoteME_iTunesServer_SubmitCommandSubmitCommand1Gateway implements InvocationAbstraction {
        /**
         *  This method performs the actual invocation of server functionality. It is
         *  used by the servlet to delegate functionality to external classes.
         *
         *@param  clientID         The unique identifier of a client
         *@param  input            The stream from which we should read the parameters
         *      for the methods
         *@return                  The return value for the method NULL IS NOT
         *      SUPPORTED!!!!
         *@exception  IOException  Thrown when a protocol error occurs
         */
        public Object invoke(HttpSession session, DataInput input) throws Exception {
            short type1 = input.readShort(); // reading the type
                
                String param1;
            if (type1 == -1) { // NULL_TYPE) {
                param1 = null;
            } else {
                param1 = input.readUTF();
            }
            MediaRemoteME.iTunesServer.SubmitCommand instance = (MediaRemoteME.iTunesServer.SubmitCommand)
                session.getAttribute("MediaRemoteME.iTunesServer.SubmitCommand");
            if (instance == null) {
                instance = (MediaRemoteME.iTunesServer.SubmitCommand) Class.forName("MediaRemoteME.iTunesServer.SubmitCommand").newInstance();
                session.setAttribute("MediaRemoteME.iTunesServer.SubmitCommand", instance);
            }
            return Utility.toObject(instance.SubmitCommand(param1));
            
        }
    }
    
    private static Object readObject(DataInput in) throws IOException {
        return Utility.readObject(in);
    }
}
