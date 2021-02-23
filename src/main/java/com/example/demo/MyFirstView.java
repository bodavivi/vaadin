package com.example.demo;

import com.vaadin.navigator.View;
import com.vaadin.ui.Label;

public class MyFirstView extends Label implements View {

    public MyFirstView(String text) {
        super(text);
    }
}
