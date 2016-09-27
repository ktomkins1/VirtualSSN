package smartsensornetwork;

/**
 *
 * @author Todd
 */
public class TEDS {
    private String text;
    
    public TEDS(String text){
        this.text = text;
    }
    
    public TEDS(){
        text = "general TEDS";
    }
    
    public String get_text(){
        return text;
    }
}
