import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.IOException;

public class Client{

    public static void main(String[] args){
        MySocket s = new MySocket("localhost", 10000);
        System.out.println("*****INICI Conversa*****");
        new Thread(){
            @Override
            public void run(){
                String line;
                BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
                try{
                    while((line = kbd.readLine()) != null){ 
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
                        System.out.println("Server: " + line);
                    }s.close();
                } catch (IOException e){                    
                }
            }
        }.start();
    }
}
