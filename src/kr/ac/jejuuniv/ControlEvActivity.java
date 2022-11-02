package kr.ac.jejuuniv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ControlEvActivity extends Activity implements OnClickListener {

private String IP = null;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control);

        ToggleButton tbtnEngine = (ToggleButton) findViewById(R.id.btnEngine);
        tbtnEngine.setOnClickListener(this);
        ToggleButton tbtnAP = (ToggleButton) findViewById(R.id.btnAudioPlayer);
        tbtnAP.setOnClickListener(this);
        ToggleButton tbtnDoors = (ToggleButton) findViewById(R.id.btnDoors);
        tbtnDoors.setOnClickListener(this);
        ToggleButton tbtnTrunk = (ToggleButton) findViewById(R.id.btnTrunk);
        tbtnTrunk.setOnClickListener(this);
        ToggleButton tbtnHeadLigths = (ToggleButton) findViewById(R.id.btnHeadLigths);
        tbtnHeadLigths.setOnClickListener(this);
        ToggleButton tbtnInteriorLight = (ToggleButton) findViewById(R.id.btnInteriorLight);
        tbtnInteriorLight.setOnClickListener(this);
        ToggleButton tbtnAlarm = (ToggleButton) findViewById(R.id.btnAlarm);
        tbtnAlarm.setOnClickListener(this);
        ToggleButton tbtnGPS = (ToggleButton) findViewById(R.id.btnGPS);
        tbtnGPS.setOnClickListener(this);        
        
    }
	
	@Override
	public void onClick(View v) {

		ToggleButton tButton = (ToggleButton)v;
		int id = v.getId();
		
		File myDir = new File(getFilesDir().getAbsolutePath());
		try {
			BufferedReader br = new BufferedReader(new FileReader(myDir + "/EvIp.txt"));
			IP = br.readLine();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(IP != null){			
			try
			{
				Socket sock = new Socket(IP, 5554);
		    	OutputStream out = sock.getOutputStream();
		    	PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));    
		    	
				if (id == R.id.btnEngine) {
					if(tButton.isChecked())
					{				
						Toast.makeText(this, "ENGINE.START", Toast.LENGTH_SHORT).show();
						//pw.println("CONTROLCOMMAND:ENGINE.STANDBY.");											
				    	pw.println("CONTROLCOMMAND:ENGINE.START.");
				    	pw.flush();
					}
					else
					{
						Toast.makeText(this, "ENGINE.STOP", Toast.LENGTH_SHORT).show();
						pw.println("CONTROLCOMMAND:ENGINE.STOP.");
				    	pw.flush();
					}
				}
				else if (id == R.id.btnAudioPlayer) {
					if(tButton.isChecked())
					{		
						Toast.makeText(this, "AUDIOPLAYER.ON", Toast.LENGTH_SHORT).show();
						pw.println("CONTROLCOMMAND:AUDIOPLAYER.ON.");
				    	pw.flush();
					}
					else
					{
						Toast.makeText(this, "AUDIOPLAYER.OFF", Toast.LENGTH_SHORT).show();
						pw.println("CONTROLCOMMAND:AUDIOPLAYER.OFF.");
				    	pw.flush();
					}
				}
				else if (id == R.id.btnDoors) {
					if(tButton.isChecked())
					{		
						Toast.makeText(this, "DOORS.LOCK", Toast.LENGTH_SHORT).show();
						pw.println("CONTROLCOMMAND:DOORS.LOCK.");
				    	pw.flush();
					}
					else
					{
						Toast.makeText(this, "DOORS.UNLOCK", Toast.LENGTH_SHORT).show();
						pw.println("CONTROLCOMMAND:DOORS.UNLOCK.");
				    	pw.flush();
					}
				}
				else if (id == R.id.btnTrunk) {
					if(tButton.isChecked())
					{		
						Toast.makeText(this, "TRUNK.LOCK", Toast.LENGTH_SHORT).show();
						pw.println("CONTROLCOMMAND:TRUNK.LOCK.");
				    	pw.flush();
					}
					else
					{
						Toast.makeText(this, "TRUNK.UNLOCK", Toast.LENGTH_SHORT).show();
						pw.println("CONTROLCOMMAND:TRUNK.UNLOCK.");
				    	pw.flush();
					}
				}
				else if (id == R.id.btnHeadLigths) {
					if(tButton.isChecked())
					{		
						Toast.makeText(this, "HEADLIGHTS.ON", Toast.LENGTH_SHORT).show();
						pw.println("CONTROLCOMMAND:HEADLIGHTS.ON.");
				    	pw.flush();
					}
					else
					{
						Toast.makeText(this, "HEADLIGHTS.OFF", Toast.LENGTH_SHORT).show();
						pw.println("CONTROLCOMMAND:HEADLIGHTS.OFF.");
				    	pw.flush();
					}
				}
				else if (id == R.id.btnInteriorLight) {
					if(tButton.isChecked())
					{		
						Toast.makeText(this, "INTERIORLIGHT.ON", Toast.LENGTH_SHORT).show();
						pw.println("CONTROLCOMMAND:INTERIORLIGHT.ON.");
				    	pw.flush();
					}
					else
					{
						Toast.makeText(this, "INTERIORLIGHT.OFF", Toast.LENGTH_SHORT).show();
						pw.println("CONTROLCOMMAND:INTERIORLIGHT.OFF.");
				    	pw.flush();
					}
				}
				else if (id == R.id.btnAlarm) {
					if(tButton.isChecked())
					{		
						Toast.makeText(this, "SECURITYALARM.ON", Toast.LENGTH_SHORT).show();
						pw.println("CONTROLCOMMAND:SECURITYALARM.ON.");
				    	pw.flush();
					}
					else
					{
						Toast.makeText(this, "SECURITYALARM.OFF", Toast.LENGTH_SHORT).show();
						pw.println("CONTROLCOMMAND:SECURITYALARM.OFF.");
				    	pw.flush();
					}
				}
				else if (id == R.id.btnGPS) {
					if(tButton.isChecked())
					{		
						Toast.makeText(this, "GPS.ON", Toast.LENGTH_SHORT).show();
						pw.println("CONTROLCOMMAND:GPS.ON.");
				    	pw.flush();
					}
					else
					{
						Toast.makeText(this, "GPS.OFF", Toast.LENGTH_SHORT).show();
						pw.println("CONTROLCOMMAND:GPS.OFF.");
				    	pw.flush();
					}
				}	
				pw.close();
		    	sock.close();
			}
			catch(IOException ex)
			{
				Toast.makeText(this, ex.getLocalizedMessage(), Toast.LENGTH_LONG).show();
			}
		}
		else{
			Toast.makeText(this, "INPUT EV IP ADDRESS", Toast.LENGTH_LONG).show();
		}

	}

}
