import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.IOException;

public class Servidor{
    /*MyServerSocket ss;
    MySocket s = null;
    InputStreamReader print = null;
    BufferedReader brinput = null;*/

    public static void main(String[] args) {        
        MyServerSocket ss = new MyServerSocket(10000);
            MySocket sock = ss.accept();
            System.out.println("*****INICI Conversa:*****");
            new Thread(){
                public void run(){
                    BufferedReader buffIn = sock.in;
                    String line;
                    try{
                        while((line = buffIn.readLine()) != null){
                            System.out.println("Client: " + line);
                        }
                        sock.close();
                    } catch (IOException e){                    
                    }
                }
            }.start();
            
            new Thread(){
                public void run(){
                    BufferedReader buffOut = new BufferedReader(new InputStreamReader(System.in));
                    String line;
                    try {
                        while((line = buffOut.readLine()) != null){
                            sock.write_str(line);
                        }sock.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }.start();
        
    }
}
