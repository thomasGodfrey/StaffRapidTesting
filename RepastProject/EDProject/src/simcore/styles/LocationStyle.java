package simcore.styles;

import java.awt.Color;

import EDLanguage.sandbox.Staff;
import repast.simphony.gis.styleEditor.SimpleMarkFactory;
import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;
import saf.v3d.ShapeFactory2D;
import saf.v3d.scene.VSpatial;
import simcore.basicStructures.Location;

public class LocationStyle extends DefaultStyleOGL2D {

	private static SimpleMarkFactory markFac = new SimpleMarkFactory();

	@Override
	public void init(ShapeFactory2D factory) {
		super.init(factory);
	}

	@Override
	public VSpatial getVSpatial(Object agent, VSpatial spatial) {
		if (spatial == null) {
			if(agent instanceof Location) {
				spatial = shapeFactory.createRectangle(((Location)agent).getW(), ((Location)agent).getH());
			}
		}
		return spatial;
	}

	@Override
	public Color getColor(Object object) {
		Staff a;
		if (object instanceof Staff) {
			a = (Staff) object;
			if (a.amIStayingAtHome()) {
				return Color.RED;
			} else {
				return Color.GREEN;
			}
		} 
		
		return Color.black;
	}

	@Override
	public float getRotation(Object object) {
		return 0;
	}

	@Override
	public float getScale(Object object) {
		return 10;
	}

}

