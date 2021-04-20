import java.net.InetAddress;
import java.util.HashMap;

public class Servidor{
    private static HashMap<InetAddress, MySocket> users;
    private static MySocket socketclient;

    public static void main(String[] args) {       
        users=new HashMap<>();
        MyServerSocket ss = new MyServerSocket(10000);
        while(true){
            System.out.println("Esperant nova connexió de client");
            socketclient=ss.accept();
            System.out.println("Nou client acceptat");
            users.put(socketclient.getID(),socketclient);
            new Thread(){
                @Override
                public void run(){
                    MySocket socketthread = users.get(socketclient.getID());
                    String line;
                    while((line=socketthread.read_str())!=null){
                        broadcast(line,socketthread);
                    }socketthread.close();
                }
            }.start();
        }
    }
    private static void broadcast(String string, MySocket socketclient){
        for(MySocket value : users.values()){
            if(socketclient!=value){
                MySocket socket=value;
                socket.write_str(string); 
            }
        }
    }

}