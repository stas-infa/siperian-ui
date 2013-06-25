package com.exadel.siperian.xml;

import java.io.IOException;
import java.io.Writer;

import javax.faces.component.UIComponent;
import javax.faces.context.ResponseWriter;

import com.exadel.siperian.xml.XMLDataModelGenerator.TimelineXML;

public class XmlResponseWriter extends ResponseWriter {
    StringBuilder sb = new StringBuilder();

    /**
     * @see javax.faces.context.ResponseWriter#cloneWithWriter(java.io.Writer)
     */
    @Override
    public ResponseWriter cloneWithWriter(Writer writer) {
        return (ResponseWriter) writer;
    }

    /**
     * @see javax.faces.context.ResponseWriter#endDocument()
     */
    @Override
    public void endDocument() throws IOException {
        throw new UnsupportedOperationException();

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.faces.context.ResponseWriter#endElement(java.lang.String)
     */
    @Override
    public void endElement(String name) throws IOException {
        write(TimelineXML.OPEN_CLOSE_TAG_CHAR + name + TimelineXML.CLOSE_TAG_CHAR);

    }

    /**
     * @see javax.faces.context.ResponseWriter#flush()
     */
    @Override
    public void flush() throws IOException {

    }

    /**
     * @see javax.faces.context.ResponseWriter#getCharacterEncoding()
     */
    @Override
    public String getCharacterEncoding() {
        throw new UnsupportedOperationException();
    }

    /**
     * @see javax.faces.context.ResponseWriter#getContentType()
     */
    @Override
    public String getContentType() {
        return "text/xml";
    }

    /**
     * @see javax.faces.context.ResponseWriter#startDocument()
     */
    @Override
    public void startDocument() throws IOException {
        throw new UnsupportedOperationException();

    }

    /**
     * @see javax.faces.context.ResponseWriter#startElement(java.lang.String,
     *      javax.faces.component.UIComponent)
     */
    @Override
    public void startElement(String name, UIComponent component) throws IOException {
        write(TimelineXML.OPEN_TAG_CHAR + name + TimelineXML.CLOSE_TAG_CHAR);

    }

    /**
     * @see javax.faces.context.ResponseWriter#writeAttribute(java.lang.String,
     *      java.lang.Object, java.lang.String)
     */
    @Override
    public void writeAttribute(String name, Object value, String property) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(name);
        sb.append("=");
        sb.append("\"");
        sb.append(value.toString());
        sb.append("\"");
        write(sb.toString());

    }

    /**
     * @see javax.faces.context.ResponseWriter#writeComment(java.lang.Object)
     */
    @Override
    public void writeComment(Object comment) throws IOException {
        throw new UnsupportedOperationException();

    }

    /**
     * @see javax.faces.context.ResponseWriter#writeText(java.lang.Object,
     *      java.lang.String)
     */
    @Override
    public void writeText(Object text, String property) throws IOException {
        write(text.toString());

    }

    /**
     * @see javax.faces.context.ResponseWriter#writeText(char[], int, int)
     */
    @Override
    public void writeText(char[] text, int off, int len) throws IOException {
        throw new UnsupportedOperationException();

    }

    /**
     * @see javax.faces.context.ResponseWriter#writeURIAttribute(java.lang.String,
     *      java.lang.Object, java.lang.String)
     */
    @Override
    public void writeURIAttribute(String name, Object value, String property) throws IOException {
        throw new UnsupportedOperationException();

    }

    /**
     * @see java.io.Writer#close()
     */
    @Override
    public void close() throws IOException {

    }

    /**
     * @see java.io.Writer#write(char[], int, int)
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        throw new UnsupportedOperationException();

    }

}