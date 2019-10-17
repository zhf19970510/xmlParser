package com.iotec.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/10/17 0017.
 */
public class DomXmlParser implements XmlParse {
    @Override
    public List<Book> parseXml(String fileName) {
        List<Book> list = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document document = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            //用生成器解析xml文档，来获取一个Document对象
            document = builder.parse(new File(fileName));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Node rootNode = document.getDocumentElement();
        //获得book元素的列表
        NodeList bookElementList = rootNode.getChildNodes();
        for(int i = 0;i<bookElementList.getLength();i++){
            //获得单个book元素
            Node bookElement = bookElementList.item(i);
            if(bookElement.getNodeName().equals("book")){
                Book book = new Book();
                NamedNodeMap map = bookElement.getAttributes();
                Node bookNoNode = map.getNamedItem("bookNo");
                String bookNoString = bookNoNode.getNodeValue();
                book.setBookNo(bookNoString.trim());
                NodeList subBookElementList = bookElement.getChildNodes();
                for(int j = 0;j<subBookElementList.getLength();j++){
                    Node subElementNode = subBookElementList.item(j);
                    String subElementNameString = subElementNode.getNodeName();
                    if(subElementNameString.equals("title")){
                        book.setTitle(subElementNode.getTextContent().trim());

                    }
                    if(subElementNameString.equals("author")){
                        book.setAuthor(subElementNode.getTextContent().trim());

                    }
                    if(subElementNameString.equals("price")){
                        book.setPrice(Double.parseDouble(subElementNode.getTextContent().trim()));

                    }
                }
                list.add(book);
            }
        }

        return list;

    }
    public static void main(String[] args) {
        XmlParse parser = new DomXmlParser();
        List<Book> list = parser.parseXml("file/books.xml");
        System.out.println("Dom 解析结果：");
        for(Book book:list){
            System.out.println(book);
        }
    }
}
