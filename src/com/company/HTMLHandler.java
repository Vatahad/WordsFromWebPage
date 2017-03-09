package com.company;

import jdk.internal.org.objectweb.asm.Handle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class HTMLHandler {

    public static void main(String[] args) throws IOException {
        HTMLHandler handler = new HTMLHandler();
        HTMLParser parser = new HTMLParser();

        StringBuilder builder = handler.handleWebPage("http://stackoverflow.com");
        System.out.println(builder);
        System.out.println("Слов на сайте: ");
        parser.countAllWords(parser.parseHTML(builder));
        HashMap<String, Integer> map = parser.addWordsAtHashMap(parser.parseHTML(builder));
        System.out.println("Уникальных повторений слов: ");
        parser.countUnicWords(map);


    }

    public StringBuilder handleWebPage(String url) throws IOException {
        StringBuilder result;

        URL targetUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) targetUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-agent", "Chrome/41.0.2228.0");

        result = new StringBuilder(readHTMLDocument(connection));

        return result;

    }

    private StringBuilder readHTMLDocument(HttpURLConnection connection) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder result = new StringBuilder();
        String nextLine;

        while ((nextLine = reader.readLine()) !=null){
            result.append(nextLine);
        }

        return getBody(result);
    }

    private StringBuilder getBody(StringBuilder htmlDocument) {

        int startBodyTag = htmlDocument.indexOf("<body");
        int endBodyTag = htmlDocument.indexOf("</body");
        return new StringBuilder(htmlDocument.substring(startBodyTag, endBodyTag));
    }
}
