package com.company.threads;

import com.company.TxtFinder.FilesValidation;
import com.company.TxtFinder.TxtFinder;

public class ThreadTask implements Runnable {
    private FilesValidation filesValidation=new FilesValidation();
    private TxtFinder txtFinder=new TxtFinder();
    private String extension;

    public ThreadTask(String extension) {
        this.extension = extension;
    }

    @Override
    public void run() {

    }
}
