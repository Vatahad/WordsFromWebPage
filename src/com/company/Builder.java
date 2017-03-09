package com.company;

import java.io.IOException;

/**
 * Created by Dio on 01.03.2017.
 */
public class Builder {
    public static void main(String[] args) throws IOException {
        String url = args[0];
        HTMLHandler handler = new HTMLHandler();
        handler.handleWebPage(url);
    }
}
