import java.io.InputStreamReader;
import java.io.BufferedReader; 
import java.io.IOException;

class RequestListener implements Runnable { 

    @Override 
    public void run() { 

        while (true) { 
            String floorNumberStr = null; 
            try { 
                // Read input from console 
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)); 
                floorNumberStr = bufferedReader.readLine(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
            } 

            if (isValidFloorNumber(floorNumberStr)) { 
                Elevator elevator = Elevator.getInstance(); 
                elevator.addFloor(Integer.parseInt(floorNumberStr)); 
            } else { 
                System.out.println("Floor Request Invalid : " + floorNumberStr); 
            } 
        } 
    } 
private boolean isValidFloorNumber(String s) { 
        return (s != null) && s.matches("\\d{1,2}"); 
    } 



} 