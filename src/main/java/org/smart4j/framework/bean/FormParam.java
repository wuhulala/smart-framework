package org.smart4j.framework.bean;

/**
 * @author xueaohui
 */
public class FormParam {
    private String fieldName;
    private Object fieldValue;

    public FormParam() {
    }

    public FormParam(String fieldName, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
