/*
 * Developed by udaysawhney on 12/2/19 1:26 PM.
 * Last modified 12/2/19 11:48 AM.
 * Github : https://github.com/udayrajsawhney
 * Copyright (c) 2019. All rights reserved.
 */

package sasakialgorithm;

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
