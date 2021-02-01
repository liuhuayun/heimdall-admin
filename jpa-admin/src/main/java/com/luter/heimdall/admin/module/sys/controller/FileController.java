package com.luter.heimdall.admin.module.sys.controller;

import com.luter.heimdall.starter.jpa.base.controller.BaseJpaController;
import com.luter.heimdall.starter.model.base.ResponseVO;
import com.luter.heimdall.starter.oss.dto.OssDTO;
import com.luter.heimdall.starter.oss.service.OssService;
import com.luter.heimdall.starter.syslog.annotation.SysLog;
import com.luter.heimdall.starter.utils.response.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传 控制器
 */
@Slf4j
@RestController
@RequestMapping("/sys/file")
@Api(value = "文件上传下载", tags = "文件上传下载")
@RequiredArgsConstructor
public class FileController extends BaseJpaController {
    private final OssService ossService;

    @PostMapping("/upload/image")
    @ApiOperation(value = "上传图片", notes = "上传图片", response = ResponseVO.class)
    @SysLog
    public ResponseEntity<ResponseVO<OssDTO>> uploadAvatar(@RequestParam("file") MultipartFile mFile) {
        final OssDTO avatar = ossService.uploadImage(mFile, "images", 200, 200, false);
        return ResponseUtils.ok("上传成功", avatar);
    }

}
