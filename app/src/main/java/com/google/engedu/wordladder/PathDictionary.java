/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.wordladder;

//import android.util.Log;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Queue;

public class PathDictionary {
    private static final int MAX_WORD_LENGTH = 4;
    private static final int MAX_DEPTH = 7;
    private static HashSet<String> words = new HashSet<>();

    public PathDictionary(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return;
        }
        //Log.i("Word ladder", "Loading dict");
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        //Log.i("Word ladder", "Loading dict");
        while ((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() > MAX_WORD_LENGTH) {
                continue;
            }
            words.add(word);
        }
    }

    public boolean isWord(String word) {

        return words.contains(word.toLowerCase());
    }

    public ArrayList<String> neighbours(String word) {
        char alpha[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        ArrayList<String> neighbourWords = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            for (char ch : alpha) {
                char[] charWord = word.toCharArray();
                charWord[i] = ch;
                if (isWord(new String(charWord))) {
                    if (!word.equals(new String(charWord)))
                        neighbourWords.add(new String(charWord));
                }

            }
        }
        return neighbourWords;
    }


    public String[] findPath(String start, String end) {
        ArrayDeque<ArrayList<String>> queue = new ArrayDeque<>();
        HashSet<String> visited = new HashSet<>();
        visited.add(start);
        ArrayList<String> startPath = new ArrayList<>();
        Log.i("neighbours length",neighbours(start).size() + "");
        startPath.add(start);
        queue.add(startPath);
        while (!queue.isEmpty()) {
            ArrayList<String> current = queue.poll();

            if (current.size() > MAX_DEPTH) {
                queue.remove();
            } else {
                String lastWord = current.get(current.size() - 1);
                for (String s : neighbours(lastWord)) {
                    if (s.equals(end)) {
                        current.add(end);
                        return current.toArray(new String[current.size()]);
                    } else {
                        if (!visited.contains(s)) {
                            visited.add(s);
                            ArrayList<String> nextPath = new ArrayList<>(current);
                            nextPath.add(s);
                            queue.add(nextPath);
                        }
                    }
                }
            }
        }
        return null;
    }

    public static void main(String args[]){

    }
}