import java.net.*;
import java.io.*;


public class MyServerSocket {
    ServerSocket servsocket;
    BufferedReader in;
    PrintWriter out;

    public MyServerSocket(int port){
        try{
            servsocket = new ServerSocket(port);
            
        } catch (IOException e){            
        }
    }

    public MySocket accept(){
        try{
            Socket cs = servsocket.accept();
            return new MySocket(cs);
        } catch (IOException e){
            return null;        
        }        
    } 

    public void init_streams(Socket socket){
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
        } catch (IOException e){   
            System.out.println("Error init_steams");         
        }
    }
}
