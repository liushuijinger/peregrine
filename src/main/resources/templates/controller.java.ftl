package ${package.Controller};

import ${parent}.common.base.ApiMessage;
import ${parent}.common.base.Assert;
import ${parent}.common.base.Result;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if swagger>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @blog https://liushuijinger.blog.csdn.net
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if swagger>
@Api(value = "${entity}对象",tags = "${table.comment!}")
</#if>
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Autowired
    private ${table.serviceName} ${table.serviceName?uncap_first};


    @ApiOperation(value = "查询", response = ${entity}.class)
    @GetMapping(value = "/{id}")
    public Result<${entity}> info(@PathVariable Integer id) {
      ${entity} ${entity?uncap_first} = ${table.serviceName?uncap_first}.getById(id);
      Assert.assertNotNull(${entity?uncap_first}, ApiMessage.NOT_FOUND);
      return Result.success(${entity?uncap_first});
    }

    @ApiOperation(value = "新增")
    @PostMapping
    public Result add(@RequestBody ${entity} ${entity?uncap_first}) {
      ${table.serviceName?uncap_first}.save(${entity?uncap_first});
      return Result.success();
    }

    @ApiOperation(value = "修改")
    @PutMapping
    public Result modify(@RequestBody ${entity} ${entity?uncap_first}) {
      ${table.serviceName?uncap_first}.updateById(${entity?uncap_first});
      return Result.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/{id}")
    public Result remove(@PathVariable Integer id) {
      ${table.serviceName?uncap_first}.removeById(id);
      return Result.success();
    }
}
</#if>
