package vangiau.example.shoptech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vangiau.example.shoptech.R;
import vangiau.example.shoptech.adapter.TinTucAdapter;
import vangiau.example.shoptech.general.XMLDOMParser;
import vangiau.example.shoptech.model.TinTuc;

public class TinTucActivity extends AppCompatActivity {
    ListView          lvTieuDe;
    ArrayList<String> arrayTitle, arrayLink, arrayImage;
    Toolbar           tbTinTuc;
    TinTucAdapter     customAdapter;
    ArrayList<TinTuc> arrayTinTuc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_tuc);

        AddControls();
        ActionToolbar();
        AddEvent();
    }

    private void AddEvent() {
        lvTieuDe.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(TinTucActivity.this, WebViewActivity.class);
            intent.putExtra("linkTinTuc", arrayLink.get(i));
            startActivity(intent);
        });
    }

    private void AddControls() {
        arrayTinTuc = new ArrayList<>();
        lvTieuDe    = findViewById(R.id.lvTieuDe);
        tbTinTuc    = findViewById(R.id.tbTinTuc);
        arrayTitle  = new ArrayList<>();
        arrayLink   = new ArrayList<>();
        arrayImage  = new ArrayList<>();
        new ReadRSS().execute("https://vnexpress.net/rss/so-hoa.rss");
    }

    private void ActionToolbar() {
        setSupportActionBar(tbTinTuc);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        tbTinTuc.setNavigationOnClickListener(view -> finish());
    }

    @SuppressLint("StaticFieldLeak")
    private class ReadRSS extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    content.append(line);
                }
                bufferedReader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLDOMParser parser =  new XMLDOMParser();
            Document document   = parser.getDocument(s);
            NodeList nodeList   = document.getElementsByTagName("item");
            NodeList nodeListDescription = document.getElementsByTagName("description");
            String hinhAnh  = "";
            String tieuDe;
            String link;
            String pubDate;

            for (int i = 0; i<nodeList.getLength(); i++){
                String cdata = nodeListDescription.item(i + 1).getTextContent();
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);
                if(matcher.find()){
                    hinhAnh = matcher.group(1);
                    //Log.d("hinhAnh", hinhAnh + "........." + i);
                }
                Element element = (Element) nodeList.item(i);
                tieuDe          = parser.getValue(element, "title");
                link            = parser.getValue(element, "link");
                pubDate         = parser.getValue(element, "pubDate");
                arrayLink.add(parser.getValue(element, "link"));
                arrayTinTuc.add(new TinTuc(tieuDe, link, hinhAnh, pubDate));
            }
            customAdapter = new TinTucAdapter(TinTucActivity.this, android.R.layout.simple_list_item_1, arrayTinTuc);
            lvTieuDe.setAdapter(customAdapter);
            super.onPostExecute(s);
        }
    }
}