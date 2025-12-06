package com.hongs.skyserver.controller.admin;

import com.hongs.skycommon.constant.JwtClaimsConstant;
import com.hongs.skycommon.pojo.dto.EmployeeDTO;
import com.hongs.skycommon.pojo.dto.EmployeeLoginDTO;
import com.hongs.skycommon.pojo.entity.Employee;
import com.hongs.skycommon.properties.JwtProperties;
import com.hongs.skycommon.pojo.vo.EmployeeLoginVO;
import com.hongs.skycommon.utils.JwtUtil;
import com.hongs.skyserver.common.BaseResponse;
import com.hongs.skyserver.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Tag(name = "员工相关接口")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登员工录
     *
     * @param employeeLoginDTO
     * @return
     */
    @Operation(summary = "员工登录")
    @PostMapping("/login")
    public BaseResponse<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录: {}", employeeLoginDTO);
        Employee employee = employeeService.login(employeeLoginDTO);

        // 登录成果生成Jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return BaseResponse.success(employeeLoginVO);
    }

    /**
     * 员工退出
     *
     * @return
     */
    @Operation(summary = "员工退出")
    @PostMapping("/logout")
    public BaseResponse<String> logout() {
        return BaseResponse.success();
    }

    /**
     * 新增员工
     * @param employeeDTO
     * @return
     */
    @Operation(summary = "新增员工")
    @PostMapping
    public BaseResponse save(@RequestBody EmployeeDTO employeeDTO) {
        log.info("新增员工: {}", employeeDTO);
        employeeService.save(employeeDTO);
        return BaseResponse.success();
    }
}
