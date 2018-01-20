package com.betrayal.betrayalchar;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;

import br.tiagohm.markdownview.MarkdownView;


/**
 * Created by Baljenurface on 30-12-2017.
 */

public class HauntTomes extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.haunt_tomes);

        MarkdownView markdownView = findViewById(R.id.markdown);
        markdownView.loadMarkdown(readSurvivalHaunt(1));
    }

    private String readSurvivalHaunt(int hauntNumber){
        String survival = "survival";
        return readHaunt(hauntNumber, survival);
    }

    private String readHaunt(int hauntNumber, String players) {
        try {
            StringBuilder builder = new StringBuilder();
            String line;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open(players + File.separator + hauntNumber + ".md")));
            while ((line = bufferedReader.readLine()) != null){
                builder.append(line).append("\n");
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String readTraitorHaunt(int hauntNumber){
        String traitors = "traitors";
        return readHaunt(hauntNumber, traitors);
    }
}

