/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.laoge.raining.common.route;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-11-03")
public class RainRequest implements org.apache.thrift.TBase<RainRequest, RainRequest._Fields>, java.io.Serializable, Cloneable, Comparable<RainRequest> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RainRequest");

  private static final org.apache.thrift.protocol.TField CLASS_URI_FIELD_DESC = new org.apache.thrift.protocol.TField("classURI", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField CLASS_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("className", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField METHOD_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("methodName", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField BODY_FIELD_DESC = new org.apache.thrift.protocol.TField("body", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField PARAM_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("paramList", org.apache.thrift.protocol.TType.LIST, (short)5);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RainRequestStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RainRequestTupleSchemeFactory();

  public String classURI; // required
  public String className; // required
  public String methodName; // required
  public String body; // required
  public java.util.List<RainRequestParam> paramList; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CLASS_URI((short)1, "classURI"),
    CLASS_NAME((short)2, "className"),
    METHOD_NAME((short)3, "methodName"),
    BODY((short)4, "body"),
    PARAM_LIST((short)5, "paramList");

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
        case 1: // CLASS_URI
          return CLASS_URI;
        case 2: // CLASS_NAME
          return CLASS_NAME;
        case 3: // METHOD_NAME
          return METHOD_NAME;
        case 4: // BODY
          return BODY;
        case 5: // PARAM_LIST
          return PARAM_LIST;
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
    tmpMap.put(_Fields.CLASS_URI, new org.apache.thrift.meta_data.FieldMetaData("classURI", org.apache.thrift.TFieldRequirementType.DEFAULT,
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CLASS_NAME, new org.apache.thrift.meta_data.FieldMetaData("className", org.apache.thrift.TFieldRequirementType.DEFAULT,
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.METHOD_NAME, new org.apache.thrift.meta_data.FieldMetaData("methodName", org.apache.thrift.TFieldRequirementType.DEFAULT,
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.BODY, new org.apache.thrift.meta_data.FieldMetaData("body", org.apache.thrift.TFieldRequirementType.DEFAULT,
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PARAM_LIST, new org.apache.thrift.meta_data.FieldMetaData("paramList", org.apache.thrift.TFieldRequirementType.DEFAULT,
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST,
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, RainRequestParam.class))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RainRequest.class, metaDataMap);
  }

  public RainRequest() {
  }

  public RainRequest(
    String classURI,
    String className,
    String methodName,
    String body,
    java.util.List<RainRequestParam> paramList)
  {
    this();
    this.classURI = classURI;
    this.className = className;
    this.methodName = methodName;
    this.body = body;
    this.paramList = paramList;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RainRequest(RainRequest other) {
    if (other.isSetClassURI()) {
      this.classURI = other.classURI;
    }
    if (other.isSetClassName()) {
      this.className = other.className;
    }
    if (other.isSetMethodName()) {
      this.methodName = other.methodName;
    }
    if (other.isSetBody()) {
      this.body = other.body;
    }
    if (other.isSetParamList()) {
      java.util.List<RainRequestParam> __this__paramList = new java.util.ArrayList<RainRequestParam>(other.paramList.size());
      for (RainRequestParam other_element : other.paramList) {
        __this__paramList.add(new RainRequestParam(other_element));
      }
      this.paramList = __this__paramList;
    }
  }

  public RainRequest deepCopy() {
    return new RainRequest(this);
  }

  @Override
  public void clear() {
    this.classURI = null;
    this.className = null;
    this.methodName = null;
    this.body = null;
    this.paramList = null;
  }

  public String getClassURI() {
    return this.classURI;
  }

  public RainRequest setClassURI(String classURI) {
    this.classURI = classURI;
    return this;
  }

  public void unsetClassURI() {
    this.classURI = null;
  }

  /** Returns true if field classURI is set (has been assigned a value) and false otherwise */
  public boolean isSetClassURI() {
    return this.classURI != null;
  }

  public void setClassURIIsSet(boolean value) {
    if (!value) {
      this.classURI = null;
    }
  }

  public String getClassName() {
    return this.className;
  }

  public RainRequest setClassName(String className) {
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

  public RainRequest setMethodName(String methodName) {
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

  public String getBody() {
    return this.body;
  }

  public RainRequest setBody(String body) {
    this.body = body;
    return this;
  }

  public void unsetBody() {
    this.body = null;
  }

  /** Returns true if field body is set (has been assigned a value) and false otherwise */
  public boolean isSetBody() {
    return this.body != null;
  }

  public void setBodyIsSet(boolean value) {
    if (!value) {
      this.body = null;
    }
  }

  public int getParamListSize() {
    return (this.paramList == null) ? 0 : this.paramList.size();
  }

  public java.util.Iterator<RainRequestParam> getParamListIterator() {
    return (this.paramList == null) ? null : this.paramList.iterator();
  }

  public void addToParamList(RainRequestParam elem) {
    if (this.paramList == null) {
      this.paramList = new java.util.ArrayList<RainRequestParam>();
    }
    this.paramList.add(elem);
  }

  public java.util.List<RainRequestParam> getParamList() {
    return this.paramList;
  }

  public RainRequest setParamList(java.util.List<RainRequestParam> paramList) {
    this.paramList = paramList;
    return this;
  }

  public void unsetParamList() {
    this.paramList = null;
  }

  /** Returns true if field paramList is set (has been assigned a value) and false otherwise */
  public boolean isSetParamList() {
    return this.paramList != null;
  }

  public void setParamListIsSet(boolean value) {
    if (!value) {
      this.paramList = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case CLASS_URI:
      if (value == null) {
        unsetClassURI();
      } else {
        setClassURI((String)value);
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

    case BODY:
      if (value == null) {
        unsetBody();
      } else {
        setBody((String)value);
      }
      break;

    case PARAM_LIST:
      if (value == null) {
        unsetParamList();
      } else {
        setParamList((java.util.List<RainRequestParam>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case CLASS_URI:
      return getClassURI();

    case CLASS_NAME:
      return getClassName();

    case METHOD_NAME:
      return getMethodName();

    case BODY:
      return getBody();

    case PARAM_LIST:
      return getParamList();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case CLASS_URI:
      return isSetClassURI();
    case CLASS_NAME:
      return isSetClassName();
    case METHOD_NAME:
      return isSetMethodName();
    case BODY:
      return isSetBody();
    case PARAM_LIST:
      return isSetParamList();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof RainRequest)
      return this.equals((RainRequest)that);
    return false;
  }

  public boolean equals(RainRequest that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_classURI = true && this.isSetClassURI();
    boolean that_present_classURI = true && that.isSetClassURI();
    if (this_present_classURI || that_present_classURI) {
      if (!(this_present_classURI && that_present_classURI))
        return false;
      if (!this.classURI.equals(that.classURI))
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

    boolean this_present_body = true && this.isSetBody();
    boolean that_present_body = true && that.isSetBody();
    if (this_present_body || that_present_body) {
      if (!(this_present_body && that_present_body))
        return false;
      if (!this.body.equals(that.body))
        return false;
    }

    boolean this_present_paramList = true && this.isSetParamList();
    boolean that_present_paramList = true && that.isSetParamList();
    if (this_present_paramList || that_present_paramList) {
      if (!(this_present_paramList && that_present_paramList))
        return false;
      if (!this.paramList.equals(that.paramList))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetClassURI()) ? 131071 : 524287);
    if (isSetClassURI())
      hashCode = hashCode * 8191 + classURI.hashCode();

    hashCode = hashCode * 8191 + ((isSetClassName()) ? 131071 : 524287);
    if (isSetClassName())
      hashCode = hashCode * 8191 + className.hashCode();

    hashCode = hashCode * 8191 + ((isSetMethodName()) ? 131071 : 524287);
    if (isSetMethodName())
      hashCode = hashCode * 8191 + methodName.hashCode();

    hashCode = hashCode * 8191 + ((isSetBody()) ? 131071 : 524287);
    if (isSetBody())
      hashCode = hashCode * 8191 + body.hashCode();

    hashCode = hashCode * 8191 + ((isSetParamList()) ? 131071 : 524287);
    if (isSetParamList())
      hashCode = hashCode * 8191 + paramList.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(RainRequest other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetClassURI()).compareTo(other.isSetClassURI());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetClassURI()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.classURI, other.classURI);
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
    lastComparison = Boolean.valueOf(isSetBody()).compareTo(other.isSetBody());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBody()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.body, other.body);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetParamList()).compareTo(other.isSetParamList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetParamList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.paramList, other.paramList);
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
    StringBuilder sb = new StringBuilder("RainRequest(");
    boolean first = true;

    sb.append("classURI:");
    if (this.classURI == null) {
      sb.append("null");
    } else {
      sb.append(this.classURI);
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
    if (!first) sb.append(", ");
    sb.append("body:");
    if (this.body == null) {
      sb.append("null");
    } else {
      sb.append(this.body);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("paramList:");
    if (this.paramList == null) {
      sb.append("null");
    } else {
      sb.append(this.paramList);
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

  private static class RainRequestStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RainRequestStandardScheme getScheme() {
      return new RainRequestStandardScheme();
    }
  }

  private static class RainRequestStandardScheme extends org.apache.thrift.scheme.StandardScheme<RainRequest> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RainRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CLASS_URI
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.classURI = iprot.readString();
              struct.setClassURIIsSet(true);
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
          case 4: // BODY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.body = iprot.readString();
              struct.setBodyIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // PARAM_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.paramList = new java.util.ArrayList<RainRequestParam>(_list0.size);
                RainRequestParam _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = new RainRequestParam();
                  _elem1.read(iprot);
                  struct.paramList.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setParamListIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, RainRequest struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.classURI != null) {
        oprot.writeFieldBegin(CLASS_URI_FIELD_DESC);
        oprot.writeString(struct.classURI);
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
      if (struct.body != null) {
        oprot.writeFieldBegin(BODY_FIELD_DESC);
        oprot.writeString(struct.body);
        oprot.writeFieldEnd();
      }
      if (struct.paramList != null) {
        oprot.writeFieldBegin(PARAM_LIST_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.paramList.size()));
          for (RainRequestParam _iter3 : struct.paramList)
          {
            _iter3.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RainRequestTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RainRequestTupleScheme getScheme() {
      return new RainRequestTupleScheme();
    }
  }

  private static class RainRequestTupleScheme extends org.apache.thrift.scheme.TupleScheme<RainRequest> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RainRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetClassURI()) {
        optionals.set(0);
      }
      if (struct.isSetClassName()) {
        optionals.set(1);
      }
      if (struct.isSetMethodName()) {
        optionals.set(2);
      }
      if (struct.isSetBody()) {
        optionals.set(3);
      }
      if (struct.isSetParamList()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetClassURI()) {
        oprot.writeString(struct.classURI);
      }
      if (struct.isSetClassName()) {
        oprot.writeString(struct.className);
      }
      if (struct.isSetMethodName()) {
        oprot.writeString(struct.methodName);
      }
      if (struct.isSetBody()) {
        oprot.writeString(struct.body);
      }
      if (struct.isSetParamList()) {
        {
          oprot.writeI32(struct.paramList.size());
          for (RainRequestParam _iter4 : struct.paramList)
          {
            _iter4.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RainRequest struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.classURI = iprot.readString();
        struct.setClassURIIsSet(true);
      }
      if (incoming.get(1)) {
        struct.className = iprot.readString();
        struct.setClassNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.methodName = iprot.readString();
        struct.setMethodNameIsSet(true);
      }
      if (incoming.get(3)) {
        struct.body = iprot.readString();
        struct.setBodyIsSet(true);
      }
      if (incoming.get(4)) {
        {
          org.apache.thrift.protocol.TList _list5 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.paramList = new java.util.ArrayList<RainRequestParam>(_list5.size);
          RainRequestParam _elem6;
          for (int _i7 = 0; _i7 < _list5.size; ++_i7)
          {
            _elem6 = new RainRequestParam();
            _elem6.read(iprot);
            struct.paramList.add(_elem6);
          }
        }
        struct.setParamListIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

