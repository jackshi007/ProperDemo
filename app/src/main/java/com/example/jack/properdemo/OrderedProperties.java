package com.example.jack.properdemo;

import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

/**
 * 创建有顺序的OrderedProperties类继承自Properties
 * 由于Properties是继承自Hashtable，而Hashtable是无序的。
 * 解决不同类型的参数需要放在一个文件里的需求
 */

public class OrderedProperties extends Properties {
    private static final long serialVersionUID = -4627607243846121965L;

    private final LinkedHashSet<Object> keys = new LinkedHashSet<Object>();

    public Enumeration<Object> keys() {

        return Collections.<Object>enumeration(keys);

    }

    public Object put(Object key, Object value) {

        keys.add(key);

        return super.put(key, value);

    }

    public Set<Object> keySet() {

        return keys;

    }

    public Set<String> stringPropertyNames() {

        Set<String> set = new LinkedHashSet<String>();

        for (Object key : this.keys) {

            set.add((String) key);

        }
        return set;

    }
}