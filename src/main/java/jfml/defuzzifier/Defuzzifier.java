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

/**
 * Abstract class for representing the Defuzzifier concept
 * @author sotillo19
 *
 */
public abstract class Defuzzifier{

	protected boolean discrete;
	
	protected float max; // Where function ends 
	protected float min; // Where function begins

	/** 
	 * @return NaN if no rule inferred this variable 
	 */
	public abstract float defuzzify();

	/**
	 * Gets the defuzzifier name
	 * @return
	 */
	public String getName() {
		String str = this.getClass().getName();
		String dfStr = "Defuzzifier";
		int ind = str.lastIndexOf('.');
		if (ind >= 0) {
			str = str.substring(ind + 1);
			if (str.startsWith(dfStr)) str = str.substring(dfStr.length());
		}
		return str;
	}

	/**
	 * Gets if the defuzzifier is continuous or discrete
	 * @return true if the defuzzifier is discrete or false otherwise
	 */
	public boolean isDiscrete() {
		return discrete;
	}
	
	/**
	 * Sets if the defuzzifier is continuous or discrete
	 * @param discrete
	 */
	public void setDiscrete(boolean discrete) {
		this.discrete = discrete;
	}

	/** Reset defuzzifier values, this method is invoked on every rule */
	public abstract void reset();


	@Override
	public String toString() {
		return getName();
	}
}
