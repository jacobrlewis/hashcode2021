import java.util.ArrayList;

public class Street{
  
  private int lightState;
  private int length;
  private String roadName;
  private ArrayList<Car> carsAtLight;
  private ArrayList<Car> drivingCars;
  private ArrayList<Car> helper;
  private int incomingIntersectionID;
  private int outgoingIntersectionID;
  //private int greenLightLength;

  
  public Street(String r, int l){
    carsAtLight = new ArrayList<Car>();
    drivingCars = new ArrayList<Car>();
    helper = new ArrayList<Car>();
  	lightState = 0; 
    roadName = r;
    length = l;
    incomingIntersectionID = -1;
    outgoingIntersectionID = -1;
    //greenLightLength = 0;
  }
  
  public void updateDrivers() {
    for (Car c: drivingCars) {
      int prog = c.move();
      if (prog == length) {
        helper.add(c);
      }
    }
    for (Car c: helper) {
      drivingCars.remove(c);
      carsAtLight.add(c);
    }
    helper.clear();
  }

  public int getLength(){
      return length;
  }

  public int getState(){
  	return lightState; 
  }

  public String getRoadName(){
    return roadName;
  }

  public int getIncomingID(){
    return incomingIntersectionID;
  }

  public int getOutgoingID(){
    return outgoingIntersectionID;
  }
  
  public void switchState(){
      //0 red, 1 green
      if(lightState == 0){
    	lightState = 1;  
    }else{
      lightState = 0;
    }
  }

  public void moveCarToLight(Car c){
    carsAtLight.add(c);
  }

  public void setIncomingIntID(int id){
      incomingIntersectionID = id;
  }

  public void setOutgoingIntID(int id){
      outgoingIntersectionID = id;
  }

  public Car getFirstCar(){
    if (carsAtLight.isEmpty()) {
      return null;
    }
    return carsAtLight.get(0);
  }

  public void removeFirstCar(){
    carsAtLight.remove(0);
  }

  public void addDrivingCar(Car c){
    drivingCars.add(c);
  }
}