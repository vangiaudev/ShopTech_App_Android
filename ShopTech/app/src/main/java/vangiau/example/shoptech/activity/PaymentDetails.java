package vangiau.example.shoptech.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import vangiau.example.shoptech.R;

public class PaymentDetails extends AppCompatActivity {
    TextView txtID, txtAmount, txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        txtID = findViewById(R.id.txtID_Pay);
        txtAmount = findViewById(R.id.txtAmount_Pay);
        txtStatus = findViewById(R.id.txtStatus_Pay);

        Intent intent = getIntent();

        try {
            JSONObject jsonObject = new JSONObject(Objects.requireNonNull(intent.getStringExtra("PaymentDetails")));
            showDetails(jsonObject.getJSONObject("response"), intent.getStringExtra("PaymentAmount"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("SetTextI18n")
    private void showDetails(JSONObject response, String paymentAmount) {
        try {
            txtID.setText(response.getString("id"));
            txtStatus.setText(response.getString("state"));
            txtID.setText("$" + paymentAmount);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}