package com.zj.test.jvm;

import java.util.ArrayList;
import java.util.List;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/12/15 14:01
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class OutofMemoryErrorTest {


    /**
     * 1.如果不指定XX:+HeapDumpOnOutOfMemoryError参数。
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     * 	at java.util.Arrays.copyOf(Arrays.java:3210)
     * 	at java.util.Arrays.copyOf(Arrays.java:3181)
     * 	at java.util.ArrayList.grow(ArrayList.java:265)
     * 	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:239)
     * 	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:231)
     * 	at java.util.ArrayList.add(ArrayList.java:462)
     * 	at com.zj.test.jvm.OutofMemoryErrorTest.main(OutofMemoryErrorTest.java:21)
     *
     * 2.指定XX:+HeapDumpOnOutOfMemoryError参数。
     * java.lang.OutOfMemoryError: Java heap space
     * Dumping heap to java_pid17744.hprof ...
     * Heap dump file created [16491648 bytes in 0.037 secs]
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     * 	at java.util.Arrays.copyOf(Arrays.java:3210)
     * 	at java.util.Arrays.copyOf(Arrays.java:3181)
     * 	at java.util.ArrayList.grow(ArrayList.java:265)
     * 	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:239)
     * 	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:231)
     * 	at java.util.ArrayList.add(ArrayList.java:462)
     * 	at com.zj.test.jvm.OutofMemoryErrorTest.main(OutofMemoryErrorTest.java:37)
     *
     * @param args
     */
    public static void main(String[] args) {
        List list = new ArrayList<String>();

        for(;;){
            list.add("1");
        }
    }
}
