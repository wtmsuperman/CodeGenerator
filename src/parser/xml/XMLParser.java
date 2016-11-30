package parser.xml;

import meta.*;
import meta.basic.ListType;
import org.apache.poi.poifs.property.Child;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.w3c.dom.*;
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

    public XMLParser()
    {
        handlerMap = new HashMap<>();
        addMetaParser(new ClassParser("class"));
        addMetaParser(new FieldParser("field"));
        addMetaParser(new EnumCaseParser("case"));
        addMetaParser(new EnumParser("enum"));
    }

    public void addMetaParser(XMLMetaParser parser)
    {
        handlerMap.put(parser.getNodeName(),parser);
    }

    @Override
    public void parse(String metaFile) {
        try
        {
            File file = new File(metaFile);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            Document doc = factory.newDocumentBuilder().parse(file);
            Element root = doc.getDocumentElement();
            doParse(root,null,null);
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doParse(Element root,XMLMetaParser parent,Meta parentMeta)
    {
        NodeList nodeList = root.getChildNodes();
        for(int i = 0; i < nodeList.getLength(); ++i) {
            Node node = nodeList.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE)
            {
                continue;
            }

            XMLMetaParser parser = handlerMap.get(node.getNodeName());
            if (parser == null)
            {
                throw new RuntimeException("unknown type:" + node.getNodeName());
            }
            Meta meta = parser.parse((Element) node,parent,parentMeta);
            if (node.hasChildNodes())
            {
                doParse((Element)node,parser,meta);
            }
            parser.parseDone(meta);
        }
    }
}
