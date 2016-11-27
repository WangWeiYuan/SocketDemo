package ClientDemo;


import ServerDemo.MyServer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.net.Socket;

public class MyClient
{
    public static void main(String[] args)
    {
        Socket socket = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        try
        {
            socket = new Socket("127.0.0.1", MyServer.PORT);
            System.out.println("Socket=" + socket);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
            for (int i = 0; i < 10; i++)
            {
                pw.println("Message  " + i);
                pw.flush();
                String str = br.readLine();
                System.out.println(str);
            }
            pw.println("end");
            pw.flush();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                System.out.println("close");
                br.close();
                pw.close();
                socket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}