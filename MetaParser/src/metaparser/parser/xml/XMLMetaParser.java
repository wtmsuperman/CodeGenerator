package metaparser.parser.xml;

import metaparser.meta.Meta;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Created by Administrator on 2016/11/30.
 */
public abstract class XMLMetaParser {
    private String nodeName;

    public XMLMetaParser(String nodeName) {
        this.nodeName = nodeName;
    }

    public Meta parse(Element element, XMLMetaParser parent, Meta parentMeta) {
        if (element == null) {
            return null;
        }

        Meta meta = create();
        NamedNodeMap attributes = element.getAttributes();
        int len = attributes.getLength();
        for (int i = 0; i < len; i++) {
            Node node = attributes.item(i);
            String name = node.getNodeName().trim();
            String value = node.getNodeValue().trim();
            if (!parseAttr(meta, name, value)) {
                //unkonw metaparser.meta data
                meta.addMetaData(name, value);
            }
        }

        if (parent == null)
            return meta;

        if (!parent.handleChild(parentMeta, meta)) {
            throw new RuntimeException(parent.nodeName + " could not parse this child node " + nodeName);
        }

        return meta;
    }

    public void parseDone(Meta meta) {

    }

    protected boolean parseAttr(Meta meta, String name, String value) {
        if (name.equalsIgnoreCase("range")) {
            return true;
        } else if (name.equalsIgnoreCase("ref")) {
            return true;
        } else if (name.equalsIgnoreCase("comment")) {
            meta.setComment(value);
            return true;
        }
        return false;
    }

    public String getNodeName() {
        return nodeName;
    }

    protected boolean handleChild(Meta parent, Meta child) {
        return false;
    }

    protected abstract Meta create();
}


