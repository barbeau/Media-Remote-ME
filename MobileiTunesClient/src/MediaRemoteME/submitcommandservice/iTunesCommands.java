/*
 * iTunesCommands.java
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

package MediaRemoteME.submitcommandservice;

/**
 * Defines constants used for iTunes commands
 * @author Sean Barbeau
 */
public interface iTunesCommands {
    public static final int PLAY_PAUSE = 0;
    public static final int NEXT_TRACK = 1;
    public static final int PREVIOUS_TRACK = 2;
    public static final int VOLUME_UP = 3;
    public static final int VOLUME_DOWN = 4;
    public static final int FAST_FORWARD = 5;
    public static final int REWIND = 6;
    public static final int STOP = 7;
}
