package vangiau.example.shoptech.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.jem.liquidswipe.LiquidSwipeViewPager;

import vangiau.example.shoptech.R;

public class MainActivity extends AppCompatActivity {

    private static final int NUM_PAGE = 1;
    ImageView           imgMHC;
    LottieAnimationView lavMHC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);

        imgMHC                         = findViewById(R.id.imgMHC);
        lavMHC                         = findViewById(R.id.lavMHC);
        LiquidSwipeViewPager viewPager = findViewById(R.id.pager);
        ScreenSLidePagerAdapter pagerAdapter = new ScreenSLidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);


        Thread thread = new Thread(() -> {
            try {
                imgMHC.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
                lavMHC.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
                Thread.sleep(5500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                Intent intent = new Intent(MainActivity.this, DangNhapActivity.class);
                startActivity(intent);
            }
        });
        thread.start();
    }

    private static class ScreenSLidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSLidePagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new IntroFragment();
            }
            return null;
        }
        @Override
        public int getCount() {
            return NUM_PAGE;
        }
    }
}