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
* "Key"
* "A key is a reference to an element by its id."@en
*/
@KnownSubtypes({
	@KnownSubtypes.Type(value = DefaultKey.class)
})
public interface Key {

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
	* "Type of the key value. In case of idType = idShort local shall be true. In case type=GlobalReference idType shall not be IdShort."@en
	* "Constraint AASd-080: In case Key/type == GlobalReference idType shall not be any LocalKeyType (IdShort, FragmentId)."@en
	* "Constraint AASd-081: In case Key/type==AssetAdministrationShell Key/idType shall not be any  LocalKeyType (IdShort, FragmentId)."@en
	* @return Returns the KeyType for the property idType.
	* More information under https://admin-shell.io/aas/3/0/RC01/Key/idType
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Key/idType")
	public KeyType getIdType();

	/**
	* "Denote which kind of entity is referenced. In case type = GlobalReference then the element is a global unique id. In all other cases the key references a model element of the same or of another AAS. The name of the model element is explicitly listed."@en
	* @return Returns the KeyElements for the property type.
	* More information under https://admin-shell.io/aas/3/0/RC01/Key/type
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Key/type")
	public KeyElements getType();

	/**
	* "The key value, for example an IRDI if the idType=IRDI."@en
	* @return Returns the String for the property value.
	* More information under https://admin-shell.io/aas/3/0/RC01/Key/value
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Key/value")
	public String getValue();

}
