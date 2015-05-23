package marciosn.sampleadmob_android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class SampleADMOB extends Activity {
	private InterstitialAd interstitial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample_admob);
		
		// os métodos são chamados no oncreate para que a aplicação possa carregar os banners
		carregarADS_Banner();
		carregarADS_Interticial();
		
	}
	
	public void carregarADS_Banner(){
		try {
			RelativeLayout banner = (RelativeLayout) findViewById(R.id.sample_banner);
			
			AdView ads = new AdView(this);
			ads.setAdSize(AdSize.SMART_BANNER);
			
			// insira o código do banner aqui
			
			ads.setAdUnitId("ca-app-pub-4967173191053190/8475327465");
			AdRequest request = new AdRequest.Builder().build();
			
			//seta o device como despositivo de teste para impedir que você clique nos bannes por acidente
			request.isTestDevice(this);
			
			banner.addView(ads);			
			ads.loadAd(request);
			
		} catch (Exception e) {
			Log.e("carregarADS_Banner", e.getMessage());
		}
	}
	
	 public void carregarADS_Interticial(){
			try {
				interstitial = new InterstitialAd(SampleADMOB.this);
				interstitial.setAdUnitId("ca-app-pub-4967173191053190/9952060664");
				
				AdRequest request = new AdRequest.Builder().build();
				request.isTestDevice(this);
				
				interstitial.loadAd(request);
				
			} catch (Exception e) {
				Log.e("carregarADS_Interticial", e.getMessage());
			}
	}
	 public void displayInterstitial() {
		  if (interstitial.isLoaded()) {
			  interstitial.show();
		   }
	 }
	 
	 @Override
	protected void onDestroy() {
		// Quando a aplicação for fechada um ads intersticial será carregado
		 displayInterstitial();
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sample_admob, menu);
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
}
