import java.net.InetAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.*;


public class Servidor{
    //private static ConcurrentHashMap<InetAddress, MySocket> users;
    private static MySocket socketclient;

    private Servidor(){
        //users=new ConcurrentHashMap<>();
    }

    public static void main(String[] args) {           
        ConcurrentHashMap<Integer, MySocket> users= new ConcurrentHashMap<>();
        MyServerSocket ss = new MyServerSocket(10000);
        while(true){
            System.out.println("Esperant nova connexió de client");
            socketclient=ss.accept();
            System.out.print("--Nou client acceptat");
            users.put(socketclient.getPort(),socketclient);
            System.out.println(":    MIDA DE LLISTA --"+users.size()+"--");
            new Thread(){
                @Override
                public void run(){
                    MySocket socketthread = users.get(socketclient.getPort());
                    String line;
                    while((line=socketthread.read_str())!=null){
                        System.out.println("ENVIEM BROADCAST");
                        broadcast(line,socketthread,users);
                        System.out.println("BROADCAST ENVIAT");
                    }socketthread.close();
                    System.out.println("socketthread.close");
                }
            }.start();
        }
    }
    private static void broadcast(String string, MySocket socketclient, ConcurrentHashMap<Integer, MySocket> users){
            for(MySocket value : users.values()){
                if(socketclient!=value){
                    System.out.println("MESSAGE pendent enviar");
                    value.write_str(string);
                    System.out.println("MESSAGE enviat");
                }
                System.out.println("Iteracio n del for");
        }
        
    }

}