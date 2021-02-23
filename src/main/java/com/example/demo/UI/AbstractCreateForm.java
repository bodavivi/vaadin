package com.example.demo.UI;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;

public abstract class AbstractCreateForm extends FormLayout {

    private Button saveButton;
    private Button cancelButton;

    public AbstractCreateForm() {
        addComponent(getSaveButton());
        addComponent(getCancelButton());
    }

    public Button getSaveButton() {
        if (saveButton == null) {
            saveButton = new Button("SAVE");
            saveButton.setIcon(VaadinIcons.LOCK);
        }
        return saveButton;
    }

    public Button getCancelButton() {
        if (cancelButton == null) {
            cancelButton = new Button("CANCEL");
            cancelButton.setIcon(VaadinIcons.CLOSE_CIRCLE);
        }
        return cancelButton;
    }
}
