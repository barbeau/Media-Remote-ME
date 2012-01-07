//GEN-BEGIN:Client
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
 * This file is generated. Please do not change
 */
package MediaRemoteME.submitcommandservice;

import java.util.*;
import java.io.*;
import javax.microedition.io.*;

/**
 * Mobile stubs to communication with web services
 * 
 * @author Sean J. Barbeau
 */
public class WebToMobileClient {
    
    /** The URL of the servlet gateway */
    private String serverURL;
    
    /** The session cookie of this client */
    private String sessionCookie;
    
    /**
     * Empty array used for no-argument calls, and to represent the value "void"
     */
    
    private final static Object[] _ = new Object[0];
    
    /**
     * Constructs a new WebToMobileClient
     * and initializes the URL to the servlet gateway from a hard-coded value.
     */
    public WebToMobileClient() {
        this("http://97.97.166.100:8220/iTunesServer/servlet/MediaRemoteME.iTunesServer.webservlet6.WebToMobileServlet2");
    }
    
    /**
     * Constructs a new WebToMobileClient
     * and initializes the URL to the servlet gateway from the given value
     *
     * @param serverURL URL of the deployed servlet
     */
    public WebToMobileClient(String serverURL) {
        this.serverURL = serverURL;
    }
    
    public String SubmitCommand(String command) throws IOException {
        Object params[] = new Object[] {
            (String)command
        };
        int paramIDs[] = new int[] {
            10
        };
        return (String)invokeServer(1, params, paramIDs);
    }
    
    
    /**
     *  This method performes a dynamic invocation on the server. It is generic in
     *  order to reduce the code size.
     *
     *@param  requestID        The id of the server service (method) we wish to
     *      invoke.
     *@param  parameters       The parameters that should be passed to the server
     *      (type safety is not checked by this method!)
     *@param  returnType       Is used to indicate the return type we should read
     *      from the server
     *@return                  The return value from the invoked service
     *@exception  IOException  When a communication error or a remove exception
     * occurs
     */
    private Object invokeServer(int requestID, Object[] parameters, int[] paramIDs ) throws IOException {
        HttpConnection connection = (HttpConnection) Connector.open( serverURL );
        connection.setRequestMethod(HttpConnection.POST);
        connection.setRequestProperty("Content-Type", "application/octet-stream");
        connection.setRequestProperty("Accept", "application/octet-stream");
        
        if (sessionCookie == null) {
            // if this is the first time this client contatcs the server,
            // verify that the version matches
            connection.setRequestProperty("version", "???");
        } else {
            connection.setRequestProperty("cookie", sessionCookie);
        }
        
        DataOutputStream output = connection.openDataOutputStream();
        
        // Write invocation code
        output.writeShort( 1 );
        
        /* Write the byte signifying that only one call
         * is being made.
         */
        // TODO This is not reflected on server now
        //output.writeShort(1 /* one call to be made to the server */);
        
        output.writeInt(requestID);
        for (int i = 0; i < parameters.length; i++ ) {
            writeObject(output, parameters[i], paramIDs[i]);
        }
        
        output.close();
        
        int response;
        try {
            response = connection.getResponseCode();
        } catch (IOException e) {
            throw new IOException("No response from " + serverURL);
        }
        if (response != 200) {
            throw new IOException(response + " " + connection.getResponseMessage());
        }
        DataInputStream input = connection.openDataInputStream();
        String sc = connection.getHeaderField("set-cookie");
        if (sc != null) {
            sessionCookie = sc;
        }
        short errorCode = input.readShort();
        if (errorCode != 1) {
            // there was a remote exception
            throw new IOException(input.readUTF());
        }
        
        Object returnValue = readObject(input);
        
        input.close();
        connection.close();
        return returnValue;
    }
    
    /**
     * Serializes object to the stream with given type id
     *
     * @param out
     * @param o object to be serialized to the stream
     * @param id idetification code of the serialized object
     */
    private void writeObject( DataOutputStream out, Object o, int id ) throws IOException {
        if( o == null ) {
            // write null type to the stream
            out.writeShort( -1 );
            return;
        }
        switch( id ) {
            case 10:
                // String
                out.writeShort(10);
                out.writeUTF((String)o);
                break;
            default:
                // default if a data type is not supported
                throw new IllegalArgumentException("Unsupported parameter type: " + o.getClass());
        }
    }
    
    private static Object readObject(DataInput in) throws IOException {
        int type = in.readShort();
        Object result;
        switch (type) {
            case 10:
                // String
                result = in.readUTF();
                return result;
            case -1: /* NULL */
                return null;
        }
        throw new IllegalArgumentException("Unsupported return type (" + type + ")");
    }
}
//GEN-END:Client
