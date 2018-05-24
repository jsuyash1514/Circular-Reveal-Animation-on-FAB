package com.example.suyash.circularrevealanimation;

import android.animation.Animator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewAnimationUtils;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    Animator animator;
    RelativeLayout card;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        card = (RelativeLayout)findViewById(R.id.card);
        card.setVisibility(View.INVISIBLE);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_visibility_off_black_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewCard();
                if (card.getVisibility() == View.INVISIBLE) {
                    fab.setImageResource(R.drawable.ic_visibility_off_black_24dp);
                }
                else{
                    fab.setImageResource(R.drawable.ic_visibility_black_24dp);
                }
            }
        });
    }

    private void viewCard() {
        if (card.getVisibility() == View.INVISIBLE){
            int x = card.getRight() ;
            int y = card.getTop() ;

            float startRad = 0;
            float endRad = (float)Math.max(card.getHeight(), card.getWidth())*2;
            animator = ViewAnimationUtils.createCircularReveal(card, x, y, startRad, endRad);
            card.setVisibility(View.VISIBLE);
            animator.setDuration(2000);
            animator.start();
        }

        else{
            int x = card.getRight() ;
            int y = card.getTop() ;

            float endRad = 0;
            float startRad = (float)Math.max(card.getHeight(), card.getWidth())*2;
            animator = ViewAnimationUtils.createCircularReveal(card, x, y, startRad, endRad);
            animator.setDuration(2000);
            animator.start();
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    card.setVisibility(View.INVISIBLE);
                    fab.setImageResource(R.drawable.ic_visibility_off_black_24dp);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
