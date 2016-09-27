package smartsensornetwork;

/**
 *
 * @author Todd
 */
class Client {
    private NCAP active_network;
    
    public Client(){
        
    }
    
    public void access_network(NCAP n){
        this.active_network = n;
    }
    
    public void send_request(String req){
        this.active_network.accept_request(req);
    }
}
