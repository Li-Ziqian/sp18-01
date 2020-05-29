/**
 * Created by Li-Ziqian
  @May. 28. 2020
 */

public class Body{
  public double xxPos;//Its current x position
  public double yyPos;//Its current y position
  public double xxVel;//Its current velocity in the x direction
  public double yyVel;//Its curreny velocity in the y direction
  public double mass;//Its mass
  public String imgFileName;//The name of the file that corresponds to the image that depicts the body

  /**
   * Constructor1
   */
  public Body(double xP, double yP, double xV, double yV, double m, String img){
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  /**
   * Constructor2
   */
  public Body(Body b){
    /** Constructor2 */
    xxPos = b.xxPos;
    yyPos = b.yyPos;
    xxVel = b.xxVel;
    yyVel = b.yyVel;
    mass = b.mass;
    imgFileName = b.imgFileName;
  }

  /**
   * calculate the distance between this object and the given body.
   */
  public double calcDistance(Body b){
    double dx = Math.abs(xxPos - b.xxPos);
    double dy = Math.abs(yyPos - b.yyPos);
    double distance = Math.sqrt(dx * dx + dy * dy);
    return distance;
  }

  /**
   * Calculate the force of this object given by the given body.
   */
  public double calcForceExertedBy(Body b){
    double G = 6.67e-11;
    double force = (G * mass * b.mass)/(this.calcDistance(b) * this.calcDistance(b));
    return force;
  }

  /**
   * Calculate the force of this object given by the given body in the x-direction.
   */
  public double calcForceExertedByX(Body b){
    //double dx = Math.abs(xxPos - b.xxPos);
    double dx = b.xxPos - xxPos;
    double fx = calcForceExertedBy(b) * dx / calcDistance(b);
    return fx;
  }

  /**
   * Calculate the force of this object given by the given body in the y-direction.
   */
  public double calcForceExertedByY(Body b){
    //double dy = Math.abs(yyPos - b.yyPos);
    double dy = b.yyPos - yyPos;
    double fy = calcForceExertedBy(b) * dy / calcDistance(b);
    return fy;
  }

  /**
   *calculates the net X force exerted by all bodies in that array upon the current Body.
   */
  public double calcNetForceExertedByX(Body[] allbodys){
    int i;
    double netForceByX = 0;
    for(i = 0; i < allbodys.length; i++){
      //Body cur_body = Body(allbodys[i]);
      if(this == allbodys[i]) continue;

      netForceByX += calcForceExertedByX(allbodys[i]);
    }

    return netForceByX;
  }

  /**
   *calculates the net Y force exerted by all bodies in that array upon the current Body.
   */
  public double calcNetForceExertedByY(Body[] allbodys){
    int i;
    double netForceByY = 0;
    for(i = 0; i < allbodys.length; i++){
      //Body cur_body = Body(allbodys[i]);
      if(this == allbodys[i]) continue;

      netForceByY += calcForceExertedByY(allbodys[i]);
    }

    return netForceByY;
  }

  /*
   * Determines how much the forces exerted on the body will cause that body to accelerate,
   * and the resulting change in the body’s velocity and position in a small period of time dt.
   *
   */
  public void update(double dt, double fx, double fy){
    //1.Calculate the acceleration using the provided x- and y-forces.
    double ax = fx / mass;
    double ay = fy / mass;

    /*
     * 2.Calculate the new velocity by using the acceleration and current velocity.
     * Recall that acceleration describes the change in velocity per unit time,
     * so the new velocity is (vx+dt⋅ax,vy+dt⋅ay).
     */
    xxVel = xxVel + dt * ax;
    yyVel = yyVel + dt * ay;

    /*
     * 3.Calculate the new position by using the velocity computed in step 2 and the current position.
     * The new position is (px+dt⋅vx,py+dt⋅vy).
     */
    xxPos = xxPos + dt * xxVel;
    yyPos = yyPos + dt * yyVel;

  }

  public void draw(double radius){
    //StdDraw.clear();会导致只有水星在转 也就是说只有列表中最后一个没有被clear掉
    StdDraw.picture(xxPos * 100 / radius, yyPos * 100 / radius, "images/"+imgFileName);
  }


}
