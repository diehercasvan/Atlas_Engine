package com.example.diegoh.atlasengine;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.FrameLayout;

import com.example.diegoh.atlasengine.class_general.Animation_general;
import com.example.diegoh.atlasengine.class_general.General;
import com.example.diegoh.atlasengine.class_general.MenuFragment;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private FloatingActionButton[] floatingActionButtons;
    private FrameLayout frameBlackboard;
    private FloatingActionsMenu floatingActionsMenu;
    private Animation_general animation_general;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            loadGeneral();
        }
        loadToolbar();
        loadDrawerLayout();
        loadNavigationView();
        loadView();
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }

    private void loadGeneral() {

        General.CONTEXT = this;
    }

    private void loadToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    private void loadDrawerLayout() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //loadListMenu();
    }

    private void loadNavigationView() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void loadView() {
        animation_general = new Animation_general();
        floatingActionButtons = new FloatingActionButton[3];
        floatingActionButtons[0] = (FloatingActionButton) findViewById(R.id.btn_blackboard);
        floatingActionButtons[1] = (FloatingActionButton) findViewById(R.id.btn_share);
        floatingActionButtons[2] = (FloatingActionButton) findViewById(R.id.btn_search);
        for (int i = 0; i < floatingActionButtons.length; i++) {
            floatingActionButtons[i].setOnClickListener(this);
        }
        frameBlackboard = (FrameLayout) findViewById(R.id.content_background);
        floatingActionsMenu=(FloatingActionsMenu) findViewById(R.id.contentFloating);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_camera) {
            // Handle the camera action
            loadFragment(0);
        } else if (id == R.id.nav_gallery) {
            loadFragment(1);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_blackboard:
                selectedBlackboard();
                floatingActionsMenu.collapse();
                break;
            case R.id.btn_share:
                floatingActionsMenu.collapse();
                break;
            case R.id.btn_search:
                floatingActionsMenu.collapse();
                break;
        }
    }

    private void selectedBlackboard() {

        if (frameBlackboard.getVisibility() == View.VISIBLE) {
            frameBlackboard.setAnimation(animation_general.selectAnimation(1));
            frameBlackboard.setVisibility(View.GONE);

        } else {
            frameBlackboard.setVisibility(View.VISIBLE);
            frameBlackboard.setAnimation(animation_general.selectAnimation(0));
        }
    }

    private void loadFragment(int iTypeSelect) {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.content_view);

        View view = frameLayout;
        frameLayout.clearAnimation();
        HideContent(view);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_view, new MenuFragment().selectedFragment(iTypeSelect));
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    private void revealContent(View view) {
        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;
        int finalRadius = Math.max(view.getWidth(), view.getHeight());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator anim = null;
            anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
            view.setVisibility(View.VISIBLE);
            anim.start();
        }else{
            view.startAnimation(animation_general.selectAnimation(0));
        }

    }

    private void HideContent(final View view) {

        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;
        int initialRadius = view.getWidth();
        Animator anim = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0);
            anim.start();
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    view.setVisibility(View.INVISIBLE);
                    revealContent(view);
                }
            });
        }else{
            view.startAnimation(animation_general.selectAnimation(1));
        }

    }

}
