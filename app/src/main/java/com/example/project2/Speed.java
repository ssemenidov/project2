package com.example.project2;

public class Speed {
    private float xv;
    private float yv;

    public Speed(){
        this.xv=1;
        this.yv=1;
    }

    public Speed(float xv, float yv){
        this.xv= xv;
        this.yv= yv;
    }
    public float getXv(){
        return xv;
    }
    public void setXv(float xv){
        this.xv= xv;
    }
    public float getYv(){
        return yv;
    }
    public void setYv(float yv){
        this.yv= yv;
    }
    public void onStop() {
        xv = 0;
        yv=0;
    }
    public void toggleXDirection() { xv = -xv; }
    public void toggleYDirection() { yv=-yv; }
    public void rotate(int speedmax,String direction){
        if (direction=="u"){ yv=-speedmax;xv=0; }
        if (direction=="l"){ yv=0;xv=-speedmax; }
        if (direction=="r"){ yv=0;xv=speedmax; }
        if (direction=="d"){ yv=speedmax;xv=0; }
        }





}
