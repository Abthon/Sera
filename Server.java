import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.HashMap;

class Server implements Runnable{

    // Array list for holding list of clients.
    private ArrayList<connectionHandler> connections;
    private HashMap<Integer, String> connectionDictionary = new HashMap<Integer,String>();
    private ServerSocket server;
    private boolean done;
    private ExecutorService pool;   

    Server(){
        connections = new ArrayList<connectionHandler>();
        this.done = false;
    }

    @Override
    public void run() {
        try{
            server = new ServerSocket(5000);
            pool = Executors.newCachedThreadPool();
            while (!this.done){
                System.out.println("Waiting for incoming connections...");
                Socket client = server.accept();
                connectionHandler handler = new connectionHandler(client);
                pool.execute(handler);
                connections.add(handler);
            }

        }catch(IOException e){
            this.shutdown();
        }
    }

    // clientu messagun kene semu yilekal
    // server dictionary wust itterate karege buhala melso semun kene messagu yilekal
    void brodcast(String message, String messageOwner){
        System.out.println(connections.size() + "Sayizu");
        for(connectionHandler ch: connections){
            ch.sendMessage(message + "-" + messageOwner);
        }
    }


    void shutdown(){
        try{
            if(!this.server.isClosed()){
                this.server.close();
            }

            // Closing clients connection
            for (connectionHandler handler : connections){
                handler.shutdown();
            }
        }catch(IOException e){

        }
    }


    class connectionHandler implements Runnable{
        private Socket client;
        private BufferedReader in;
        private PrintWriter out;
        private String clientName;
        private String message;

        connectionHandler(Socket client){
            this.client = client;
        }

        @Override
        public void run(){
            try{
                this.out = new PrintWriter(client.getOutputStream(),true);
                this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                this.clientName = in.readLine();
                System.out.println("Client accepted with name " + this.clientName);
                connectionDictionary.put(this.client.hashCode(), this.clientName);

                // Getting list of users
                java.util.ArrayList<String> listOfUsers = new java.util.ArrayList<String>();
                for (java.util.Map.Entry<Integer,String> entry : connectionDictionary.entrySet()) {
                    listOfUsers.add(entry.getValue());
                }

                Client.addOnlineUsers(listOfUsers);
                while((message = in.readLine()) != null){
                    if(message.startsWith("/quit")){
                        brodcast("Leaving the group",connectionDictionary.get(this.client.hashCode()));
                        this.shutdown();
                        // exit client's thread.
                    }else{
                        brodcast(message,connectionDictionary.get(this.client.hashCode()));
                    }
                }
            }catch(IOException e){
                this.shutdown();
            }
        }

        // a method for sending the message to the client.
        void sendMessage(String message){
            out.println(message);
        }

        void shutdown(){
            try{
                if(!this.client.isClosed()){
                    this.out.close();
                    this.in.close();
                    this.client.close();
                }
            }catch(IOException e){
                // passed
            }
        }
    }

    public static void main(String[] args) {
        Server s = new Server();
        s.run();
    }
}