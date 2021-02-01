
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

package com.luter.heimdall.admin.module.security.details;

import com.luter.heimdall.admin.module.sys.dto.SysUserDTO;
import com.luter.heimdall.core.details.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDetails implements UserDetails {
    private SysUserDTO user;
    private String principal;

    public AppUserDetails(SysUserDTO user) {
        this.user = user;
    }

    @Override
    public String getPrincipal() {
        return "APP:" + user.getId();
    }

    @Override
    public boolean enabled() {
        return !user.getLocked();
    }

    public SysUserDTO getUser() {
        return user;
    }

    public void setUser(SysUserDTO user) {
        this.user = user;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }


}
