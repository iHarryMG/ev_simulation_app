package kr.ac.jejuuniv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EVCActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button btn_State = (Button)findViewById(R.id.btnStateEV);
        btn_State.setOnClickListener(this);
        
        Button btn_Control = (Button)findViewById(R.id.btnControlEV);
        btn_Control.setOnClickListener(this);
        
        Button btn_Setting = (Button)findViewById(R.id.btnSetting);
        btn_Setting.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.btnStateEV) {
			Intent i = new Intent(this, StateBatteryEvActivity.class);
			startActivity(i);
		}
		else if (id == R.id.btnControlEV) {
			Intent i = new Intent(this, ControlEvActivity.class);
			startActivity(i);
		}
		else if (id == R.id.btnSetting) {
			Intent i = new Intent(this, SettingActivity.class);
			startActivity(i);
		}
		
	}
}