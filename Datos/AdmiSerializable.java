package Datos;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Modelo.Producto;

/**
 *
 * @author estudiante
 */

public class AdmiSerializable {
   
        // ---------- Custom Java Serialization as XML ----------
        private void writeObject(ObjectOutputStream out, Producto p) throws IOException {
            // We store a single UTF string containing the XML
            String xml = toXml(p);
            out.writeUTF(xml);
        }

        private void readObject(ObjectInputStream in)
                throws IOException, ClassNotFoundException {
            String xml = in.readUTF();
            fromXml(xml);
        }
        // ------------------------------------------------------

        // Helpers to turn the object into XML and back
        public String toXml(Producto p) {
            // super minimal XML; escape as needed for your domain
            return new StringBuilder()
                    .append("<person>")
                    .append("<name>").append(escape(p.getName())).append("</name>")
                    .append("<age>").append(p.getAge()).append("</age>")
                    .append("<email>").append(escape(p.getEmail())).append("</email>")
                    .append("</person>")
                    .toString();
        }

        private Person fromXml(String xml) throws IOException {
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                dbf.setNamespaceAware(false);
                dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
                doc.getDocumentElement().normalize();
                Person p = null;    
                p.setName(text(doc, "name"));
                p.setAge(Integer.parseInt(text(doc, "age")));
                p.setEmail(text(doc, "email"));
                return p;
            } catch (ParserConfigurationException | SAXException e) {
                throw new IOException("Failed to parse XML", e);
            }
        }

        private static String text(Document d, String tag) {
            NodeList list = d.getElementsByTagName(tag);
            return list.getLength() > 0 ? list.item(0).getTextContent() : null;
        }

        private static String escape(String s) {
            if (s == null) return "";
            return s.replace("&","&amp;")
                    .replace("<","&lt;")
                    .replace(">","&gt;")
                    .replace("\"","&quot;")
                    .replace("'","&apos;");
        } 
}
