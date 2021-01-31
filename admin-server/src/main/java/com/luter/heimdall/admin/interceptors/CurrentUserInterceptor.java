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

package com.luter.heimdall.admin.interceptors;

import com.luter.heimdall.admin.module.security.details.AppUserDetails;
import com.luter.heimdall.core.manager.AuthenticationManager;
import com.luter.heimdall.core.session.SimpleSession;
import com.luter.heimdall.starter.utils.context.BaseContextHolder;
import com.luter.heimdall.starter.utils.json.JacksonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

@Slf4j
@RequiredArgsConstructor
public class CurrentUserInterceptor implements WebRequestInterceptor {

    private final AuthenticationManager authenticationManager;


    @Override
    public void preHandle(WebRequest webRequest) {
        final SimpleSession currentUser = authenticationManager.getCurrentUser(false);
        if (null != currentUser && null != currentUser.getDetails()) {
            AppUserDetails userDetails = (AppUserDetails) currentUser.getDetails();
            if (null != userDetails && null != userDetails.getUser()) {
                BaseContextHolder.setUser(JacksonUtils.toJson(userDetails.getUser()));
                BaseContextHolder.setUserId(userDetails.getUser().getId());
            }

        }
    }

    @Override
    public void postHandle(WebRequest webRequest, ModelMap modelMap) {

    }

    @Override
    public void afterCompletion(WebRequest webRequest, Exception e) {
        BaseContextHolder.remove();
    }
}
