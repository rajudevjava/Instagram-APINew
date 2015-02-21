package com.demo.interview;

import org.json.JSONException;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class DemoActivity extends BaseDemoActivity {

	private GridView imageGridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		imageGridView = (GridView) findViewById(R.id.image_grid_view);
		retriveImages(this, imageGridView);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		imageGridView.setNumColumns(metrics.widthPixels / TILE_WIDTH);

		imageGridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				Intent intent = new Intent(DemoActivity.this,
						DemoZoomImagesActivity.class);

				try {

					String url = getInformation().getJSONArray("data")
							.getJSONObject(position).getJSONObject("images")
							.getJSONObject("standard_resolution")
							.getString("url");
					intent.putExtra("url", url);
				} catch (JSONException e) {
					intent.putExtra("url", "");
				}

				startActivity(intent);
			}
		});
		imageGridView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
			}

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}
		});

	}

}