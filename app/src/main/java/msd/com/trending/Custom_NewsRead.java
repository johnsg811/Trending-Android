package msd.com.trending;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.app.Activity;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Custom_NewsRead extends AppCompatActivity {

    WebView webView;

    private class MyWebViewClient extends WebViewClient{
        public boolean urlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom__news_read);

        webView=(WebView)findViewById(R.id.webView1);
        webView.setWebViewClient(new MyWebViewClient());
        openUrl();

        /*String url = "https://www.google.com/";
        WebView view = (WebView) this.findViewById(R.id.webView1);
        view.getSettings().setJavaScriptEnabled(true);
        view.loadUrl(url);*/


        //WebView webView = (WebView) findViewById(R.id.webView1);
        //webView.loadUrl("http://www.bbc.com");

    }

    private void openUrl()
    {
        webView.loadUrl("https://www.independent.ie/");
        webView.requestFocus();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_custom__news_read, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
