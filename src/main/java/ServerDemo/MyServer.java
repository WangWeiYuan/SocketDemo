package ServerDemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer
{

    public static int PORT = 8080;
    public static void main(String[] agrs)
    {
        ServerSocket s = null;
        Socket socket = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        try
        {
            s = new ServerSocket(PORT);
            System.out.println("ServerSocket Start:"+s);
            socket = s.accept();
            System.out.println("Connection accept socket:"+socket);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
            while(true)
            {
                String str = br.readLine();
                if(str.equals("end"))
                {
                    break;
                }
                System.out.println("Client Socket Message:"+str);
                Thread.sleep(1000);
                pw.println("Message Received ! ");
                pw.flush();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            System.out.println("Close");
            try
            {
                br.close();
                pw.close();
                socket.close();
                s.close();
            }
            catch (Exception e2)
            {
                e2.printStackTrace();
            }
        }
    }
}