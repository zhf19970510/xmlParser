package com.iotec.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.stream.events.StartElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/10/17 0017.
 */
public class MySaxHandler extends DefaultHandler {
    private String tag;
    private Book book;
    private List<Book> list;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        list = new ArrayList<>();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equals("book")) {
            book = new Book();
            String bookNo = attributes.getValue("bookNo");
            book.setBookNo(bookNo);
        }
        tag = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (qName.equals("book")) {
            list.add(book);
            book = null;
        }
        tag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if (tag != null) {
            String str = new String(ch, start, length).trim();
            if (tag.equals("title")) {
                book.setTitle(str);
            } else if (tag.equals("author")) {
                book.setAuthor(str);
            } else if (tag.equals("price")) {
                book.setPrice(Double.parseDouble(str));
            }

        }
    }

    public List<Book> getList() {
        return list;
    }
}
