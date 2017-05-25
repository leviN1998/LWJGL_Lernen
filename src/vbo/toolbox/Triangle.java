package vbo.toolbox;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

/**
 * Created by levin on 28.04.2017.
 */
public class Triangle {

    private Vector2f a;
    private Vector2f b;
    private Vector2f c;

    private Gerade ab;
    private Gerade ac;
    private Gerade bc;

    private boolean line;

    public Triangle(Vector2f a, Vector2f b, Vector2f c){
        this.a = a;
        this.b = b;
        this.c = c;

        ab = Gerade.createFromPoints(a,b);
        ac = Gerade.createFromPoints(a,c);
        bc = Gerade.createFromPoints(b,c);

        if(ab.punktprobe(c)){
            line = true;
        }else {
            line = false;
        }
    }

    public boolean punktprobe(Vector2f p){
        if(line){
            return ab.punktprobe(p);
        }else {
            Gerade hori = Gerade.createFromPoints(p, new Vector2f(p.getX() + 10, p.getY()));
            Gerade vert = Gerade.createFromPoints(p, new Vector2f(p.getX(), p.getY() + 10));

            Vector2f hori1, hori2 ,hori3 = null;
            Vector2f vert1,vert2,vert3 = null;
            hori1 = null;hori2=null;vert1=null;vert2=null;

            try {
                hori1 = ab.schneiden(hori);
                if(!checkLine(a,b,hori1)){
                    hori1 = null;
                }
                if(hori1 != null){

                }

            }catch (Gerade.SchneidetNichtException e){
                //System.out.println("Hori 1 false");
            }catch (Gerade.IsTheSameException e){
                if(checkLine(a,b,p)){
                    return true;
                }
            }

            try {
                hori2 = ac.schneiden(hori);
                if(!checkLine(a,c,hori2)){
                    //System.out.println("Hori2 false");
                    //System.out.println(ac.isInfinite());
                    hori2 = null;
                }else {
                    //System.out.println("Hori2 true");
                }
            }catch (Gerade.SchneidetNichtException e){
                //System.out.println("Hori2 false");
            }catch (Gerade.IsTheSameException e){
                //System.out.println("Hori2 same");
                if(checkLine(a,c,p)){

                    return true;
                }
            }

            try {
                hori3 = bc.schneiden(hori);
                //System.out.println(bc.isInfinite());
                if(!checkLine(b,c,hori3)){
                    hori3 = null;
                }//else System.out.println("Hori3 true");
            }catch (Gerade.SchneidetNichtException e){
                //System.out.println("Hori3 false");
            }catch (Gerade.IsTheSameException e){
                //System.out.println("Hori3 same");
                if(checkLine(b,c,p)){
                    return true;
                }
            }

            try {
                vert1 = ab.schneiden(vert);
                if(!checkLine(a,b,vert1)){
                    //System.out.println("vert 1 schneidet nicht");
                    vert1 = null;
                }//else System.out.println("vert1 true");
            }catch (Gerade.SchneidetNichtException e){
            }catch (Gerade.IsTheSameException e){
                if(checkLine(a,b,p)){
                    return true;
                }
            }

            try {
                vert2 = ac.schneiden(vert);
                if(!checkLine(a,c,vert2)){
                    vert2 = null;
                }
            }catch (Gerade.SchneidetNichtException e){
            }catch (Gerade.IsTheSameException e){
                //System.out.println("vert2 same");
                if(checkLine(a,c,p)){
                    return true;
                }
            }

            try {
                vert3 = bc.schneiden(vert);
                if(!checkLine(b,c,vert3)){
                    vert3 = null;
                }
            }catch (Gerade.SchneidetNichtException e){
            }catch (Gerade.IsTheSameException e){
                if(checkLine(b,c,p)){
                    return true;
                }
            }

            Vector4f horis = checkHoris(hori1,hori2,hori3,p);
            if(horis == null){
                //System.out.println("horis false");
                return false;
            }
            Vector4f verts = checkVerts(vert1,vert2,vert3,p);
            if(verts == null){
                //System.out.println("verts false");
                return false;
            }


            if(aussortieren(vert1,vert2,vert3).w <= 1){
                if(aussortieren(hori1,hori2,hori3).w <= 1){
                    return true;
                }
            }

        }

        return false;

    }

