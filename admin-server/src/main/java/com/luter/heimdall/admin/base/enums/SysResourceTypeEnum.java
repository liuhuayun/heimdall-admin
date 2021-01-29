package com.luter.heimdall.admin.base.enums;


public enum SysResourceTypeEnum {

    MENU(1, "菜单"),
    PAGE(2, "页面"),
    PERM(3, "操作权限(url)");
    private final int value;
    private final String name;


    SysResourceTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int value() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.value + " " + this.name();
    }

    public static SysResourceTypeEnum valueOf(int value) {
        SysResourceTypeEnum status = resolve(value);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + value + "]");
        } else {
            return status;
        }
    }

    public static SysResourceTypeEnum resolve(int value) {
        SysResourceTypeEnum[] var1 = values();
        for (SysResourceTypeEnum status : var1) {
            if (status.value == value) {
                return status;
            }
        }

        return null;
    }
}
