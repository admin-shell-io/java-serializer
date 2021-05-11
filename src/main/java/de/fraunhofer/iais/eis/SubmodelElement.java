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
* "Submodel Element"
* "A submodel element is an element suitable for the description and differentiation of assets."@en
* "The concept of type and instance applies to submodel elements. Properties are special submodel elements. The property types are defined in dictionaries (like the IEC Common Data Dictionary or eCl@ss), they do not have a value. The property type (kind=Type) is also called data element type in some standards. The property instances (kind=Instance) typically have a value. A property instance is also called property-value pair in certain standards."@en
*/
@KnownSubtypes({
	@KnownSubtypes.Type(value = DefaultSubmodelElement.class),
	@KnownSubtypes.Type(value = RelationshipElement.class),
	@KnownSubtypes.Type(value = DataElement.class),
	@KnownSubtypes.Type(value = File.class),
	@KnownSubtypes.Type(value = Event.class),
	@KnownSubtypes.Type(value = Capability.class),
	@KnownSubtypes.Type(value = Entity.class),
	@KnownSubtypes.Type(value = EventElement.class),
	@KnownSubtypes.Type(value = EventMessage.class),
	@KnownSubtypes.Type(value = Operation.class),
	@KnownSubtypes.Type(value = SubmodelElementCollection.class)
})
public interface SubmodelElement extends Referable, Qualifiable, HasDataSpecification, HasKind, HasSemantics {

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


}
