package jp.ac.aitech.k17097kk.ev3_1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class SendToEv3 extends Thread {

    private static final long serialVersionUID = 1L;
    private BufferedWriter bw;
    private Socket socket;


    public void run(String mode, String number) {

        try {
            // ソケットを開く
            // 192.168.00.00の部分はEV3のIPアドレスを指定
            // 9999の部分にはEV3側のプログラムと同じ任意のポートを指定
            socket = new Socket("10.4.32.246", 9999);
            bw = new BufferedWriter(new
                    OutputStreamWriter(socket.getOutputStream()));

            sendCommand("STRAIGHT", "500");


            bw.close();
            socket.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }


    // EV3に命令を送る
    private void sendCommand(String mode, String number) {
        try {
            String command = mode + "," + number + "\n";
            bw.write(command);
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
