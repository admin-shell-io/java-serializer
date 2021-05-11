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
* "Entity"
* "An entity is a submodel element that is used to model entities."@en
* "Constraint AASd-056: The semanticId of a Entity submodel element shall only reference a ConceptDescription with the category ENTITY. The ConceptDescription describes the elements assigned to the entity via Entity/statement."@en
*/
@KnownSubtypes({
	@KnownSubtypes.Type(value = DefaultEntity.class)
})
public interface Entity extends SubmodelElement {

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
	* "Reference to the asset the entity is representing."@en
	* "The asset attribute must be set if entityType is set to \'SelfManagedEntity\'. It is empty otherwise."@en
	* "Constraint AASd-014: Either the attribute globalAssetId or externalAssetId of an Entity must be set if Entity/entityType is set to \'SelfManagedEntity\'. They are not existing otherwise."@en
	* @return Returns the Reference for the property globalAssetId.
	* More information under https://admin-shell.io/aas/3/0/RC01/Entity/globalAssetId
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Entity/globalAssetId")
	public Reference getGlobalAssetId();

	/**
	* "Reference to an identifier key value pair representing an external identifier of the asset represented by the asset administration shell. "@en
	* "The asset attribute must be set if entityType is set to \'SelfManagedEntity\'. It is empty otherwise."@en
	* "Constraint AASd-014: Either the attribute globalAssetId or externalAssetId of an Entity must be set if Entity/entityType is set to \'SelfManagedEntity\'. They are not existing otherwise."@en
	* @return Returns the IdentifierKeyValuePair for the property externalAssetId.
	* More information under https://admin-shell.io/aas/3/0/RC01/Entity/externalAssetId
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Entity/externalAssetId")
	public IdentifierKeyValuePair getExternalAssetId();

	/**
	* "Describes whether the entity is a co-managed entity or a self-managed entity."@en
	* @return Returns the EntityType for the property entityType.
	* More information under https://admin-shell.io/aas/3/0/RC01/Entity/entityType
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Entity/entityType")
	public EntityType getEntityType();

	/**
	* "Describes statements applicable to the entity by a set of submodel elements, typically with a qualified value."@en
	* @return Returns the List of SubmodelElements for the property statements.
	* More information under https://admin-shell.io/aas/3/0/RC01/Entity/statement
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Entity/statement")
	public List<SubmodelElement> getStatements();

}
