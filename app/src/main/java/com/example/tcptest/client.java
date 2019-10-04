package com.example.tcptest;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class client {
    public client() {
        Socket socket = null;
        String serverIP = "15.164.97.173";
        int serverPort = 9999;
        String filename = "test.txt";

        String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        sdPath += "/Test";//directory name
        File file = new File(sdPath);
        File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS), "Test");
        file.mkdir();
        if (!dir.isDirectory()) {
            if (!dir.mkdir())
                System.out.println("yes");

        }else{
            System.out.println("no");
        }
        /*try{
            if (!file.exists()){


                System.out.println("successfully created directory");
            }
            System.out.println("Directory already exist");
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("Failed to create directory");
        }*/

        /*try{
            socket = new Socket(serverIP, serverPort);
            System.out.println("Connected to Server");

            FileSender fs = new FileSender(socket, "/"+filename);
            fs.run();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}

class FileSender extends Thread {
    Socket socket;
    PrintWriter printwriter;
    DataInputStream dis;
    DataOutputStream dos;

    String filename;
    int control = 0;

    public FileSender(Socket socket, String filestr){
        this.socket = socket;
        this.filename = filestr;
    }

    public void run() {
         try{
             this.printwriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream())), true);
             this.dis = new DataInputStream(new FileInputStream(new File(Environment.getExternalStorageDirectory(), filename)));
             this.dos = new DataOutputStream(socket.getOutputStream());

             byte[] buf = new byte[1024];

             long totalReadBytes = 0;
             int readBytes;

             while((readBytes = dis.read(buf)) > 0){
                 dos.write(buf, 0, readBytes);
                 totalReadBytes += readBytes;
             }
             dos.close();

             System.out.println("Complete");
         } catch(IOException e){
             e.printStackTrace();
         }
    }
}