package plb.accounting.common.test.helpers;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.core.GenericCollectionTypeResolver;
import org.springframework.core.MethodParameter;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;


public abstract class MockHelper {
    /**
     *
     */
    public static final Random RNG = new Random();

    /**
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MockHelper.class);


    /**
     * 
     * @param <T>
     * @param targetClass
     * @return
     */
    public static <T> T populate(Class<T> targetClass) {
        return populate(targetClass, null);
    }

    /**
     * 
     * @param <T>
     * @param targetClass
     * @return
     */
    public static <T> T populate(Class<T> targetClass, Map<String, MockObjectCreator<?>> objFactoriesMap) {
        return initObject(targetClass, objFactoriesMap, true);
    }

    /**
     * 
     * @param <T>
     * @param targetClass
     * @return
     */
    public static <T> List<T> populateList(Class<T> targetClass, Map<String, MockObjectCreator<?>> objFactoriesMap) {
        List<T> results = new ArrayList<T>();
        int size = RNG.nextInt(200);

        for (int i = 0; i < size; i++) {
            results.add(initObject(targetClass, objFactoriesMap, true));
        }

        return results;
    }

    /**
     * 
     * @param <T>
     * @param targetClass
     * @return
     */
    public static <T> List<T> populateList(Class<T> targetClass) {

        return populateList(targetClass, null);
    }


    /**
     * 
     * @return
     */
    public static String getRandomTime() {
        return "1256";//StringUtils.leftPad("" + RNG.nextInt(24), 2, "0") + StringUtils.leftPad("" + RNG.nextInt(60), 2, "0");
    }

