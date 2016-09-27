package smartsensornetwork;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Todd
 */
public class SSN_XML_Reader {
    public static ArrayList<NCAP> open_doc(SSN_UI ui){
        int i, j;
        ArrayList<NCAP> ncaps_out = new ArrayList<>();
        try{
        File file = new File("src/smartsensornetwork/userdata.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        NodeList nl = document.getChildNodes().item(0).getChildNodes();
        ui.print_line("" + nl.getLength());
        Node n;
        for(i = 0; i < nl.getLength(); i++){
            n = nl.item(i);
            switch(n.getNodeName()){
                case "NCAP":
                    ncaps_out.add(get_ncap(n));
                    break;
                case "#text":
                    break;
                default:
                    break;
            }
        }
        }catch(NullPointerException | ParserConfigurationException | SAXException | IOException | DOMException e){
            ui.print_line("Reading document failed.");
            ui.print_line(e.toString());
        }
        return ncaps_out;
    }

    private static NCAP get_ncap(Node n) {
        int i;
        Node sub_node;
        NodeList nl = n.getChildNodes();
        NCAP new_ncap = new NCAP(nl.item(0).getTextContent());
        for(i = 0; i < nl.getLength(); i++){
            sub_node = nl.item(i);
            if(sub_node.getNodeName().equals("TIM")){
                new_ncap.register_module(get_tim(sub_node));
            }
        }
        return new_ncap;
    }

    private static TIM get_tim(Node n) {
        int i;
        Node sub_node;
        NodeList nl = n.getChildNodes();
        TIM new_tim = new TIM(nl.item(0).getTextContent());
        for(i = 0; i < nl.getLength(); i++){
            sub_node = nl.item(i);
            if(sub_node.getNodeName().equals("TRANSDUCER")){
                new_tim.add_channel(get_transducer(sub_node));
            }
        }
        return null;
    }
    
    private static Transducer get_transducer(Node n){
        int i;
        Node sub_node;
        NodeList nl = n.getChildNodes();
        Transducer new_t = new Transducer(nl.item(0).getTextContent());
        for(i = 1; i < nl.getLength(); i++){
            sub_node = nl.item(i);
            switch(sub_node.getNodeName()){
                case "TEDS":
                    new_t = Transducer.get_from_TEDS(get_teds(sub_node));
                    break;
                default:
                    break;
            }
        }
        return new_t;
    }

    private static TEDS get_teds(Node n) {
        TEDS new_teds = new TEDS(n.getTextContent());
        return new_teds;
    }
}
