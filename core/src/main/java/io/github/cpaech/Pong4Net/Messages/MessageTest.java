package io.github.cpaech.Pong4Net.Messages;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

//type:c3
public class MessageTest
{
    public int type = 3;
    public String message;
    
    public MessageTest(InputStream reader)
    {
        Charset charset = Charset.forName("ASCII");
        try{
            int length = reader.read();
            byte[] msg = new byte[length];
            reader.read(msg);
            message = new String(msg, charset);
            System.out.println(message);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public MessageTest(String message)
    {
        this.message = message;
    }

    public void Send(OutputStream writer)
    {
        Charset charset = Charset.forName("ASCII");
        try{
            writer.write(type);
            writer.write(message.length());
            writer.write(message.getBytes(charset));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}