    /**
     * 
     * @return
     */
    public static Date getRandomDateTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,-RNG.nextInt(100));
        calendar.add(Calendar.MINUTE,-RNG.nextInt(100));
        calendar.add(Calendar.SECOND,-RNG.nextInt(100));

        return calendar.getTime();
    }

    /**
     * 
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        String result = new String();

        for (int i = 0; i < length; i++) {
            result += (char) ('a' + RNG.nextInt(26));
        }

        return result;
    }

    private static <T> T initObject(Class<T> clazz, Map<String, MockObjectCreator<?>> objFactoriesMap,
            boolean initValueTypes) {
        T myInstance = instantiateClass(clazz, objFactoriesMap);
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(myInstance);
        PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();

        for (PropertyDescriptor pd : pds) {
            if (pd.getWriteMethod() != null) {
                Class<?> propClazz = pd.getPropertyType();
                Object propertyValue = beanWrapper.getPropertyValue(pd.getName());
//                LOGGER.debug("Initializing " + pd.getName());

                    // Object that needs to be initialized
                if (isObjectType(propClazz)) {
                    beanWrapper.setPropertyValue(pd.getName(), initObject(propClazz, objFactoriesMap, initValueTypes));

                    // Collection
                } else if (Collection.class.isAssignableFrom(propClazz)) {
                    Collection myCollection;

                    if (propClazz.isInterface()) {
                        if (List.class.isAssignableFrom(propClazz)) {
                            myCollection = new ArrayList();
                        } else if (Set.class.isAssignableFrom(propClazz)) {
                            myCollection = new HashSet();
                        } else {
                            throw new RuntimeException("unsupported collection interface:" + propClazz.getName());
                        }
                    } else {
                        myCollection = (Collection) BeanUtils.instantiateClass(propClazz);
                    }

                    Class<?> collectionParameterType = getCollectionParameterType(pd);

                    if (collectionParameterType != null) {
                        if (isObjectType(collectionParameterType)) {
                            if (collectionParameterType.getSimpleName().startsWith("DeclarationAction")) {
                                initDeclarationActionCollection(initValueTypes, myCollection, collectionParameterType);
                            } else {
                                myCollection.add(initObject(collectionParameterType, objFactoriesMap, initValueTypes));
                                // if initValueTypes also add random number of items
                                for (int i = 0; initValueTypes == true && i < RNG.nextInt(100); i++) {
                                    myCollection.add(initObject(collectionParameterType, objFactoriesMap,
                                            initValueTypes));
                                }
                            }
                        } else {
                            myCollection.add(BeanUtils.instantiateClass(collectionParameterType));
                            // if initValueTypes also add random number of items
                            for (int i = 0; initValueTypes == true && i < RNG.nextInt(100); i++) {
                                myCollection.add(BeanUtils.instantiateClass(collectionParameterType));
                            }
                        }
                    } else {
                        /*
                         * commented out as it would result in ClassCastException myCollection.add(new Object()); //if
                         * initValueTypes also add random number of items for (int i = 0; initValueTypes == true && i <
                         * RNG.nextInt(100); i++) { myCollection.add(new Object()); }
                         */
                    }

                    beanWrapper.setPropertyValue(pd.getName(), myCollection);

                    // Array
                } else if (propClazz.isArray()) {
                    Class arrayType = propClazz.getComponentType();
                    Object myArray = Array.newInstance(arrayType, 1);

                    if (byte[].class.isAssignableFrom(propClazz)) {
                        Object defaultVal = getDefaultValue(propClazz, pd);
                        beanWrapper.setPropertyValue(pd.getName(), defaultVal);
                    } else if (isObjectType(arrayType)) {
                        Array.set(myArray, 0, initObject(arrayType, objFactoriesMap, initValueTypes));
                    } else {
                        Array.set(myArray, 0, BeanUtils.instantiateClass(arrayType));
                    }

                    beanWrapper.setPropertyValue(pd.getName(), myArray);

                    // Value type or other object
                } else if (initValueTypes) {
                    LOGGER.debug("Setting init value " + pd.getName());

                    Object defaultVal = getDefaultValue(propClazz, pd);

                    if (null != defaultVal) {
                        LOGGER.debug("\t" + defaultVal);
                        beanWrapper.setPropertyValue(pd.getName(), defaultVal);
                    } else if (!propClazz.isInterface() && Serializable.class.isAssignableFrom(propClazz)) {
                        try {
                            LOGGER.debug("\tnew instance of " + propClazz.getName());
                            beanWrapper.setPropertyValue(pd.getName(), instantiateClass(propClazz, objFactoriesMap));
                        } catch (BeanInstantiationException e) {
                            throw new RuntimeException(
                                    "Could not init field " + pd.getName() + " of type " + propClazz, e);
                        }
                    }
                }
            }
        }

        return myInstance;
    }

    /**
     * This approach can be extended by providing object factory per object field
     * 
     * @param clazz
     * @param objFactoriesMap
     * @return
     */
    private static <T> T instantiateClass(Class<T> clazz, Map<String, MockObjectCreator<?>> objFactoriesMap) {

        if (objFactoriesMap != null) {
            if (objFactoriesMap.containsKey(clazz.getSimpleName()))
                return (T) objFactoriesMap.get(clazz.getSimpleName()).create();
        }

        return (T) BeanUtils.instantiateClass(clazz);
    }

    /**
     * 
     * @param initValueTypes
     * @param declarationActionCollection
     * @param collectionParameterType
     */
    private static void initDeclarationActionCollection(boolean initValueTypes, Collection declarationActionCollection,
            Class<?> collectionParameterType) {
        // find actionType class
        Class actionTypeEnumClazz = null;
        Field[] fields = collectionParameterType.getDeclaredFields();

        for (Field field : fields) {
            if (field.getName().equals("actionType") && field.getType().isEnum()) {
                actionTypeEnumClazz = field.getType();

                break;
            }
        }

        if (actionTypeEnumClazz == null) {
            throw new RuntimeException("Cannot find actionType enum field on class " + collectionParameterType);
        }

        // for each enum constant add a declaration action
        Object[] enumConstants = actionTypeEnumClazz.getEnumConstants();

        for (Object enumConstant : enumConstants) {
            Object initObject = initObject(collectionParameterType, null, initValueTypes);

            // set actionType
            ReflectionUtils.invokeMethod(ReflectionUtils.findMethod(collectionParameterType, "setActionType",
                    new Class[] { actionTypeEnumClazz }), initObject, new Object[] { enumConstant });

            // set actionReferenceId
            Method setActionReferenceIdMethod = ReflectionUtils.findMethod(collectionParameterType,
                    "setActionReferenceId", new Class[] { String.class });

            if (setActionReferenceIdMethod != null) {
                String actionRefIdvalue;

                if (((Enum) enumConstant).name().endsWith("calculateDutiesAndTaxes")
                        || ((Enum) enumConstant).name().endsWith("receiveDeclaration")
                        || ((Enum) enumConstant).name().endsWith("takeDecision")
                        || ((Enum) enumConstant).name().endsWith("release_registerControlResults")) {
                    actionRefIdvalue = "GR003240200234-2";
                } else {
                    actionRefIdvalue = "1234";
                }

                ReflectionUtils.invokeMethod(setActionReferenceIdMethod, initObject, new Object[] { actionRefIdvalue });
            } else {
                setActionReferenceIdMethod = ReflectionUtils.findMethod(collectionParameterType,
                        "setActionReferenceId", new Class[] { Long.class });
                ReflectionUtils.invokeMethod(setActionReferenceIdMethod, initObject, new Object[] { new Long(1234L) });
            }

            // set EmployeeNumber
            Method setEmployeeNumberMethod = ReflectionUtils.findMethod(collectionParameterType, "setEmployeeNumber",
                    new Class[] { String.class });

            if (setEmployeeNumberMethod == null) {
                setEmployeeNumberMethod = ReflectionUtils.findMethod(collectionParameterType, "setEmployeeNo",
                        new Class[] { String.class });
            }

            ReflectionUtils.invokeMethod(setEmployeeNumberMethod, initObject, new Object[] { "999999999" });

            declarationActionCollection.add(initObject);
        }
    }

    /**
     * 
     * 
     * @param clazz
     * 
     * @return
     */
    private static boolean isObjectType(Class<?> clazz) {
        if (clazz.isInterface() || clazz.isEnum() || clazz.isArray() || Collection.class.isAssignableFrom(clazz)
                || BeanUtils.isSimpleValueType(clazz) || clazz.isAnnotation()) {
            return false;
        }

        return true;
    }

    /**
     * 
     * @param pd
     * @return
     */
    private static Class<?> getCollectionParameterType(PropertyDescriptor pd) {
        MethodParameter writeMethodParameter = BeanUtils.getWriteMethodParameter(pd);

        return GenericCollectionTypeResolver.getCollectionParameterType(writeMethodParameter);
    }

    /**
     * 
     * @param clazz
     * @param pd
     * @return
     */
    private static Object getDefaultValue(Class clazz, PropertyDescriptor pd) {
        if (Number.class.isAssignableFrom(clazz)) {
            if (pd.getName().equals("itemNumber")) {
                return 1;
            }

            return RNG.nextInt(100);
        } else if (String.class.isAssignableFrom(clazz)) {
            if (pd.getName().equals("version")) {
                return "" + RNG.nextInt(100);
            }

            if (pd.getName().endsWith("Time")) {
                return getRandomTime();
            }
            if (pd.getName().toLowerCase().endsWith("payload")) {
                return SAMPLE_XML_DOCUMENT;
            }

            return getRandomString(5);
        } else if (Date.class.isAssignableFrom(clazz)) {
            return getRandomDateTime();
        } else if (Boolean.class.isAssignableFrom(clazz)) {
            return RNG.nextBoolean();
        } else if (boolean.class.isAssignableFrom(clazz)) {
            return RNG.nextBoolean();
        } else if (long.class.isAssignableFrom(clazz)) {
            return RNG.nextInt(100);
        } else if (clazz.isEnum()) {
            return clazz.getEnumConstants()[0];
        } else if (byte[].class.isAssignableFrom(clazz)) {
            byte[] randomByteArray = new byte[65];
            RNG.nextBytes(randomByteArray);
            return randomByteArray;
        }

        return null;
    }

    /**
     * 
     * @param returnClass
     * @return
     */
    private static Class<?> classForName(String returnClass) {
        Class<?> clazz;

        try {
            clazz = ClassUtils.forName(returnClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (LinkageError e) {
            throw new RuntimeException(e);
        }

        return clazz;
    }

    private interface FieldCallback {
        /**
         * 
         * 
         * @param field
         */
        public void field(String field);

        /**
         * 
         * 
         * @param path
         */
        public void pushNestedPath(String path);

        /**
         *
         */
        public void popNestedPath();
    }

    private static String SAMPLE_XML_DOCUMENT = "<?xml version='1.0' encoding='UTF-8'?><sampleMessage attributeName='attribute value'><sampleChild childAttributeName='attribute value'>sample content</sampleChild></sampleMessage>";
}
