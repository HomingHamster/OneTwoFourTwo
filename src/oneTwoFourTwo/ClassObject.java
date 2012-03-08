/**
 * Based on classes Written by
 * @author Amy James (arj18)
 * Modified by Felix Farquharson (fef)
 * This class holds all the information about a class that is not
 * part of the drag and drop functionality or the rectangle drawing
 * functionality. Its all of the plain information.
 */

package oneTwoFourTwo;
import java.util.Vector;

public class ClassObject {
	public String name;
	public String description;
	public Vector<String> attributes = new Vector<String>();
	public Vector<String> methods = new Vector<String>();
	
	/*
	 * On initial creation only assign a name and description,
	 * the other information will be set later on editing.
	 */
	public ClassObject(String name, String description){
		this.name = name;
		this.description = description;
	}

}
