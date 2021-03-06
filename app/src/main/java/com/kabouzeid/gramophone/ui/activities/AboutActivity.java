package com.kabouzeid.gramophone.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.internal.ThemeSingleton;
import com.kabouzeid.appthemehelper.ThemeStore;
import com.kabouzeid.gramophone.ui.activities.base.AbsBaseActivity;
import com.kabouzeid.gramophone.ui.activities.intro.AppIntroActivity;

import org.omnirom.gramophone.R;

import de.psdev.licensesdialog.LicensesDialog;

/**
 * @author Karim Abou Zeid (kabouzeid)
 */
@SuppressWarnings("FieldCanBeLocal")
public class AboutActivity extends AbsBaseActivity implements View.OnClickListener {

    private static String GITHUB = "https://github.com/kabouzeid/Phonograph";

    private static String GOOGLE_PLUS = "https://google.com/+KarimAbouZeid23697";
    private static String TWITTER = "https://twitter.com/karim23697";
    private static String WEBSITE = "https://kabouzeid.com/";

    private static String GOOGLE_PLUS_COMMUNITY = "https://plus.google.com/u/0/communities/106227738496107108513";
    private static String TRANSLATE = "https://phonograph.oneskyapp.com/collaboration/project?id=26521";
    private static String RATE_ON_GOOGLE_PLAY = "https://play.google.com/store/apps/details?id=com.kabouzeid.gramophone";

    private static String AIDAN_FOLLESTAD_GOOGLE_PLUS = "https://google.com/+AidanFollestad";
    private static String AIDAN_FOLLESTAD_GITHUB = "https://github.com/afollestad";

    private static String MICHAEL_COOK_GOOGLE_PLUS = "https://plus.google.com/102718493746376292361";
    private static String MICHAEL_COOK_WEBSITE = "http://cookicons.co/";

    private static String MAARTEN_CORPEL_GOOGLE_PLUS = "https://google.com/+MaartenCorpel";

    private static String ALEKSANDAR_TESIC_GOOGLE_PLUS = "https://google.com/+aleksandarte??i??";

    Toolbar toolbar;
    TextView appVersion;
    LinearLayout intro;
    LinearLayout licenses;
    LinearLayout writeAnEmail;
    LinearLayout addToGooglePlusCircles;
    LinearLayout followOnTwitter;
    LinearLayout forkOnGitHub;
    LinearLayout visitWebsite;
    LinearLayout reportBugs;
    LinearLayout joinGooglePlusCommunity;
    LinearLayout translate;
    LinearLayout donate;
    AppCompatButton aidanFollestadGooglePlus;
    AppCompatButton aidanFollestadGitHub;
    AppCompatButton michaelCookGooglePlus;
    AppCompatButton michaelCookWebsite;
    AppCompatButton maartenCorpelGooglePlus;
    AppCompatButton aleksandarTesicGooglePlus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setDrawUnderStatusbar(true);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appVersion = (TextView) findViewById(R.id.app_version);
        intro = (LinearLayout) findViewById(R.id.intro);
        licenses = (LinearLayout) findViewById(R.id.licenses);
        writeAnEmail = (LinearLayout) findViewById(R.id.write_an_email);
        addToGooglePlusCircles = (LinearLayout) findViewById(R.id.add_to_google_plus_circles);
        followOnTwitter = (LinearLayout) findViewById(R.id.follow_on_twitter);
        forkOnGitHub = (LinearLayout) findViewById(R.id.fork_on_github);
        visitWebsite = (LinearLayout) findViewById(R.id.visit_website);
        reportBugs = (LinearLayout) findViewById(R.id.report_bugs);
        joinGooglePlusCommunity = (LinearLayout) findViewById(R.id.join_google_plus_community);
        translate = (LinearLayout) findViewById(R.id.translate);
        donate = (LinearLayout) findViewById(R.id.donate);
        aidanFollestadGooglePlus = (AppCompatButton) findViewById(R.id.aidan_follestad_google_plus);
        aidanFollestadGitHub = (AppCompatButton) findViewById(R.id.aidan_follestad_git_hub);
        michaelCookGooglePlus = (AppCompatButton) findViewById(R.id.michael_cook_google_plus);
        michaelCookWebsite = (AppCompatButton) findViewById(R.id.michael_cook_website);
        maartenCorpelGooglePlus = (AppCompatButton) findViewById(R.id.maarten_corpel_google_plus);
        aleksandarTesicGooglePlus = (AppCompatButton) findViewById(R.id.aleksandar_tesic_google_plus);

