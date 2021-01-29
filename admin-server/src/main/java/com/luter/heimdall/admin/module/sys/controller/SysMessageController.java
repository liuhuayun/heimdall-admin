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

package com.luter.heimdall.admin.module.sys.controller;


import com.luter.heimdall.admin.module.sys.dto.SysMessageDTO;
import com.luter.heimdall.admin.module.sys.service.SysMessageService;
import com.luter.heimdall.admin.module.sys.vo.SysMessageVO;
import com.luter.heimdall.starter.jpa.base.controller.BaseJpaController;
import com.luter.heimdall.starter.model.base.ResponseVO;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;
import com.luter.heimdall.starter.syslog.annotation.SysLog;
import com.luter.heimdall.starter.syslog.enums.BizType;
import com.luter.heimdall.starter.utils.response.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/sys/message")
@Api(value = "系统消息内容", tags = "系统消息内容")
@RequiredArgsConstructor
public class SysMessageController extends BaseJpaController {

    private final SysMessageService sysMessageService;



    @GetMapping("/detail/{id}")
    @ApiOperation(value = "获取数据详情", notes = "获取数据详情", response = ResponseVO.class)
    @SysLog(value = "根据ID查询单条记录详情", type = BizType.DETAIL)
    public ResponseEntity<ResponseVO<SysMessageDTO>> getById(@PathVariable Long id) {
        SysMessageDTO data = sysMessageService.getById(id);
        return ResponseUtils.ok(data);
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表数据查询", notes = "列表数据查询", response = PageDTO.class)
    @SysLog(value = "列表数据查询", type = BizType.LIST)
    public ResponseEntity<ResponseVO<PageDTO<SysMessageDTO>>> list(SysMessageVO param, PagerVO pager) {
        PageDTO<SysMessageDTO> list = sysMessageService.list(param, pager);
        return ResponseUtils.ok(list);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增数据", notes = "新增数据", response = ResponseVO.class)
    @SysLog(value = "新增数据", type = BizType.INSERT)
    public ResponseEntity<ResponseVO<SysMessageDTO>> save(@Validated @RequestBody SysMessageVO param) {
        SysMessageDTO data = sysMessageService.save(param);
        return ResponseUtils.ok("新增成功", data);
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改数据", notes = "修改数据", response = ResponseVO.class)
    @SysLog(value = "修改数据", type = BizType.UPDATE)
    public ResponseEntity<ResponseVO<Void>> update(@Validated @RequestBody SysMessageVO param) {
        sysMessageService.update(param);
        return ResponseUtils.ok("修改成功");
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除数据", notes = "删除数据", response = ResponseVO.class)
    @SysLog(value = "根据ID删除单条记录", type = BizType.DELETE)
    public ResponseEntity<ResponseVO<Void>> delete(@PathVariable Long id) {
        int i = sysMessageService.deleteById(id);
        if (i == 1) {
            return ResponseUtils.ok("删除成功:" + i);
        }
        return ResponseUtils.fail("删除失败" + i);
    }

}


