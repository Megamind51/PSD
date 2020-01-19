package protos;
// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/protos/proto_auth.proto

public final class ProtoAuth {
  private ProtoAuth() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface AuthOrBuilder extends
      // @@protoc_insertion_point(interface_extends:Auth)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>.Auth.Operation operation = 1;</code>
     * @return The enum numeric value on the wire for operation.
     */
    int getOperationValue();
    /**
     * <code>.Auth.Operation operation = 1;</code>
     * @return The operation.
     */
    ProtoAuth.Auth.Operation getOperation();

    /**
     * <code>.Auth.Type type = 2;</code>
     * @return The enum numeric value on the wire for type.
     */
    int getTypeValue();
    /**
     * <code>.Auth.Type type = 2;</code>
     * @return The type.
     */
    ProtoAuth.Auth.Type getType();

    /**
     * <code>string username = 3;</code>
     * @return The username.
     */
    java.lang.String getUsername();
    /**
     * <code>string username = 3;</code>
     * @return The bytes for username.
     */
    com.google.protobuf.ByteString
        getUsernameBytes();

    /**
     * <code>string password = 4;</code>
     * @return The password.
     */
    java.lang.String getPassword();
    /**
     * <code>string password = 4;</code>
     * @return The bytes for password.
     */
    com.google.protobuf.ByteString
        getPasswordBytes();

