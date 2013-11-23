package com.ags.pirate.model;

import java.lang.reflect.Method;

/**
 * @author Angel
 * @since 23/11/13
 */
public class BeanItem<B> {

    private B beanItem;

    public BeanItem(B beanItem) {
        this.beanItem = beanItem;
    }

    public Object getValueFromProperty(String property) {
        Method[] methods = beanItem.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().toLowerCase().contains(property.toLowerCase()) && isGetter(method)) {
                try {
                    return method.invoke(beanItem,null);
                } catch (Exception e) {
                    throw new RuntimeException("no property found: "+property);
                }
            }
        }
        throw new RuntimeException("no property found: "+property);
    }

    public B getBeanItem() {
        return beanItem;
    }

    private boolean isGetter(Method method) {
        if (!method.getName().startsWith("get")) return false;
        if (method.getParameterTypes().length != 0) return false;
        if (void.class.equals(method.getReturnType())) return false;
        return true;
    }

    private boolean isSetter(Method method) {
        if (!method.getName().startsWith("set")) return false;
        if (method.getParameterTypes().length != 1) return false;
        return true;
    }
}
