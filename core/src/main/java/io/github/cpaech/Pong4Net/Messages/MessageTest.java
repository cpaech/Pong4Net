package io.github.cpaech.Pong4Net.Messages;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * Used to send a text message to the receiving end of the connection.
 * The message is limited to 255 characters and encoded in ASCII.
 * @author cpaech
 *
 */
public class MessageTest
{
    /**
     * This is the type of the message. It is used to identify the message type on the receiving end.
     * The type is set to 3 for this message.
     */
    public int type = 3;
    /**
     * This is the message that will be sent. It is limited to 255 characters and encoded in ASCII.
     */
    public String message;
    
    /**
     * This is the constructor for the {@link MessageTest} class. It takes an {@link InputStream} as a parameter.
     * The message is read from the input stream and stored in the {@link MessageTest#message} variable.
     * This constructor is used to read a message from the input stream.
     * 
     * @param reader {@link InputStream} the InputStream to read the message from
     */
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

    /**
     * This is the constructor for the {@link MessageTest} class. It takes a {@link String} as a parameter.
     * The message is stored in the {@link MessageTest#message} variable. This constructor is used to create a new message to be sent.
     * 
     * @param message {@link String} the message to be sent
     */
    public MessageTest(String message)
    {   
        if (message.length() > 255)
        {
            throw new IllegalArgumentException("Message too long"); //TODO: Test this with JUnit
        }
        this.message = message;
    }

    /**
     * This method is used to send the message to the receiving end of the connection.
     * It takes an {@link OutputStream} as a parameter and writes the message to it.
     * 
     * @param writer {@link OutputStream} the OutputStream to write the message to
     */
    public void Send(OutputStream writer)
    {
        Charset charset = Charset.forName("ASCII");
        try{
            writer.write(type); //TODO Make helper functions
            writer.write(message.length());
            writer.write(message.getBytes(charset));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}