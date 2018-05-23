package main;

import receiveFile.ReceiveFile;
import runScript.RunFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileReceiveNode extends ReceiveFile {

    public FileReceiveNode() throws IOException {
        super();
    }

    private void receiveFile() throws IOException {
        // Defining the server socket
        setServSoc(new ServerSocket(getPort()));

        // Defining the variable for reading the file
        int bytesRead;

        System.out.println("ServerTransferFile: Establishing socket");

        while (true) {
            // Connecting to the client socket
            Socket clientSoc = getServSoc().accept();
            System.out.println("ServerTransferFile: Client connected");

            // Defining the socket output (inputStream) and the file output (outputstream)
            InputStream inStream = clientSoc.getInputStream();
            OutputStream output = new FileOutputStream(getFileStorePath() + getFileName());

            // Read the write the file to the fileStorePath
            byte[] byteArray = new byte[1024];
            while ((bytesRead = inStream.read(byteArray)) != -1) {
                output.write(byteArray, 0, bytesRead);
            }

            //Start the MatLab processing thread
            startMatlab();

            // Close serversocket and outputstream, break the loop
            getServSoc().close();
            output.close();
            break;
        }
    }

    private void startMatlab(){
        RunFile tempRun = new RunFile(getFileStorePath(), getFileName(), "");
        tempRun.detectAndRun();
    }
}
