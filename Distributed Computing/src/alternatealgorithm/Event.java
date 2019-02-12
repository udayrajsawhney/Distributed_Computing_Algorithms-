/*
 * Developed by udaysawhney on 12/2/19 1:27 PM.
 * Last modified 12/2/19 11:09 AM.
 * Github : https://github.com/udayrajsawhney
 * Copyright (c) 2019. All rights reserved.
 */

package alternatealgorithm;

public class Event {
    public String name;
    public int src;
    public int dest;
    public int msg;

    public Event(String event, int src, int dest, int msg) {
        this.name = event;
        this.src = src;
        this.dest = dest;
        this.msg = msg;
    }
}
