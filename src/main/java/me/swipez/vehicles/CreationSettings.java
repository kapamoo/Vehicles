package me.swipez.vehicles;

public class CreationSettings {
    public double rotationAmount = 30;
    public double movementAmount = 0.5;
    public boolean rotateBody = true;
    public boolean rotateLeftArm = false;
    public boolean rotateRightArm = false;

    public String axis = "x";

    public void loopAxis(){
        if (axis.equals("x")){
            axis = "y";
        } else if (axis.equals("y")){
            axis = "z";
        } else if (axis.equals("z")){
            axis = "x";
        }
    }

    public void setRotateLeftArm(){
        rotateBody = false;
        rotateLeftArm = true;
        rotateRightArm = false;
    }

    public void setRotateRightArm(){
        rotateBody = false;
        rotateLeftArm = false;
        rotateRightArm = true;
    }

    public void setRotateBody(){
        rotateBody = true;
        rotateLeftArm = false;
        rotateRightArm = false;
    }
}
