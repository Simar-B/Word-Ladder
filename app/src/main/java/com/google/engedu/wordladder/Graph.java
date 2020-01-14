package com.google.engedu.wordladder;

//import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {
    HashMap<String, List<String>> nodes = new HashMap<String, List<String>>();

    public Graph(HashMap<String, List<String>> nodes) {
        this.nodes = nodes;
    }


    public void add(String word) {

        this.nodes.put(word, new ArrayList<String>());



    }

    public void createEdges() {
        for (String key : this.nodes.keySet()
        ) {
           // Log.i("Primary Word",key);
            for (String wordIterator:this.nodes.keySet()
                 ) {
             //   Log.i("Secondary Word",wordIterator);
                if(oneLetterDiff(key,wordIterator)){
                    this.nodes.get(key).add(wordIterator);
                }

            }

        }
    }

    public static boolean oneLetterDiff(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        } else {
            int numOfDiffs = 0;
            for (int i = 0; i < word1.length(); i++) {
                if (word1.charAt(i) != (word2.charAt(i))) {
                    numOfDiffs++;
                }
            }
            if (numOfDiffs == 1) {
                return true;
            } else {
                return false;
            }
        }

    }

    public static void main(String args[]) {
        Graph graph = new Graph(new HashMap<String, List<String>>());
        System.out.println(oneLetterDiff("hi", "ii"));

    }
}
