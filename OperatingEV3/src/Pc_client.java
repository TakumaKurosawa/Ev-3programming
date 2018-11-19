
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Pc_client extends JFrame {

	private static final long serialVersionUID = 1L;
	private BufferedWriter bw;
	private Socket socket;

	public static void main(String[] args) {
		Pc_client frame = new Pc_client();
		frame.setSize(300, 300);
		frame.setVisible(true);
	}

	public Pc_client(){
		try {
        			// ソケットを開く
        			// 192.168.00.00の部分はEV3のIPアドレスを指定
        			// 9999の部分にはEV3側のプログラムと同じ任意のポートを指定
        			socket = new Socket("10.11.21.21",9999);
        			bw = new BufferedWriter(new
			OutputStreamWriter(socket.getOutputStream()));

        			// レイアウトと各ボタンを押したときの処理
        			setLayout(new BorderLayout());

        			JButton straight = new JButton("1");
        			add(straight, BorderLayout.NORTH);
        			straight.addMouseListener(new MouseAdapter() {
        				public void mouseClicked(MouseEvent e){
        					sendCommand("STRAIGHT", "500");
        				}
        			});

        			JButton right = new JButton("2");
        			add(right, BorderLayout.EAST);
        			right.addMouseListener(new MouseAdapter() {
        				public void mouseClicked(MouseEvent e){
        					sendCommand("RightRotate", "45");
        				}
        			});

        			JButton left = new JButton("3");
        			add(left, BorderLayout.WEST);
        			left.addMouseListener(new MouseAdapter() {
        				public void mouseClicked(MouseEvent e){
        					sendCommand("LeftRotate", "45");
        				}
        			});


            		addWindowListener(new WindowAdapter() {
                 		public void windowClosing(WindowEvent e)  {
                	 			try {
                		 			bw.close();
                		 			if( socket != null){
                			 			socket.close();
                			 			socket = null;
                			 		}
                	 			}
                	 			catch (Exception ex) {
                		 			System.out.println(ex);
                	 			}
					System.exit(0);
                 		}
            		});
        		}
        		catch (Exception e) {
            		System.out.println(e);
        		}
	}

	// EV3に命令を送る
	private void sendCommand(String mode, String number) {
		try {
			String command = mode + "," + number + System.lineSeparator();
			bw.write(command);
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
