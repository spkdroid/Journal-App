package dija.secretjournal;


import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import dija.secretjournal.sqlitehelper.LogTableHelper;

public class ViewActivity extends ActionBarActivity {

	TextView storytitle,summary;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);
	
	storytitle=(TextView)findViewById(R.id.storytitle);
	summary=(TextView)findViewById(R.id.summary);
	
	Intent i=getIntent();
	final String id=i.getStringExtra("id");
	Toast.makeText(getApplicationContext(),id,Toast.LENGTH_LONG).show();
	
	LogTableHelper log=new LogTableHelper(getApplicationContext(),"log.db",null,1);
	log.getReadableDatabase();
	
	Cursor c=log.fetchParticular(id);
	c.moveToFirst();
	
	storytitle.setText(c.getString(1));
	summary.setText(c.getString(2));
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view, menu);
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
