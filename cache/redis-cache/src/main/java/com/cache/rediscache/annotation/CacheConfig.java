//package com.cache.rediscache.annotation;
//
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.AnnotationUtils;
//import org.springframework.expression.Expression;
//import org.springframework.expression.ExpressionParser;
//import org.springframework.expression.spel.standard.SpelExpressionParser;
//import org.springframework.expression.spel.support.StandardEvaluationContext;
//import org.springframework.util.StringUtils;
//
//import java.lang.reflect.Parameter;
//
//@EnableCaching // 使用了CacheManager，别忘了开启它  否则无效
//@Configuration
//public class CacheConfig extends CachingConfigurerSupport {
//
//    @Bean(name = "myMethodParamKeyGenerator")
//    public KeyGenerator myMethodParamKeyGenerator() {
//        return (target, method, params) -> {
//            //获得注解
//            MyCacheable myCacheable = AnnotationUtils.findAnnotation(method, MyCacheable.class);
//            if (myCacheable != null) {
//                String myKey = myCacheable.myKey();
//                if (myKey != null && StringUtils.hasText(myKey)) {
//                    //获取方法的参数集合
//                    Parameter[] parameters = method.getParameters();
//                    StandardEvaluationContext context = new StandardEvaluationContext();
//
//                    //遍历参数，以参数名和参数对应的值为组合，放入StandardEvaluationContext中
//                    // 注意：若没有java8的编译参数-parameters，参数名都回事arg0,arg1...  若有参数就是具体的参数名了
//                    for (int i = 0; i < parameters.length; i++) {
//                        context.setVariable(parameters[i].getName(), params[i]);
//                    }
//
//
//                    ExpressionParser parser = new SpelExpressionParser();
//                    //根据newKey来解析获得对应值
//                    Expression expression = parser.parseExpression(myKey);
//                    return expression.getValue(context, String.class);
//
//                }
//            }
//            return params[0].toString();
//        };
//    }
//}
