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
* "Permission"
* "Description of a single permission."@en
*/
@KnownSubtypes({
	@KnownSubtypes.Type(value = DefaultPermission.class)
})
public interface Permission {

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
	* "Description of the kind of permission. Possible kind of permission also include the denial of the permission."@en
	* @return Returns the PermissionKind for the property kindOfPermission.
	* More information under https://admin-shell.io/aas/3/0/RC01/Permission/kindOfPermission
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Permission/kindOfPermission")
	public PermissionKind getKindOfPermission();

	/**
	* "Reference to a property that defines the semantics of the permission."@en
	* "Constraint AASs-010: The property referenced in Permission/permission shall have the category \'CONSTANT\'."@en
	* "Constraint AASs-011: The property referenced in Permission/permission shall be part of the submodel that is referenced within the \'selectablePermissions\' attribute of \'AccessControl\'."@en
	* @return Returns the Reference for the property permission.
	* More information under https://admin-shell.io/aas/3/0/RC01/Permission/permission
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Permission/permission")
	public Reference getPermission();

}
