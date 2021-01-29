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

package com.luter.heimdall.admin.module.sys.service;


import com.luter.heimdall.admin.base.service.BaseService;
import com.luter.heimdall.admin.module.sys.dto.SysDictItemDTO;
import com.luter.heimdall.admin.module.sys.vo.SysDictItemVO;
import com.luter.heimdall.starter.model.pagination.PageDTO;
import com.luter.heimdall.starter.model.pagination.PagerVO;

import java.util.List;

public interface SysDictItemService extends BaseService {


    PageDTO<SysDictItemDTO> list(SysDictItemVO param, PagerVO pagerVO);

    List<SysDictItemDTO> listByTypeId(Long typeId);

    List<SysDictItemDTO> listByTypeName(String typeName);

    SysDictItemDTO getById(Long id);

    SysDictItemDTO save(SysDictItemVO param);

    void update(SysDictItemVO param);

    int deleteById(Long id);


}
