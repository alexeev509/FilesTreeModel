package com.company.listeners;

import com.company.files.TxtReader;
import com.company.view.MainWindow;

import javax.swing.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.IOException;

public class ScrollAdjustmentListener implements AdjustmentListener {
    private TxtReader txtReader = new TxtReader();
    private MainWindow mainWindow;
    public static final int UP_SCROLL = 1;
    public static final int DOWN_SCROLL = 2;
    private static final double UP_SCROLL_SCALE = 0.9;
    private static final double DOWN_SCROLL_SCALE = 0.1;

    public ScrollAdjustmentListener(TxtReader txtReader, MainWindow mainWindow) {
        this.txtReader = txtReader;
        this.mainWindow = mainWindow;
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        JScrollBar scrollBar = (JScrollBar) e.getAdjustable();
        int extent = scrollBar.getModel().getExtent();
//        System.out.println("1. Value: " + (scrollBar.getValue() + extent) + " Max: " + scrollBar.getMaximum());
        try {
            if (scrollBar.getValue() + extent == scrollBar.getMaximum()) {

                String text = txtReader.readNextBytes();
                if(! "".equals(text)) {
                    mainWindow.addText(text, UP_SCROLL);
                    scrollBar.setValue((int) (UP_SCROLL_SCALE * scrollBar.getMaximum()));
                }

            } else if (scrollBar.getValue() == scrollBar.getMinimum()) {

                if(txtReader.getPositionInFileFirstPage()!=0) {
                    String text = txtReader.readPreviousBytes();
                    mainWindow.addText(text, DOWN_SCROLL);
                    scrollBar.setValue((int) (DOWN_SCROLL_SCALE * scrollBar.getMaximum()));
                }
            }
        } catch (IOException e1) {
        }
    }
}
