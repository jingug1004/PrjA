package kr.co.comes.projectA.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.comes.projecta.decryptor.Decryptor;

public class LicenseDecoder {
	private static String serial = "";
	private static int term = 0;
	private static String maxuser = "";

	public static String getSerial() {
		return serial;
	}

	public static int getTerm() {
		return term;
	}

	public static String getMaxuser() {
		return maxuser;
	}

	public static void initData(HttpServletRequest request) {
		String adduser = (String) request.getSession().getAttribute("id");
		// mins edit : license decode
		try {
			/*
			 * String root_path =
			 * request.getSession().getServletContext().getRealPath("/");
			 */
			String root_path = "/home/comes/license/";
			System.out.println(root_path);
			String decoded_data = Decryptor.getInstance().decode(root_path);
			System.out.println(root_path);
			System.out.println(decoded_data);

			String[] parse = decoded_data.split("#");

			serial = parse[0].toString();
			term = compareAtoB(parse[1].toString());
			maxuser = parse[2].toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getMacAdrress() {
		InetAddress ip;
		StringBuilder sb = new StringBuilder();
		try {
			ip = InetAddress.getLocalHost();
			System.out.println("Current IP address : " + ip.getHostAddress());
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			byte[] mac;
			mac = network.getHardwareAddress();

			System.out.print("Current MAC address : ");

			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	public static int compareAtoB(String date) {
		Date da;
		int compare = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		Date currentDate = new Date();
		formatter.setLenient(false);

		try {
			da = formatter.parse(date.toString());
			long diff = da.getTime() - currentDate.getTime();
			compare = (int)(diff/(24*60*60*1000));
//	         compare = currentDate.compareTo(da);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return compare;
	}
}
