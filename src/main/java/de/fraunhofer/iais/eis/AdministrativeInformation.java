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
* "Administrative Information"
* "Every Identifiable may have administrative information. Administrative information includes for example 1) Information about the version of the element 2) Information about who created or who made the last change to the element 3) Information about the languages available in case the element contains text, for translating purposed also themmaster or default language may be definedIn the first version of the AAS metamodel only version information as defined by IEC 61360 is defined. In later versions additional attributes may be added."@en
*/
@KnownSubtypes({
	@KnownSubtypes.Type(value = DefaultAdministrativeInformation.class)
})
public interface AdministrativeInformation extends HasDataSpecification {

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
	* "Version of the element."@en
	* @return Returns the String for the property version.
	* More information under https://admin-shell.io/aas/3/0/RC01/AdministrativeInformation/version
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AdministrativeInformation/version")
	public String getVersion();

	/**
	* "Revision of the element."@en
	* "Constraint AASd-005: A revision requires a version. This means, if there is no version there is no revision neither."@en
	* @return Returns the String for the property revision.
	* More information under https://admin-shell.io/aas/3/0/RC01/AdministrativeInformation/revision
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AdministrativeInformation/revision")
	public String getRevision();

}
