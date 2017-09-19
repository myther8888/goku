package com.yongle.goku.utils.constant;

/**
 * Created by weinh on 2017/2/9.
 */
public class ReturnCode {
    //系统相关
    public static final int SUCCESS = 0;
    public static final int ERROR = -1;
    public static final int SIGNATURE_ERROR = -10;
    public static final int TIMESTAMP_ERROR = -11;
    public static final int TIMESTAMP_EMPTY = -12;

    public static final int PARAM_EMPTY = 1001;
    //用户相关
    public static final int USERNAME_EMPTY = 1002;
    public static final int USERNAME_OVERLONG = 1003;
    public static final int USERNAME_REPEAT = 1004;
    public static final int NICKNAME_EMPTY = 1005;
    public static final int NICKNAME_OVERLONG = 1006;
    public static final int SEX_EMPTY = 1007;
    public static final int SEX_ERROR = 1008;
    public static final int PASSWORD_EMPTY = 1009;
    public static final int PASSWORD_INCONFORMITY = 1010;

    public static final int EMAIL_EMPTY = 1009;
    public static final int EMAIL_FORMAT_ERROR = 1010;
    public static final int EMAIL_OVERLONG = 1011;
    public static final int EMAIL_REPEAT = 1012;
    public static final int CONTACT_EMPTY = 1013;
    public static final int CONTACT_FORMAT_ERROR = 1014;
    public static final int CREATE_VERIFY_CODE_ERROR = 1015;
    public static final int VERIFY_CODE_EMPTY = 1016;
    public static final int VERIFY_CODE_ERROR = 1017;
    public static final int PHONECODE_REPEAT = 1019;
    public static final int PHONECODE_FORMAT_ERROR = 1019;
    public static final int USERNAME_ERROR = 1020;
    public static final int PASSWORD_ERROR = 1021;
    public static final int TOKEN_EMPTY = 1022;
    public static final int TOKEN_ERROR = 1023;
    public static final int CONTACT_OVERLONG = 1024;
    public static final int CHECK_VERIFICATION_CODE_ERROR = 1025;

    //瓶子相关
    public static final int BOTTLE_EMPTY = 2001;
    public static final int BOTTLE_TYPE_EMPTY = 2002;
    public static final int BOTTLE_TYPE_ERROR = 2003;
    public static final int BOTTLE_CONTENT_EMPTY = 2004;
    public static final int BOTTLE_CONTENT_OVERLONG = 2005;
    public static final int BOTTLE_IMAGE_EMPTY = 2006;
    public static final int BOTTLE_VOICE_EMPTY = 2007;
    public static final int BOTTLE_VIDEO_EMPTY = 2008;
    public static final int THROW_OVERSTEP_TIMES = 2009;
    public static final int UN_GOOD_LUCKY = 2010;
    public static final int PICK_OVERSTEP_TIMES = 2011;
}
