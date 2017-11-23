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
package jfml.knowledgebase.variable;

/**
 * Java class for representing the values w and z in the tsukamotoVariableType
 * @author sotillo19
 *
 */
public class WZ {

	float w;
	float z;
	
	public WZ(float w, float z){
		this.w=w;
		this.z=z;
	}

	/**
	 * @return the w value
	 */
	public float getW() {
		return w;
	}

	/**
	 * @return the z value
	 */
	public float getZ() {
		return z;
	}

	/**
	 * @param w the w to set
	 */
	public void setW(float w) {
		this.w = w;
	}

	/**
	 * @param z the z to set
	 */
	public void setZ(float z) {
		this.z = z;
	}
	
	@Override
	public String toString(){
		return "WZ:["+w+", "+z+"]";
	}
	
}