    /**
     * <pre>
     * 0 - Authenticated; 1 - User Not Found; 2 - Passwords Don't Match
     * </pre>
     *
     * <code>int32 authenticated = 5;</code>
     * @return The authenticated.
     */
    int getAuthenticated();
  }
  /**
   * Protobuf type {@code Auth}
   */
  public  static final class Auth extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:Auth)
      AuthOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Auth.newBuilder() to construct.
    private Auth(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Auth() {
      operation_ = 0;
      type_ = 0;
      username_ = "";
      password_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new Auth();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private Auth(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              int rawValue = input.readEnum();

              operation_ = rawValue;
              break;
            }
            case 16: {
              int rawValue = input.readEnum();

              type_ = rawValue;
              break;
            }
            case 26: {
              java.lang.String s = input.readStringRequireUtf8();

              username_ = s;
              break;
            }
            case 34: {
              java.lang.String s = input.readStringRequireUtf8();

              password_ = s;
              break;
            }
            case 40: {

              authenticated_ = input.readInt32();
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return ProtoAuth.internal_static_Auth_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return ProtoAuth.internal_static_Auth_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              ProtoAuth.Auth.class, ProtoAuth.Auth.Builder.class);
    }

    /**
     * Protobuf enum {@code Auth.Operation}
     */
    public enum Operation
        implements com.google.protobuf.ProtocolMessageEnum {
      /**
       * <code>LOGIN = 0;</code>
       */
      LOGIN(0),
      /**
       * <code>LOGOUT = 1;</code>
       */
      LOGOUT(1),
      /**
       * <code>REGISTER = 2;</code>
       */
      REGISTER(2),
      UNRECOGNIZED(-1),
      ;

      /**
       * <code>LOGIN = 0;</code>
       */
      public static final int LOGIN_VALUE = 0;
      /**
       * <code>LOGOUT = 1;</code>
       */
      public static final int LOGOUT_VALUE = 1;
      /**
       * <code>REGISTER = 2;</code>
       */
      public static final int REGISTER_VALUE = 2;


      public final int getNumber() {
        if (this == UNRECOGNIZED) {
          throw new java.lang.IllegalArgumentException(
              "Can't get the number of an unknown enum value.");
        }
        return value;
      }

      /**
       * @param value The numeric wire value of the corresponding enum entry.
       * @return The enum associated with the given numeric wire value.
       * @deprecated Use {@link #forNumber(int)} instead.
       */
      @java.lang.Deprecated
      public static Operation valueOf(int value) {
        return forNumber(value);
      }

      /**
       * @param value The numeric wire value of the corresponding enum entry.
       * @return The enum associated with the given numeric wire value.
       */
      public static Operation forNumber(int value) {
        switch (value) {
          case 0: return LOGIN;
          case 1: return LOGOUT;
          case 2: return REGISTER;
          default: return null;
        }
      }

      public static com.google.protobuf.Internal.EnumLiteMap<Operation>
          internalGetValueMap() {
        return internalValueMap;
      }
      private static final com.google.protobuf.Internal.EnumLiteMap<
          Operation> internalValueMap =
            new com.google.protobuf.Internal.EnumLiteMap<Operation>() {
              public Operation findValueByNumber(int number) {
                return Operation.forNumber(number);
              }
            };

      public final com.google.protobuf.Descriptors.EnumValueDescriptor
          getValueDescriptor() {
        return getDescriptor().getValues().get(ordinal());
      }
      public final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptorForType() {
        return getDescriptor();
      }
      public static final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptor() {
        return ProtoAuth.Auth.getDescriptor().getEnumTypes().get(0);
      }

      private static final Operation[] VALUES = values();

      public static Operation valueOf(
          com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
        if (desc.getType() != getDescriptor()) {
          throw new java.lang.IllegalArgumentException(
            "EnumValueDescriptor is not for this type.");
        }
        if (desc.getIndex() == -1) {
          return UNRECOGNIZED;
        }
        return VALUES[desc.getIndex()];
      }

      private final int value;

      private Operation(int value) {
        this.value = value;
      }

      // @@protoc_insertion_point(enum_scope:Auth.Operation)
    }

    /**
     * Protobuf enum {@code Auth.Type}
     */
    public enum Type
        implements com.google.protobuf.ProtocolMessageEnum {
      /**
       * <code>MANUFACTURER = 0;</code>
       */
      MANUFACTURER(0),
      /**
       * <code>IMPORTER = 1;</code>
       */
      IMPORTER(1),
      UNRECOGNIZED(-1),
      ;

      /**
       * <code>MANUFACTURER = 0;</code>
       */
      public static final int MANUFACTURER_VALUE = 0;
      /**
       * <code>IMPORTER = 1;</code>
       */
      public static final int IMPORTER_VALUE = 1;


      public final int getNumber() {
        if (this == UNRECOGNIZED) {
          throw new java.lang.IllegalArgumentException(
              "Can't get the number of an unknown enum value.");
        }
        return value;
      }

      /**
       * @param value The numeric wire value of the corresponding enum entry.
       * @return The enum associated with the given numeric wire value.
       * @deprecated Use {@link #forNumber(int)} instead.
       */
      @java.lang.Deprecated
      public static Type valueOf(int value) {
        return forNumber(value);
      }

      /**
       * @param value The numeric wire value of the corresponding enum entry.
       * @return The enum associated with the given numeric wire value.
       */
      public static Type forNumber(int value) {
        switch (value) {
          case 0: return MANUFACTURER;
          case 1: return IMPORTER;
          default: return null;
        }
      }

      public static com.google.protobuf.Internal.EnumLiteMap<Type>
          internalGetValueMap() {
        return internalValueMap;
      }
      private static final com.google.protobuf.Internal.EnumLiteMap<
          Type> internalValueMap =
            new com.google.protobuf.Internal.EnumLiteMap<Type>() {
              public Type findValueByNumber(int number) {
                return Type.forNumber(number);
              }
            };

      public final com.google.protobuf.Descriptors.EnumValueDescriptor
          getValueDescriptor() {
        return getDescriptor().getValues().get(ordinal());
      }
      public final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptorForType() {
        return getDescriptor();
      }
      public static final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptor() {
        return ProtoAuth.Auth.getDescriptor().getEnumTypes().get(1);
      }

      private static final Type[] VALUES = values();

      public static Type valueOf(
          com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
        if (desc.getType() != getDescriptor()) {
          throw new java.lang.IllegalArgumentException(
            "EnumValueDescriptor is not for this type.");
        }
        if (desc.getIndex() == -1) {
          return UNRECOGNIZED;
        }
        return VALUES[desc.getIndex()];
      }

      private final int value;

      private Type(int value) {
        this.value = value;
      }

      // @@protoc_insertion_point(enum_scope:Auth.Type)
    }

    public static final int OPERATION_FIELD_NUMBER = 1;
    private int operation_;
    /**
     * <code>.Auth.Operation operation = 1;</code>
     * @return The enum numeric value on the wire for operation.
     */
    public int getOperationValue() {
      return operation_;
    }
    /**
     * <code>.Auth.Operation operation = 1;</code>
     * @return The operation.
     */
    public ProtoAuth.Auth.Operation getOperation() {
      @SuppressWarnings("deprecation")
      ProtoAuth.Auth.Operation result = ProtoAuth.Auth.Operation.valueOf(operation_);
      return result == null ? ProtoAuth.Auth.Operation.UNRECOGNIZED : result;
    }

    public static final int TYPE_FIELD_NUMBER = 2;
    private int type_;
    /**
     * <code>.Auth.Type type = 2;</code>
     * @return The enum numeric value on the wire for type.
     */
    public int getTypeValue() {
      return type_;
    }
    /**
     * <code>.Auth.Type type = 2;</code>
     * @return The type.
     */
    public ProtoAuth.Auth.Type getType() {
      @SuppressWarnings("deprecation")
      ProtoAuth.Auth.Type result = ProtoAuth.Auth.Type.valueOf(type_);
      return result == null ? ProtoAuth.Auth.Type.UNRECOGNIZED : result;
    }

    public static final int USERNAME_FIELD_NUMBER = 3;
    private volatile java.lang.Object username_;
    /**
     * <code>string username = 3;</code>
     * @return The username.
     */
    public java.lang.String getUsername() {
      java.lang.Object ref = username_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        username_ = s;
        return s;
      }
    }
    /**
     * <code>string username = 3;</code>
     * @return The bytes for username.
     */
    public com.google.protobuf.ByteString
        getUsernameBytes() {
      java.lang.Object ref = username_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        username_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int PASSWORD_FIELD_NUMBER = 4;
    private volatile java.lang.Object password_;
    /**
     * <code>string password = 4;</code>
     * @return The password.
     */
    public java.lang.String getPassword() {
      java.lang.Object ref = password_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        password_ = s;
        return s;
      }
    }
    /**
     * <code>string password = 4;</code>
     * @return The bytes for password.
     */
    public com.google.protobuf.ByteString
        getPasswordBytes() {
      java.lang.Object ref = password_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        password_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int AUTHENTICATED_FIELD_NUMBER = 5;
    private int authenticated_;
    /**
     * <pre>
     * 0 - Authenticated; 1 - User Not Found; 2 - Passwords Don't Match
     * </pre>
     *
     * <code>int32 authenticated = 5;</code>
     * @return The authenticated.
     */
    public int getAuthenticated() {
      return authenticated_;
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (operation_ != ProtoAuth.Auth.Operation.LOGIN.getNumber()) {
        output.writeEnum(1, operation_);
      }
      if (type_ != ProtoAuth.Auth.Type.MANUFACTURER.getNumber()) {
        output.writeEnum(2, type_);
      }
      if (!getUsernameBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, username_);
      }
      if (!getPasswordBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, password_);
      }
      if (authenticated_ != 0) {
        output.writeInt32(5, authenticated_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (operation_ != ProtoAuth.Auth.Operation.LOGIN.getNumber()) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(1, operation_);
      }
      if (type_ != ProtoAuth.Auth.Type.MANUFACTURER.getNumber()) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(2, type_);
      }
      if (!getUsernameBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, username_);
      }
      if (!getPasswordBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, password_);
      }
      if (authenticated_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(5, authenticated_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof ProtoAuth.Auth)) {
        return super.equals(obj);
      }
      ProtoAuth.Auth other = (ProtoAuth.Auth) obj;

      if (operation_ != other.operation_) return false;
      if (type_ != other.type_) return false;
      if (!getUsername()
          .equals(other.getUsername())) return false;
      if (!getPassword()
          .equals(other.getPassword())) return false;
      if (getAuthenticated()
          != other.getAuthenticated()) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + OPERATION_FIELD_NUMBER;
      hash = (53 * hash) + operation_;
      hash = (37 * hash) + TYPE_FIELD_NUMBER;
      hash = (53 * hash) + type_;
      hash = (37 * hash) + USERNAME_FIELD_NUMBER;
      hash = (53 * hash) + getUsername().hashCode();
      hash = (37 * hash) + PASSWORD_FIELD_NUMBER;
      hash = (53 * hash) + getPassword().hashCode();
      hash = (37 * hash) + AUTHENTICATED_FIELD_NUMBER;
      hash = (53 * hash) + getAuthenticated();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static ProtoAuth.Auth parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ProtoAuth.Auth parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ProtoAuth.Auth parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ProtoAuth.Auth parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ProtoAuth.Auth parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static ProtoAuth.Auth parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static ProtoAuth.Auth parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static ProtoAuth.Auth parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static ProtoAuth.Auth parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static ProtoAuth.Auth parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static ProtoAuth.Auth parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static ProtoAuth.Auth parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(ProtoAuth.Auth prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code Auth}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:Auth)
        ProtoAuth.AuthOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return ProtoAuth.internal_static_Auth_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return ProtoAuth.internal_static_Auth_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                ProtoAuth.Auth.class, ProtoAuth.Auth.Builder.class);
      }

      // Construct using ProtoAuth.Auth.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        operation_ = 0;

        type_ = 0;

        username_ = "";

        password_ = "";

        authenticated_ = 0;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return ProtoAuth.internal_static_Auth_descriptor;
      }

      @java.lang.Override
      public ProtoAuth.Auth getDefaultInstanceForType() {
        return ProtoAuth.Auth.getDefaultInstance();
      }

      @java.lang.Override
      public ProtoAuth.Auth build() {
        ProtoAuth.Auth result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public ProtoAuth.Auth buildPartial() {
        ProtoAuth.Auth result = new ProtoAuth.Auth(this);
        result.operation_ = operation_;
        result.type_ = type_;
        result.username_ = username_;
        result.password_ = password_;
        result.authenticated_ = authenticated_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof ProtoAuth.Auth) {
          return mergeFrom((ProtoAuth.Auth)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(ProtoAuth.Auth other) {
        if (other == ProtoAuth.Auth.getDefaultInstance()) return this;
        if (other.operation_ != 0) {
          setOperationValue(other.getOperationValue());
        }
        if (other.type_ != 0) {
          setTypeValue(other.getTypeValue());
        }
        if (!other.getUsername().isEmpty()) {
          username_ = other.username_;
          onChanged();
        }
        if (!other.getPassword().isEmpty()) {
          password_ = other.password_;
          onChanged();
        }
        if (other.getAuthenticated() != 0) {
          setAuthenticated(other.getAuthenticated());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        ProtoAuth.Auth parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (ProtoAuth.Auth) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int operation_ = 0;
      /**
       * <code>.Auth.Operation operation = 1;</code>
       * @return The enum numeric value on the wire for operation.
       */
      public int getOperationValue() {
        return operation_;
      }
      /**
       * <code>.Auth.Operation operation = 1;</code>
       * @param value The enum numeric value on the wire for operation to set.
       * @return This builder for chaining.
       */
      public Builder setOperationValue(int value) {
        operation_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>.Auth.Operation operation = 1;</code>
       * @return The operation.
       */
      public ProtoAuth.Auth.Operation getOperation() {
        @SuppressWarnings("deprecation")
        ProtoAuth.Auth.Operation result = ProtoAuth.Auth.Operation.valueOf(operation_);
        return result == null ? ProtoAuth.Auth.Operation.UNRECOGNIZED : result;
      }
      /**
       * <code>.Auth.Operation operation = 1;</code>
       * @param value The operation to set.
       * @return This builder for chaining.
       */
      public Builder setOperation(ProtoAuth.Auth.Operation value) {
        if (value == null) {
          throw new NullPointerException();
        }
        
        operation_ = value.getNumber();
        onChanged();
        return this;
      }
      /**
       * <code>.Auth.Operation operation = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearOperation() {
        
        operation_ = 0;
        onChanged();
        return this;
      }

      private int type_ = 0;
      /**
       * <code>.Auth.Type type = 2;</code>
       * @return The enum numeric value on the wire for type.
       */
      public int getTypeValue() {
        return type_;
      }
      /**
       * <code>.Auth.Type type = 2;</code>
       * @param value The enum numeric value on the wire for type to set.
       * @return This builder for chaining.
       */
      public Builder setTypeValue(int value) {
        type_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>.Auth.Type type = 2;</code>
       * @return The type.
       */
      public ProtoAuth.Auth.Type getType() {
        @SuppressWarnings("deprecation")
        ProtoAuth.Auth.Type result = ProtoAuth.Auth.Type.valueOf(type_);
        return result == null ? ProtoAuth.Auth.Type.UNRECOGNIZED : result;
      }
      /**
       * <code>.Auth.Type type = 2;</code>
       * @param value The type to set.
       * @return This builder for chaining.
       */
      public Builder setType(ProtoAuth.Auth.Type value) {
        if (value == null) {
          throw new NullPointerException();
        }
        
        type_ = value.getNumber();
        onChanged();
        return this;
      }
      /**
       * <code>.Auth.Type type = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearType() {
        
        type_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object username_ = "";
      /**
       * <code>string username = 3;</code>
       * @return The username.
       */
      public java.lang.String getUsername() {
        java.lang.Object ref = username_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          username_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string username = 3;</code>
       * @return The bytes for username.
       */
      public com.google.protobuf.ByteString
          getUsernameBytes() {
        java.lang.Object ref = username_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          username_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string username = 3;</code>
       * @param value The username to set.
       * @return This builder for chaining.
       */
      public Builder setUsername(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        username_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string username = 3;</code>
       * @return This builder for chaining.
       */
      public Builder clearUsername() {
        
        username_ = getDefaultInstance().getUsername();
        onChanged();
        return this;
      }
      /**
       * <code>string username = 3;</code>
       * @param value The bytes for username to set.
       * @return This builder for chaining.
       */
      public Builder setUsernameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        username_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object password_ = "";
      /**
       * <code>string password = 4;</code>
       * @return The password.
       */
      public java.lang.String getPassword() {
        java.lang.Object ref = password_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          password_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string password = 4;</code>
       * @return The bytes for password.
       */
      public com.google.protobuf.ByteString
          getPasswordBytes() {
        java.lang.Object ref = password_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          password_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string password = 4;</code>
       * @param value The password to set.
       * @return This builder for chaining.
       */
      public Builder setPassword(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        password_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string password = 4;</code>
       * @return This builder for chaining.
       */
      public Builder clearPassword() {
        
        password_ = getDefaultInstance().getPassword();
        onChanged();
        return this;
      }
      /**
       * <code>string password = 4;</code>
       * @param value The bytes for password to set.
       * @return This builder for chaining.
       */
      public Builder setPasswordBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        password_ = value;
        onChanged();
        return this;
      }

      private int authenticated_ ;
      /**
       * <pre>
       * 0 - Authenticated; 1 - User Not Found; 2 - Passwords Don't Match
       * </pre>
       *
       * <code>int32 authenticated = 5;</code>
       * @return The authenticated.
       */
      public int getAuthenticated() {
        return authenticated_;
      }
      /**
       * <pre>
       * 0 - Authenticated; 1 - User Not Found; 2 - Passwords Don't Match
       * </pre>
       *
       * <code>int32 authenticated = 5;</code>
       * @param value The authenticated to set.
       * @return This builder for chaining.
       */
      public Builder setAuthenticated(int value) {
        
        authenticated_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 0 - Authenticated; 1 - User Not Found; 2 - Passwords Don't Match
       * </pre>
       *
       * <code>int32 authenticated = 5;</code>
       * @return This builder for chaining.
       */
      public Builder clearAuthenticated() {
        
        authenticated_ = 0;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:Auth)
    }

    // @@protoc_insertion_point(class_scope:Auth)
    private static final ProtoAuth.Auth DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new ProtoAuth.Auth();
    }

    public static ProtoAuth.Auth getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Auth>
        PARSER = new com.google.protobuf.AbstractParser<Auth>() {
      @java.lang.Override
      public Auth parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Auth(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Auth> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Auth> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public ProtoAuth.Auth getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Auth_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Auth_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\033src/protos/proto_auth.proto\"\331\001\n\004Auth\022\"" +
      "\n\toperation\030\001 \001(\0162\017.Auth.Operation\022\030\n\004ty" +
      "pe\030\002 \001(\0162\n.Auth.Type\022\020\n\010username\030\003 \001(\t\022\020" +
      "\n\010password\030\004 \001(\t\022\025\n\rauthenticated\030\005 \001(\005\"" +
      "0\n\tOperation\022\t\n\005LOGIN\020\000\022\n\n\006LOGOUT\020\001\022\014\n\010R" +
      "EGISTER\020\002\"&\n\004Type\022\020\n\014MANUFACTURER\020\000\022\014\n\010I" +
      "MPORTER\020\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_Auth_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Auth_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Auth_descriptor,
        new java.lang.String[] { "Operation", "Type", "Username", "Password", "Authenticated", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
