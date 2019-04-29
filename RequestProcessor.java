class RequestProcessor implements Runnable { 

    @Override 
    public void run() { 
        while (true) { 
            Elevator elevator = Elevator.getInstance(); 
            int floor = elevator.nextFloor();
            int currentFloor = elevator.getCurrentFloor(); 
            try{ 
                if (floor >= 0) { 
                    if (currentFloor > floor) { 
                        while (currentFloor > floor) { 
                            elevator.setCurrentFloor(--currentFloor); 
                        } 
                    } else { 
                        while (currentFloor < floor) { 
                            elevator.setCurrentFloor(++currentFloor); 
                        } 
                    } 
                    System.out.println("\tDoors Opening");
                    System.out.println("\t\tWelcome to Floor : " + elevator.getCurrentFloor());
                    Thread.sleep(3000);
                    System.out.println("\tDoors Closing");
                } 
                 
            }catch(InterruptedException e){ 
                if(elevator.getCurrentFloor() != floor){ 
                    elevator.getRequestSet().add(floor); 
                } 
            } 
        } 
    }


} 