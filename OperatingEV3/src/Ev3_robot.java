
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Ev3_robot {

	static EV3GyroSensor gyroSensor = new EV3GyroSensor(SensorPort.S2);

	static RegulatedMotor leftMotor = Motor.B;

	static RegulatedMotor rightMotor = Motor.C;

	static final double tireDistance = 177;

	Delay wait = new Delay();

	// モーターのパワーを制御
	private static void motor_set(int l_motor_pow, int r_motor_pow) {
		leftMotor.setSpeed(l_motor_pow);
		rightMotor.setSpeed(r_motor_pow);
	}

	/**
	 * 指定された角度分左折します．
	 * @param angle : 指定された角度
	 */
	public static void leftRotate(int angle) {

		motor_set(50, 50);

		rightMotor.rotate(angle * 2, true);
		leftMotor.rotate(-angle * 2, true);

	}

	/**
	 * 指定された角度分右折します．
	 * @param angle : 指定された角度
	 */
	public static void rightRotate(int angle) {

		motor_set(50, 50);

		rightMotor.rotate(-angle * 2, true);
		leftMotor.rotate(angle * 2, true);

	}

	/**
	 * タイヤ1回転あたりに進む距離(177mm)を利用して，指定された距離分前進する
	 * @param distance : 進む距離(mm)
	 */
	public static void goStraight(double distance) {

		int rotate = (int) (distance / 177) * 360;

		motor_set(500, 500);

		rightMotor.rotate(rotate, true);
		leftMotor.rotate(rotate, true);

	}

	public static void main(String[] args) throws Exception {

		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(9999);

			while (true) {
				Socket socket = serverSocket.accept();
				new Ev3_Thread(socket).start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null) {
					serverSocket.close();
				}
			} catch (IOException e) {
			}

		}
	}

	static class Ev3_Thread extends Thread {

		private Socket socket;

		public Ev3_Thread(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				String line;
				while ((line = in.readLine()) != null) {
					String[] operator = line.split(",", -1);

					LCD.clear();
					LCD.drawString(operator[0], 0, 0);
					LCD.drawString(operator[1], 0, 1);

					if (operator[0].equals("STRAIGHT")) {
						double distance = Double.parseDouble(operator[1].toString());
						goStraight(distance);
					} else if (operator[0].equals("RightRotate")) {
						int angle = Integer.parseInt(operator[1].toString());
						rightRotate(angle);
					} else if (operator[0].equals("LeftRotate")) {
						int angle = Integer.parseInt(operator[1].toString());
						leftRotate(angle);
					} else {
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (socket != null) {
						socket.close();
					}
				} catch (IOException e) {
				}
			}
		}
	}
}