package com.yongle.goku.constant;

/**
 * Created by weinh on 2016/5/8.
 */
public class SysDict {
    public enum Sex {
        male("1", "男"), female("2", "女");
        public final String value;
        public final String name;

        Sex(String value, String name) {
            this.value = value;
            this.name = name;
        }

        public static boolean contains(String value) {
            boolean isHas = false;
            for (Sex sex : Sex.values()) {
                if (sex.value.equals(value)) {
                    isHas = true;
                    break;
                }
            }
            return isHas;
        }
    }

    public enum Status {
        enabled("1", "有效"), locked("2", "已锁定"), deleted("3", "已删除"), returm("4", "扔回大海");
        public final String value;
        public final String name;

        Status(String value, String name) {
            this.value = value;
            this.name = name;
        }
    }

    public enum VerifyCodeType {
        login("1", "登录"), bind_email("2", "绑定邮箱"), bind_phone("3", "绑定手机"),
        forgetPassword("4", "忘记密码");
        public final String value;
        public final String name;

        VerifyCodeType(String value, String name) {
            this.value = value;
            this.name = name;
        }
    }

    public enum MessageType {
        email("1", "邮件"), sms("2", "短信");
        public final String value;
        public final String name;

        MessageType(String value, String name) {
            this.value = value;
            this.name = name;
        }
    }

    public enum BottleType {
        text("1", "文字"), image("2", "图片"), voice("3", "语音"), video("4", "视频");
        public final String value;
        public final String name;

        BottleType(String value, String name) {
            this.value = value;
            this.name = name;
        }

        public static boolean isBottleType(String b) {
            BottleType allBottleType[] = BottleType.values();
            for (BottleType bottleType : allBottleType) {
                if (bottleType.value.equals(b)) {
                    return true;
                }
            }
            return false;
        }
    }
}
