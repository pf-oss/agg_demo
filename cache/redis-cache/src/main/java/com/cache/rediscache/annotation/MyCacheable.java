//package com.cache.rediscache.annotation;
//
//
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.core.annotation.AliasFor;
//
//import java.lang.annotation.*;
//
///**
// * @Description:
// * @author: pf
// * @create: 2020/12/16 10:41
// */
//@Target({ElementType.METHOD, ElementType.TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//@Inherited
//@Documented
//@Cacheable
//public @interface MyCacheable {
//
//    @AliasFor("cacheNames")
//    String[] value() default {};
//    @AliasFor("value")
//    String[] cacheNames() default {};
//    String key() default "";
//    String keyGenerator() default "";
//    String cacheManager() default "";
//    String cacheResolver() default "";
//    String condition() default "";
//    String unless() default "";
//    boolean sync() default false;
//
//    // 自定义的属性，代替key属性（请不要使用key属性了）
//    String myKey() default "";
//}
