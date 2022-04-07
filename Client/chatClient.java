import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class chatClient 
{

    public static void main(String[] args) throws Exception
    {
        Socket s = new Socket("localhost", 2222); 
        Scanner clientInput = new Scanner(s.getInputStream());
        String question = clientInput.nextLine();
        System.out.println(question);
        Scanner localInput = new Scanner(System.in);
        PrintStream clientOutput = new PrintStream(s.getOutputStream());

        Thread lt = new Thread() {
            public void run()
            {
                String line;
                while(true)
                {
                    line = clientInput.nextLine();
                    System.out.println(line);

                    if (line.equalsIgnoreCase("/quit"))
                    {
                        //s.close();
                        //break;
                    }
                }
            }
        };

        lt.start();

        while(true)
        {
            clientOutput.println(localInput.nextLine());
        }
    }
}
