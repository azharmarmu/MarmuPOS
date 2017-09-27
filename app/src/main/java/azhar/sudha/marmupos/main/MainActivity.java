package azhar.sudha.marmupos.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import azhar.sudha.marmupos.R;
import azhar.sudha.marmupos.login.LoginActivity;
import azhar.sudha.marmupos.main.view.Items;
import azhar.sudha.marmupos.main.view.Receipts;
import azhar.sudha.marmupos.main.view.Sales;
import azhar.sudha.marmupos.utils.Constants;
import azhar.sudha.marmupos.utils.DBUtils;
import azhar.sudha.marmupos.utils.ImageUtils;

import static azhar.sudha.marmupos.utils.DBUtils.mAuth;

@SuppressWarnings({"deprecation", "ConstantConditions", "unchecked"})
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    View sales, receipts, items;
    public static int whereIam = 0;

    TextView headerItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        headerItemCount = (TextView) findViewById(R.id.tv_item_count);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Ticket");
        }

        sales = findViewById(R.id.sales_holder);
        receipts = findViewById(R.id.receipts_holder);
        items = findViewById(R.id.items_holder);

        sales.setVisibility(View.VISIBLE);
        new Sales().evaluate(MainActivity.this, sales);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navSetup(navigationView.getHeaderView(0));
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    private void navSetup(View headerView) {
        final ImageView ivProfilePic = headerView.findViewById(R.id.iv_profile_pic);
        final TextView tvBusinessName = headerView.findViewById(R.id.tv_business_name);
        final TextView tvEmail = headerView.findViewById(R.id.tv_email);

        DBUtils.userListDBRef.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    HashMap<String, Object> userDetails = (HashMap<String, Object>) dataSnapshot.getValue();
                    tvBusinessName.setText(String.valueOf(userDetails.get(Constants.BUSINESS_NAME)));
                    tvEmail.setText(String.valueOf(userDetails.get(Constants.EMAIL)));
                    if (userDetails.containsKey(Constants.PROFILE_PIC)) {
                        ImageUtils.loadCircleImageToViewByURL(MainActivity.this,
                                ivProfilePic,
                                Uri.parse(String.valueOf(userDetails.get(Constants.EMAIL))));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Error", databaseError.getMessage());
            }
        });
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
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_logout:
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.action_settings:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_sales:
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Ticket");
                    headerItemCount.setVisibility(View.VISIBLE);
                }
                whereIam = 0;
                break;
            case R.id.nav_receipts:
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Receipts");
                    headerItemCount.setVisibility(View.GONE);
                }
                whereIam = 1;
                break;
            case R.id.nav_items:
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Items");
                    headerItemCount.setVisibility(View.GONE);
                }
                whereIam = 2;
                break;
            case R.id.nav_feedbacks:
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Feedbacks");
                    headerItemCount.setVisibility(View.GONE);
                }
                whereIam = 3;
                break;
            case R.id.nav_settings:
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Settings");
                    headerItemCount.setVisibility(View.GONE);
                }
                whereIam = 4;
                break;
            case R.id.nav_support:
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Support");
                    headerItemCount.setVisibility(View.GONE);
                }
                whereIam = 5;
                break;
            case R.id.nav_share:

                break;
        }

        switchScreen();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void switchScreen() {
        sales.setVisibility(View.GONE);
        receipts.setVisibility(View.GONE);
        items.setVisibility(View.GONE);

        switch (whereIam) {
            case 0:
                sales.setVisibility(View.VISIBLE);
                new Sales().evaluate(MainActivity.this, sales);
                break;
            case 1:
                receipts.setVisibility(View.VISIBLE);
                new Receipts().evaluate(MainActivity.this, receipts);
                break;
            case 2:
                items.setVisibility(View.VISIBLE);
                new Items().evaluate(MainActivity.this, items);
                break;
            case 3:
                break;
            case 4:
                //sign out
                break;
        }
    }
}
