package smartsensornetwork;

import java.util.Random;

/**
 *
 * @author Todd
 */
public class Transducer {
    private String data;
    private boolean intake;
    private String name;
    private final Random rand;
    private TEDS teds;
    private String control_command;
    private String interrupt_mask;
    private String auxiliary_mask;
    private TEDS calibration_TEDS;
    private TEDS cal_id_TEDS;
    private TEDS end_TEDS;
    
    Transducer(String name){
        this.name = name;
        rand = new Random(System.currentTimeMillis());
        data = "" + rand.nextFloat();
        intake = false;
    }
    
    Transducer(){
        name = "new transducer " + ((int) System.currentTimeMillis());
        rand = new Random(System.currentTimeMillis());
        data = "" + rand.nextFloat();
        intake = false;
        teds = new TEDS(name);
        control_command = "cc";
        interrupt_mask = "mask1";
        auxiliary_mask = "mask2";
        calibration_TEDS = new TEDS("calibration");
        cal_id_TEDS = new TEDS("cal id");
        end_TEDS = new TEDS("end users application specific TEDS");
    }
    
    public void set_name(String name){
        this.name = name;
    }

    void print_name() {
        System.out.print(name);
    }
    
    public void update_data(){
        data = "" + rand.nextFloat();
    }
    
    //setters (mechanics)
    
    public void set_io(boolean setting){
        intake = setting;
    }
    
    public void set_data(String data){
        if(intake){
            this.data = data;
        }
    }
    
    //functional setters (standard implied)

    void set_control_command(String command) {
        this.control_command = command;
    }

    void set_standard_interrupt_mask(String mask) {
        this.interrupt_mask = mask;
    }

    void set_auxiliary_interrupt_mask(String mask) {
        this.auxiliary_mask = mask;
    }

    void set_calibration_TEDS(TEDS setTEDS) {
        this.calibration_TEDS = setTEDS;
    }

    void set_calibration_identification_TEDS(TEDS setTEDS) {
        this.cal_id_TEDS = setTEDS;
    }

    void set_euas_TEDS(TEDS setTEDS) {
        this.end_TEDS = setTEDS;
    }
    
    //getters
    
    public String get_data(){
        return data;
    }
    
    public String get_control_command() {
        return control_command;
    }

    public String get_standard_interrupt_mask() {
        return interrupt_mask;
    }

    public String get_auxiliary_interrupt_mask() {
        return auxiliary_mask;
    }
    
    public TEDS get_TEDS(){
        return teds;
    }

    public TEDS get_calibration_TEDS() {
        return calibration_TEDS;
    }

    public TEDS get_calibration_identification_TEDS() {
        return cal_id_TEDS;
    }

    public TEDS get_euas_TEDS() {
        return end_TEDS;
    }

    String get_standard_status() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String get_aux_status() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String get_identification_TEDS() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static Transducer get_from_TEDS(TEDS input_TEDS){
        return new Transducer();
    }
}
