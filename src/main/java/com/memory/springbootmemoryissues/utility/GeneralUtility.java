package com.memory.springbootmemoryissues.utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;


public class GeneralUtility {
    public static long calculateMapSizeOnDisk(Map<?, ?> map) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(map);
            oos.close();
            return baos.toByteArray().length;
        } catch (IOException e) {
            e.printStackTrace();
            return -1; // Error occurred, return -1
        }
    }
}