    private boolean checkLine(Vector2f p1, Vector2f p2, Vector2f toCheck){
        float higherX = Maths.getHigher(new Vector2f(p1.getX(),p2.getX()));
        float higherY = Maths.getHigher(new Vector2f(p1.getY(),p2.getY()));
        float lowerX = Maths.getLower(new Vector2f(p1.getX(),p2.getX()));
        float lowerY = Maths.getLower(new Vector2f(p1.getY(),p2.getY()));
        //System.out.println(higherX+" "+lowerX);
        //System.out.println(higherY+" "+lowerY+"    "+toCheck.x+"/"+toCheck.y);

        if(toCheck.x >= lowerX && toCheck.x <= higherX){

            if(toCheck.y >= lowerY && toCheck.y <= higherY){
                return true;
            }
        }
        return false;
    }

    private Vector4f aussortieren(Vector2f v1, Vector2f v2, Vector2f v3){
        Vector4f output = new Vector4f(0,0,0,0);
        if(v1 == null){
            output.setX(0);
            output.w++;
        } if(v2 == null){
            output.setY(0);
            output.w++;
        } if(v3 == null){
            output.setZ(0);
            output.w++;
        }
        return output;
    }

    private Vector4f checkHoris(Vector2f hori1, Vector2f hori2, Vector2f hori3, Vector2f p){
        //System.out.println(hori1.x + "    " + hori2.x+"     " + hori3.x);
        if(hori1 != null && hori2 != null && hori3 != null){
            //System.out.println("not null");
            if (hori1.x == hori2.x) hori1 = null;
            else if (hori1.x == hori3.x) hori3 = null;
            else if (hori2.x == hori3.x) hori2 = null;
        }

        if(hori1 == null){
            //System.out.println("1");
            float xN = Maths.getLower(new Vector2f(hori2.x,hori3.x));
            float xH = Maths.getHigher(new Vector2f(hori2.x,hori3.x));
            if(p.getX() >= xN && p.getX() <= xH){
                return new Vector4f(hori2.x,hori2.y,hori3.x,hori3.y);
            }else return null;
        }else if(hori2 == null){
            //System.out.println("2");
            float xN = Maths.getLower(new Vector2f(hori1.x,hori3.x));
            float xH = Maths.getHigher(new Vector2f(hori1.x,hori3.x));
            if(p.getX() >= xN && p.getX() <= xH){
                return new Vector4f(hori1.x,hori1.y,hori3.x,hori3.y);
            }else return null;
        }else if(hori3 == null){
            //System.out.println("3");
            float xN = Maths.getLower(new Vector2f(hori2.x,hori1.x));
            float xH = Maths.getHigher(new Vector2f(hori2.x,hori1.x));
            if(p.getX() >= xN && p.getX() <= xH){
                return new Vector4f(hori2.x,hori2.y,hori1.x,hori1.y);
            }else return null;
        }
        return null;
    }

    private Vector4f checkVerts(Vector2f vert1, Vector2f vert2, Vector2f vert3, Vector2f p){

        if(vert1 != null && vert2 != null && vert3 != null){
            if(vert1.y == vert2.y) vert1 = null;
            else if(vert1.y == vert3.y) vert3 = null;
            else if(vert2.y == vert3.y) vert2 = null;
        }

        if(vert1 == null){
            //System.out.println("1");
            float yN = Maths.getLower(new Vector2f(vert2.y,vert3.y));
            float yH = Maths.getHigher(new Vector2f(vert2.y,vert3.y));
            if(p.getX() >= yN && p.getX() <= yH){
                return new Vector4f(vert2.x,vert2.y,vert3.x,vert3.y);
            }else return null;
        }else if(vert2 == null){
            //System.out.println("2");
            float yN = Maths.getLower(new Vector2f(vert1.y,vert3.y));
            float yH = Maths.getHigher(new Vector2f(vert1.y,vert3.y));
            if(p.getX() >= yN && p.getX() <= yH){
                return new Vector4f(vert1.x,vert1.y,vert3.x,vert3.y);
            }else return null;
        }else if(vert3 == null){
            //System.out.println("3");
            float yN = Maths.getLower(new Vector2f(vert2.y,vert1.y));
            float yH = Maths.getHigher(new Vector2f(vert2.y,vert1.y));

            if(p.getY() >= yN && p.getY() <= yH){
                return new Vector4f(vert2.x,vert2.y,vert1.x,vert1.y);
            }else return null;
        }
        return null;
    }


}
