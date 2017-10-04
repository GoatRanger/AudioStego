//
// This program compiles but won't run successfully.
//
public class WhatHappens {
    public static void main(String[] args) {
        StringBuffer[] stringBuffers = new StringBuffer[10];

        for (int i = 0; i < stringBuffers.length; i ++) {
            stringBuffers[i].append("StringBuffer at index " + i);
        }
    }
}
