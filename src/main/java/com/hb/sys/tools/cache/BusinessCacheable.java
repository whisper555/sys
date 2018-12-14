package com.hb.sys.tools.cache;

/**
 * 描述：
 * 作者： hanbin
 * 时间： 2018-10-29 16:46
 **/


import org.springframework.cache.annotation.Cacheable;

import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Cacheable(value="defaultCache")//与配置
public @interface BusinessCacheable {

}
