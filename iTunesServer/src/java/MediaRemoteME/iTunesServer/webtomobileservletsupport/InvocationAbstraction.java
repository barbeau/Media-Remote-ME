package MediaRemoteME.iTunesServer.webtomobileservletsupport;

import java.io.DataInput;
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
 * This interface is used to abstract the servlet from the functionality be it
 *  an EJB or a plain Java class or a Web service.
 */
public interface InvocationAbstraction {
    /**
     *  This method performs the actual invocation of server functionality. It is
     *  used by the servlet to delegate functionality to external classes.
     *
     *@param  session          this http session
     *@param  input            The stream from which we should read the parameters
     *      for the methods
     *@return                  The return value for the method NULL IS NOT
     *      SUPPORTED!!!!
     *@exception  IOException  Thrown when a protocol error occurs
     */
    public Object invoke(HttpSession session,
        DataInput input) throws Exception;
}

