package vbo.toolbox;

import org.lwjgl.util.vector.Vector2f;

/**
 * Created by levin on 28.04.2017.
 */
public class Gerade {
    // y = mx + b

    private float m;
    private float b;
    private boolean infinity;    //m = x

    public Gerade(float m, float b){
        this.m = m;
        this.b = b;
        infinity = false;
    }

    public static Gerade createFromPoints(Vector2f a, Vector2f b){
        /*float dx = Maths.getHigher(new Vector2f(a.getX(), b.getX())) - Maths.getLower(new Vector2f(a.getX(), b.getX()));
        float dy = Maths.getHigher(new Vector2f(a.getY(), b.getY())) - Maths.getLower(new Vector2f(a.getY(), b.getY()));*/
        float dy;
        float dx = Maths.getHigher(new Vector2f(a.getX(), b.getX())) - Maths.getLower(new Vector2f(a.getX(), b.getX()));
        if(a.x == Maths.getHigher(new Vector2f(a.x,b.x))){
            dy = a.y - b.y;
        }else {
            dy = b.y - a.y;
        }


        float m = dy / dx;
        float by = a.getY() - (m*a.getX());
        //System.out.println(a+"    "+b+"     "+dx);
        if(dx == 0){
            Gerade out = new Gerade(a.getX(),by);
            out.infinity = true;
            return out;
        }
        return new Gerade(m,by);
    }

    public float setY(float y){
        if(infinity){
            return m;
        }
        return (y-b) / m;
    }

    public float setX(float x){
        if(infinity){
            return 0;
        }
        return (m*x)+b;
    }

    public boolean punktprobe(Vector2f point){
        if(infinity){
            if(point.getX() == m)return true;
            else return false;
        }
        if(point.getY() == (m*point.getX()) + b){
            return true;
        }
        return false;
    }

    public Vector2f schneiden(Gerade g)throws SchneidetNichtException, IsTheSameException{
        if(infinity){
            if(g.isInfinite()){
                if(m == g.m){
                    throw new IsTheSameException(this,g);
                }else {
                    throw new SchneidetNichtException(this,g);
                }
            }
            Vector2f out = new Vector2f(m,g.setX(m));
            return out;
        }
        if(g.isInfinite()){
            return g.schneiden(this);
        }
        if(g.punktprobe(new Vector2f(0,setX(0)))) {
            throw new IsTheSameException(this, g);
        }
        if(g.getM() == m){
            throw new SchneidetNichtException(this,g);
        }
        float try1 = b - g.getB();
        float try2 = g.getM() - m;
        float x = try1 / try2;
        //System.out.println(g.isInfinite());
        if(infinity)System.out.println("fehler");
        float y = setX(x);
        //System.out.println(x+"   " +y);
        return new Vector2f(x,y);
    }

    public float getM(){
        return m;
    }

    public float getB(){
        return b;
    }

    public boolean isInfinite(){
        return infinity;
    }

    public class SchneidetNichtException extends Exception{
        public Gerade sourceA;
        public Gerade sourceB;

        public SchneidetNichtException(Gerade a, Gerade b){
            this.sourceA = a;
            this.sourceB = b;
        }

        public String getName(){return "Schneidet nicht";}
    }

    public class IsTheSameException extends Exception{
        public Gerade sourceA;
        public Gerade sourceB;

        public IsTheSameException(Gerade a, Gerade b){
            this.sourceA = a;
            this.sourceB = b;
        }

        public String getName(){return "Is the same";}
    }
}
