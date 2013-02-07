package edu.research.p2ptestapp.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;
import edu.research.p2ptestapp.R;

@SuppressLint("SetJavaScriptEnabled")
public class WebFragment extends Fragment {

	private WebView mWebView;
	private EditText mUrlBar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View main = inflater.inflate(R.layout.web_browser, container, false);
		mWebView = (WebView) main.findViewById(R.id.main_webview);
		mUrlBar = (EditText) main.findViewById(R.id.main_webview_text);
		mWebView.getSettings().setJavaScriptEnabled(true);
		
		mWebView.loadUrl("http://www.google.com");
		mUrlBar.setText("http://www.google.com");
		
		mWebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				getActivity().setProgress(newProgress * 1000);
			}
		});
		
		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				Toast.makeText(getActivity(), "Oh no!" + description,
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
			}
		});

		// Actions for the browser (i.e. back, forward,
		mUrlBar.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {

				boolean actionTrue = false;
				if (actionId == EditorInfo.IME_ACTION_GO) {
					mWebView.loadUrl(mUrlBar.getText().toString());
					actionTrue = true;
				} else if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					mWebView.loadUrl(mUrlBar.getText().toString());
					actionTrue = true;
				} else if (actionId == EditorInfo.IME_ACTION_PREVIOUS) {

				}

				return actionTrue;
			}
		});

		return main;
	}
}
