package gigahex.sportnewsrss.screens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import gigahex.sportnewsrss.R;
public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        WebView brower = (WebView) findViewById(R.id.webBrowser);
        brower.setWebViewClient(new MyWebViewClient());
        Bundle arguments  = getIntent().getExtras();
        if(arguments != null){
            String link = arguments.getString("link");
            brower.loadUrl(link);
        }
    }
}
