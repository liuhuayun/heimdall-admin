
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
