package com.exadel.siperian.xml;
import java.io.InputStream;

public interface DataSource {
    
    InputStream getInputStream();

    String getContentType();

    int getSize();
}