package kr.ac.jejuuniv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingActivity extends Activity implements OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);     
        
        Button btn_Save = (Button)findViewById(R.id.btnSaveEvIp);
        btn_Save.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.btnSaveEvIp) {
			
			EditText ip = (EditText)findViewById(R.id.txtEvIP);
			String EVip = null;
			EVip = ip.getText().toString();
			
			File myDir = new File(getFilesDir().getAbsolutePath());
			if(EVip != null){	
				try {

					FileWriter fw = new FileWriter(myDir + "/EvIp.txt");
					fw.write(EVip);
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		        Toast.makeText(this, "IP Saved on "+ myDir.toString()+"/"+EVip, Toast.LENGTH_SHORT).show();
			}
			else
				Toast.makeText(this, "Enter EV IP address!", Toast.LENGTH_LONG).show();
				
		}
		
	}
}