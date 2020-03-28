package com.example.homework2.util;

import com.example.homework2.R;

/**
 * 选择头像Icon
 */
public class IconSelector {

    private final static String TYPE_ROBOT = "TYPE_ROBOT";
    private final static String TYPE_GAME = "TYPE_GAME";
    private final static String TYPE_SYSTEM = "TYPE_SYSTEM";
    private final static String TYPE_STRANGER = "TYPE_STRANGER";
    private final static String TYPE_USER = "TYPE_USER";


    public static int getIconId(String iconType) {
        switch (iconType) {
            case TYPE_ROBOT:
                return R.drawable.session_robot;
            case TYPE_GAME:
                return R.drawable.icon_micro_game_comment;
            case TYPE_SYSTEM:
                return R.drawable.session_system_notice;
            case TYPE_STRANGER:
                return R.drawable.session_stranger;
            case TYPE_USER:
                return R.drawable.icon_girl;
            default:
                return R.drawable.icon_girl;
        }
    }
}
