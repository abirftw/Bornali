package com.sc4ever.bornali;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Collections;

public class Alphabets {

    private int width = getScreenWidth()/10 ;
    private int height = getScreenHeight()/10 ;

    private static int getScreenWidth(){
        return Resources.getSystem().getDisplayMetrics().widthPixels ;
    }

    private static int getScreenHeight(){
        return Resources.getSystem().getDisplayMetrics().heightPixels ;
    }

    public ArrayList<dots> A() { // construct the shape of A
        ArrayList<dots> dotsOfA = new ArrayList() ;
        float j ;
        float slope = (6*height)/(3*width) ;
        float constant = (8*width*slope/10) - (7*height/10) ;
        for (float i = 2 * width; i <= 8 * width; i += width/2) {
            if(i <= 5*width) {
                j = height * 10 - i*slope ;
            }
            else {
                j = i*slope - constant*21/2 ;
            }
            dotsOfA.add(new dots(i, j));
        }
        for(float i = 35/10*width ; i <= 65/10*width ; i+= width){
            j = 632 ;
            dotsOfA.add(new dots(i, j));
        }
        return  dotsOfA ;
    }

    public ArrayList<dots> B(){
            ArrayList <dots> dotsOfB = new ArrayList<dots>();
            for(float y = 2*height ; y <= 7*height ; y+= height/2){
                float x = 3*width ;
                dotsOfB.add(new dots(x,y));
            }
            dotsOfB.add(new dots(4*width , (float)2*height)) ;
            dotsOfB.add(new dots(5*width , (float)2*height)) ;
            dotsOfB.add(new dots(6*width,(float)21/10*height)) ;
            dotsOfB.add(new dots(7*width,(float)25/10*height)) ;
            dotsOfB.add(new dots((float)71/10*width,(float)3*height)) ;
            dotsOfB.add(new dots((float)7*width,(float)35/10*height)) ;
            dotsOfB.add(new dots((float)65/10*width,(float)39/10*height)) ;
            dotsOfB.add(new dots((float)58/10*width,(float)41/10*height)) ;
            dotsOfB.add(new dots((float)5*width,(float)43/10*height)) ;
            dotsOfB.add(new dots((float)4*width,(float)44/10*height)) ;
            //lower circle
            dotsOfB.add(new dots((float)4*width,(float)46/10*height)) ;
            dotsOfB.add(new dots((float)5*width,(float)47/10*height)) ;
            dotsOfB.add(new dots((float)6*width,(float)49/10*height)) ;
            dotsOfB.add(new dots((float)65/10*width , (float)52/10*height));
            // mid point

            dotsOfB.add(new dots((float)70/10*width,(float)55/10*height)) ;
            dotsOfB.add(new dots((float)71/10*width,(float)6*height)) ;
            dotsOfB.add(new dots((float)70/10*width,(float)65/10*height)) ;
            dotsOfB.add(new dots((float)6*width,(float)69/10*height)) ;
            dotsOfB.add(new dots((float)5*width,(float)70/10*height)) ;
            dotsOfB.add(new dots((float)4*width,(float)7*height)) ;
            return  dotsOfB ;
    }

    public ArrayList<dots> E(){
        ArrayList <dots> dotsOfE = new ArrayList<dots>() ;
        for(float y = 2*height ; y <= 7*height ; y+= height/2){
            float x = 3*width ;
            dotsOfE.add(new dots(x,y));
        }
        for(float x = 35/10*width ; x <= 8*width ; x += height/2){
            float y = 2*height ;
            dotsOfE.add(new dots(x,y));
        }
        for(float x = 35/10*width ; x <= 8*width ; x += height/2){
            float y = (float)45/10*height ;
            dotsOfE.add(new dots(x,y));
        }
        for(float x = 35/10*width ; x <= 8*width ; x += height/2){
            float y = 7*height ;
            dotsOfE.add(new dots(x,y));
        }
        return dotsOfE ;
    }

    private ArrayList<dots> D(){
        ArrayList<dots> dotsOfD = new ArrayList<dots>() ;
        for(float y = 2*height ; y <= 7*height ; y+= height/2){
            float x = 3*width ;
            dotsOfD.add(new dots(x,y));
        }

        for(float x = 4*width ; x <= 6*width ; x += width){
            float y = 2*height ;
            dotsOfD.add(new dots(x,y));
        }
        float z = (float)25/10*height ;
        for(float x = 7*width ; x <= 8*width ; x += width/2 , z += height/2){
            dotsOfD.add(new dots(x,z));
        }

        dotsOfD.add(new dots((float)82/10*width , 4*height)) ;
        dotsOfD.add(new dots((float)83/10*width , (float)45/10*height)) ;
        dotsOfD.add(new dots((float)82/10*width , 5*height)) ;

        z = (float)55/10*height ;
        for(float x = 8*width ; x >= 7*width ; x -= width/2 , z += height/2){
            dotsOfD.add(new dots(x,z));
        }
        for(float x = 4*width ; x <= 6*width ; x += width){
            float y = 7*height ;
            dotsOfD.add(new dots(x,y));
        }

        return dotsOfD ;
    }

    private ArrayList<dots> C(){
        ArrayList<dots> dotsOfC = new ArrayList<dots>() ;

        dotsOfC.add(new dots(7*width , (float)24/10*height)) ;
        dotsOfC.add(new dots(6*width , (float)21/10*height)) ;
        dotsOfC.add(new dots(5*width , 2*height)) ;
        dotsOfC.add(new dots(4*width , (float)22/10*height)) ;
        dotsOfC.add(new dots(3*width , (float)25/10*height)) ;

        dotsOfC.add(new dots((float)25/10*width , (float)28/10*height)) ;
        dotsOfC.add(new dots((float)22/10*width , (float)31/10*height)) ;
        dotsOfC.add(new dots(2*width , (float)35/10*height)) ;
        dotsOfC.add(new dots((float)19/10*width , 4*height)) ;
        dotsOfC.add(new dots((float)19/10*width , (float)45/10*height)) ;
        dotsOfC.add(new dots((float)19/10*width , 5*height)) ;

        dotsOfC.add(new dots((float)21/10*width , (float)55/10*height)) ;
        dotsOfC.add(new dots((float)25/10*width , 6*height)) ;
        dotsOfC.add(new dots(3*width , (float)63/10*height)) ;
        dotsOfC.add(new dots(4*width , (float)66/10*height)) ;
        dotsOfC.add(new dots(5*width , (float)68/10*height)) ;
        dotsOfC.add(new dots(6*width , (float)68/10*height)) ;
        dotsOfC.add(new dots(7*width , (float)65/10*height)) ;

        return dotsOfC ;
    }


}
