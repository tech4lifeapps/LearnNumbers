package com.tech4lifeapps.numbers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


public class MainActivity extends Activity {

    // THE NUMBERS
    public final String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18",
            "19", "20", "30", "40", "50", "60", "70", "80", "90", "100"};

    // ENGLISH
    public final static String[] textLabels_en = {"zero", "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen",
            "sixteen", "seventeen", "eighteen", "nineteen",
            "twenty", "thirty", "forty", "fifty", "sixty",
            "seventy", "eighty", "ninety", "one-hundred"};

    // INDONESIAN
    public final static String[] textLabels_in = {"nol", "satu", "dua", "tiga", "empat",
            "lima", "enam", "tujuh", "delapan", "sembilan", "sepuluh",
            "sebelas", "duabelas", "tigabelas", "empatbelas", "limabelas",
            "enambelas", "tujuhbelas", "delapanbelas", "sembilanbelas",
            "duapuluh", "tigapuluh", "empatpuluh", "limapuluh", "enampuluh",
            "tujuhpuluh", "delapanpuluh", "sembilanpuluh", "seratus"};

    // KHMER
    public final static String[] textLabels_kh = {"soon", "moo-ay", "bpee", "bay", "boo-un",
            "bprum", "bprum-moo-ay", "bprum-bpee (bprum-bpoel)", "bprum-bay",
            "bprum-boo-un", "dop", "dop-moo-ay", "dop-bpee", "dop-bay", "dop-boo-un",
            "dop-bprum", "dop-bprum\nmoo-ay", "dop-bprum\nbpee", "dop-bprum-bay",
            "dop-bprum\nboo-un", "m'pay", "saam seup", "sai seup", "haa seup", "hok seup",
            "jeut seup", "bpait seup", "gao seup", "moo-ay roy"};

    // BURMESE
    public final static String[] textLabels_mm = {"thone-nya", "tit", "hnit", "thone:", "lay:",
            "nga:", "chaut", "khon-hnit", "shit", "ko:", "ta-'hse",
            "'hse-tit", "'hse-hnit", "'hse-thone:", "'hse-lay:",
            "'hse-nga:", "'hse-chaut", "'hse-khon-hnit", "'hse-shit",
            "'hse-ko:", "hnit-'hse", "thone:-'hse", "lay:-'hse", "nga:-'hse",
            "chaut-'hse", "khone-'hse", "shit-'hse", "ko:-'hse", "ta-ya:"};

    // THAI
    public final static String[] textLabels_th = {"soon", "nueng", "soong", "saam", "sii",
            "haa", "hok", "djet", "bpaet", "gaao", "sip", "sip-et", "sip-soong",
            "sip-saam", "sip-sii", "sip-haa", "sip-hok", "sip-djet", "sip-bpaet",
            "sip-gaao", "yee-sip", "saam-sip", "sii-sip", "haa-sip", "hok-sip",
            "djet-sip", "bpaet-sip", "gaao-sip", "nueng-rooy"};


    // DEFINES WHICH DIRECTORY CONTAINS OUR SOUND CLIPS
    public static String ASSETS_DIRECTORY;

    // DEFINES WHICH STRING ARRAY WILL BE USED TO DISPLAY OUR TEXT LABELS
    public static String[] TEXT_LABELS;

    private List<Sound> mySounds = new ArrayList<Sound>();
    private MediaPlayer mp = new MediaPlayer();

    GridView gridView;

    // Create an ArrayList object of items by calling the constructor
    private ArrayList<Item> gridArray = new ArrayList<Item>();
    CustomGridViewAdapter customGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // https://www.youtube.com/watch?v=SGx03Uqn9JA LESSON 57
        // ACCESS SHARED PREFERENCES
        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());
        String values = getPrefs.getString("list", "1");

