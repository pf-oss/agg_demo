package com.validation.customize.enums;


import com.alibaba.fastjson.JSON;
import com.validation.customize.validator.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @author: pf
 * @create: 2020/12/24 16:59
 */
@Getter
@AllArgsConstructor
public enum GenderEnum implements IntArrayValuable {


    MALE(1, "男"),
    FEMALE(2, "女");

    /**
     * 值数组
     */
    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(GenderEnum::getValue).toArray();

    /**
     * 性别值
     */
    private  Integer value;
    /**
     * 性别名
     */
    private  String name;

    @Override
    public int[] array() {
        return ARRAYS;
    }
}
