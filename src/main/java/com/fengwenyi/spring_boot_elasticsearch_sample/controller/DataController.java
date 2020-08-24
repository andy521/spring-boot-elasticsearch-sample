package com.fengwenyi.spring_boot_elasticsearch_sample.controller;

import com.fengwenyi.api.result.CommonResponse;
import com.fengwenyi.spring_boot_elasticsearch_sample.entity.PhoneEntity;
import com.fengwenyi.spring_boot_elasticsearch_sample.repository.PhoneRepository;
import com.fengwenyi.spring_boot_elasticsearch_sample.service.DataService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 数据接口
 * @author Erwin Feng
 * @since 2020/7/10
 */
@Api("搜索服务接口")
@RestController
@RequestMapping(value = "/api/data",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class DataController {

    @Autowired
    private DataService dataService;

    @Autowired
    private PhoneRepository phoneRepository;

    // 抓取数据接口
    @GetMapping("/grab")
    public Mono<CommonResponse<?>> grab() {
        new Thread(() -> dataService.jd()).start();
        return Mono.just(CommonResponse.ok());
    }

    // 导出数据接口
    @GetMapping("/export")
    public Mono<CommonResponse<?>> export() {
        new Thread(() -> dataService.export()).start();
        return Mono.just(CommonResponse.ok());
    }

    @GetMapping("all")
    public Mono<CommonResponse<?>> getAll() {
        Iterable<PhoneEntity> all = phoneRepository.findAll();
        all.forEach(System.out::println);
        return Mono.just(CommonResponse.ok());
    }

}
