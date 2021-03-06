package com.assemblr.arena06.common.utils;

import java.awt.geom.Point2D;


public class Vector2D {
    
    public static Vector2D add(Vector2D a, Vector2D b) {
        return new Vector2D(a.x + b.x, a.y + b.y);
    }
    
    public static Vector2D subtract(Vector2D a, Vector2D b) {
        return new Vector2D(a.x - b.x, a.y - b.y);
    }
    
    public static Vector2D multiply(Vector2D vector, double scalar) {
        return new Vector2D(vector.x * scalar, vector.y * scalar);
    }
    
    public static Vector2D divide(Vector2D vector, double scalar) {
        return new Vector2D(vector.x / scalar, vector.y / scalar);
    }
    
    public static double dot(Vector2D a, Vector2D b) {
        return a.x*b.x + a.y*b.y;
    }
    
    public static double lengthSquared(Vector2D vector) {
        return Math.pow(vector.x, 2) + Math.pow(vector.y, 2);
    }
    
    public static double length(Vector2D vector) {
        return Math.sqrt(lengthSquared(vector));
    }
    
    public static Vector2D normalize(Vector2D vector) {
        Vector2D value = new Vector2D(vector);
        value.divide(length(value));
        return value;
    }
    
    
    public static double getAngle(Vector2D vector) {
        double x = vector.x;
        double y = vector.y;
        if (x > 0) {
            return Math.atan(y / x);
        }
        else if (x < 0) {
            return Math.atan(y / x) + Math.PI;
        } else if (y > 0) {
            return -Math.PI / 2;
        } else {
            return Math.PI / 2;
        }
    }
    
    public static Vector2D scale(Vector2D vector, double length) {
        return new Vector2D(vector).normalize().multiply(length);
    }
    
    public static Vector2D clamp(Vector2D vector, double length) {
        if (length(vector) > length) {
            return scale(vector, length);
        }
        return new Vector2D(vector);
    }
    
    public double x;
    public double y;
    
    public Vector2D() {
        this(0, 0);
    }
    
    public Vector2D(Vector2D o) {
        this(o.x, o.y);
    }
    
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Vector2D(double angle, double magnitude, boolean useAngle) {
        if (useAngle) {
        x = Math.cos(angle) * magnitude;
        y = Math.sin(angle) * magnitude;
        } else {
            
        }
    }
    
    public Vector2D setAngle(double angle) {
        double magnitude = length(this);
        x = Math.cos(angle) * magnitude;
        y = Math.sin(angle) * magnitude;
        return this;
    }
    
    public Point2D.Double getPoint() {
        return new Point2D.Double(x, y);
    }
    public Vector2D add(Vector2D o) {
        x += o.x;
        y += o.y;
        return this;
    }
    public Vector2D add(Point2D.Double o) {
        x += o.x;
        y += o.y;
        return this;
    }
    
    public Vector2D addAngle(double angle) {
        double originalAngle = getAngle(this);
        double newAngle = originalAngle + angle;
        this.setAngle(newAngle);
        return this;
    }
    
    public Vector2D subtract(Vector2D o) {
        x -= o.x;
        y -= o.y;
        return this;
    }
    
    public Vector2D multiply(double scalar) {
        x *= scalar;
        y *= scalar;
        return this;
    }
    
    public Vector2D divide(double scalar) {
        x /= scalar;
        y /= scalar;
        return this;
    }
    
    public Vector2D normalize() {
        divide(length(this));
        return this;
    }
    
    public Vector2D scale(double length) {
        normalize();
        multiply(length);
        return this;
    }
    
    public Vector2D clamp(double length) {
        if (length(this) > length) {
            normalize();
            multiply(length);
        }
        return this;
    }
    
    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
    
}
