
package com.quocanh.emoji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    TextView textView;
    EmojiAdapter emojiAdapter;
    List<Integer> emojiUni = new ArrayList<>(Arrays.asList( 0x1F600, 0x1F62A, 0x1F601, 0x1F606,
                                                            0x1F605, 0x1F923, 0x1F602, 0x1F642,
                                                            0x1F644, 0x1F609, 0x1F60A, 0x1F607,
                                                            0x1F970, 0x1F60D, 0x1F929, 0x1F618,
                                                            0x1F617, 0x1F914, 0x1F60B, 0x1F911
                                                          ));
    List<String> emoji = new ArrayList<>();
    int playerLife = 3;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createEmojiGame();

    }

    private void createEmojiGame() {

        for (int i = 0; i < emojiUni.size(); i++) {
            emoji.add(new String(Character.toChars(emojiUni.get(i))));
        }

        Collections.shuffle(emoji);

        gridView = findViewById(R.id.gridview);
        emojiAdapter = new EmojiAdapter(MainActivity.this, R.layout.gridview_items, emoji);
        gridView.setAdapter(emojiAdapter);

        List<String> tempEmoji = new ArrayList<>(emoji);

        final String[] randomEmoji = {getRandomEmoji(tempEmoji)};

        textView = findViewById(R.id.textview);
        textView.setText(randomEmoji[0]);

        clickEmoji(randomEmoji, tempEmoji);


    }

    private void clickEmoji(String[] randomEmoji, List<String> tempEmoji) {
        gridView.setOnItemClickListener((adapterView, view, i, l) -> {
            TextView tempText = (TextView) view;
            String selectedEmoji = (String) tempText.getText();
            if (selectedEmoji.equals(randomEmoji[0])) {

                score++;
                tempText.setText("");
                tempEmoji.remove(randomEmoji[0]);

                if(tempEmoji.size() < 1) {
                    Intent intent = new Intent(MainActivity.this, EndGameActivity.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                } else {
                    randomEmoji[0] = getRandomEmoji(tempEmoji);
                    textView.setText(randomEmoji[0]);
                    Toast.makeText(MainActivity.this, "Good! Score: " +score, Toast.LENGTH_SHORT).show();
                }
            } else {

                playerLife--;

                if(playerLife < 1) {
                    Intent intent = new Intent(MainActivity.this, EndGameActivity.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Wrong! Lives: "+playerLife, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String getRandomEmoji(@NonNull List<String> emoji) {
        return emoji.get(new Random().nextInt(emoji.size()));
    }
}