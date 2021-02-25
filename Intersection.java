import java.util.ArrayList;

public class Intersection{
	
  private ArrayList<Street> enteringStreets;
  private int intName;
  private int numRoads;
  private ArrayList<Street> leavingStreets;

  private ArrayList<LogEntry> log = new ArrayList<LogEntry>();
  private int logIndex = 0;
  
  public Intersection(int id) {
      intName = id;
      numRoads = 0;
      enteringStreets = new ArrayList<Street>();
      leavingStreets = new ArrayList<Street>(); 
  }

  public void decide() {
    // look at the first car in each incoming street
    int minDistance = Integer.MAX_VALUE;
    Street choice = null;
    Car chosenCar = null;

    //find lowest remaining
    for (Street s: enteringStreets) {
        Car test = s.getFirstCar();
        if (test == null) {
            continue;
        }
        if (test.getRemainingDist() <= minDistance){
            minDistance = test.getRemainingDist();
            choice = s;
            chosenCar = test;
        }
    }

    if (choice == null) {
        
    }
    else {
        // pick lowest remaining
        if (log.isEmpty()) {
            log.add(new LogEntry(0, choice.getRoadName()));
        }

        if (choice.getRoadName() == log.get(logIndex).streetName) {
            log.get(logIndex).duration += 1;
        }
        else {
            logIndex += 1;
            log.add(new LogEntry(1, choice.getRoadName()));
        }

        // cross car over
        choice.removeFirstCar();

        chosenCar.crossIntersection();
    }
  }

  public ArrayList<LogEntry> getLogs(){
      return log;
  }

  public void connectIncomingStreet(Street s){
      enteringStreets.add(s);
      s.setIncomingIntID(intName);
      numRoads++;
  }

  public void connectOutgoingStreet(Street s){
      leavingStreets.add(s);
      s.setOutgoingIntID(intName);
      numRoads++;
  }

  public int getNumRoads(){
      return numRoads;
  }

  public ArrayList<Street> getIncomingStreets(){
      return enteringStreets;
  }

  public ArrayList<Street> getOutgoingStreets(){
      return leavingStreets;
  }

  public int getIntName(){
      return intName;
  }
  
  /*@Override
  public String toString() {
    String str = String.format("Intersection ID: %d\nNumber of Roads: %d\n", intName, numRoads);
    str += "";

    return str;
  }*/
}