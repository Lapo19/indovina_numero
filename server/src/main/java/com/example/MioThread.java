package com.example;
import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class MioThread extends Thread{
    Socket s;

    MioThread(Socket s){
        this.s = s;
    }

    public void run(){
        try{
            System.out.println("un client si è collegato");
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            String numeroTent = "";
            int tent = 0;
            Random random = new Random();
            int numero = random.nextInt(100);
            boolean risp = false;
            do{
                numeroTent = in.readLine();
                int n = Integer.parseInt(numeroTent);
                String s = "";
                if(n>numero){
                    s=">";
                }
                else if(n<numero){
                    s="<";
                }
                else{
                    s="=";
                    risp = true;
                }
                tent++;
                out.writeBytes( s+ '\n');
            }
            while(!risp);
            out.writeBytes( ""+ tent + '\n');
        }
        catch(Exception e){}
    }
}
