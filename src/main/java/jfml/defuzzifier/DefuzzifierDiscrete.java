/**************************************************************
      GNU GENERAL PUBLIC LICENSE - Version 3 

  JFML: A Java Library for the IEEE Standard for Fuzzy Markup Language
  (IEEE Std 1855-2016). Copyright (C) 2017

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

     You should have received a copy of the GNU General Public License
     along with this program.  If not, see <http://www.gnu.org/licenses/>.

  Contact information: <http://www.uco.es/JFML>

  J.M. Soto-Hidalgo & Jose M. Alonso & Jesus Alcala-Fdez
 **************************************************************/
package jfml.defuzzifier;

import java.util.HashMap;
import java.util.Iterator;

public abstract class DefuzzifierDiscrete extends Defuzzifier implements Iterable<Float> {

	/** 
	 * Function values: A generic discrete function 
	 * 			x = [x_1, x_2, .... , x_n]
	 * 			y = [y_1, y_2, .... , y_n]
	 * 			y_i = f[x_i]
	 * Values are stored in 'discreteValues' hash
	 */
	protected HashMap<Float, Float> discreteValues;

	public DefuzzifierDiscrete(float leftDomain, float rightDomain) {
		super();
		discrete = true;
		min=leftDomain;
		max=rightDomain;
		discreteValues = new HashMap<Float, Float>();
	}
	
	@Override
	public abstract float defuzzify();

	/** Get a point's 'y' value */
	public float getDiscreteValue(float x) {
		Float y = discreteValues.get(x);
		if( y == null ) return 0;
		return y.floatValue();
	}

	/** Get an iterator (on discreteValues' keys) */
	@Override
	public Iterator<Float> iterator() {
		return discreteValues.keySet().iterator();
	}

	/** Reset values */
	@Override
	public void reset() {
		if( discreteValues != null ) { // Set all values to 0
			for( Float key : this )
				discreteValues.put(key, 0.0f);
		}
	}

	/** Set a point */
	public void setPoint(float x, float y) {
		discreteValues.put(x, y);
	}
	

	/** How many points are there in this defuzzifier */
	public int size() {
		return discreteValues.size();
	}

}
