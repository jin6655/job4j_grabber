package ru.job4.xinst;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class EchoServer {

    private static String message(String[] msg) {
        return Arrays.stream(msg)
                .filter(s -> s.contains("?msg="))
                .map(s -> s.substring(s.indexOf("=") + 1))
                .findFirst().orElse("");
    }

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write("Hello, dear friend.".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        String msg = EchoServer.message(str.split(" "));
                        System.out.println(msg);
                        if (msg.equals("Exit")) {
                            server.close();
                        } else {
                            out.write("Пиши ещё".getBytes());
                        }
                    }
                    out.flush();
                }
            }
        }
    }

}
