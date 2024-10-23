package com.example;
import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("client partito");
        Socket s = new Socket("localhost",3000);

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        Scanner sc = new Scanner(System.in);
        String numero = "";
        String  tent = "";
        String stringaRicevuta = "";
        boolean risp = false;
        int n = 0;
        String stringaNumero = "";

        do{
            do{

                System.out.println("inserisci un numero compreso fra 0 e 100");
                try{
                numero = sc.nextLine();

                n = Integer.parseInt(numero);
                
                if(n>=0 && n<=100)
                {
                    risp = true;
                    stringaNumero = Integer.toString(n);
                    out.writeBytes(stringaNumero + '\n');
                }
                }
                catch(Exception e){
                    System.out.println("Non puoi inserire una stringa");
                }
            }while(!risp);
            
            stringaRicevuta = in.readLine();
            

            if(stringaRicevuta.equals(">")){
                System.out.println("Numero troppo grande");
            }
            else if(stringaRicevuta.equals("<")){
                System.out.println("Numero troppo piccolo");
            }
            else{
                tent= in.readLine();
                System.out.println("HAI INDOVINATO IN "+ tent +" tentativi");
            }
    
            }while(!stringaRicevuta.equals("="));
            System.out.println("client finito");
            s.close();
    }
}