package smartsensornetwork;

import java.util.ArrayList;

/**
 * The Smart Sensor Network connects clients to the systems that control their
 * building's sensors and actuators.
 *
 * @author Todd
 */
public class Smart_Sensor_Network {
    private static ArrayList<NCAP> networks;
    private static SSN_UI system;
    //private static Scanner sc;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        system = new SSN_UI();
        networks = SSN_XML_Reader.open_doc(system);
        main_loop();
        system.close();
    }
    
    private static void main_loop(){
        do{
            print_menu(0);
            system.wait_line();
        }while(process_input(system.get_line()));
    }
    
    private static void print_menu(int menu){
        switch(menu){
            case 0:
                system.print_line("Pick an option from below:");
                system.print_line("ls) list networks");
                system.print_line("an) add network");
                system.print_line("nac) add channel to network");
                system.print_line("cat) add transducer to channel");
                system.print_line("c) enter command");
                system.print_line("X) close");
                break;
            case 1:
                int i = 1;
                system.print_line("NCAPs:\n");
                for(NCAP n: networks){
                    system.print_line("" + i + ") " + n.get_name() + "\n");
                    i++;
                }
                break;
            default:
                
                break;
        }
    }
    
    private static boolean process_input(String line){
        switch(line){
            case "ls":
                print_menu(1);
                break;
            case "an":
                add_network();
                break;
            case "X":
                return false;
            default:
                break;
        }
        return true;
    }
    
    private static NCAP add_ncap(String name){
        NCAP new_ncap = new NCAP(name);
        networks.add(new_ncap);
        return new_ncap;
    }
    
    private static void add_network(){
        system.print_line("Please enter the name of the new NCAP.\n");
        system.wait_line();
        system.print_line("NCAP " + add_ncap(system.get_line()).get_name() + " added.\n");
    }
}
