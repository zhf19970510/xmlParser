package com.iotec.xml;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/10/17 0017.
 */
public class SaxParser implements XmlParse {

    @Override
    public List<Book> parseXml(String fileName) {
        List<Book> list = new ArrayList<>();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        MySaxHandler handler = null;
        //通过工厂方法获取一个解析器对象
        try {
            SAXParser parser = factory.newSAXParser();
            InputStream is = new FileInputStream(fileName);
            handler = new MySaxHandler();
            parser.parse(is, handler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        list = handler.getList();
        return list;
    }

    public static void main(String[] args) {
        XmlParse parse = new SaxParser();
        List<Book> list = parse.parseXml("file/books.xml");
        System.out.println("SAX 解析结果：");
        for (Book book:list){
            System.out.println(book);
        }

    }
}
