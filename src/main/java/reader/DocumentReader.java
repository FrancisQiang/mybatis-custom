package reader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * @author lgq
 * @date 2019/10/28
 */
public class DocumentReader {

    public static Document getDocument(InputStream inputStream) {
        Document document = null;
        SAXReader saxReader = new SAXReader();
        try {
            document = saxReader.read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

}
