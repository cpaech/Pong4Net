package io.github.cpaech.Pong4Net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.GdxRuntimeException;

import io.github.cpaech.Pong4Net.Messages.MessageTest;

//Types
// c0 -> create account ->                  [(byte)type/(int)account id]    -> response type 1
// s1 -> response to create account ->      [(byte)type/(boolean)sucess]
// c3 -> test message ->                    [(byte)type/(string)message] 
// ----------- all other messages will be identified over the account id -----------
// s2 -> set background color ->            [(byte)type/(int)colorA/(int)colorB/(int)colorC]
//
//
//
//
//
//
//
//





public class ServerController {

    public Model mvcModel;
    public ServerSocket server;
    IncomingConnections incomingConnections;
    public List<Socket> clients;
    public ServerController(Model mvcModel) {
        this.mvcModel = mvcModel;
        server = Gdx.net.newServerSocket(Protocol.TCP, 7654, new ServerSocketHints());
        clients = new ArrayList<Socket>();
        incomingConnections = new IncomingConnections(this);
        incomingConnections.start();
    }

    public void render() {
        for (Socket s : clients) {
            System.out.println("TWS");
            InputStream stream = (s.getInputStream());
            try {
                if (stream.read() == 3)
                {
                    System.out.println(new MessageTest(stream).message);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }


    }
        
}

