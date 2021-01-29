package com.luter.heimdall.admin.module.sys.controller;


import com.luter.heimdall.admin.module.sys.service.SysOnlineUserService;
import com.luter.heimdall.core.session.Page;
import com.luter.heimdall.core.session.SimpleSession;
import com.luter.heimdall.starter.jpa.base.controller.BaseJpaController;
import com.luter.heimdall.starter.model.base.ResponseVO;
import com.luter.heimdall.starter.model.pagination.PagerVO;
import com.luter.heimdall.starter.syslog.annotation.SysLog;
import com.luter.heimdall.starter.utils.response.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/sys/online")
@Api(value = "在线用户管理", tags = "在线用户管理")
@RequiredArgsConstructor
public class SysOnlineUserController extends BaseJpaController {

    private final SysOnlineUserService sysOnlineUserService;


    @GetMapping("/list")
    @ApiOperation(value = "获取在线用户列表", notes = "获取在线用户列表", response = ResponseVO.class)
    @SysLog
    public ResponseEntity<ResponseVO<Page<SimpleSession>>> list(PagerVO page) {
        return ResponseUtils.ok(sysOnlineUserService.getOnlineUser(page));
    }

    @PostMapping("/kick")
    @ApiOperation(value = "强制注销用户", notes = "强制注销用户", response = ResponseVO.class)
    @SysLog
    public Object kickOut(@RequestParam String sessionId) {
        return ResponseUtils.ok(sysOnlineUserService.kickoutBySessionId(sessionId));
    }
}
