package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dio on 01.03.2017.
 */
public class HTMLParser {

    public List<String> parseHTML(StringBuilder htmlDoc) throws IOException {
        List<String> result = new ArrayList<>();

        StringBuilder builder = new StringBuilder(htmlDoc);

        String words = "";
        int i = 0;
        while (builder.length() != 0) {
            switch (ourChar(builder.charAt(i))) {


                case 1: {
                    if (words != "") {
                        result.add(words);
                        words = "";
                    }
                    builder.delete(0, i + 1);
                    i = 0;
                }
                break;
                case 2: {
                    if (words != "") {
                        result.add(words);
                        words = "";
                    }
                    builder.delete(0, tags(i, builder));
                    i = 0;
                }
                break;
                case 0: {
                    words += builder.charAt(i);
                    i++;
                }
                break;
            }
        }

        return result;
    }

    private int script(int secondIndex, StringBuilder builder) {

        secondIndex += 2;
        for (int i = secondIndex; i < builder.length(); i++) {
            if (builder.charAt(i) == '<' && builder.charAt(i + 1) == '/' && builder.charAt(i + 2) == 's') {
                secondIndex = i + 9;
                break;
            }
        }
        return secondIndex;
    }

    private int tags(int secondIndex, StringBuilder builder) {
        String script = "";
        for (int i = 1; i < 7; i++) {
            script += builder.charAt(i);
        }
        if (script.equals("script")) {
            secondIndex = script(secondIndex, builder);
        } else {
            for (int i = secondIndex; i < builder.length(); i++) {
                if (builder.charAt(i) == '>') {
                    secondIndex = i + 1;
                    break;
                }
            }
        }
        return secondIndex;
    }

    private int ourChar(char a) {
        int n = 0;
        switch (a) {
            case ' ':
                n = 1;
                break;
            case '$':
                n = 1;
                break;
            case '(':
                n = 1;
                break;
            case ')':
                n = 1;
                break;
            case '%':
                n = 1;
                break;
            case '&':
                n = 1;
                break;
            case '/':
                n = 1;
                break;
            case '@':
                n = 1;
                break;
            case '-':
                n = 1;
                break;
            case ',':
                n = 1;
                break;
            case '.':
                n = 1;
                break;
            case ':':
                n = 1;
                break;
            case ';':
                n = 1;
                break;
            case '{':
                n = 1;
                break;
            case '}':
                n = 1;
                break;
            case '1':
                n = 1;
                break;
            case '2':
                n = 1;
                break;
            case '3':
                n = 1;
                break;
            case '4':
                n = 1;
                break;
            case '5':
                n = 1;
                break;
            case '6':
                n = 1;
                break;
            case '7':
                n = 1;
                break;
            case '8':
                n = 1;
                break;
            case '9':
                n = 1;
                break;
            case '0':
                n = 1;
                break;
            case '!':
                n = 1;
                break;
            case '?':
                n = 1;
                break;
            case '#':
                n = 1;
                break;
            case '+':
                n = 1;
                break;
            case '=':
                n = 1;
                break;
            case '*':
                n = 1;
                break;
            case '<':
                n = 2;
                break;

        }
        return n;
    }

    public void countAllWords(List<String> list) {
        int count = 0;
        for (String item : list) {
            count++;
        }
        System.out.println(count);
    }

    public HashMap<String, Integer> addWordsAtHashMap(List<String> list) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String item : list) {
            map.put(item, 0);
        }
        for (String item : list) {
            String word = item;

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getKey().equals(word)) {
                    Integer a = entry.getValue();
                    a++;
                    entry.setValue(a);
                }
            }
        }
        return map;
    }

    public void countUnicWords(HashMap<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " " + value);
        }
    }
}
