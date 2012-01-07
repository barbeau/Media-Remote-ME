
package MediaRemoteME.iTunesServer.webtomobileservletsupport;

import java.io.*;
import java.lang.reflect.*;

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
 *  This class is used as an external protocol utility. It is so we don't
 *  generate as much code.
 */

public class Utility {
    
    private final static short STRING_TYPE = 7;
    
    /** Type value for arrays. */
    private final static short ARRAY_TYPE = -2;
    
    /** Marker for null. Null is a type and a value together. */
    private final static short NULL_TYPE = -1;
    
    /** Marker for void return types. */
    public final static Object VOID_VALUE = new Object();
    
    /** Compute the type value for a given class
     * @param clazz An object to be send to the client
     * @return a type value to identify the object
     */
    private static short getType(Class clazz) {
        String className = clazz.getName();
        if (className.equals("java.lang.String")) {
            return STRING_TYPE;
        }
        
        throw new IllegalArgumentException("Unsupported type (" + className + ")");
    }
    
    
    /**
     *  Sends return values to the client output stream.
     *
     *@param  output           The output stream into which all the data should be
     *      written
     *@param  returnValues     The values which we should write into the stream
     *@exception  IOException  If an error occured while writing the results
     */
    public static void writeResults( DataOutput output, Object[] returnValues) throws IOException {
        for ( int i = 0; i < returnValues.length; i++ ) {
            writeObject(output, returnValues[i]);
        }
    }
    
    public static void writeObject(DataOutput output, Object o) throws IOException {
        if (o == null) { // return null
            output.writeShort(NULL_TYPE);
        } else if (o instanceof String) {
            output.writeShort(STRING_TYPE);
            output.writeUTF((String) (String) o);
        }
    }
    
    static Object toObject(byte b) {
        return new Byte(b);
    }
    
    static Object toObject(short s) {
        return new Short(s);
    }
    
    static Object toObject(char c) {
        return new Character(c);
    }
    
    static Object toObject(int i) {
        return new Integer(i);
    }
    
    static Object toObject(long l) {
        return new Long(l);
    }
    
    static Object toObject(float f) {
        return new Float(f);
    }
    
    static Object toObject(double d) {
        return new Double(d);
    }
    
    static Object toObject(boolean b) {
        return new Boolean(b);
    }
    
    static Object toObject(Object o) {
        return o;
    }
    
    protected static Object readObject(DataInput in) throws IOException {
        short type = in.readShort();
        
        switch (type) {
            case STRING_TYPE: /* String */
                return in.readUTF();
            case NULL_TYPE: /* null */
                return null;
            default:
                throw new IllegalArgumentException(
                    "Unsupported return type (" + type + ")");
        }
    }
    
    public static class CompositeDataOutput implements DataOutput {
        private DataOutput[] outputs;
        
        public CompositeDataOutput(DataOutput[] outputs) {
            this.outputs = (DataOutput[]) outputs.clone();
        }
        
        public void write(int b) throws IOException {
            this.writeByte(b);
        }
        public void write(byte[] b) throws IOException {
            this.write(b, 0, b.length);
        }
        public void write(byte[] b, int offset, int len) throws IOException {
            for (int i = 0; i < outputs.length; i++) {
                outputs[i].write(b, offset, len);
            }
        }
        public void writeBoolean(boolean b) throws IOException {
            for (int i = 0; i < outputs.length; i++) {
                outputs[i].writeBoolean(b);
            }
        }
        public void writeByte(int b) throws IOException {
            for (int i = 0; i < outputs.length; i++) {
                outputs[i].writeByte(b);
            }
        }
        public void writeBytes(String s) throws IOException {
            for (int i = 0; i < outputs.length; i++) {
                outputs[i].writeBytes(s);
            }
        }
        public void writeChar(int c) throws IOException {
            for (int i = 0; i < outputs.length; i++) {
                outputs[i].writeChar(c);
            }
        }
        public void writeChars(String s) throws IOException {
            for (int i = 0; i < outputs.length; i++) {
                outputs[i].writeChars(s);
            }
        }
        public void writeDouble(double d) throws IOException {
            for (int i = 0; i < outputs.length; i++) {
                outputs[i].writeDouble(d);
            }
        }
        public void writeFloat(float f) throws IOException {
            for (int i = 0; i < outputs.length; i++) {
                outputs[i].writeFloat(f);
            }
        }
        public void writeInt(int value) throws IOException {
            for (int i = 0; i < outputs.length; i++) {
                outputs[i].writeInt(value);
            }
        }
        public void writeLong(long l) throws IOException {
            for (int i = 0; i < outputs.length; i++) {
                outputs[i].writeLong(l);
            }
        }
        public void writeShort(int s) throws IOException {
            for (int i = 0; i < outputs.length; i++) {
                outputs[i].writeShort(s);
            }
        }
        public void writeUTF(String s) throws IOException {
            for (int i = 0; i < outputs.length; i++) {
                outputs[i].writeUTF(s);
            }
        }
    }
    
    public static class TracedDataOutput implements DataOutput {
        private PrintStream log;
        public TracedDataOutput(PrintStream log) {
            this.log = log;
        }
        public void write(int b) throws IOException {
            this.writeByte(b);
        }
        public void write(byte[] b) throws IOException {
            this.write(b, 0, b.length);
        }
        public void write(byte[] b, int offset, int len) throws IOException {
            log.println("Writing " + len + " bytes");
        }
        public void writeBoolean(boolean b) throws IOException {
            log.println("Writing boolean: " + b);
        }
        public void writeByte(int b) throws IOException {
            log.println("Writing byte: " + b);
        }
        public void writeBytes(String s) throws IOException {
            log.println("Writing bytes: '" + s + "'");
        }
        public void writeChar(int c) throws IOException {
            log.println("Writing char: " + c);
        }
        public void writeChars(String s) throws IOException {
            log.println("Writing chars: '" + s + "'");
        }
        public void writeDouble(double d) throws IOException {
            log.println("Writing double: " + d);
        }
        public void writeFloat(float f) throws IOException {
            log.println("Writing float: " + f);
        }
        public void writeInt(int i) throws IOException {
            log.println("Writing integer: " + i);
        }
        public void writeLong(long l) throws IOException {
            log.println("Writing long: " + l);
        }
        public void writeShort(int s) throws IOException {
            log.println("Writing short: " + s);
        }
        public void writeUTF(String s) throws IOException {
            log.println("Writing string: '" + s + "'");
        }
    }
    
}
