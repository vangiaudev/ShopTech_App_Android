package vangiau.example.shoptech.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import vangiau.example.shoptech.R;

public class TroGiupActivity extends AppCompatActivity {

    LinearLayout llFeedback;
    ImageView    icFB, icYTB, icTWI, icGIT, icINS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tro_giup);
        llFeedback = findViewById(R.id.llFeedback);
        icFB       = findViewById(R.id.icFB);
        icYTB      = findViewById(R.id.icYTB);
        icGIT      = findViewById(R.id.icGIT);
        icINS      = findViewById(R.id.icINS);
        icTWI      = findViewById(R.id.icTWI);

        llFeedback.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "vangiau.recca@gmail.com"));
            startActivity(intent);
        });

        icFB.setOnClickListener(view -> goToURL("https://www.facebook.com/vangiau.recca"));
        icINS.setOnClickListener(view -> goToURL("https://www.instagram.com/vangiau.recca"));
        icTWI.setOnClickListener(view -> goToURL("https://twitter.com/vangiau_recca"));
        icYTB.setOnClickListener(view -> goToURL("https://www.youtube.com/channel/UCaxR1aMhRUId7JzXN3unNXQ?view_as=subscriber"));
        icGIT.setOnClickListener(view -> goToURL("https://github.com/vangiaurecca"));
    }

    private void goToURL(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

}