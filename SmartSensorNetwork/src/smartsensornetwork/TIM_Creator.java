package smartsensornetwork;

/**
 *
 * @author Todd
 */
public class TIM_Creator {
    private TIM tim;
    
    public TIM_Creator(TEDS input){
        create_TIM(input);
    }
    
    private void create_TIM(TEDS input){
        tim = new TIM("new tim");
        tim.write_calibration_TEDS(0, input);
    }
    
    public TIM get_TIM(){
        return tim;
    }
}
