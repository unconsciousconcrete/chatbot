import java.io.*;
import java.net.*;
import java.util.Scanner;


public class client {
    private static final String ServerAddress = "localhost";
    private static final int serverPortNumber = 6214;
    public static void main(String[] args){
        try {
            Socket socket = new Socket(ServerAddress, serverPortNumber);
            
            System.out.println("connected successfully");
            // setup input and output streams

            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //thread for the conversation
            new Thread(() ->{
                try{
                    String serverResponse;

                    while((serverResponse = in.readLine()) != null){
                        System.out.println(serverResponse);
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }).start();

            //read message from the console
            Scanner scanner = new Scanner(System.in);
            String userInput;

            while(true) {
                userInput = scanner.nextLine();
                out.println(userInput);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

