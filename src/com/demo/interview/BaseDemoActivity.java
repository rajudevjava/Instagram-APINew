package com.demo.interview;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;

public abstract  class BaseDemoActivity extends Activity implements DemoConstants {
	
	private JSONObject information;
	
	public JSONObject getInformation() {
		return information;
	}


	public void setInformation(JSONObject information) {
		this.information = information;
	}


	protected void retriveImages(Context context,GridView imageGridView){
		new DemoImagesRetrival(URL, this, imageGridView).execute();
	}
	
	
	public class DemoImagesRetrival extends AsyncTask<Void, Void, Void> {
		private final String instagramUrl;
		private final Context ctx;
		private final  GridView imageGridView;
		private int number = 0;

		public DemoImagesRetrival(String instagramUrl, Context ctx,GridView imageGridView) {
			super();
			this.instagramUrl = instagramUrl;
			this.ctx = ctx;
			this.imageGridView = imageGridView;
		}

		@Override
		protected Void doInBackground(Void... params) {
			information = WebView.makeWebServiceCall(instagramUrl);
			setInformation(information);
			return null;
		}

		@Override
		protected void onPostExecute(Void unused) {
			imageGridView.setAdapter(new DemoImagesAdapter(ctx,
					information, number));
		}

	}

}
