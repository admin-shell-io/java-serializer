//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 generiert 
// Siehe <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2021.07.19 um 12:09:30 PM CEST 
//


package org.opcfoundation.ua._2008._02.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.kscs.util.jaxb.Buildable;
import com.kscs.util.jaxb.PropertyTree;
import com.kscs.util.jaxb.PropertyTreeUse;


/**
 * <p>Java-Klasse für ListOfSimpleAttributeOperand complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ListOfSimpleAttributeOperand"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SimpleAttributeOperand" type="{http://opcfoundation.org/UA/2008/02/Types.xsd}SimpleAttributeOperand" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListOfSimpleAttributeOperand", propOrder = {
    "simpleAttributeOperand"
})
public class ListOfSimpleAttributeOperand {

    @XmlElement(name = "SimpleAttributeOperand", nillable = true)
    protected List<SimpleAttributeOperand> simpleAttributeOperand;

    /**
     * Gets the value of the simpleAttributeOperand property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the simpleAttributeOperand property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSimpleAttributeOperand().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SimpleAttributeOperand }
     * 
     * 
     */
    public List<SimpleAttributeOperand> getSimpleAttributeOperand() {
        if (simpleAttributeOperand == null) {
            simpleAttributeOperand = new ArrayList<SimpleAttributeOperand>();
        }
        return this.simpleAttributeOperand;
    }

    /**
     * Copies all state of this object to a builder. This method is used by the {@link
     * #copyOf} method and should not be called directly by client code.
     * 
     * @param _other
     *     A builder instance to which the state of this object will be copied.
     */
    public<_B >void copyTo(final ListOfSimpleAttributeOperand.Builder<_B> _other) {
        if (this.simpleAttributeOperand == null) {
            _other.simpleAttributeOperand = null;
        } else {
            _other.simpleAttributeOperand = new ArrayList<SimpleAttributeOperand.Builder<ListOfSimpleAttributeOperand.Builder<_B>>>();
            for (SimpleAttributeOperand _item: this.simpleAttributeOperand) {
                _other.simpleAttributeOperand.add(((_item == null)?null:_item.newCopyBuilder(_other)));
            }
        }
    }

    public<_B >ListOfSimpleAttributeOperand.Builder<_B> newCopyBuilder(final _B _parentBuilder) {
        return new ListOfSimpleAttributeOperand.Builder<_B>(_parentBuilder, this, true);
    }

    public ListOfSimpleAttributeOperand.Builder<Void> newCopyBuilder() {
        return newCopyBuilder(null);
    }

    public static ListOfSimpleAttributeOperand.Builder<Void> builder() {
        return new ListOfSimpleAttributeOperand.Builder<Void>(null, null, false);
    }

    public static<_B >ListOfSimpleAttributeOperand.Builder<_B> copyOf(final ListOfSimpleAttributeOperand _other) {
        final ListOfSimpleAttributeOperand.Builder<_B> _newBuilder = new ListOfSimpleAttributeOperand.Builder<_B>(null, null, false);
        _other.copyTo(_newBuilder);
        return _newBuilder;
    }

    /**
     * Copies all state of this object to a builder. This method is used by the {@link
     * #copyOf} method and should not be called directly by client code.
     * 
     * @param _other
     *     A builder instance to which the state of this object will be copied.
     */
    public<_B >void copyTo(final ListOfSimpleAttributeOperand.Builder<_B> _other, final PropertyTree _propertyTree, final PropertyTreeUse _propertyTreeUse) {
        final PropertyTree simpleAttributeOperandPropertyTree = ((_propertyTree == null)?null:_propertyTree.get("simpleAttributeOperand"));
        if (((_propertyTreeUse == PropertyTreeUse.INCLUDE)?(simpleAttributeOperandPropertyTree!= null):((simpleAttributeOperandPropertyTree == null)||(!simpleAttributeOperandPropertyTree.isLeaf())))) {
            if (this.simpleAttributeOperand == null) {
                _other.simpleAttributeOperand = null;
            } else {
                _other.simpleAttributeOperand = new ArrayList<SimpleAttributeOperand.Builder<ListOfSimpleAttributeOperand.Builder<_B>>>();
                for (SimpleAttributeOperand _item: this.simpleAttributeOperand) {
                    _other.simpleAttributeOperand.add(((_item == null)?null:_item.newCopyBuilder(_other, simpleAttributeOperandPropertyTree, _propertyTreeUse)));
                }
            }
        }
    }

    public<_B >ListOfSimpleAttributeOperand.Builder<_B> newCopyBuilder(final _B _parentBuilder, final PropertyTree _propertyTree, final PropertyTreeUse _propertyTreeUse) {
        return new ListOfSimpleAttributeOperand.Builder<_B>(_parentBuilder, this, true, _propertyTree, _propertyTreeUse);
    }

    public ListOfSimpleAttributeOperand.Builder<Void> newCopyBuilder(final PropertyTree _propertyTree, final PropertyTreeUse _propertyTreeUse) {
        return newCopyBuilder(null, _propertyTree, _propertyTreeUse);
    }

    public static<_B >ListOfSimpleAttributeOperand.Builder<_B> copyOf(final ListOfSimpleAttributeOperand _other, final PropertyTree _propertyTree, final PropertyTreeUse _propertyTreeUse) {
        final ListOfSimpleAttributeOperand.Builder<_B> _newBuilder = new ListOfSimpleAttributeOperand.Builder<_B>(null, null, false);
        _other.copyTo(_newBuilder, _propertyTree, _propertyTreeUse);
        return _newBuilder;
    }

    public static ListOfSimpleAttributeOperand.Builder<Void> copyExcept(final ListOfSimpleAttributeOperand _other, final PropertyTree _propertyTree) {
        return copyOf(_other, _propertyTree, PropertyTreeUse.EXCLUDE);
    }

    public static ListOfSimpleAttributeOperand.Builder<Void> copyOnly(final ListOfSimpleAttributeOperand _other, final PropertyTree _propertyTree) {
        return copyOf(_other, _propertyTree, PropertyTreeUse.INCLUDE);
    }

    public static class Builder<_B >implements Buildable
    {

        protected final _B _parentBuilder;
        protected final ListOfSimpleAttributeOperand _storedValue;
        private List<SimpleAttributeOperand.Builder<ListOfSimpleAttributeOperand.Builder<_B>>> simpleAttributeOperand;

        public Builder(final _B _parentBuilder, final ListOfSimpleAttributeOperand _other, final boolean _copy) {
            this._parentBuilder = _parentBuilder;
            if (_other!= null) {
                if (_copy) {
                    _storedValue = null;
                    if (_other.simpleAttributeOperand == null) {
                        this.simpleAttributeOperand = null;
                    } else {
                        this.simpleAttributeOperand = new ArrayList<SimpleAttributeOperand.Builder<ListOfSimpleAttributeOperand.Builder<_B>>>();
                        for (SimpleAttributeOperand _item: _other.simpleAttributeOperand) {
                            this.simpleAttributeOperand.add(((_item == null)?null:_item.newCopyBuilder(this)));
                        }
                    }
                } else {
                    _storedValue = _other;
                }
            } else {
                _storedValue = null;
            }
        }

        public Builder(final _B _parentBuilder, final ListOfSimpleAttributeOperand _other, final boolean _copy, final PropertyTree _propertyTree, final PropertyTreeUse _propertyTreeUse) {
            this._parentBuilder = _parentBuilder;
            if (_other!= null) {
                if (_copy) {
                    _storedValue = null;
                    final PropertyTree simpleAttributeOperandPropertyTree = ((_propertyTree == null)?null:_propertyTree.get("simpleAttributeOperand"));
                    if (((_propertyTreeUse == PropertyTreeUse.INCLUDE)?(simpleAttributeOperandPropertyTree!= null):((simpleAttributeOperandPropertyTree == null)||(!simpleAttributeOperandPropertyTree.isLeaf())))) {
                        if (_other.simpleAttributeOperand == null) {
                            this.simpleAttributeOperand = null;
                        } else {
                            this.simpleAttributeOperand = new ArrayList<SimpleAttributeOperand.Builder<ListOfSimpleAttributeOperand.Builder<_B>>>();
                            for (SimpleAttributeOperand _item: _other.simpleAttributeOperand) {
                                this.simpleAttributeOperand.add(((_item == null)?null:_item.newCopyBuilder(this, simpleAttributeOperandPropertyTree, _propertyTreeUse)));
                            }
                        }
                    }
                } else {
                    _storedValue = _other;
                }
            } else {
                _storedValue = null;
            }
        }

        public _B end() {
            return this._parentBuilder;
        }

        protected<_P extends ListOfSimpleAttributeOperand >_P init(final _P _product) {
            if (this.simpleAttributeOperand!= null) {
                final List<SimpleAttributeOperand> simpleAttributeOperand = new ArrayList<SimpleAttributeOperand>(this.simpleAttributeOperand.size());
                for (SimpleAttributeOperand.Builder<ListOfSimpleAttributeOperand.Builder<_B>> _item: this.simpleAttributeOperand) {
                    simpleAttributeOperand.add(_item.build());
                }
                _product.simpleAttributeOperand = simpleAttributeOperand;
            }
            return _product;
        }

        /**
         * Fügt Werte zur Eigenschaft "simpleAttributeOperand" hinzu.
         * 
         * @param simpleAttributeOperand
         *     Werte, die zur Eigenschaft "simpleAttributeOperand" hinzugefügt werden.
         */
        public ListOfSimpleAttributeOperand.Builder<_B> addSimpleAttributeOperand(final Iterable<? extends SimpleAttributeOperand> simpleAttributeOperand) {
            if (simpleAttributeOperand!= null) {
                if (this.simpleAttributeOperand == null) {
                    this.simpleAttributeOperand = new ArrayList<SimpleAttributeOperand.Builder<ListOfSimpleAttributeOperand.Builder<_B>>>();
                }
                for (SimpleAttributeOperand _item: simpleAttributeOperand) {
                    this.simpleAttributeOperand.add(new SimpleAttributeOperand.Builder<ListOfSimpleAttributeOperand.Builder<_B>>(this, _item, false));
                }
            }
            return this;
        }

        /**
         * Setzt den neuen Wert der Eigenschaft "simpleAttributeOperand" (Vorher
         * zugewiesener Wert wird ersetzt)
         * 
         * @param simpleAttributeOperand
         *     Neuer Wert der Eigenschaft "simpleAttributeOperand".
         */
        public ListOfSimpleAttributeOperand.Builder<_B> withSimpleAttributeOperand(final Iterable<? extends SimpleAttributeOperand> simpleAttributeOperand) {
            if (this.simpleAttributeOperand!= null) {
                this.simpleAttributeOperand.clear();
            }
            return addSimpleAttributeOperand(simpleAttributeOperand);
        }

        /**
         * Fügt Werte zur Eigenschaft "simpleAttributeOperand" hinzu.
         * 
         * @param simpleAttributeOperand
         *     Werte, die zur Eigenschaft "simpleAttributeOperand" hinzugefügt werden.
         */
        public ListOfSimpleAttributeOperand.Builder<_B> addSimpleAttributeOperand(SimpleAttributeOperand... simpleAttributeOperand) {
            addSimpleAttributeOperand(Arrays.asList(simpleAttributeOperand));
            return this;
        }

        /**
         * Setzt den neuen Wert der Eigenschaft "simpleAttributeOperand" (Vorher
         * zugewiesener Wert wird ersetzt)
         * 
         * @param simpleAttributeOperand
         *     Neuer Wert der Eigenschaft "simpleAttributeOperand".
         */
        public ListOfSimpleAttributeOperand.Builder<_B> withSimpleAttributeOperand(SimpleAttributeOperand... simpleAttributeOperand) {
            withSimpleAttributeOperand(Arrays.asList(simpleAttributeOperand));
            return this;
        }

        /**
         * Erzeugt einen neuen "Builder" zum Zusammenbauen eines zusätzlichen Wertes für
         * die Eigenschaft "SimpleAttributeOperand".
         * Mit {@link
         * org.opcfoundation.ua._2008._02.types.SimpleAttributeOperand.Builder#end()} geht
         * es zurück zum aktuellen Builder.
         * 
         * @return
         *     Ein neuer "Builder" zum Zusammenbauen eines zusätzlichen Wertes für die
         *     Eigenschaft "SimpleAttributeOperand".
         *     Mit {@link
         *     org.opcfoundation.ua._2008._02.types.SimpleAttributeOperand.Builder#end()} geht
         *     es zurück zum aktuellen Builder.
         */
        public SimpleAttributeOperand.Builder<? extends ListOfSimpleAttributeOperand.Builder<_B>> addSimpleAttributeOperand() {
            if (this.simpleAttributeOperand == null) {
                this.simpleAttributeOperand = new ArrayList<SimpleAttributeOperand.Builder<ListOfSimpleAttributeOperand.Builder<_B>>>();
            }
            final SimpleAttributeOperand.Builder<ListOfSimpleAttributeOperand.Builder<_B>> simpleAttributeOperand_Builder = new SimpleAttributeOperand.Builder<ListOfSimpleAttributeOperand.Builder<_B>>(this, null, false);
            this.simpleAttributeOperand.add(simpleAttributeOperand_Builder);
            return simpleAttributeOperand_Builder;
        }

        @Override
        public ListOfSimpleAttributeOperand build() {
            if (_storedValue == null) {
                return this.init(new ListOfSimpleAttributeOperand());
            } else {
                return ((ListOfSimpleAttributeOperand) _storedValue);
            }
        }

        public ListOfSimpleAttributeOperand.Builder<_B> copyOf(final ListOfSimpleAttributeOperand _other) {
            _other.copyTo(this);
            return this;
        }

        public ListOfSimpleAttributeOperand.Builder<_B> copyOf(final ListOfSimpleAttributeOperand.Builder _other) {
            return copyOf(_other.build());
        }

    }

    public static class Select
        extends ListOfSimpleAttributeOperand.Selector<ListOfSimpleAttributeOperand.Select, Void>
    {


        Select() {
            super(null, null, null);
        }

        public static ListOfSimpleAttributeOperand.Select _root() {
            return new ListOfSimpleAttributeOperand.Select();
        }

    }

    public static class Selector<TRoot extends com.kscs.util.jaxb.Selector<TRoot, ?> , TParent >
        extends com.kscs.util.jaxb.Selector<TRoot, TParent>
    {

        private SimpleAttributeOperand.Selector<TRoot, ListOfSimpleAttributeOperand.Selector<TRoot, TParent>> simpleAttributeOperand = null;

        public Selector(final TRoot root, final TParent parent, final String propertyName) {
            super(root, parent, propertyName);
        }

        @Override
        public Map<String, PropertyTree> buildChildren() {
            final Map<String, PropertyTree> products = new HashMap<String, PropertyTree>();
            products.putAll(super.buildChildren());
            if (this.simpleAttributeOperand!= null) {
                products.put("simpleAttributeOperand", this.simpleAttributeOperand.init());
            }
            return products;
        }

        public SimpleAttributeOperand.Selector<TRoot, ListOfSimpleAttributeOperand.Selector<TRoot, TParent>> simpleAttributeOperand() {
            return ((this.simpleAttributeOperand == null)?this.simpleAttributeOperand = new SimpleAttributeOperand.Selector<TRoot, ListOfSimpleAttributeOperand.Selector<TRoot, TParent>>(this._root, this, "simpleAttributeOperand"):this.simpleAttributeOperand);
        }

    }

}
