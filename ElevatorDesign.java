 

public class ElevatorDesign{ 

    public static void main(String[] args) { 
       
        
        Thread requestListenerThread= new Thread(new RequestListener(), 
                "RequestListenerThread"); 
        Thread requestProcessorThread = new Thread(new RequestProcessor(), 
                "RequestProcessorThread"); 
        Elevator.getInstance().setRequestProcessorThread(requestProcessorThread); 
        
        requestListenerThread.start(); 
        requestProcessorThread.start(); 
     
    } 
} 
