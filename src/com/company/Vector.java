package com.company;

public class Vector {

    public float x, y;

    public Vector(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Vector sub(float x, float y){
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector div(float x, float y){
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vector div(float n){
        this.x /= n;
        this.y /= n;
        return this;
    }

    public Vector mult(float n){
        this.x *= n;
        this.y *= n;
        return this;
    }

    public void setMag(float n){
        this.norm();
        this.mult(n);
    }

    public void norm(){
        this.div(this.mag());
    }

    public float mag(){
        return (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public float magSq(){
        return this.x * this.x + this.y * this.y;
    }

    public void limit(float n){
        if (this.magSq() > n * n){
            this.div((float) Math.sqrt(this.magSq())).mult(n);
        }
    }
}
