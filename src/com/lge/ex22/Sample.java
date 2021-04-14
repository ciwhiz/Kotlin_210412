package com.lge.ex22;

import java.io.*;

class MyResource implements AutoCloseable {
    public void close() {
        System.out.println("MyResource close");
    }
}

public class Sample {
    // Java 1.7 - Try with Resources
    //  : AutoCloseable
    public static void main(String[] args) throws Exception {
        // 비메모리 자원은 반드시 명시적인 종료 메소드를 통해 해지해주어야 합니다. - close()
        // => 예외 발생에 상관없이 무조건 수행되어야 한다.
        try (FileOutputStream fos = new FileOutputStream("a.txt");
             MyResource resource = new MyResource();
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             DataOutputStream dos = new DataOutputStream(bos)) {

            dos.writeInt(42);
            dos.writeUTF("Tom");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Java 1.6
    /*
    public static void main(String[] args) {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        DataOutputStream dos = null;
        try {
            fos = new FileOutputStream("a.txt");
            bos = new BufferedOutputStream(fos);
            dos = new DataOutputStream(bos);

            dos.writeInt(42);
            dos.writeUTF("Tom");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    */


    /*
    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("a.txt");
        DataOutputStream dos = new DataOutputStream(fos);

        dos.writeInt(42);
        dos.writeUTF("Tom");

        dos.close();
        fos.close();
    }
    */
}
