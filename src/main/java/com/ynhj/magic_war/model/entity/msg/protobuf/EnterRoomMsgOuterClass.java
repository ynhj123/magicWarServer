package com.ynhj.magic_war.model.entity.msg.protobuf;// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: proto/EnterRoomMsg.proto

public final class EnterRoomMsgOuterClass {
  private EnterRoomMsgOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface EnterRoomMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:EnterRoomMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string roomId = 1;</code>
     */
    java.lang.String getRoomId();
    /**
     * <code>string roomId = 1;</code>
     */
    com.google.protobuf.ByteString
        getRoomIdBytes();

    /**
     * <code>string code = 2;</code>
     */
    java.lang.String getCode();
    /**
     * <code>string code = 2;</code>
     */
    com.google.protobuf.ByteString
        getCodeBytes();

    /**
     * <code>string msg = 3;</code>
     */
    java.lang.String getMsg();
    /**
     * <code>string msg = 3;</code>
     */
    com.google.protobuf.ByteString
        getMsgBytes();
  }
  /**
   * Protobuf type {@code EnterRoomMsg}
   */
  public  static final class EnterRoomMsg extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:EnterRoomMsg)
      EnterRoomMsgOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use EnterRoomMsg.newBuilder() to construct.
    private EnterRoomMsg(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private EnterRoomMsg() {
      roomId_ = "";
      code_ = "";
      msg_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private EnterRoomMsg(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
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
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();

              roomId_ = s;
              break;
            }
            case 18: {
              java.lang.String s = input.readStringRequireUtf8();

              code_ = s;
              break;
            }
            case 26: {
              java.lang.String s = input.readStringRequireUtf8();

              msg_ = s;
              break;
            }
            default: {
              if (!parseUnknownFieldProto3(
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
      return EnterRoomMsgOuterClass.internal_static_EnterRoomMsg_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return EnterRoomMsgOuterClass.internal_static_EnterRoomMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              EnterRoomMsgOuterClass.EnterRoomMsg.class, EnterRoomMsgOuterClass.EnterRoomMsg.Builder.class);
    }

    public static final int ROOMID_FIELD_NUMBER = 1;
    private volatile java.lang.Object roomId_;
    /**
     * <code>string roomId = 1;</code>
     */
    public java.lang.String getRoomId() {
      java.lang.Object ref = roomId_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        roomId_ = s;
        return s;
      }
    }
    /**
     * <code>string roomId = 1;</code>
     */
    public com.google.protobuf.ByteString
        getRoomIdBytes() {
      java.lang.Object ref = roomId_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        roomId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int CODE_FIELD_NUMBER = 2;
    private volatile java.lang.Object code_;
    /**
     * <code>string code = 2;</code>
     */
    public java.lang.String getCode() {
      java.lang.Object ref = code_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        code_ = s;
        return s;
      }
    }
    /**
     * <code>string code = 2;</code>
     */
    public com.google.protobuf.ByteString
        getCodeBytes() {
      java.lang.Object ref = code_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        code_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int MSG_FIELD_NUMBER = 3;
    private volatile java.lang.Object msg_;
    /**
     * <code>string msg = 3;</code>
     */
    public java.lang.String getMsg() {
      java.lang.Object ref = msg_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        msg_ = s;
        return s;
      }
    }
    /**
     * <code>string msg = 3;</code>
     */
    public com.google.protobuf.ByteString
        getMsgBytes() {
      java.lang.Object ref = msg_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        msg_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
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
      if (!getRoomIdBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, roomId_);
      }
      if (!getCodeBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, code_);
      }
      if (!getMsgBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, msg_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getRoomIdBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, roomId_);
      }
      if (!getCodeBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, code_);
      }
      if (!getMsgBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, msg_);
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
      if (!(obj instanceof EnterRoomMsgOuterClass.EnterRoomMsg)) {
        return super.equals(obj);
      }
      EnterRoomMsgOuterClass.EnterRoomMsg other = (EnterRoomMsgOuterClass.EnterRoomMsg) obj;

      boolean result = true;
      result = result && getRoomId()
          .equals(other.getRoomId());
      result = result && getCode()
          .equals(other.getCode());
      result = result && getMsg()
          .equals(other.getMsg());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + ROOMID_FIELD_NUMBER;
      hash = (53 * hash) + getRoomId().hashCode();
      hash = (37 * hash) + CODE_FIELD_NUMBER;
      hash = (53 * hash) + getCode().hashCode();
      hash = (37 * hash) + MSG_FIELD_NUMBER;
      hash = (53 * hash) + getMsg().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static EnterRoomMsgOuterClass.EnterRoomMsg parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static EnterRoomMsgOuterClass.EnterRoomMsg parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static EnterRoomMsgOuterClass.EnterRoomMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static EnterRoomMsgOuterClass.EnterRoomMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static EnterRoomMsgOuterClass.EnterRoomMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static EnterRoomMsgOuterClass.EnterRoomMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static EnterRoomMsgOuterClass.EnterRoomMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static EnterRoomMsgOuterClass.EnterRoomMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static EnterRoomMsgOuterClass.EnterRoomMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static EnterRoomMsgOuterClass.EnterRoomMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static EnterRoomMsgOuterClass.EnterRoomMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static EnterRoomMsgOuterClass.EnterRoomMsg parseFrom(
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
    public static Builder newBuilder(EnterRoomMsgOuterClass.EnterRoomMsg prototype) {
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
     * Protobuf type {@code EnterRoomMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:EnterRoomMsg)
        EnterRoomMsgOuterClass.EnterRoomMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return EnterRoomMsgOuterClass.internal_static_EnterRoomMsg_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return EnterRoomMsgOuterClass.internal_static_EnterRoomMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                EnterRoomMsgOuterClass.EnterRoomMsg.class, EnterRoomMsgOuterClass.EnterRoomMsg.Builder.class);
      }

      // Construct using EnterRoomMsgOuterClass.EnterRoomMsg.newBuilder()
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
        roomId_ = "";

        code_ = "";

        msg_ = "";

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return EnterRoomMsgOuterClass.internal_static_EnterRoomMsg_descriptor;
      }

      @java.lang.Override
      public EnterRoomMsgOuterClass.EnterRoomMsg getDefaultInstanceForType() {
        return EnterRoomMsgOuterClass.EnterRoomMsg.getDefaultInstance();
      }

      @java.lang.Override
      public EnterRoomMsgOuterClass.EnterRoomMsg build() {
        EnterRoomMsgOuterClass.EnterRoomMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public EnterRoomMsgOuterClass.EnterRoomMsg buildPartial() {
        EnterRoomMsgOuterClass.EnterRoomMsg result = new EnterRoomMsgOuterClass.EnterRoomMsg(this);
        result.roomId_ = roomId_;
        result.code_ = code_;
        result.msg_ = msg_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return (Builder) super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof EnterRoomMsgOuterClass.EnterRoomMsg) {
          return mergeFrom((EnterRoomMsgOuterClass.EnterRoomMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(EnterRoomMsgOuterClass.EnterRoomMsg other) {
        if (other == EnterRoomMsgOuterClass.EnterRoomMsg.getDefaultInstance()) return this;
        if (!other.getRoomId().isEmpty()) {
          roomId_ = other.roomId_;
          onChanged();
        }
        if (!other.getCode().isEmpty()) {
          code_ = other.code_;
          onChanged();
        }
        if (!other.getMsg().isEmpty()) {
          msg_ = other.msg_;
          onChanged();
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
        EnterRoomMsgOuterClass.EnterRoomMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (EnterRoomMsgOuterClass.EnterRoomMsg) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object roomId_ = "";
      /**
       * <code>string roomId = 1;</code>
       */
      public java.lang.String getRoomId() {
        java.lang.Object ref = roomId_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          roomId_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string roomId = 1;</code>
       */
      public com.google.protobuf.ByteString
          getRoomIdBytes() {
        java.lang.Object ref = roomId_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          roomId_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string roomId = 1;</code>
       */
      public Builder setRoomId(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        roomId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string roomId = 1;</code>
       */
      public Builder clearRoomId() {
        
        roomId_ = getDefaultInstance().getRoomId();
        onChanged();
        return this;
      }
      /**
       * <code>string roomId = 1;</code>
       */
      public Builder setRoomIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        roomId_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object code_ = "";
      /**
       * <code>string code = 2;</code>
       */
      public java.lang.String getCode() {
        java.lang.Object ref = code_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          code_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string code = 2;</code>
       */
      public com.google.protobuf.ByteString
          getCodeBytes() {
        java.lang.Object ref = code_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          code_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string code = 2;</code>
       */
      public Builder setCode(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        code_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string code = 2;</code>
       */
      public Builder clearCode() {
        
        code_ = getDefaultInstance().getCode();
        onChanged();
        return this;
      }
      /**
       * <code>string code = 2;</code>
       */
      public Builder setCodeBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        code_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object msg_ = "";
      /**
       * <code>string msg = 3;</code>
       */
      public java.lang.String getMsg() {
        java.lang.Object ref = msg_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          msg_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string msg = 3;</code>
       */
      public com.google.protobuf.ByteString
          getMsgBytes() {
        java.lang.Object ref = msg_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          msg_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string msg = 3;</code>
       */
      public Builder setMsg(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        msg_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string msg = 3;</code>
       */
      public Builder clearMsg() {
        
        msg_ = getDefaultInstance().getMsg();
        onChanged();
        return this;
      }
      /**
       * <code>string msg = 3;</code>
       */
      public Builder setMsgBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        msg_ = value;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:EnterRoomMsg)
    }

    // @@protoc_insertion_point(class_scope:EnterRoomMsg)
    private static final EnterRoomMsgOuterClass.EnterRoomMsg DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new EnterRoomMsgOuterClass.EnterRoomMsg();
    }

    public static EnterRoomMsgOuterClass.EnterRoomMsg getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<EnterRoomMsg>
        PARSER = new com.google.protobuf.AbstractParser<EnterRoomMsg>() {
      @java.lang.Override
      public EnterRoomMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new EnterRoomMsg(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<EnterRoomMsg> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<EnterRoomMsg> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public EnterRoomMsgOuterClass.EnterRoomMsg getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_EnterRoomMsg_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_EnterRoomMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\030proto/EnterRoomMsg.proto\"9\n\014EnterRoomM" +
      "sg\022\016\n\006roomId\030\001 \001(\t\022\014\n\004code\030\002 \001(\t\022\013\n\003msg\030" +
      "\003 \001(\tb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_EnterRoomMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_EnterRoomMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_EnterRoomMsg_descriptor,
        new java.lang.String[] { "RoomId", "Code", "Msg", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
