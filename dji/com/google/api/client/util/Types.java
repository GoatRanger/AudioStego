package com.google.api.client.util;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Types {
    public static ParameterizedType getSuperParameterizedType(Type type, Class<?> cls) {
        if ((type instanceof Class) || (type instanceof ParameterizedType)) {
            Type type2 = type;
            while (type2 != null && type2 != Object.class) {
                Class cls2;
                if (type2 instanceof Class) {
                    cls2 = (Class) type2;
                } else {
                    ParameterizedType parameterizedType = (ParameterizedType) type2;
                    Class<?> rawClass = getRawClass(parameterizedType);
                    if (rawClass == cls) {
                        return parameterizedType;
                    }
                    if (cls.isInterface()) {
                        for (Type type3 : rawClass.getGenericInterfaces()) {
                            if (cls.isAssignableFrom(type3 instanceof Class ? (Class) type3 : getRawClass((ParameterizedType) type3))) {
                                type2 = type3;
                                break;
                            }
                        }
                    }
                    Class<?> cls3 = rawClass;
                }
                type2 = cls2.getGenericSuperclass();
            }
        }
        return null;
    }

    public static boolean isAssignableToOrFrom(Class<?> cls, Class<?> cls2) {
        return cls.isAssignableFrom(cls2) || cls2.isAssignableFrom(cls);
    }

    public static <T> T newInstance(Class<T> cls) {
        try {
            return cls.newInstance();
        } catch (Exception e) {
            throw handleExceptionForNewInstance(e, cls);
        } catch (Exception e2) {
            throw handleExceptionForNewInstance(e2, cls);
        }
    }

    private static IllegalArgumentException handleExceptionForNewInstance(Exception exception, Class<?> cls) {
        StringBuilder append = new StringBuilder("unable to create new instance of class ").append(cls.getName());
        ArrayList arrayList = new ArrayList();
        if (cls.isArray()) {
            arrayList.add("because it is an array");
        } else if (cls.isPrimitive()) {
            arrayList.add("because it is primitive");
        } else if (cls == Void.class) {
            arrayList.add("because it is void");
        } else {
            if (Modifier.isInterface(cls.getModifiers())) {
                arrayList.add("because it is an interface");
            } else if (Modifier.isAbstract(cls.getModifiers())) {
                arrayList.add("because it is abstract");
            }
            if (!(cls.getEnclosingClass() == null || Modifier.isStatic(cls.getModifiers()))) {
                arrayList.add("because it is not static");
            }
            if (Modifier.isPublic(cls.getModifiers())) {
                try {
                    cls.getConstructor(new Class[0]);
                } catch (NoSuchMethodException e) {
                    arrayList.add("because it has no accessible default constructor");
                }
            } else {
                arrayList.add("possibly because it is not public");
            }
        }
        Iterator it = arrayList.iterator();
        Object obj = null;
        while (it.hasNext()) {
            String str = (String) it.next();
            if (obj != null) {
                append.append(" and");
            } else {
                obj = 1;
            }
            append.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(str);
        }
        return new IllegalArgumentException(append.toString(), exception);
    }

    public static boolean isArray(Type type) {
        return (type instanceof GenericArrayType) || ((type instanceof Class) && ((Class) type).isArray());
    }

    public static Type getArrayComponentType(Type type) {
        return type instanceof GenericArrayType ? ((GenericArrayType) type).getGenericComponentType() : ((Class) type).getComponentType();
    }

    public static Class<?> getRawClass(ParameterizedType parameterizedType) {
        return (Class) parameterizedType.getRawType();
    }

    public static Type getBound(WildcardType wildcardType) {
        Type[] lowerBounds = wildcardType.getLowerBounds();
        if (lowerBounds.length != 0) {
            return lowerBounds[0];
        }
        return wildcardType.getUpperBounds()[0];
    }

    public static Type resolveTypeVariable(List<Type> list, TypeVariable<?> typeVariable) {
        GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        if (genericDeclaration instanceof Class) {
            Class cls = (Class) genericDeclaration;
            int size = list.size();
            ParameterizedType parameterizedType = null;
            while (parameterizedType == null) {
                size--;
                if (size < 0) {
                    break;
                }
                parameterizedType = getSuperParameterizedType((Type) list.get(size), cls);
            }
            if (parameterizedType != null) {
                TypeVariable[] typeParameters = genericDeclaration.getTypeParameters();
                int i = 0;
                while (i < typeParameters.length && !typeParameters[i].equals(typeVariable)) {
                    i++;
                }
                Type type = parameterizedType.getActualTypeArguments()[i];
                if (!(type instanceof TypeVariable)) {
                    return type;
                }
                Type resolveTypeVariable = resolveTypeVariable(list, (TypeVariable) type);
                if (resolveTypeVariable != null) {
                    return resolveTypeVariable;
                }
                return type;
            }
        }
        return null;
    }

    public static Class<?> getRawArrayComponentType(List<Type> list, Type type) {
        Type resolveTypeVariable;
        if (type instanceof TypeVariable) {
            resolveTypeVariable = resolveTypeVariable(list, (TypeVariable) type);
        } else {
            resolveTypeVariable = type;
        }
        if (resolveTypeVariable instanceof GenericArrayType) {
            return Array.newInstance(getRawArrayComponentType(list, getArrayComponentType(resolveTypeVariable)), 0).getClass();
        }
        if (resolveTypeVariable instanceof Class) {
            return (Class) resolveTypeVariable;
        }
        if (resolveTypeVariable instanceof ParameterizedType) {
            return getRawClass((ParameterizedType) resolveTypeVariable);
        }
        boolean z;
        if (resolveTypeVariable == null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "wildcard type is not supported: %s", resolveTypeVariable);
        return Object.class;
    }

    public static Type getIterableParameter(Type type) {
        return getActualParameterAtPosition(type, Iterable.class, 0);
    }

    public static Type getMapValueParameter(Type type) {
        return getActualParameterAtPosition(type, Map.class, 1);
    }

    private static Type getActualParameterAtPosition(Type type, Class<?> cls, int i) {
        Type type2 = getSuperParameterizedType(type, cls).getActualTypeArguments()[i];
        if (!(type2 instanceof TypeVariable)) {
            return type2;
        }
        Type resolveTypeVariable = resolveTypeVariable(Arrays.asList(new Type[]{type}), (TypeVariable) type2);
        if (resolveTypeVariable != null) {
            return resolveTypeVariable;
        }
        return type2;
    }

    public static <T> Iterable<T> iterableOf(final Object obj) {
        if (obj instanceof Iterable) {
            return (Iterable) obj;
        }
        Class cls = obj.getClass();
        Preconditions.checkArgument(cls.isArray(), "not an array or Iterable: %s", cls);
        if (cls.getComponentType().isPrimitive()) {
            return new Iterable<T>() {
                public Iterator<T> iterator() {
                    return new Iterator<T>() {
                        int index = 0;
                        final int length = Array.getLength(obj);

                        public boolean hasNext() {
                            return this.index < this.length;
                        }

                        public T next() {
                            if (hasNext()) {
                                Object obj = obj;
                                int i = this.index;
                                this.index = i + 1;
                                return Array.get(obj, i);
                            }
                            throw new NoSuchElementException();
                        }

                        public void remove() {
                            throw new UnsupportedOperationException();
                        }
                    };
                }
            };
        }
        return Arrays.asList((Object[]) obj);
    }

    public static Object toArray(Collection<?> collection, Class<?> cls) {
        if (!cls.isPrimitive()) {
            return collection.toArray((Object[]) Array.newInstance(cls, collection.size()));
        }
        Object newInstance = Array.newInstance(cls, collection.size());
        int i = 0;
        for (Object obj : collection) {
            int i2 = i + 1;
            Array.set(newInstance, i, obj);
            i = i2;
        }
        return newInstance;
    }

    private Types() {
    }
}
