import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Client{
    public static String nom;
    public static void main(String[] args){
        MySocket s = new MySocket("localhost", 10000);
        System.out.println("*****INICI Conversa*****");
        System.out.print("Introdueix el teu nom: ");
        Scanner sc = new Scanner(System.in);
        nom = sc.nextLine();
        
        new Thread(){
            @Override
            public void run(){
                String line;
                BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
                try{
                    while((line = kbd.readLine()) != null){ 
                        line=nom+": "+line;
                        s.write_str(line);
                    }s.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }.start();

        //Output thread
        new Thread(){
            public void run(){
                BufferedReader buffIn = s.in;
                String line;
                try{
                    while((line = buffIn.readLine()) != null){
                        System.out.println(line);
                    }s.close();
                } catch (IOException e){                    
                }
            }
        }.start();
    }
}