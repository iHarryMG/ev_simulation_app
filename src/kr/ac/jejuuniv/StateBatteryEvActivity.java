package kr.ac.jejuuniv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class StateBatteryEvActivity extends Activity{
	
	TextView percent;
	TextView percentInfo;
	public int eHeight = 0;
	private String iP = null;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statebattery);
        
        percent = (TextView)findViewById(R.id.enegyPercent);                      
        percentInfo = (TextView)findViewById(R.id.batteryStateInfo); 
        	    
        new Thread(){
        	@Override
        	public void run(){
			try {
				while(true){						
					runOnUiThread(new Runnable() {
						@Override
						public void run() {							
							try {							
								File myDir = new File(getFilesDir().getAbsolutePath());
								try {
									BufferedReader br = new BufferedReader(new FileReader(myDir + "/EvIp.txt"));
									iP = br.readLine();
									
								} catch (FileNotFoundException e1) {
									e1.printStackTrace();
								} catch (IOException e1) {
									e1.printStackTrace();
								} 				    
																
								Socket sock = new Socket(iP, 5554);
								OutputStream out = sock.getOutputStream();
								InputStream in = sock.getInputStream();
								
								BufferedReader br = new BufferedReader(new InputStreamReader(in));
								PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
								
								pw.println("REQUEST:BATTERYINFO.");
								pw.flush();
								int requestResult = Integer.parseInt(br.readLine());
								
								percent.setText(requestResult + " %");
								
								if(requestResult >= 90){
									percentInfo.setText("FULL");
									percentInfo.setTextColor(0xFF1EFF00);
								}
								else if((requestResult < 89) && (requestResult > 70)){
									percentInfo.setText("RARELY FULL");
									percentInfo.setTextColor(0xFF90FF00);
								}
								else if((requestResult < 69) && (requestResult > 40)){
									percentInfo.setText("MEDIUM");
									percentInfo.setTextColor(0xFFF6FF00);
								}
								else if((requestResult < 39) && (requestResult > 20)){
									percentInfo.setText("LOW");
									percentInfo.setTextColor(0xFFFF9600);
								}
								else if((requestResult < 19) && (requestResult > 5)){
									percentInfo.setText("CHARGE BATTERY!");
									percentInfo.setTextColor(0xFFFF5a00);
								}
								else if(requestResult < 4){
									percentInfo.setText("EMPTY!");
									percentInfo.setTextColor(0xFFFF0000);
								}
																
								pw.close();
								br.close();
								sock.close();
							
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					});
			    	sleep(2000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}    
        	}
        }.start();
        
        
    }
	

}
