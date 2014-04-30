package com.wangbo.weview;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

public class AppActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 加载layout
		setContentView(R.layout.activity_app);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new VideoFragment()).commit();
		}
	}

	/**
	 * 在运行状态保存需要跨activity和fragment的基础数据
	 * 
	 * @param savedInstanceState
	 */
	public void onSavedInstanceState(Bundle savedInstanceState) {
	}

	public void findView() {
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.app, menu);
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

	@SuppressLint("ValidFragment")
	public static class VideoFragment extends Fragment {
		MediaController mediaController;
		public VideoFragment() {

		}

		public void startPlay(final VideoView videoView) {
			TimerTask task = new TimerTask() {

				@Override
				public void run() {
					videoView.start();
				}
			};

			Timer timer = new Timer();
			timer.schedule(task, 1000);
		}


		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_app, container, false);
			VideoView videoView_cemara1 = (VideoView) rootView.findViewById(R.id.videoView_cemara1);

			// 创建MediaController对象
			mediaController = new MediaController(getActivity());
			String cemaraOnePath = this.getString(R.string.cemara1_path);
			Uri cemaraOneUri = Uri.parse(cemaraOnePath);
			videoView_cemara1.setVideoURI(cemaraOneUri);
			videoView_cemara1.setMediaController(mediaController);
			mediaController.setMediaPlayer(videoView_cemara1);
			videoView_cemara1.start();
			videoView_cemara1.requestFocus();

			// Intent intent = new Intent(Intent.ACTION_VIEW);
			// intent.setDataAndType(cemaraOneUri, "audio/x-mpegurl");
			// startActivity(intent);
			return rootView;
		}
	}

}
