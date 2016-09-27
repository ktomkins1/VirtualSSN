package smartsensornetwork;

import java.util.ArrayList;

/**
 *
 * @author Todd
 */
public class TIM {
    private String name;
    private ArrayList<Transducer> channels;
    private String transducer_data;
    private String standard_address;
    private String triggered_channel_address;
    private String auxiliary_status;
    private String standard_interrupt_mask;
    private String auxiliary_interrupt_mask;
    private String version;
    private String meta_TEDS;
    private String meta_identification_TEDS;
    private String euas_TEDS;
    
    TIM(String name){
        this.name = name;
        this.channels = new ArrayList<>();
    }
    
    //Reading Functional methods - channel 1 - 255
    
    public String read_channel_transducer_data(int channel){
        channels.get(channel).update_data();
        return channels.get(channel).get_data();
    }
    
    public String read_channel_standard_status(int channel){
        return channels.get(channel).get_standard_status();
    }
    
    public String read_channel_auxiliary_status(int channel){
        return channels.get(channel).get_aux_status();
    }
    
    public String read_channel_standard_interrupt_mask(int channel){
        return channels.get(channel).get_standard_interrupt_mask();
    }
    
    public String read_channel_auxiliary_interrupt_mask(int channel){
        return channels.get(channel).get_auxiliary_interrupt_mask();
    }
    
    public String read_channel_TEDS(int channel){
        return channels.get(channel).get_TEDS().get_text();
    }
    
    public String read_channel_identification_TEDS(int channel){
        return channels.get(channel).get_identification_TEDS();
    }
    
    public String read_calibration_TEDS(int channel){
        return channels.get(channel).get_calibration_TEDS().get_text();
    }
    
    public String read_calibration_identification_TEDS(int channel){
        return channels.get(channel).get_calibration_identification_TEDS().get_text();
    }
    
    public String read_channel_end_users_application_specific_TEDS(int channel){
        return channels.get(channel).get_euas_TEDS().get_text();
    }
    
    //Writing Functional methods - channel 1 - 255
    
    public void write_channel_transducer_data(int channel, String data){
        channels.get(channel).set_data(data);
    }
    
    public void write_channel_control_command(int channel, String command){
        channels.get(channel).set_control_command(command);
    }
    
    public void write_channel_standard_interrupt_mask(int channel, String mask){
        channels.get(channel).set_standard_interrupt_mask(mask);
    }
    
    public void write_channel_auxiliary_interrupt_mask(int channel, String mask){
        channels.get(channel).set_auxiliary_interrupt_mask(mask);
    }
    
    public void write_calibration_TEDS(int channel, TEDS setTEDS){
        channels.get(channel).set_calibration_TEDS(setTEDS);
    }
    
    public void write_calibration_identification_TEDS(int channel, TEDS setTEDS){
        channels.get(channel).set_calibration_identification_TEDS(setTEDS);
    }
    
    public void write_channel_end_users_application_specific_TEDS(int channel, TEDS setTEDS){
        channels.get(channel).set_euas_TEDS(setTEDS);
    }
    
    //Global Reading Functional methods channel 0
    
    public String read_global_transducer_data(){
        return this.transducer_data;
    }
    
    public String read_global_standard_address(){
        return this.standard_address;
    }
    
    public String read_triggered_channel_address(){
        return this.triggered_channel_address;
    }
    
    public String read_global_auxiliary_status(){
        return this.auxiliary_status;
    }
    
    public String read_global_standard_interrupt_mask(){
        return this.standard_interrupt_mask;
    }
    
    public String read_global_auxiliary_interrupt_mask(){
        return this.auxiliary_interrupt_mask;
    }
    
    public String read_TIM_version(){
        return this.version;
    }
    
    public String read_meta_TEDS(){
        return this.meta_TEDS;
    }
    
    public String read_meta_identification_TEDS(){
        return this.meta_identification_TEDS;
    }
    
    public String read_global_end_users_application_specific_TEDS(){
        return this.euas_TEDS;
    }
    
    //Global Writing Functional methods channel 0
    
    public void write_global_transducer_data(String data){
        int i;
        for(i = 0; i < channels.size(); i++){
            this.write_channel_transducer_data(i, data);
        }
    }
    
    public void write_global_control_command(String command){
        int i;
        for(i = 0; i < channels.size(); i++){
            this.write_channel_control_command(i, command);
        }
    }
    
    public void write_triggered_channel_address(){
        //TODO: consult standard to find out what this does
    }
    
    public void write_global_standard_interrupt_mask(String mask){
        int i;
        for(i = 0; i < channels.size(); i++){
            this.write_channel_standard_interrupt_mask(i, mask);
        }
    }
    
    public void write_global_auxiliary_interrupt_mask(String mask){
        int i;
        for(i = 0; i < channels.size(); i++){
            this.write_channel_auxiliary_interrupt_mask(i, mask);
        }
    }
    
    public void write_global_end_users_application_specific_TEDS(String stringTEDS){
        int i;
        for(i = 0; i < channels.size(); i++){
            this.write_channel_auxiliary_interrupt_mask(i, stringTEDS);
        }
    }
    
    //Housekeeping methods
    
    public void set_name(String name){
        this.name = name;
    }
    
    public void print_name(){
        System.out.print(name);
    }
    
    public void add_channel(Transducer t){
        channels.add(t);
    }
    
    public void add_channel(String name){
        channels.add(new Transducer(name));
    }
    
    public void remove_channel(int ch){
        channels.remove(ch);
    }
    
    public void print_channels(){
        int i;
        for(i = 0; i < channels.size(); i++){
            System.out.println("Channel " + i + ": ");
            channels.get(i).print_name();
            i++;
        }
    }
}
