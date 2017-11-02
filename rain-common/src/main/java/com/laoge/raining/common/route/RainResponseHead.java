/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.laoge.raining.common.route;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-11-02")
public class RainResponseHead implements org.apache.thrift.TBase<RainResponseHead, RainResponseHead._Fields>, java.io.Serializable, Cloneable, Comparable<RainResponseHead> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RainResponseHead");

  private static final org.apache.thrift.protocol.TField CLASS_RUI_FIELD_DESC = new org.apache.thrift.protocol.TField("classRUI", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField CLASS_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("className", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField METHOD_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("methodName", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RainResponseHeadStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RainResponseHeadTupleSchemeFactory();

  public String classRUI; // required
  public String className; // required
  public String methodName; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CLASS_RUI((short)1, "classRUI"),
    CLASS_NAME((short)2, "className"),
    METHOD_NAME((short)3, "methodName");

    private static final java.util.Map<String, _Fields> byName = new java.util.HashMap<String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // CLASS_RUI
          return CLASS_RUI;
        case 2: // CLASS_NAME
          return CLASS_NAME;
        case 3: // METHOD_NAME
          return METHOD_NAME;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CLASS_RUI, new org.apache.thrift.meta_data.FieldMetaData("classRUI", org.apache.thrift.TFieldRequirementType.DEFAULT,
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CLASS_NAME, new org.apache.thrift.meta_data.FieldMetaData("className", org.apache.thrift.TFieldRequirementType.DEFAULT,
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.METHOD_NAME, new org.apache.thrift.meta_data.FieldMetaData("methodName", org.apache.thrift.TFieldRequirementType.DEFAULT,
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RainResponseHead.class, metaDataMap);
  }

  public RainResponseHead() {
  }

  public RainResponseHead(
    String classRUI,
    String className,
    String methodName)
  {
    this();
    this.classRUI = classRUI;
    this.className = className;
    this.methodName = methodName;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RainResponseHead(RainResponseHead other) {
    if (other.isSetClassRUI()) {
      this.classRUI = other.classRUI;
    }
    if (other.isSetClassName()) {
      this.className = other.className;
    }
    if (other.isSetMethodName()) {
      this.methodName = other.methodName;
    }
  }

  public RainResponseHead deepCopy() {
    return new RainResponseHead(this);
  }

  @Override
  public void clear() {
    this.classRUI = null;
    this.className = null;
    this.methodName = null;
  }

  public String getClassRUI() {
    return this.classRUI;
  }

  public RainResponseHead setClassRUI(String classRUI) {
    this.classRUI = classRUI;
    return this;
  }

  public void unsetClassRUI() {
    this.classRUI = null;
  }

  /** Returns true if field classRUI is set (has been assigned a value) and false otherwise */
  public boolean isSetClassRUI() {
    return this.classRUI != null;
  }

  public void setClassRUIIsSet(boolean value) {
    if (!value) {
      this.classRUI = null;
    }
  }

  public String getClassName() {
    return this.className;
  }

  public RainResponseHead setClassName(String className) {
    this.className = className;
    return this;
  }

  public void unsetClassName() {
    this.className = null;
  }

  /** Returns true if field className is set (has been assigned a value) and false otherwise */
  public boolean isSetClassName() {
    return this.className != null;
  }

  public void setClassNameIsSet(boolean value) {
    if (!value) {
      this.className = null;
    }
  }

  public String getMethodName() {
    return this.methodName;
  }

  public RainResponseHead setMethodName(String methodName) {
    this.methodName = methodName;
    return this;
  }

  public void unsetMethodName() {
    this.methodName = null;
  }

  /** Returns true if field methodName is set (has been assigned a value) and false otherwise */
  public boolean isSetMethodName() {
    return this.methodName != null;
  }

  public void setMethodNameIsSet(boolean value) {
    if (!value) {
      this.methodName = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case CLASS_RUI:
      if (value == null) {
        unsetClassRUI();
      } else {
        setClassRUI((String)value);
      }
      break;

    case CLASS_NAME:
      if (value == null) {
        unsetClassName();
      } else {
        setClassName((String)value);
      }
      break;

    case METHOD_NAME:
      if (value == null) {
        unsetMethodName();
      } else {
        setMethodName((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case CLASS_RUI:
      return getClassRUI();

    case CLASS_NAME:
      return getClassName();

    case METHOD_NAME:
      return getMethodName();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case CLASS_RUI:
      return isSetClassRUI();
    case CLASS_NAME:
      return isSetClassName();
    case METHOD_NAME:
      return isSetMethodName();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof RainResponseHead)
      return this.equals((RainResponseHead)that);
    return false;
  }

  public boolean equals(RainResponseHead that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_classRUI = true && this.isSetClassRUI();
    boolean that_present_classRUI = true && that.isSetClassRUI();
    if (this_present_classRUI || that_present_classRUI) {
      if (!(this_present_classRUI && that_present_classRUI))
        return false;
      if (!this.classRUI.equals(that.classRUI))
        return false;
    }

    boolean this_present_className = true && this.isSetClassName();
    boolean that_present_className = true && that.isSetClassName();
    if (this_present_className || that_present_className) {
      if (!(this_present_className && that_present_className))
        return false;
      if (!this.className.equals(that.className))
        return false;
    }

    boolean this_present_methodName = true && this.isSetMethodName();
    boolean that_present_methodName = true && that.isSetMethodName();
    if (this_present_methodName || that_present_methodName) {
      if (!(this_present_methodName && that_present_methodName))
        return false;
      if (!this.methodName.equals(that.methodName))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetClassRUI()) ? 131071 : 524287);
    if (isSetClassRUI())
      hashCode = hashCode * 8191 + classRUI.hashCode();

    hashCode = hashCode * 8191 + ((isSetClassName()) ? 131071 : 524287);
    if (isSetClassName())
      hashCode = hashCode * 8191 + className.hashCode();

    hashCode = hashCode * 8191 + ((isSetMethodName()) ? 131071 : 524287);
    if (isSetMethodName())
      hashCode = hashCode * 8191 + methodName.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(RainResponseHead other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetClassRUI()).compareTo(other.isSetClassRUI());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetClassRUI()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.classRUI, other.classRUI);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetClassName()).compareTo(other.isSetClassName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetClassName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.className, other.className);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMethodName()).compareTo(other.isSetMethodName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMethodName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.methodName, other.methodName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("RainResponseHead(");
    boolean first = true;

    sb.append("classRUI:");
    if (this.classRUI == null) {
      sb.append("null");
    } else {
      sb.append(this.classRUI);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("className:");
    if (this.className == null) {
      sb.append("null");
    } else {
      sb.append(this.className);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("methodName:");
    if (this.methodName == null) {
      sb.append("null");
    } else {
      sb.append(this.methodName);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class RainResponseHeadStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RainResponseHeadStandardScheme getScheme() {
      return new RainResponseHeadStandardScheme();
    }
  }

  private static class RainResponseHeadStandardScheme extends org.apache.thrift.scheme.StandardScheme<RainResponseHead> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RainResponseHead struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CLASS_RUI
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.classRUI = iprot.readString();
              struct.setClassRUIIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CLASS_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.className = iprot.readString();
              struct.setClassNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // METHOD_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.methodName = iprot.readString();
              struct.setMethodNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, RainResponseHead struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.classRUI != null) {
        oprot.writeFieldBegin(CLASS_RUI_FIELD_DESC);
        oprot.writeString(struct.classRUI);
        oprot.writeFieldEnd();
      }
      if (struct.className != null) {
        oprot.writeFieldBegin(CLASS_NAME_FIELD_DESC);
        oprot.writeString(struct.className);
        oprot.writeFieldEnd();
      }
      if (struct.methodName != null) {
        oprot.writeFieldBegin(METHOD_NAME_FIELD_DESC);
        oprot.writeString(struct.methodName);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RainResponseHeadTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RainResponseHeadTupleScheme getScheme() {
      return new RainResponseHeadTupleScheme();
    }
  }

  private static class RainResponseHeadTupleScheme extends org.apache.thrift.scheme.TupleScheme<RainResponseHead> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RainResponseHead struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetClassRUI()) {
        optionals.set(0);
      }
      if (struct.isSetClassName()) {
        optionals.set(1);
      }
      if (struct.isSetMethodName()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetClassRUI()) {
        oprot.writeString(struct.classRUI);
      }
      if (struct.isSetClassName()) {
        oprot.writeString(struct.className);
      }
      if (struct.isSetMethodName()) {
        oprot.writeString(struct.methodName);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RainResponseHead struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.classRUI = iprot.readString();
        struct.setClassRUIIsSet(true);
      }
      if (incoming.get(1)) {
        struct.className = iprot.readString();
        struct.setClassNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.methodName = iprot.readString();
        struct.setMethodNameIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

