package com.typemoon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FilePathEnum {

    AVATAR("typemoon/avatar/", "头像路径"),

    ARTICLE("typemoon/articles/", "文章图片路径"),

    VOICE("typemoon/voice/", "音频路径"),

    PHOTO("typemoon/photos/", "相册路径"),

    CONFIG("typemoon/config/", "配置图片路径"),

    TALK("typemoon/talks/", "配置图片路径"),

    MD("typemoon/markdown/", "md文件路径");

    private final String path;

    private final String desc;

}