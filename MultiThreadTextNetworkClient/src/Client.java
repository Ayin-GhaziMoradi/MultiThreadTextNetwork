import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;


public class Client {

    public static void main(String[] args) {
        try (Socket client = new Socket("192.168.42.65", 7070)) {
            System.out.println("Connected to server.");
            OutputStream out = client.getOutputStream();
            InputStream in = client.getInputStream();
            byte[] buffer = new byte[2048];
            Scanner sc= new Scanner(System.in);
            String input = sc.nextLine();
            while (!input.equals("over")) {
                out.write(input.getBytes());
                int read = in.read(buffer);
                System.out.println("Current Full Text : " + new String(buffer, 0, read));
                input = sc.nextLine();
            }
            out.write(input.getBytes());
            System.out.print("Closing Connection ... ");
        } catch (IOException ex) {
            System.err.println(ex);
        }
        System.out.println("done.");
    }
}