/*
        // METHOD I
		if (values.contentEquals("1")) {
			ASSETS_DIRECTORY = "numbers_english";
			TEXT_LABELS = textLabels_en;

		} else if (values.contentEquals("2")) {
			ASSETS_DIRECTORY = "indonesian_numbers";
			TEXT_LABELS = textLabels_in;

		} else if (values.contentEquals("3")) {
			ASSETS_DIRECTORY = "khmer_numbers";
			TEXT_LABELS = textLabels_kh;

		} else if (values.contentEquals("4")) {
			ASSETS_DIRECTORY = "burmese_numbers";
			TEXT_LABELS = textLabels_mm;

		} else if (values.contentEquals("5")) {
			ASSETS_DIRECTORY = "thai_numbers";
			TEXT_LABELS = textLabels_th;
		}
*/

        // METHOD II, using a switch statement
        assignDirectoriesLabels(values);

        // POPULATE mySounds List
        final AssetManager assets = getBaseContext().getAssets();
        try {
            final String[] fileNames = assets.list(ASSETS_DIRECTORY);
            for (int i = 0; i < fileNames.length; i++) {
                mySounds.add(new Sound(fileNames[i]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

		/*
		 * // set up gridArray items Bitmap homeIcon =
		 * BitmapFactory.decodeResource(this.getResources(), R.drawable.home);
		 * Bitmap userIcon = BitmapFactory.decodeResource(this.getResources(),
		 * R.drawable.personal);
		 * 
		 * // gridArray.add(new Item(userIcon,"1000","one thousand")); //
		 * gridArray.add(new Item(homeIcon,"10000","ten thousand")); //
		 * gridArray.add(new Item(userIcon,"100000","one hundred thousand")); //
		 * gridArray.add(new Item(homeIcon,"1000000","one million"));
		 */

        populateGridView();

        gridView = (GridView) findViewById(R.id.gridView1);
        customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid,
                gridArray);
        gridView.setAdapter(customGridAdapter);
        registerClickCallback();
    }

    // METHOD II, using a switch statement
    public static void assignDirectoriesLabels(String value) {
        char firstLetter = value.charAt(0);

        switch (firstLetter) {
            case '1':
                ASSETS_DIRECTORY = "numbers_english";
                TEXT_LABELS = textLabels_en;
                break;
            case '2':
                ASSETS_DIRECTORY = "indonesian_numbers";
                TEXT_LABELS = textLabels_in;
                break;
            case '3':
                ASSETS_DIRECTORY = "khmer_numbers";
                TEXT_LABELS = textLabels_kh;
                break;
            case '4':
                ASSETS_DIRECTORY = "burmese_numbers";
                TEXT_LABELS = textLabels_mm;
                break;
            case '5':
                ASSETS_DIRECTORY = "thai_numbers";
                TEXT_LABELS = textLabels_th;
            default:
                break;
        }
    }

    private void populateGridView() {

        Bitmap homeIcon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.home);
        Bitmap userIcon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.personal);

        Bitmap icon;

        for (int i = 0; i < numbers.length; i++) {
            if ((i % 2) == 0) {
                icon = homeIcon;
                Log.d("MAIN ACTIVITY", "EVEN");
            } else {
                icon = userIcon;
                Log.d("MAIN ACTIVITY", "ODD");
            }
            gridArray.add(new Item(icon, numbers[i], TEXT_LABELS[i]));
        }
    }

    private void registerClickCallback() {

        GridView grid = (GridView) findViewById(R.id.gridView1);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long id) {

                // Get the Item object that was clicked in our GridView
                // Item clickedItem = gridArray.get(position);
                Sound clickedSound = mySounds.get(position);

                // Play the Sound that corresponds with its position in the mySounds List
                // see http://stackoverflow.com/questions/3289038/play-audio-file-from-the-assets-directory?rq=1
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        mp = new MediaPlayer();
                    }
                    mp.reset();

                    AssetFileDescriptor descriptor = getAssets().openFd(
                            ASSETS_DIRECTORY + "/"
                                    + clickedSound.getSoundFileName());
                    mp.setDataSource(descriptor.getFileDescriptor(),
                            descriptor.getStartOffset(), descriptor.getLength());
                    descriptor.close();

                    mp.prepare();
                    mp.setVolume(1f, 1f);
                    mp.start();
                } catch (IOException e) {
                    Log.v(getString(R.string.app_name), e.getMessage());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater blowUp = getMenuInflater();
        blowUp.inflate(R.menu.cool_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.preferences) {
            Intent p = new Intent("com.tech4lifeapps.numbers.PREFS");
            startActivity(p);
        } else if (itemId == R.id.aboutUs) {
            Intent i = new Intent("com.tech4lifeapps.numbers.ABOUT");
            startActivity(i);
        } else if (itemId == R.id.restart) {
            Intent rs = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage(getBaseContext().getPackageName());
            rs.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(rs);
        }
        return false;
    }
}
