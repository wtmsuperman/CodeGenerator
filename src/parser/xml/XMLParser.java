package parser.xml;

import meta.Meta;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import parser.Parser;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/30.
 */
public class XMLParser implements Parser {

    private HashMap<String, XMLMetaParser> handlerMap;

    public XMLParser() {
        handlerMap = new HashMap<>();
        addMetaParser(new ClassParser("class"));
        addMetaParser(new FieldParser("field"));
        addMetaParser(new EnumCaseParser("case"));
        addMetaParser(new EnumParser("enum"));
    }

    public void addMetaParser(XMLMetaParser parser) {
        handlerMap.put(parser.getNodeName(), parser);
    }

    @Override
    public boolean parse(String metaFile) {
        try {
            File file = new File(metaFile);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            Document doc = factory.newDocumentBuilder().parse(file);
            Element root = doc.getDocumentElement();
            return doParse(root, null, null);
        } catch (ParserConfigurationException | IOException | SAXException | RuntimeException e) {
            System.err.println("parse xml failed:" + metaFile);
            e.printStackTrace();
        }
        return false;
    }

    private boolean doParse(Element root, XMLMetaParser parent, Meta parentMeta) {
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node node = nodeList.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if (node.getNodeName().equalsIgnoreCase("import"))
            {
                Element e = (Element)node;
                String fileName = e.getAttribute("file");
                if (fileName == null || fileName.isEmpty())
                {
                    throw new RuntimeException("import error file is empty");
                }
                if (!parse(fileName))
                {
                    return false;
                }
                continue;
            }

            XMLMetaParser parser = handlerMap.get(node.getNodeName());
            if (parser == null) {
                throw new RuntimeException("unknown type:" + node.getNodeName());
            }
            Meta meta = parser.parse((Element) node, parent, parentMeta);
            if (node.hasChildNodes()) {
                boolean ret = doParse((Element) node, parser, meta);
                if (!ret)
                {
                    return false;
                }
            }
            parser.parseDone(meta);
        }

        return true;
    }
}
