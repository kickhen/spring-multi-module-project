package com.example.common.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;

public class BufferingServletOutputStream extends ServletOutputStream {

    private final ServletOutputStream original;

    public BufferingServletOutputStream(ServletOutputStream original) {
        this.original = original;
    }

    @Override
    public boolean isReady() {
        return original.isReady();

    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
        original.setWriteListener(writeListener);

    }

    @Override
    public void write(int b) throws IOException {
        original.write(b);
    }
}
