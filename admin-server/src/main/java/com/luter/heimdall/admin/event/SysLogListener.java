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

package com.luter.heimdall.admin.event;

import cn.hutool.json.JSONUtil;
import com.luter.heimdall.admin.module.sys.service.SysLogService;
import com.luter.heimdall.admin.module.sys.vo.SysLogVO;
import com.luter.heimdall.starter.syslog.dto.SysLogEventDTO;
import com.luter.heimdall.starter.syslog.event.SysLogEvent;
import com.luter.heimdall.starter.utils.ipregion.Ip2RegionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SysLogListener {
    private final SysLogService sysLogService;

    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        SysLogEventDTO sysLogDTO = (SysLogEventDTO) event.getSource();
        if (null != sysLogDTO) {
            SysLogVO logVO = new SysLogVO();
            BeanUtils.copyProperties(sysLogDTO, logVO);
            //获取IP地址归属地
            logVO.setUserLocation(Ip2RegionUtil.getIpRegionInfo(sysLogDTO.getRequestIp()));
            log.debug("记录系统日志:{}", JSONUtil.toJsonPrettyStr(logVO));
            sysLogService.save(logVO);
        }
    }

}