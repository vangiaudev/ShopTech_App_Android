package vangiau.example.shoptech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Objects;

import vangiau.example.shoptech.R;

public class WebViewActivity extends AppCompatActivity {
    WebView wvTinTuc;
    Toolbar tbNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_tin_tuc);

        AddControls();
        ActionToolbar();
        Intent intent = getIntent();
        String link   = intent.getStringExtra("linkTinTuc");
        wvTinTuc.loadUrl(link);
        wvTinTuc.setWebViewClient(new WebViewClient());
    }

    private void AddControls() {
        wvTinTuc = findViewById(R.id.wvTinTuc);
        tbNews   = findViewById(R.id.tbNews);
    }

    private void ActionToolbar() {
        setSupportActionBar(tbNews);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        tbNews.setNavigationOnClickListener(view -> finish());
    }
}