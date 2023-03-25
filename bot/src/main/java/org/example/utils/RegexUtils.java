package org.example.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 *
 * @author 13988
 * @date 2023/03/09
 */
public class RegexUtils {
    public static String[] getArgs(String sourceMsg) {
        String[] split = sourceMsg.split(" ");
        if(split.length>1)
            return split;
        else
            return null;
    }

    /**
     * 是uid
     *
     * @param sample 样本
     * @return boolean
     */
    public static boolean isUid(String sample) {
        String regex = "^[0-9]*$";
        return isMatch(regex, sample);
    }

    /**
     * 匹配
     *
     * @param regex  正则表达式
     * @param sample 样本
     * @return boolean
     */
    public static boolean isMatch(String regex, String sample) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sample);
        return matcher.matches();
    }
}


