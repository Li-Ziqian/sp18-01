/*
 *  NBody is a class that will actually run your simulation. This class will have NO constructor.
 *  The goal of this class is to simulate a universe specified in one of the data files.
 */
public class NBody{

  public static double readRadius(String filePath){
    In in = new In(filePath);
    int bodyNums = in.readInt();
    double radius = in.readDouble();
    return radius;
  }

  public static Body[] readBodies(String filePath){
    In in = new In(filePath);
    int bodyNums = in.readInt();
    double radius = in.readDouble();

    Body[] allbodies = new Body[bodyNums];

    int i;
    for(i = 0; i < bodyNums; i++){
      double xp = in.readDouble();
      double yp = in.readDouble();

      double xv = in.readDouble();
      double yv = in.readDouble();

      double m = in.readDouble();

      String img = in.readString();

      allbodies[i] = new Body(xp, yp, xv, yv, m, img);
    }
    return allbodies;
  }

  public static void main(String[] args){
    //Collecting All Needed Input
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);

    String fileName = args[2];

    double universeRadius = readRadius(fileName);
    Body[] actualOutput = readBodies(fileName);


    //Drawing the Background
    String imageToDraw = "images/starfield.jpg";
    //StdDraw.picture(1350, 830, imageToDraw);

    StdDraw.setScale(-100, 100);

    StdDraw.picture(0, 0, imageToDraw);
    //StdDraw.show();


    /**Drawing bodies*/
    int i = 0;
    while(i < 5){
      actualOutput[i].draw(universeRadius);
      i = i + 1;
    }

    /*Animation SO COOL!!!!*/
    StdDraw.enableDoubleBuffering();

    Double curTime = 0.00;
    //loop
    while(curTime < T){
      double[] xForces = new double[5];
      double[] yForces = new double[5];
      i = 0;
      while(i < 5){
        xForces[i] = actualOutput[i].calcNetForceExertedByX(actualOutput);
        yForces[i] = actualOutput[i].calcNetForceExertedByY(actualOutput);
        i= i + 1;
      }

      i = 0;
      StdDraw.clear();
      StdDraw.picture(0, 0, imageToDraw);
      while(i < 5){
      actualOutput[i].update(dt, xForces[i], yForces[i]);
      actualOutput[i].draw(universeRadius);
      i = i + 1;
      }
      StdDraw.show();
      StdDraw.pause(10);

      curTime = curTime + dt;
    }
  }
}
