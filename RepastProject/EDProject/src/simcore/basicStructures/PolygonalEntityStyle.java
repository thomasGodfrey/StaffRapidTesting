package simcore.basicStructures;

import java.awt.Color;
import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;
import saf.v3d.scene.VSpatial;

public class PolygonalEntityStyle extends DefaultStyleOGL2D{

	 

    //Constructs our own VSpatial for our entity based on its intrinsic vertices.

    @Override

    public VSpatial getVSpatial(Object object, VSpatial spatial){
	
    	Location pLoc = (Location)object;
	    //Create the spatial and return it.
	    spatial = shapeFactory.createRectangle(pLoc.getW(), pLoc.getH());
	    return spatial;
    }

   

    //Override the below to change the other style properties:

    public Color getColor(Object object) {

                    return Color.GRAY;

    }

    public Color getBorderColor(Object object) {

                    return Color.BLACK;

    }

    public int getBorderSize(Object object) {

                    return 1;

    }

    public float getScale(Object object) {

                    return 1;

    }

}
