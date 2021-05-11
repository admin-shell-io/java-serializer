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

public class DefaultPermission implements Permission {

	protected URI id;

	//List of all labels of this class
	@JsonIgnore
	protected List<TypedLiteral> labels = Arrays.asList(new TypedLiteral("Permission", ""));

	//List of all comments of this class
	@JsonIgnore
	protected List<TypedLiteral> comments = Arrays.asList(new TypedLiteral("Description of a single permission.", "en"));

	// instance fields as derived from the Asset Administration Shell ontology

	/**
	* "has kind of permission"
	* "Description of the kind of permission. Possible kind of permission also include the denial of the permission."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Permission/kindOfPermission")
	protected PermissionKind kindOfPermission;


	/**
	* "has permission"
	* "Reference to a property that defines the semantics of the permission."@en
	* "Constraint AASs-010: The property referenced in Permission/permission shall have the category \'CONSTANT\'."@en
	* "Constraint AASs-011: The property referenced in Permission/permission shall be part of the submodel that is referenced within the \'selectablePermissions\' attribute of \'AccessControl\'."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Permission/permission")
	protected Reference permission;


	// no manual construction
	protected DefaultPermission() {
		id = VocabUtil.getInstance().createRandomUrl("permission");
	}

	final public URI getId() {
		return id;
	}

	public List<TypedLiteral> getLabels() {
		return this.labels;
	}

	public List<TypedLiteral> getComments() {
		return this.comments;
	}

	@Override
	public int hashCode() {
		return Objects.hash(new Object[]{this.kindOfPermission,
			this.permission});
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (this.getClass() != obj.getClass()) {
			return false;
		} else {
			DefaultPermission other = (DefaultPermission) obj;
			return Objects.equals(this.kindOfPermission, other.kindOfPermission) &&
				Objects.equals(this.permission, other.permission);
		}
	}


	// accessor method implementations as derived from the Asset Administration Shell ontology


	@IRI("https://admin-shell.io/aas/3/0/RC01/Permission/kindOfPermission")
	final public PermissionKind getKindOfPermission() {
		return kindOfPermission;
	}
	
	final public void setKindOfPermission (PermissionKind kindOfPermission) {
		this.kindOfPermission = kindOfPermission;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/Permission/permission")
	final public Reference getPermission() {
		return permission;
	}
	
	final public void setPermission (Reference permission) {
		this.permission = permission;
	}
}
