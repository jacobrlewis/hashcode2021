import java.util.ArrayList;

public class Car {
	
    private ArrayList<Street> path;
    private int curProgress;
    private int distRemaining;
    
    public Car(){
        path = new ArrayList<Street>();
        curProgress = 0;
    }
    
    // moves forward on current road
    public int move() {
        curProgress += 1;
        return curProgress;
    }

    // cross through intersection
    public void crossIntersection() {
        curProgress = 0;

        path.remove(0);

        if (path.isEmpty()) {
            MainProgram.finish();
            return;
        }

        path.get(0).addDrivingCar(this);
        calculateRemaining();
    }

    // called when reading input after reading all streets
    public int calculateRemaining() {
        if (path.size() == 1) {
            distRemaining = 0;
        }
        else {
            for (int i=1; i < path.size(); i++) {
                distRemaining += path.get(i).getLength();
            }
        }
        return distRemaining;
    }

    public int getRemainingDist() {
        return distRemaining;
    }

    public ArrayList<Street> getPath(){
        return path;
    }

    public void addStreet(Street st) {
        path.add(st);
    }

    @Override
    public String toString(){
        String res="Streets: ";
        for(int i=0;i<path.size();i++){
            res+=path.get(i)+" ";
        }
        return res;
    }
}