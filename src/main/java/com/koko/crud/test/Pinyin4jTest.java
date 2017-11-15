package com.koko.crud.test;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.Test;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.Collator;
import java.util.*;

public class Pinyin4jTest {
    @Test
    public void test_sort_pinyin() {
        Collator cmp = Collator.getInstance(java.util.Locale.CHINA);
        String[] arr = {"张三", "李四", "王五", "赵六", "JAVA", "123", "$%$#", "哈哈A",
                "1哈哈A", "1哈哈b", "1哈哈a", "哈哈", "哈",};
        List<String> list = Arrays.asList(arr);
        Arrays.sort(arr, cmp);
        System.out.println(list);
    }

    @Test
    public void testPinyin() {
        String name = "音乐";
        char[] charArray = name.toCharArray();
        for (char c : charArray) {
            System.out.println(c);
        }
        StringBuilder pinyin = new StringBuilder();
        for (int i = 0; i < charArray.length; i++) {
            if (Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")) {
                pinyin.append(PinyinHelper.toHanyuPinyinStringArray(charArray[i])[0]);
            } else {
                pinyin.append(charArray[i]);
            }
        }
        String[] strings = pinyin.toString().split("[0-9]+");
        StringBuilder sb = new StringBuilder();
        for (String str : strings) {
            char oneChar = Character.toUpperCase(str.charAt(0));
            sb.append(oneChar);
        }
        System.out.println(sb);
        System.out.println(pinyin);
    }

    @Test
    public void test11() {
        String ChineseStr = "音乐";
        char[] chineseChars = ChineseStr.trim().toCharArray();
        StringBuilder pinyin = new StringBuilder();
        for (int i = 0; i < chineseChars.length; i++) {
            // 如果字符是中文,则将中文转为汉语拼音,并取第一个字母大写
            if (String.valueOf(chineseChars[i]).matches("[\u4e00-\u9fa5]+")) {
                pinyin.append(Character.toUpperCase(PinyinHelper.toHanyuPinyinStringArray(chineseChars[i])[0].charAt(0)));
            }
        }
        System.out.println(pinyin);

    }
}

