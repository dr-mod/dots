package utils;

import java.io.Serializable;

public class Vector implements Serializable {

    public float x;

    public float y;

    public Vector() {
    }

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    static public Vector fromAngle(float angle) {
        return fromAngle(angle,null);
    }


    static public Vector fromAngle(float angle, Vector target) {
        if (target == null) {
            target = new Vector((float)Math.cos(angle),(float)Math.sin(angle));
        } else {
            target.set((float)Math.cos(angle),(float)Math.sin(angle));
        }
        return target;
    }

    public Vector copy() {
        return new Vector(x, y);
    }

    public float[] get(float[] target) {
        if (target == null) {
            return new float[] { x, y };
        }
        if (target.length >= 2) {
            target[0] = x;
            target[1] = y;
        }
        return target;
    }

    public float mag() {
        return (float) Math.sqrt(x*x + y*y);
    }

    public float magSq() {
        return (x*x + y*y);
    }

    public Vector add(Vector v) {
        x += v.x;
        y += v.y;
        return this;
    }

    public Vector add(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    static public Vector add(Vector v1, Vector v2) {
        return add(v1, v2, null);
    }

    static public Vector add(Vector v1, Vector v2, Vector target) {
        if (target == null) {
            target = new Vector(v1.x + v2.x,v1.y + v2.y);
        } else {
            target.set(v1.x + v2.x, v1.y + v2.y);
        }
        return target;
    }

    public float dist(Vector v) {
        float dx = x - v.x;
        float dy = y - v.y;
        return (float) Math.sqrt(dx*dx + dy*dy);
    }

    static public float dist(Vector v1, Vector v2) {
        float dx = v1.x - v2.x;
        float dy = v1.y - v2.y;
        return (float) Math.sqrt(dx*dx + dy*dy);
    }

    public float dot(Vector v) {
        return x*v.x + y*v.y;
    }

    public float dot(float x, float y, float z) {
        return this.x*x + this.y*y;
    }

    static public float dot(Vector v1, Vector v2) {
        return v1.x*v2.x + v1.y*v2.y;
    }

    public Vector normalize(Vector target) {
        if (target == null) {
            target = new Vector();
        }
        float m = mag();
        if (m > 0) {
            target.set(x/m, y/m);
        } else {
            target.set(x, y);
        }
        return target;
    }

    public Vector mult(float n) {
        x *= n;
        y *= n;
        return this;
    }

    static public Vector mult(Vector v, float n) {
        return mult(v, n, null);
    }

    static public Vector mult(Vector v, float n, Vector target) {
        if (target == null) {
            target = new Vector(v.x*n, v.y*n);
        } else {
            target.set(v.x*n, v.y*n);
        }
        return target;
    }

    public Vector limit(float max) {
        if (magSq() > max*max) {
            normalize();
            mult(max);
        }
        return this;
    }

    public Vector normalize() {
        float m = mag();
        if (m != 0 && m != 1) {
            div(m);
        }
        return this;
    }

    public Vector div(float n) {
        x /= n;
        y /= n;
        return this;
    }

    static public Vector div(Vector v, float n) {
        return div(v, n, null);
    }

    static public Vector div(Vector v, float n, Vector target) {
        if (target == null) {
            target = new Vector(v.x/n, v.y/n);
        } else {
            target.set(v.x/n, v.y/n);
        }
        return target;
    }

    public Vector setMag(float len) {
        normalize();
        mult(len);
        return this;
    }

    public Vector setMag(Vector target, float len) {
        target = normalize(target);
        target.mult(len);
        return target;
    }

    public float heading() {
        float angle = (float) Math.atan2(y, x);
        return angle;
    }

    @Override
    public String toString() {
        return "[ " + x + ", " + y + " ]";
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector)) {
            return false;
        }
        final Vector p = (Vector) obj;
        return x == p.x && y == p.y;
    }


    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + Float.floatToIntBits(x);
        result = 31 * result + Float.floatToIntBits(y);
        return result;
    }
}