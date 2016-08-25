package dija.secretjournal;

import com.spkd.secretjournal.sqlitehelper.LogTableHelper;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends ActionBarActivity  {

	
	EditText utitle,usummary;
	String ut,us;
	
	Button update;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);
	
		utitle=(EditText)findViewById(R.id.edittitletext);
		usummary=(EditText)findViewById(R.id.updateeditsummary);
		
		update=(Button)findViewById(R.id.update);
		
		Intent i=getIntent();
		final String id=i.getStringExtra("id");
		Toast.makeText(getApplicationContext(),id,Toast.LENGTH_LONG).show();
		
		LogTableHelper log=new LogTableHelper(getApplicationContext(),"log.db",null,1);
		log.getReadableDatabase();
		
		Cursor c=log.fetchParticular(id);
		c.moveToFirst();
	//	Toast.makeText(getApplicationContext(),"The Fetch Count is"+ c.getString(1)+c.getString(2), Toast.LENGTH_LONG).show();
	
		//	ut=c.getString(0);
		//us=c.getString(1);
		
		utitle.setText(c.getString(1));
		usummary.setText(c.getString(2));
	
		ut=utitle.getText()+"";
		us=usummary.getText()+"";
		
		update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LogTableHelper log=new LogTableHelper(getApplicationContext(),"log.db",null,1);
				log.getWritableDatabase();
				if(log.updateLog(id,utitle.getText()+"",usummary.getText()+""))
				{
					finish(); 
					Intent i=new Intent(getApplicationContext(),MenuScreen.class);
					startActivity(i);
				}
				}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onBackPressed()
	{
		finish(); 
		Intent i=new Intent(getApplicationContext(),MenuScreen.class);
		startActivity(i);
	}


}