        setStatusbarColorAuto();
        setNavigationbarColorAuto();

        setUpViews();
    }

    private void setUpViews() {
        setUpToolbar();
        setUpAppVersion();
        setUpOnClickListeners();
    }

    private void setUpToolbar() {
        toolbar.setBackgroundColor(ThemeStore.primaryColor(this));
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setUpAppVersion() {
        appVersion.setText(getCurrentVersionName(this));
    }

    private void setUpOnClickListeners() {
        intro.setOnClickListener(this);
        licenses.setOnClickListener(this);
//         addToGooglePlusCircles.setOnClickListener(this);
        followOnTwitter.setOnClickListener(this);
        forkOnGitHub.setOnClickListener(this);
        visitWebsite.setOnClickListener(this);
        reportBugs.setOnClickListener(this);
        writeAnEmail.setOnClickListener(this);
        joinGooglePlusCommunity.setOnClickListener(this);
        translate.setOnClickListener(this);
        donate.setOnClickListener(this);
        aidanFollestadGooglePlus.setOnClickListener(this);
        aidanFollestadGitHub.setOnClickListener(this);
        michaelCookGooglePlus.setOnClickListener(this);
        michaelCookWebsite.setOnClickListener(this);
        maartenCorpelGooglePlus.setOnClickListener(this);
        aleksandarTesicGooglePlus.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static String getCurrentVersionName(@NonNull final Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "0.0.0";
    }

    @Override
    public void onClick(View v) {
        if (v == licenses) {
            showLicenseDialog();
        } else if (v == intro) {
            startActivity(new Intent(this, AppIntroActivity.class));
        } else if (v == addToGooglePlusCircles) {
            openUrl(GOOGLE_PLUS);
        } else if (v == followOnTwitter) {
            openUrl(TWITTER);
        } else if (v == forkOnGitHub) {
            openUrl(GITHUB);
        } else if (v == visitWebsite) {
            openUrl(WEBSITE);
        } else if (v == writeAnEmail) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:contact@kabouzeid.com"));
            intent.putExtra(Intent.EXTRA_EMAIL, "contact@kabouzeid.com");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Phonograph");
            startActivity(Intent.createChooser(intent, "E-Mail"));
        } else if (v == joinGooglePlusCommunity) {
            openUrl(GOOGLE_PLUS_COMMUNITY);
        } else if (v == translate) {
            openUrl(TRANSLATE);
        } else if (v == aidanFollestadGooglePlus) {
            openUrl(AIDAN_FOLLESTAD_GOOGLE_PLUS);
        } else if (v == aidanFollestadGitHub) {
            openUrl(AIDAN_FOLLESTAD_GITHUB);
        } else if (v == michaelCookGooglePlus) {
            openUrl(MICHAEL_COOK_GOOGLE_PLUS);
        } else if (v == michaelCookWebsite) {
            openUrl(MICHAEL_COOK_WEBSITE);
        } else if (v == maartenCorpelGooglePlus) {
            openUrl(MAARTEN_CORPEL_GOOGLE_PLUS);
        } else if (v == aleksandarTesicGooglePlus) {
            openUrl(ALEKSANDAR_TESIC_GOOGLE_PLUS);
        }
    }

    private void openUrl(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    private void showLicenseDialog() {
        new LicensesDialog.Builder(this)
                .setNotices(R.raw.notices)
                .setTitle(R.string.licenses)
                .setNoticesCssStyle(getString(R.string.license_dialog_style)
                        .replace("{bg-color}", String.format("%06x", this.getResources().getColor(R.color.popupBackground) & 0x00ffffff))
                        .replace("{text-color}", String.format("%06x", this.getResources().getColor(R.color.textColorPrimary) & 0x00ffffff))
                        .replace("{license-bg-color}", String.format("%06x", this.getResources().getColor(R.color.popupBackground) & 0x00ffffff))
                )
                .setIncludeOwnLicense(true)
                .setThemeResourceId(R.style.LicenseDialogTheme)
                .build()
                .showAppCompat();
    }
}
