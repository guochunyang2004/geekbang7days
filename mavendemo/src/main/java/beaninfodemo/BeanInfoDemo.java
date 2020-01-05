package beaninfodemo;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.stream.Stream;

public class BeanInfoDemo {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class,Object.class);
        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(propertyDescriptor->{
                    System.out.println(propertyDescriptor);
                    // PropertyDescriptor 允许添加属性编辑器 - propertyEditor
                    Class<?> propertyType = propertyDescriptor.getPropertyType();
                    String propertyName = propertyType.getName();
                    if ("age".equals(propertyName)){
                        propertyDescriptor.setPropertyEditorClass(StringToIntegerPropertyEditor.class);
                        //propertyDescriptor.createPropertyEditor();
                    }
                });

    }

    static class  StringToIntegerPropertyEditor extends PropertyEditorSupport{
        public void setAsText(String text) throws java.lang.IllegalArgumentException {
           Integer value = Integer.valueOf(text);
            setValue(value);
        }
    }
}
