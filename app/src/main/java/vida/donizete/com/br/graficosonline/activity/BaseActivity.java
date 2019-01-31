package vida.donizete.com.br.graficosonline.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import vida.donizete.com.br.graficosonline.R;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    protected void setUpToolbar() {
        Toolbar t = findViewById(R.id.include_toolbar);

        if (t != null) {
            setSupportActionBar(t);
        }
    }

    protected void setUpMenuIcon() {
        ActionBar ab = getSupportActionBar();

        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        setUpNavDrawer();
    }

    private void setUpNavDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout);

        if (drawerLayout != null) {
            NavigationView navigationView = findViewById(R.id.navigation_view);

            if (navigationView != null) {
                navigationView.setNavigationItemSelectedListener(this);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setCheckable(true);
        drawerLayout.closeDrawers();
        onNavItemSelected(menuItem);
        return false;
    }

    private void onNavItemSelected(MenuItem menuItem) {
        Intent intent = null;
        switch (menuItem.getItemId()) {
            case R.id.createPieChartActivity:
                intent = new Intent(BaseActivity.this, CreatePieChartActivity.class);
                startActivity(intent);
                break;

            case R.id.newActivity:
                intent = new Intent(BaseActivity.this, Main2Activity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout != null)
            if (drawerLayout.isDrawerOpen(GravityCompat.START))
                drawerLayout.closeDrawers();

            else
                super.onBackPressed();

    }
}
