package de.fraunhofer.iais.eis;

import de.fraunhofer.iais.eis.util.*;
import de.fraunhofer.iais.eis.*;

import javax.xml.datatype.XMLGregorianCalendar;
import java.lang.String;
import java.math.BigInteger;
import java.net.URL;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
* "Permission Per Object"
* "Table that defines access permissions for a specified object. The object is any referable element in the AAS. Additionally object attributes can be defined that further specify the kind of object the permissions apply to."@en
*/
@KnownSubtypes({
	@KnownSubtypes.Type(value = DefaultPermissionsPerObject.class)
})
public interface PermissionsPerObject {

	// standard methods

	/**
	* This function retrieves the ID of the current object (can be set via the constructor of the builder class)
	* @return ID of current object as URI
	*/
	public URI getId();

	/**
	* This function retrieves a human readable labels about the current class, as defined in the ontology.
	* This label could, for example, be used as a field heading in a user interface
	* @return Human readable labels
	*/
	public List<TypedLiteral> getLabels();

	/**
	* This function retrieves a human readable explanatory comments about the current class, as defined in the ontology.
	* This comment could, for example, be used as a tooltip in a user interface
	* @return Human readable explanatory comments
	*/
	public List<TypedLiteral> getComments();

	// accessor methods as derived from the Asset Administration Shell ontology


	/**
	* "Element to which permission shall be assigned."@en
	* @return Returns the Referable for the property object.
	* More information under https://admin-shell.io/aas/3/0/RC01/PermissionsPerObject/object
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/PermissionsPerObject/object")
	public Referable getObject();

	/**
	* "Permissions assigned to the object. The permissions hold for all subjects as specified in the access permission rule."@en
	* @return Returns the List of Permissions for the property permissions.
	* More information under https://admin-shell.io/aas/3/0/RC01/PermissionsPerObject/permission
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/PermissionsPerObject/permission")
	public List<Permission> getPermissions();

	/**
	* "Target object attributes that need to be fulfilled so that the access permissions apply to the accessing subject."@en
	* @return Returns the ObjectAttributes for the property targetObjectAttributes.
	* More information under https://admin-shell.io/aas/3/0/RC01/PermissionsPerObject/targetObjectAttributes
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/PermissionsPerObject/targetObjectAttributes")
	public ObjectAttributes getTargetObjectAttributes();

}
