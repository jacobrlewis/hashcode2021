
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

import java.io.IOException;



public class MainProgram {
  // files
  private static String fileName;
  private static PrintWriter pw;

  // constants
  private static int simDuration;
  private static int numIntersections;
  private static int numStreets;
  private static int numCars;
  private static int bonusPts;
  
  public static int totalPts;

  // lists
  private static ArrayList<Car> Cars;
  private static ArrayList<Intersection> Intersections;
  private static HashMap<String,Street> Streets;
  
  // point
  private static int curTime;
  private static int totalPoints;

  //private static PrintWriter pw;
  private static Scanner in;
  
  //graph ??
  //queue ??
  
	public static void readFile() throws IOException{
    in = new Scanner(new File("" + fileName + ".txt"));
    // first line of input
    //System.out.println("FileName to be read is: " + fileName);
    simDuration = in.nextInt();
    numIntersections = in.nextInt();
    numStreets = in.nextInt();
    numCars = in.nextInt();
    bonusPts = in.nextInt();

    //System.out.println("First 5 numbers: " + simDuration + " " + numIntersections + " " + numStreets + " " + numCars + " " + bonusPts);

    // create intersections
    for (int i=0; i<numIntersections;i++) {
      Intersections.add(new Intersection(i));
      //System.out.println("Created intersection " + i);
    }
    
    // create cars
    for (int i=0;i<numCars;i++) {
      Cars.add(new Car());
    }

    // read in S streets
    for(int i=0; i<numStreets; i++) {
    
      // find the ending intersection

      // add streetlight to intersection
      int intersectionAtStart = in.nextInt();
      int intersectionAtEnd = in.nextInt();
      String roadName = in.next();
      int stLength = in.nextInt();

      Street newSt = new Street(roadName, stLength);
      newSt.setOutgoingIntID(intersectionAtStart);
      newSt.setIncomingIntID(intersectionAtEnd);
      Intersections.get(intersectionAtStart).connectOutgoingStreet(newSt);
      Intersections.get(intersectionAtEnd).connectIncomingStreet(newSt);
      Streets.put(roadName, newSt);
    }

    // read in V lines of car paths
    for (Car c: Cars) {
      int numStreetsForCar = in.nextInt();
      for(int j = 0; j < numStreetsForCar; j++){
        String stName = in.next();
        Street streetObj = Streets.get(stName);
        c.addStreet(streetObj);

        // add car to current street
        if (j == 0) {
          streetObj.moveCarToLight(c);
        }
      }
      c.calculateRemaining();
    }
  }

  public static void runSim() {
    for (curTime = 0; curTime < simDuration; curTime++) {
      for (Intersection i: Intersections) {
        for (Street s: i.getIncomingStreets()) {
          // update cars driving on roads  
          s.updateDrivers();
        }
        // make intersections decide what is green
        i.decide();
      }
    }
  }

  // a car has finished, add to the score
  public static void finish() {
      totalPoints += bonusPts + (simDuration - curTime);
  }

  public static void printFile(){
    pw.print("" + numIntersections + "\n");
    for(int i = 0; i < numIntersections; i++){
      Intersection inter = Intersections.get(i);
      pw.println("" + inter.getIntName());
      int numInSt = inter.getIncomingStreets().size();
      pw.println("" + numInSt);
      for(int j = 0; j < numInSt; j++){
        //LogEntry le = inter.getLogs().get(j);
        //pw.print("" + le.streetName + " " + le.duration + "\n");
      }

    }
    pw.close();
  	
  }
  
  public static void promptUser() {
    Scanner fName = new Scanner(System.in);  
    System.out.println();
    fileName = fName.nextLine();
    fName.close();
  }

  public static void main(String[] args){
    //System.out.println("START");
    //promptUser();
    fileName = "f";
    Intersections = new ArrayList<Intersection>();
    Cars = new ArrayList<Car>();
    Streets = new HashMap<String,Street>();
    try {
    pw = new PrintWriter(new File("" + fileName + "_out.txt"));
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
    //pw = new PrintWriter(new File("" + file_Name + "_out.txt"));		
    try {
        readFile();
        
        //testing
        /*
        System.out.println("PRINTING INTERSECTIONS: ");
        for (Intersection i : Intersections) {
          System.out.println(i.toString());
        }

        for(Car i:Cars){
          System.out.println(i.toString());
        }*/

      }
    catch (Exception e) {
        System.out.println(e.getMessage());
    }
    
    runSim();
    System.out.println("file: " + fileName);
    System.out.println("TOTAL POINTS: " + totalPoints);
    printFile();
    pw.close();
    System.out.println("TOTAL POINTS" + totalPoints);
  }
}
  


  
  
  
  
  
  
  
  
  