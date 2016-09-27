package smartsensornetwork;

import java.util.ArrayList;

/**
 * A NCAP is a Network Capable Application Processor
 *
 * @author Todd
 */
public class NCAP {
    private ArrayList<TIM> modules;
    private String name;
    
    public NCAP(String name){
        modules = new ArrayList<>();
        this.name = name;
    }
    
    private String[] parse_string(String s){
        return s.split(":");
    }
    
    public String accept_request(String request){
        String[] parsed_message = parse_string(request);
        return parsed_message[0];
    }
    
    public String send_text(String text){
        return text;
    }
    
    public void register_module(TIM t){
        modules.add(t);
    }
    
    public String get_name(){
        return name;
    }
}