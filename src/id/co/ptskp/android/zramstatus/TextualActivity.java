package id.co.ptskp.android.zramstatus;

import java.text.NumberFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class TextualActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textual);
        recalculate();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case R.id.action_reload:
    		recalculate();
    		return true;
    	case R.id.action_exit:
    		finish();
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
    
    private void recalculate() {
    	
    	Zram zram = new Zram();
    	
    	try {
    		NumberFormat nf = NumberFormat.getNumberInstance();
    		
    		TextView tvZramDiskSize = (TextView) findViewById(R.id.zram_disk_size);
    		tvZramDiskSize.setText(
        			getResources().getString(R.string.zram_disk_size) 
        			+ " "
        			+ nf.format(zram.getDiskSize())
        			+ " bytes"
    			);
    		
    		TextView tvZramCompressedDataSize = (TextView) findViewById(R.id.zram_compressed_data_size);
    		tvZramCompressedDataSize.setText(
        			getResources().getString(R.string.zram_compressed_data_size) 
        			+ " "
        			+ nf.format(zram.getCompressedDataSize())
        			+ " bytes"
    			);
    		
    		TextView tvZramOriginalDataSize = (TextView) findViewById(R.id.zram_original_data_size);
    		tvZramOriginalDataSize.setText(
        			getResources().getString(R.string.zram_original_data_size) 
        			+ " "
        			+ nf.format(zram.getOriginalDataSize())
        			+ " bytes"
    			);
    		
    		TextView tvZramMemUsedTotal = (TextView) findViewById(R.id.zram_mem_used_total);
    		tvZramMemUsedTotal.setText(
        			getResources().getString(R.string.zram_mem_used_total) 
        			+ " "
        			+ nf.format(zram.getMemUsedTotal())
        			+ " bytes"
    			);
    		
    		final int compressionRatio = Math.round(zram.getCompressionRatio() * 100);
    		ProgressBar pbZramCompressionRatio = (ProgressBar) findViewById(R.id.zram_compression_ratio_bar);
    		pbZramCompressionRatio.setProgress(compressionRatio);
    		
    		TextView tvZramCompressionRatio = (TextView) findViewById(R.id.zram_compression_ratio);
    		tvZramCompressionRatio.setText(
        			getResources().getString(R.string.zram_compression_ratio) 
        			+ " "
        			+ Integer.toString(compressionRatio)
        			+ "%"
    			);
    		
    		final int usedRatio = Math.round(zram.getUsedRatio() * 100);
    		ProgressBar pbZramUsedRatio = (ProgressBar) findViewById(R.id.zram_used_ratio_bar);
    		pbZramUsedRatio.setProgress(usedRatio);
    		
    		TextView tvZramUsedRatio = (TextView) findViewById(R.id.zram_used_ratio);
    		tvZramUsedRatio.setText(
        			getResources().getString(R.string.zram_used_ratio) 
        			+ " "
        			+ Integer.toString(usedRatio)
        			+ "%"
    			);
    		
    		
    		
    	} catch (Exception e) {
    		Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    		finish();
    	}
    	
    }
    
}