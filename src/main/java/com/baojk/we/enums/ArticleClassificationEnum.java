package com.baojk.we.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/11/29
 */
public enum ArticleClassificationEnum {
    FRONT_END(101, "前端"),
    BACK_END(102, "后端"),
    DATABASE(103, "数据库"),
    OPERATIONS(104, "运维"),
    GOOD_IDEAS(106, "奇技淫巧"),
    ZATAN(200, "随笔"),
    ALGORITHM(300, "算法"),
    JAVA(400, "Java"),;

    /**
     * 分类编码
     */
    public int code;

    /**
     * 分类描述
     */
    public String name;

    ArticleClassificationEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getVal(int code) {
        switch (code) {
            case 101:
                return "前端";
            case 102:
                return "后端";
            case 103:
                return "数据库";
            case 104:
                return "运维";
            case 300:
                return "算法";
            case 106:
                return "奇技淫巧";
            case 200:
                return "随笔";
            case 400:
                return "Java";
            default:
                break;
        }
        return "";
    }

    public static String getTagColor(int code) {
        switch (code) {
            case 101:
                return "background-color: #6b5152;";
            case 102:
                return "background-color: #c1cbd7;";
            case 103:
                return "background-color: #96a48b;";
            case 104:
                return "background-color: #d8caaf;";
            case 300:
                return "background-color: #965454;";
            case 106:
                return "background-color: #d3d4cc;";
            case 200:
                return "background-color: #a27e7e;";
            case 400:
                return "background-color: #9ca8b8;";
            default:
                break;
        }
        return "";
    }

    public static List<Integer> getCodes(List<String> classficationList) {
        List<Integer> list = new ArrayList<>();

        classficationList.forEach(c -> {
            switch (c) {
                case "all":
                    list.add(101);
                    list.add(102);
                    list.add(103);
                    list.add(104);
                    list.add(300);
                    list.add(106);
                    list.add(200);
                    list.add(400);
                    break;
                case "前端":
                    list.add(101);
                    break;
                case "后端":
                    list.add(102);
                    break;
                case "数据库":
                    list.add(103);
                    break;
                case "运维":
                    list.add(104);
                    break;
                case "算法":
                    list.add(300);
                    break;
                case "奇技淫巧":
                    list.add(106);
                    break;
                case "随笔":
                    list.add(200);
                    break;
                case "Java":
                    list.add(400);
                    break;
                default:
                    break;
            }
        });

        return list;
    }

    //    public static void main(String args []){
    //        System.out.println(FRONT_END.code);
    //    }
}
