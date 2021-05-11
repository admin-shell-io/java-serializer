package de.fraunhofer.iais.eis;

import java.net.URI;
import java.util.List;

import de.fraunhofer.iais.eis.util.KnownSubtypes;
import de.fraunhofer.iais.eis.util.TypedLiteral;

/**
 * "Asset" "An Asset describes meta data of an asset that is represented by an
 * AAS. The asset may either represent an asset type or an asset instance. The
 * asset has a globally unique identifier plus - if needed - additional domain
 * specific (proprietary) identifiers."@en "Objects may be known in the form of
 * a type or of an instance. An object in the planning phase is known as a
 * type"@en
 */
@KnownSubtypes({ @KnownSubtypes.Type(value = DefaultAsset.class) })
public interface Asset extends HasDataSpecification, Identifiable {

	// standard methods

	/**
	 * This function retrieves the ID of the current object (can be set via the
	 * constructor of the builder class)
	 * 
	 * @return ID of current object as URI
	 */
	@Override
	public URI getId();

	/**
	 * This function retrieves a human readable labels about the current class, as
	 * defined in the ontology. This label could, for example, be used as a field
	 * heading in a user interface
	 * 
	 * @return Human readable labels
	 */
	@Override
	public List<TypedLiteral> getLabels();

	/**
	 * This function retrieves a human readable explanatory comments about the
	 * current class, as defined in the ontology. This comment could, for example,
	 * be used as a tooltip in a user interface
	 * 
	 * @return Human readable explanatory comments
	 */
	@Override
	public List<TypedLiteral> getComments();

}
