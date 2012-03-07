package oneTwoFourTwo;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeGenerator {
	Vector<DragAndDropClassObject> classObjects =
		new Vector<DragAndDropClassObject>();
	
	// This regular expression will spilt the attribute line into the
	// three necessarry parts when it is used.
	Pattern attributePattern = 
		Pattern.compile("^([-+])\\s?([a-zA-z]+):\\s?([a-z]+)$");
	
	public CodeGenerator(Vector<DragAndDropClassObject> classObjects){
		this.classObjects = classObjects;
	}
	
	/*
	 * This method will generate the code for a single class
	 * and return a string. It must be called for every class in 
	 * the project.
	 * 
	 * It should get passed a ClassObject, but i put the links in
	 * the DragAndDropClassObject stupidly and now i cba to move them
	 * around. So it needs a DragAndDropClassObject.
	 */
	public String generateClassCode(DragAndDropClassObject classObject){
		Matcher matcher;
		String output = null;		//sets output to nothing.
		String tab = "    ";		//sets what tab is.
		
		// Add the fairly static beginning of the file:
		output += "import java.util.*;\n";
		output += "\n";
		output += "public class " + classObject.name + "{\n";
		output += "\n";

		String attribute;
		
		//For every attribute the class has do the following:
		for (int i = 0; i < classObject.classObject.attributes.size(); i++){
			attribute = classObject.classObject.attributes.get(i);
			// 1 - Indent the line
			output += tab;
			// 2 - Apply the regex
			matcher = attributePattern.matcher(attribute);
			// 3 - Check it worked
			if (matcher.matches() && matcher.groupCount()==3){
				
				// 4 - Print public or private:
				if (matcher.group(1).equals("-")){
					output += "private ";
				} else if (matcher.group(1).equals("+")){
					output += "public ";
				}
				
				// 5 - print the rest of the line in correct
				// order with newline.
				output += matcher.group(3) + " " + matcher.group(2) 
							+ "() {\n";
			} else { // If there is no match
				// Stick an error in the output. (as comment);
				output += "//error reading " + attribute + "\n";
			}
		}
		
		//TODO: interpret the methods..
		
		return output;
	}
}

// ^([-+])\s?([a-zA-z]+):\s?([a-z]+)$
