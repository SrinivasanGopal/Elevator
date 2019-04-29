import java.io.InputStreamReader;
import java.io.BufferedReader; 
import java.io.IOException;

class RequestListener implements Runnable { 

    @Override 
    public void run() { 

        while (true) { 
            String floorNumberStr = null; 
            String userInput = null; 
            String input[] = null;
            try { 
                // Read input from console 
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)); 
                userInput = bufferedReader.readLine(); 
                input = userInput.split(",");
                floorNumberStr = input[0];
                
            } catch (IOException e) { 
                e.printStackTrace(); 
            } 

            Elevator elevator = Elevator.getInstance();
            elevator.addWeight(Integer.parseInt(input[1]));
            if (elevator.getTotalWt() > 100) { 
                System.out.println("Maximum Weight reached : Kindly get down " );
                elevator.removeWeight(Integer.parseInt(input[1]));
            } 
            else { 
                 
            if (isValidFloorNumber(floorNumberStr)) { 
                 elevator.setPplWt(Integer.parseInt(floorNumberStr), Integer.parseInt(input[1]));
                elevator.addFloor(Integer.parseInt(floorNumberStr)); 
            } else { 
                System.out.println("Floor Request Invalid : " + floorNumberStr); 
            } 
            }
        } 
    } 

private boolean isValidFloorNumber(String s) { 
        return (s != null) && s.matches("\\d{1,2}"); 
    } 



} 