/*
 *
 *  *
 *  *
 *  *      Copyright 2020-2021 Luter.me
 *  *
 *  *      Licensed under the Apache License, Version 2.0 (the "License");
 *  *      you may not use this file except in compliance with the License.
 *  *      You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *      Unless required by applicable law or agreed to in writing, software
 *  *      distributed under the License is distributed on an "AS IS" BASIS,
 *  *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *      See the License for the specific language governing permissions and
 *  *      limitations under the License.
 *  *
 *  *
 *
 */

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
