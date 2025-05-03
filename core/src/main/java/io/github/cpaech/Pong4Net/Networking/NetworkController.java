package io.github.cpaech.Pong4Net;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.GdxRuntimeException;

import io.github.cpaech.Pong4Net.Messages.IMessage;
import io.github.cpaech.Pong4Net.Messages.MessageTest;

public class NetworkController extends Thread {
    
    public ConcurrentLinkedQueue<IMessage> queue = new ConcurrentLinkedQueue<IMessage>();
    /**
     * This is the server socket that will be used to accept incoming connections
     */
    public ServerSocket server;
    /**
     * This is the thread that will be used to accept incoming connections
     */
    IncomingConnections incomingConnections;
    /**
     * This is the list of all connected clients
     */
    public List<Socket> clients;
    /**
    * This is the socket that will be used to connect to the server
    */
   public Socket clienSocket;

    public boolean running = true;

    public boolean isClient;
    public boolean isServer;

    public NetworkController(boolean client) {
        this.isClient = client;
        this.isServer = !client;

        if (isServer){
            try{
                server = Gdx.net.newServerSocket(Protocol.TCP, 7654, new ServerSocketHints());
                
                clients = new ArrayList<Socket>();
                incomingConnections = new IncomingConnections(server);
                incomingConnections.start();
            }
            catch (GdxRuntimeException e)
            {
                throw new RuntimeException("Failed creating a server", e); //TODO: Handle this by retuning to menu
            }
        }
        if (isClient)
        {
            SocketHints socketHints = new SocketHints();
            // Socket will time out in 4 seconds
            socketHints.connectTimeout = 4000;
            try {
                clienSocket = Gdx.net.newClientSocket(Protocol.TCP, "localhost", 7654, socketHints);
            } catch (GdxRuntimeException e) {
                System.out.println("Server not found");
                e.printStackTrace();
                clienSocket = null;
                //TODO: return to menu
            }
        }
    }
    public void dispose() {
        running = false;
    }
    @Override
    public void run() {
        while (running) {
            if (isServer)
            {
                
            {
                Socket s = incomingConnections.queue.poll();
                while (s != null) {
                    clients.add(s);
                    s = incomingConnections.queue.poll();
                }
            }

            for (Socket s : clients) {
                //The Socket is closed if the client disconnects thus we remove it from the list
                if (s.isConnected() == false)
                {
                    s.dispose();
                    clients.remove(s);
                    continue;

                }
                
                InputStream stream = (s.getInputStream());
                try {
                    //This is a blocking call, it will wait until a message is received. Not great not terrible
                    if (stream.read() == 3) //TODO: Replace with switch case
                    {
                        queue.add(new MessageTest(stream));
                    }
                }
                catch (IOException e)
                {
                    //Something is wrong with the socket, we remove it from the list
                    e.printStackTrace();
                    clients.remove(s);
                    continue;
                }
            }
            }
            if (isClient)
            {
                if (clienSocket != null && clienSocket.isConnected() == false)
                {
                    System.out.println("Server disconnected");  
                    clienSocket = null;
                    //TODO: return to menu
                }
            }
        }
        if (isClient)
        {
            clienSocket.dispose();
        }
        if (isServer)
        {
            server.dispose();
            for (Socket s : clients) {
                s.dispose();
            }
        }
    }

    //Client only
    public void Send(IMessage msg)
    {
        if (isServer)
        {
            throw new IllegalAccessError("This Method only is intended for Clients");
        }
        if (clienSocket == null)
        {
            throw new NullPointerException("clientSocket is null");
        }
        msg.Send(clienSocket.getOutputStream());
        
    }
}

