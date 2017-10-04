package com.google.api.client.json;

import com.google.api.client.json.JsonPolymorphicTypeMap.TypeDef;
import com.google.api.client.util.Beta;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sets;
import com.google.api.client.util.Types;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class JsonParser {
    private static WeakHashMap<Class<?>, Field> cachedTypemapFields = new WeakHashMap();
    private static final Lock lock = new ReentrantLock();

    public abstract void close() throws IOException;

    public abstract BigInteger getBigIntegerValue() throws IOException;

    public abstract byte getByteValue() throws IOException;

    public abstract String getCurrentName() throws IOException;

    public abstract JsonToken getCurrentToken();

    public abstract BigDecimal getDecimalValue() throws IOException;

    public abstract double getDoubleValue() throws IOException;

    public abstract JsonFactory getFactory();

    public abstract float getFloatValue() throws IOException;

    public abstract int getIntValue() throws IOException;

    public abstract long getLongValue() throws IOException;

    public abstract short getShortValue() throws IOException;

    public abstract String getText() throws IOException;

    public abstract JsonToken nextToken() throws IOException;

    public abstract JsonParser skipChildren() throws IOException;

    public final <T> T parseAndClose(Class<T> cls) throws IOException {
        return parseAndClose((Class) cls, null);
    }

    @Beta
    public final <T> T parseAndClose(Class<T> cls, CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            T parse = parse((Class) cls, customizeJsonParser);
            return parse;
        } finally {
            close();
        }
    }

    public final void skipToKey(String str) throws IOException {
        skipToKey(Collections.singleton(str));
    }

    public final String skipToKey(Set<String> set) throws IOException {
        JsonToken startParsingObjectOrArray = startParsingObjectOrArray();
        while (startParsingObjectOrArray == JsonToken.FIELD_NAME) {
            String text = getText();
            nextToken();
            if (set.contains(text)) {
                return text;
            }
            skipChildren();
            startParsingObjectOrArray = nextToken();
        }
        return null;
    }

    private JsonToken startParsing() throws IOException {
        JsonToken nextToken;
        JsonToken currentToken = getCurrentToken();
        if (currentToken == null) {
            nextToken = nextToken();
        } else {
            nextToken = currentToken;
        }
        Preconditions.checkArgument(nextToken != null, "no JSON input found");
        return nextToken;
    }

    private JsonToken startParsingObjectOrArray() throws IOException {
        JsonToken startParsing = startParsing();
        switch (startParsing) {
            case START_OBJECT:
                JsonToken nextToken = nextToken();
                boolean z = nextToken == JsonToken.FIELD_NAME || nextToken == JsonToken.END_OBJECT;
                Preconditions.checkArgument(z, nextToken);
                return nextToken;
            case START_ARRAY:
                return nextToken();
            default:
                return startParsing;
        }
    }

    public final void parseAndClose(Object obj) throws IOException {
        parseAndClose(obj, null);
    }

    @Beta
    public final void parseAndClose(Object obj, CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            parse(obj, customizeJsonParser);
        } finally {
            close();
        }
    }

    public final <T> T parse(Class<T> cls) throws IOException {
        return parse((Class) cls, null);
    }

    @Beta
    public final <T> T parse(Class<T> cls, CustomizeJsonParser customizeJsonParser) throws IOException {
        return parse((Type) cls, false, customizeJsonParser);
    }

    public Object parse(Type type, boolean z) throws IOException {
        return parse(type, z, null);
    }

    @Beta
    public Object parse(Type type, boolean z, CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            if (!Void.class.equals(type)) {
                startParsing();
            }
            Object parseValue = parseValue(null, type, new ArrayList(), null, customizeJsonParser, true);
            return parseValue;
        } finally {
            if (z) {
                close();
            }
        }
    }

    public final void parse(Object obj) throws IOException {
        parse(obj, null);
    }

    @Beta
    public final void parse(Object obj, CustomizeJsonParser customizeJsonParser) throws IOException {
        ArrayList arrayList = new ArrayList();
        arrayList.add(obj.getClass());
        parse(arrayList, obj, customizeJsonParser);
    }

    private void parse(ArrayList<Type> arrayList, Object obj, CustomizeJsonParser customizeJsonParser) throws IOException {
        if (obj instanceof GenericJson) {
            ((GenericJson) obj).setFactory(getFactory());
        }
        JsonToken startParsingObjectOrArray = startParsingObjectOrArray();
        Type type = obj.getClass();
        ClassInfo of = ClassInfo.of(type);
        boolean isAssignableFrom = GenericData.class.isAssignableFrom(type);
        if (isAssignableFrom || !Map.class.isAssignableFrom(type)) {
            while (startParsingObjectOrArray == JsonToken.FIELD_NAME) {
                String text = getText();
                nextToken();
                if (customizeJsonParser == null || !customizeJsonParser.stopAt(obj, text)) {
                    FieldInfo fieldInfo = of.getFieldInfo(text);
                    if (fieldInfo != null) {
                        if (!fieldInfo.isFinal() || fieldInfo.isPrimitive()) {
                            Field field = fieldInfo.getField();
                            int size = arrayList.size();
                            arrayList.add(field.getGenericType());
                            Object parseValue = parseValue(field, fieldInfo.getGenericType(), arrayList, obj, customizeJsonParser, true);
                            arrayList.remove(size);
                            fieldInfo.setValue(obj, parseValue);
                        } else {
                            throw new IllegalArgumentException("final array/object fields are not supported");
                        }
                    } else if (isAssignableFrom) {
                        ((GenericData) obj).set(text, parseValue(null, null, arrayList, obj, customizeJsonParser, true));
                    } else {
                        if (customizeJsonParser != null) {
                            customizeJsonParser.handleUnrecognizedKey(obj, text);
                        }
                        skipChildren();
                    }
                    startParsingObjectOrArray = nextToken();
                } else {
                    return;
                }
            }
            return;
        }
        parseMap(null, (Map) obj, Types.getMapValueParameter(type), arrayList, customizeJsonParser);
    }

    public final <T> Collection<T> parseArrayAndClose(Class<?> cls, Class<T> cls2) throws IOException {
        return parseArrayAndClose((Class) cls, (Class) cls2, null);
    }

    @Beta
    public final <T> Collection<T> parseArrayAndClose(Class<?> cls, Class<T> cls2, CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            Collection<T> parseArray = parseArray((Class) cls, (Class) cls2, customizeJsonParser);
            return parseArray;
        } finally {
            close();
        }
    }

    public final <T> void parseArrayAndClose(Collection<? super T> collection, Class<T> cls) throws IOException {
        parseArrayAndClose((Collection) collection, (Class) cls, null);
    }

    @Beta
    public final <T> void parseArrayAndClose(Collection<? super T> collection, Class<T> cls, CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            parseArray((Collection) collection, (Class) cls, customizeJsonParser);
        } finally {
            close();
        }
    }

    public final <T> Collection<T> parseArray(Class<?> cls, Class<T> cls2) throws IOException {
        return parseArray((Class) cls, (Class) cls2, null);
    }

    @Beta
    public final <T> Collection<T> parseArray(Class<?> cls, Class<T> cls2, CustomizeJsonParser customizeJsonParser) throws IOException {
        Collection newCollectionInstance = Data.newCollectionInstance(cls);
        parseArray(newCollectionInstance, (Class) cls2, customizeJsonParser);
        return newCollectionInstance;
    }

    public final <T> void parseArray(Collection<? super T> collection, Class<T> cls) throws IOException {
        parseArray((Collection) collection, (Class) cls, null);
    }

    @Beta
    public final <T> void parseArray(Collection<? super T> collection, Class<T> cls, CustomizeJsonParser customizeJsonParser) throws IOException {
        parseArray(null, collection, cls, new ArrayList(), customizeJsonParser);
    }

    private <T> void parseArray(Field field, Collection<T> collection, Type type, ArrayList<Type> arrayList, CustomizeJsonParser customizeJsonParser) throws IOException {
        JsonToken startParsingObjectOrArray = startParsingObjectOrArray();
        while (startParsingObjectOrArray != JsonToken.END_ARRAY) {
            collection.add(parseValue(field, type, arrayList, collection, customizeJsonParser, true));
            startParsingObjectOrArray = nextToken();
        }
    }

    private void parseMap(Field field, Map<String, Object> map, Type type, ArrayList<Type> arrayList, CustomizeJsonParser customizeJsonParser) throws IOException {
        JsonToken startParsingObjectOrArray = startParsingObjectOrArray();
        while (startParsingObjectOrArray == JsonToken.FIELD_NAME) {
            String text = getText();
            nextToken();
            if (customizeJsonParser == null || !customizeJsonParser.stopAt(map, text)) {
                map.put(text, parseValue(field, type, arrayList, map, customizeJsonParser, true));
                startParsingObjectOrArray = nextToken();
            } else {
                return;
            }
        }
    }

    private final Object parseValue(Field field, Type type, ArrayList<Type> arrayList, Object obj, CustomizeJsonParser customizeJsonParser, boolean z) throws IOException {
        StringBuilder stringBuilder;
        Type resolveWildcardTypeOrTypeVariable = Data.resolveWildcardTypeOrTypeVariable(arrayList, type);
        Class cls = resolveWildcardTypeOrTypeVariable instanceof Class ? (Class) resolveWildcardTypeOrTypeVariable : null;
        if (resolveWildcardTypeOrTypeVariable instanceof ParameterizedType) {
            cls = Types.getRawClass((ParameterizedType) resolveWildcardTypeOrTypeVariable);
        }
        if (cls == Void.class) {
            skipChildren();
            return null;
        }
        String currentName;
        JsonToken currentToken = getCurrentToken();
        try {
            Type mapValueParameter;
            boolean z2;
            boolean z3;
            switch (getCurrentToken()) {
                case START_OBJECT:
                case FIELD_NAME:
                case END_OBJECT:
                    Preconditions.checkArgument(!Types.isArray(resolveWildcardTypeOrTypeVariable), "expected object or map type but got %s", resolveWildcardTypeOrTypeVariable);
                    Field cachedTypemapFieldFor = z ? getCachedTypemapFieldFor(cls) : null;
                    Object obj2 = null;
                    if (!(cls == null || customizeJsonParser == null)) {
                        obj2 = customizeJsonParser.newInstanceForObject(obj, cls);
                    }
                    Object obj3 = (cls == null || !Types.isAssignableToOrFrom(cls, Map.class)) ? null : 1;
                    if (cachedTypemapFieldFor != null) {
                        obj2 = new GenericJson();
                    } else if (obj2 == null) {
                        if (obj3 != null || cls == null) {
                            obj2 = Data.newMapInstance(cls);
                        } else {
                            obj2 = Types.newInstance(cls);
                        }
                    }
                    int size = arrayList.size();
                    if (resolveWildcardTypeOrTypeVariable != null) {
                        arrayList.add(resolveWildcardTypeOrTypeVariable);
                    }
                    if (!(obj3 == null || GenericData.class.isAssignableFrom(cls))) {
                        mapValueParameter = Map.class.isAssignableFrom(cls) ? Types.getMapValueParameter(resolveWildcardTypeOrTypeVariable) : null;
                        if (mapValueParameter != null) {
                            parseMap(field, (Map) obj2, mapValueParameter, arrayList, customizeJsonParser);
                            return obj2;
                        }
                    }
                    parse((ArrayList) arrayList, obj2, customizeJsonParser);
                    if (resolveWildcardTypeOrTypeVariable != null) {
                        arrayList.remove(size);
                    }
                    if (cachedTypemapFieldFor == null) {
                        return obj2;
                    }
                    JsonFactory factory;
                    JsonParser createJsonParser;
                    Object obj4 = ((GenericJson) obj2).get(cachedTypemapFieldFor.getName());
                    Preconditions.checkArgument(obj4 != null, "No value specified for @JsonPolymorphicTypeMap field");
                    String obj5 = obj4.toString();
                    Type type2 = null;
                    for (TypeDef typeDef : ((JsonPolymorphicTypeMap) cachedTypemapFieldFor.getAnnotation(JsonPolymorphicTypeMap.class)).typeDefinitions()) {
                        if (typeDef.key().equals(obj5)) {
                            type2 = typeDef.ref();
                            Preconditions.checkArgument(type2 == null, "No TypeDef annotation found with key: " + obj5);
                            factory = getFactory();
                            createJsonParser = factory.createJsonParser(factory.toString(obj2));
                            createJsonParser.startParsing();
                            return createJsonParser.parseValue(field, type2, arrayList, null, null, false);
                        }
                    }
                    if (type2 == null) {
                    }
                    Preconditions.checkArgument(type2 == null, "No TypeDef annotation found with key: " + obj5);
                    factory = getFactory();
                    createJsonParser = factory.createJsonParser(factory.toString(obj2));
                    createJsonParser.startParsing();
                    return createJsonParser.parseValue(field, type2, arrayList, null, null, false);
                case START_ARRAY:
                case END_ARRAY:
                    Type arrayComponentType;
                    boolean isArray = Types.isArray(resolveWildcardTypeOrTypeVariable);
                    z2 = resolveWildcardTypeOrTypeVariable == null || isArray || (cls != null && Types.isAssignableToOrFrom(cls, Collection.class));
                    Preconditions.checkArgument(z2, "expected collection or array type but got %s", resolveWildcardTypeOrTypeVariable);
                    Collection collection = null;
                    if (!(customizeJsonParser == null || field == null)) {
                        collection = customizeJsonParser.newInstanceForArray(obj, field);
                    }
                    if (collection == null) {
                        collection = Data.newCollectionInstance(resolveWildcardTypeOrTypeVariable);
                    }
                    if (isArray) {
                        arrayComponentType = Types.getArrayComponentType(resolveWildcardTypeOrTypeVariable);
                    } else if (cls == null || !Iterable.class.isAssignableFrom(cls)) {
                        arrayComponentType = null;
                    } else {
                        arrayComponentType = Types.getIterableParameter(resolveWildcardTypeOrTypeVariable);
                    }
                    mapValueParameter = Data.resolveWildcardTypeOrTypeVariable(arrayList, arrayComponentType);
                    parseArray(field, collection, mapValueParameter, arrayList, customizeJsonParser);
                    if (isArray) {
                        return Types.toArray(collection, Types.getRawArrayComponentType(arrayList, mapValueParameter));
                    }
                    return collection;
                case VALUE_TRUE:
                case VALUE_FALSE:
                    z3 = resolveWildcardTypeOrTypeVariable == null || cls == Boolean.TYPE || (cls != null && cls.isAssignableFrom(Boolean.class));
                    Preconditions.checkArgument(z3, "expected type Boolean or boolean but got %s", resolveWildcardTypeOrTypeVariable);
                    return currentToken == JsonToken.VALUE_TRUE ? Boolean.TRUE : Boolean.FALSE;
                case VALUE_NUMBER_FLOAT:
                case VALUE_NUMBER_INT:
                    z2 = field == null || field.getAnnotation(JsonString.class) == null;
                    Preconditions.checkArgument(z2, "number type formatted as a JSON number cannot use @JsonString annotation");
                    if (cls == null || cls.isAssignableFrom(BigDecimal.class)) {
                        return getDecimalValue();
                    }
                    if (cls == BigInteger.class) {
                        return getBigIntegerValue();
                    }
                    if (cls == Double.class || cls == Double.TYPE) {
                        return Double.valueOf(getDoubleValue());
                    }
                    if (cls == Long.class || cls == Long.TYPE) {
                        return Long.valueOf(getLongValue());
                    }
                    if (cls == Float.class || cls == Float.TYPE) {
                        return Float.valueOf(getFloatValue());
                    }
                    if (cls == Integer.class || cls == Integer.TYPE) {
                        return Integer.valueOf(getIntValue());
                    }
                    if (cls == Short.class || cls == Short.TYPE) {
                        return Short.valueOf(getShortValue());
                    }
                    if (cls == Byte.class || cls == Byte.TYPE) {
                        return Byte.valueOf(getByteValue());
                    }
                    throw new IllegalArgumentException("expected numeric type but got " + resolveWildcardTypeOrTypeVariable);
                case VALUE_STRING:
                    z3 = (cls != null && Number.class.isAssignableFrom(cls) && (field == null || field.getAnnotation(JsonString.class) == null)) ? false : true;
                    Preconditions.checkArgument(z3, "number field formatted as a JSON string must use the @JsonString annotation");
                    return Data.parsePrimitiveValue(resolveWildcardTypeOrTypeVariable, getText());
                case VALUE_NULL:
                    z2 = cls == null || !cls.isPrimitive();
                    Preconditions.checkArgument(z2, "primitive number field but found a JSON null");
                    if (!(cls == null || (cls.getModifiers() & 1536) == 0)) {
                        if (Types.isAssignableToOrFrom(cls, Collection.class)) {
                            return Data.nullOf(Data.newCollectionInstance(resolveWildcardTypeOrTypeVariable).getClass());
                        }
                        if (Types.isAssignableToOrFrom(cls, Map.class)) {
                            return Data.nullOf(Data.newMapInstance(cls).getClass());
                        }
                    }
                    return Data.nullOf(Types.getRawArrayComponentType(arrayList, resolveWildcardTypeOrTypeVariable));
                default:
                    throw new IllegalArgumentException("unexpected JSON node type: " + currentToken);
            }
        } catch (Throwable e) {
            stringBuilder = new StringBuilder();
            currentName = getCurrentName();
            if (currentName != null) {
                stringBuilder.append("key ").append(currentName);
            }
            if (field != null) {
                if (currentName != null) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append("field ").append(field);
            }
            throw new IllegalArgumentException(stringBuilder.toString(), e);
        }
        stringBuilder = new StringBuilder();
        currentName = getCurrentName();
        if (currentName != null) {
            stringBuilder.append("key ").append(currentName);
        }
        if (field != null) {
            if (currentName != null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("field ").append(field);
        }
        throw new IllegalArgumentException(stringBuilder.toString(), e);
    }

    private static Field getCachedTypemapFieldFor(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        lock.lock();
        try {
            Field field;
            if (cachedTypemapFields.containsKey(cls)) {
                field = (Field) cachedTypemapFields.get(cls);
                return field;
            }
            Field field2 = null;
            for (FieldInfo field3 : ClassInfo.of(cls).getFieldInfos()) {
                Field field4 = field3.getField();
                JsonPolymorphicTypeMap jsonPolymorphicTypeMap = (JsonPolymorphicTypeMap) field4.getAnnotation(JsonPolymorphicTypeMap.class);
                if (jsonPolymorphicTypeMap != null) {
                    boolean z;
                    Preconditions.checkArgument(field2 == null, "Class contains more than one field with @JsonPolymorphicTypeMap annotation: %s", cls);
                    Preconditions.checkArgument(Data.isPrimitive(field4.getType()), "Field which has the @JsonPolymorphicTypeMap, %s, is not a supported type: %s", cls, field4.getType());
                    TypeDef[] typeDefinitions = jsonPolymorphicTypeMap.typeDefinitions();
                    HashSet newHashSet = Sets.newHashSet();
                    if (typeDefinitions.length > 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    Preconditions.checkArgument(z, "@JsonPolymorphicTypeMap must have at least one @TypeDef");
                    for (TypeDef key : typeDefinitions) {
                        Preconditions.checkArgument(newHashSet.add(key.key()), "Class contains two @TypeDef annotations with identical key: %s", typeDefinitions[r0].key());
                    }
                    field = field4;
                } else {
                    field = field2;
                }
                field2 = field;
            }
            cachedTypemapFields.put(cls, field2);
            lock.unlock();
            return field2;
        } finally {
            lock.unlock();
        }
    }
}
