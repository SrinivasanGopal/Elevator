import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

 


class Elevator { 
     
    private static Elevator elevator = null; 
     
    private TreeSet<Integer> requestSet = new TreeSet<Integer>(); 
     
    private int currentFloor = 0; 
    
    public int totalWt = 0; 
    
    private Map<Integer,Integer> pplWt = new HashMap<Integer,Integer>();

    private Direction direction = Direction.UP; 

    private Elevator() {}; 
     
    private Thread requestProcessorThread; 

    /** 
     * @return singleton instance 
     */ 
    static Elevator getInstance() { 
        if (elevator == null) { 
            elevator = new Elevator(); 
        } 
        return elevator; 
    } 

    public synchronized void addFloor(int f) { 
        requestSet.add(f); 
         
        if(requestProcessorThread.getState() == Thread.State.WAITING){ 
            
            notify(); 
        }else{ 
            requestProcessorThread.interrupt(); 
        } 
         
    } 

	public synchronized int nextFloor() { 

        Integer floor = null;

        if (direction == Direction.UP) { 
            if (requestSet.ceiling(currentFloor) != null) { 
                floor =  requestSet.ceiling(currentFloor); 
            } else { 
                floor =  requestSet.floor(currentFloor); 
            } 
        } else { 
            if (requestSet.floor(currentFloor) != null) { 
                floor =  requestSet.floor(currentFloor); 
            } else { 
                floor =  requestSet.ceiling(currentFloor); 
            } 
        } 
        if (floor == null) { 
            try { 
                System.out.println("Press Any Floor Number , Entering People Weight : "); 
                wait(); 
            } catch (InterruptedException e) { 
                e.printStackTrace(); 
            } 
        } else { 
            // Remove the request from Set as it is the request in Progress. 
            requestSet.remove(floor); 
            int removeWt = this.pplWt.get(floor);
            this.totalWt = this.totalWt - removeWt;
            //pplWt.remove(floor);
        } 
        
        return (floor == null) ? -1 : floor; 
    } 

    public int getCurrentFloor() { 
        return currentFloor; 
    } 
     
    /** 
     * Set current floor and direction based on requested floor 
     *  
     * @param currentFloor 
     * @throws InterruptedException  
     */ 
    public void setCurrentFloor(int currentFloor) throws InterruptedException { 
        if (this.currentFloor > currentFloor) { 
            setDirection(Direction.DOWN); 
		System.out.println("Going Down");
        } else { 
            setDirection(Direction.UP); 
		System.out.println("Going UP");
        } 
        this.currentFloor = currentFloor; 
         
        System.out.println("Floor : " + currentFloor); 
         
        Thread.sleep(1000); 
    } 

    public Direction getDirection() { 
        return direction; 
    } 

    public void setDirection(Direction direction) { 
        this.direction = direction; 
    } 

    public Thread getRequestProcessorThread() { 
        return requestProcessorThread; 
    } 

    public void setRequestProcessorThread(Thread requestProcessorThread) { 
        this.requestProcessorThread = requestProcessorThread; 
    } 

    public TreeSet<Integer> getRequestSet() { 
        return requestSet; 
    } 

    public void setRequestSet(TreeSet<Integer> requestSet) { 
        this.requestSet = requestSet; 
    } 
    
    enum Direction { 
        UP, DOWN 
    }

    public int getTotalWt() { 
        return totalWt; 
    } 

    public void setTotalWt(int totalWt) { 
        this.totalWt = totalWt; 
    }
	public void addWeight(int weight) {
		this.totalWt = this.totalWt + weight; 
		
	}
	public void removeWeight(int weight) {
		this.totalWt = this.totalWt - weight; 
		
	}
	public Map<Integer,Integer> getPplWt() { 
        return pplWt; 
    } 

    public void setPplWt(Integer flr,Integer wt) { 
        this.pplWt.put(flr, wt); 
    }
     
} 
