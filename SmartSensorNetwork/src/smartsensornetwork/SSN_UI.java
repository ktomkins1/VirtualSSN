package smartsensornetwork;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/*
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
*/

/**
 *
 * @author Todd
 */
class SSN_UI {
    JFrame window;
    JTextArea output_area;
    JTextField input_field;
    //JTAInputStream stream_in;
    String message;
    JScrollBar sb;
    boolean text_change;
    
    public SSN_UI(){
        message = "";
        text_change = false;
        create_gui();
    }
    
    private void create_gui(){
        window = new JFrame();
        JPanel content_pane;
        content_pane = new JPanel(new BorderLayout(6, 6));
        output_area = new JTextArea("Welcome to the Smart Sensor Network.");
        JScrollPane output_scroller = new JScrollPane(output_area);
        sb = output_scroller.getVerticalScrollBar();
        output_scroller.setPreferredSize(new Dimension(400, 100));
        output_scroller.setMinimumSize(new Dimension(400,100));
        JPanel north_pane = new JPanel(new BorderLayout());
        north_pane.add(output_scroller, BorderLayout.SOUTH);
        //stream_in = new JTAInputStream(output_area);
        input_field = new JTextField("Enter message", 12);
        input_field.selectAll();
        content_pane.add(input_field, BorderLayout.WEST);
        JLabel title_label = new JLabel("Smart Sensor Network NCAP interface");
        north_pane.add(title_label, BorderLayout.NORTH);
        content_pane.add(north_pane, BorderLayout.NORTH);
        JButton send_button = new JButton("Send");
        send_button.addActionListener((ActionEvent e) -> {
            send_text();
        });
        JPanel button_pane = new JPanel(new GridLayout());
        button_pane.add(send_button);
        content_pane.add(button_pane, BorderLayout.EAST);
        window.setContentPane(content_pane);
        window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void close(){
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }
    
    public void print_line(String line){
        output_area.append("\n" + line);
        sb.setValue(sb.getMaximum());
    }
    
    public String get_line(){
        text_change = false;
        return message;
    }
    
    public boolean has_line(){
        return text_change;
    }
    
    public void wait_line(){
        while(!has_line()){
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ex) {
                
            }
        }
    }
    
    public void send_text(){
        message = input_field.getText();
        text_change = true;
        print_line("Sent: " + message);
        input_field.setText("Enter Message");
        input_field.selectAll();
    }

    /*
    public InputStream get_input_stream(){
        return stream_in;
    }
    
    /*
    the following class is a modified version of the code from:
    http://stackoverflow.com/questions/12669368/java-how-to-extend-inputstream-to-read-from-a-jtextfield
    by user1406062
    /////////////////////////////
    
    private class JTAInputStream extends InputStream{
        String out_text;
        byte[] contents;
        int pointer = 0;
        int new_text_start;

        public JTAInputStream(final JTextArea text) {

            text.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    if(e.getKeyChar()=='\n'){
                        int current_pos = text.getCaret().getDot();
                        text.select(new_text_start, current_pos);
                        out_text = text.getSelectedText();
                        contents = out_text.getBytes();
                        pointer = 0;
                        new_text_start = current_pos;
                    }
                    super.keyReleased(e);
                }
            });
        }

        @Override
        public int read() throws IOException {
            if(pointer >= contents.length) return -1;
            return this.contents[pointer++];
        }
        
        @Override
        public String nextLine(){
            while(!text_change){}
            return message;
        }
        
        public String get_line(){
            return out_text;
        }
    }
    /*
    
    */
}
