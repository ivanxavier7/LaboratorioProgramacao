package gui.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import json.ConfigJSON;


public class SocketClient {
	private static ConfigJSON configJSON = new ConfigJSON();
	private static int port=9500; 
	private String encriptedFilePath = configJSON.getPathMonitor();
	private static String decriptedFilePath = "PATH//";//configJSON.getPathDecrypt();

	public void readFile() {
		try {Socket s=new Socket("127.0.0.1",9500);
			DataInputStream so=new DataInputStream(s.getInputStream());//Input stream creation, receiving server to send information
			System.out.println(so.readUTF());
			FileOutputStream f=new FileOutputStream(encriptedFilePath);//File Storage Address
			int p;
			System.out.println("please wait........");
			while((p=so.read())!=-1){
				f.write(p);
			}
			InputStream fi=new FileInputStream(encriptedFilePath);
			byte[] bytes=EncryptionAES.toByteArray(fi);
			byte[] debytes=EncryptionAES.decrypt(bytes);
			String temp=new String(debytes);
			fi.close();
			System.out.println("decrypted content:"+temp);
	
			FileOutputStream fos=new FileOutputStream(encriptedFilePath);
			fos.write(debytes);
			s.close();
			so.close();
			f.flush();
			f.close();
			System.out.println("complete!");
		} catch(IOException e){
			System.out.println("file transfer failed!");
		}
	}
	
	public void writeFile() throws IOException {			
				ServerSocket s = new ServerSocket(port);
			try {
				//Listen
				Socket s1=s.accept();
				System.out.println("Client connection success!");

				DataOutputStream so=new DataOutputStream(s1.getOutputStream());
	so.writeUTF("FileName:" + decriptedFilePath +"\n"+"ServerIp:"+s.getInetAddress()+"\n"+"ServerPort:"+s.getLocalPort());
				FileInputStream f=new FileInputStream(decriptedFilePath);
				String list= getStringFile();
				 //System.out.println("Original byte:" + list.length());
				//int len=f.available();
				byte[] bytes=new byte[list.length()];
				bytes=EncryptionAES.encrypt(list);
				so.write(bytes);
					s.close();
					f.close();
					s1.close();
					so.flush();
					so.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public static String getStringFile() {
		String data = null;
		try {
			File myObj = new File(decriptedFilePath);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				data = myReader.nextLine();
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Ocorreu um erro ao ler o ficheiro!");
			e.printStackTrace();
		}
	return data;
	}
}