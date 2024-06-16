package com.APSharing.vo.kasi;

import java.util.Arrays;

public enum LunPh {
    NEW_MOON(0, "달이 태양과 같은 방향에 있어 보이지 않음", "🌑"),
    WAXING_CRESCENT(1, 6, "오른쪽에서부터 달이 보이기 시작함", "🌒"),
    FIRST_QUARTER(7,  "달의 오른쪽 절반이 밝음", "🌓"),
    WAXING_GIBBOUS(8, 13, "오른쪽 절반 이상이 밝음", "🌔"),
    FULL_MOON(14,  "달이 완전히 밝음", "🌕"),
    WANING_GIBBOUS(15, 20,  "왼쪽에서부터 달이 점점 어두워짐", "🌖"),
    LAST_QUARTER(21,  "달의 왼쪽 절반이 밝음", "🌗"),
    WANING_CRESCENT(22, 27,  "왼쪽 절반 이하가 어두워짐", "🌘"),
    DARK_MOON(28, 30,  "달이 거의 보이지 않는 시기", "🌑");

    private final int startDay;
    private final int endDay;
    private final String description;
    private final String emoji;

    LunPh(int startDay,  String description, String emoji) {
        this(startDay, startDay,  description, emoji);
    }

    LunPh(int startDay, int endDay,String description, String emoji) {
        this.startDay = startDay;
        this.endDay = endDay;
        this.description = description;
        this.emoji = emoji;
    }

    public int getStartDay() {
        return startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    public String getDescription() {
        return description;
    }

    public String getEmoji() {
        return emoji;
    }

    public static LunPh fromDayToValue(int lunAge) {

        return Arrays.stream(values())
                .filter(value -> lunAge >= value.startDay && lunAge <= value.endDay)
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("Invalid lunAge: " + lunAge));

    }

    public static String fromDayToDescription(int lunAge) {
        return Arrays.stream(values())
                .filter(value -> lunAge >= value.startDay && lunAge <= value.endDay)
                .map(LunPh::getDescription)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid lunAge: " + lunAge));
    }


    public static String fromDayToEmoji(int lunAge){
        return Arrays.stream(values())
                .filter(value -> lunAge >= value.startDay && lunAge <= value.endDay)
                .map(LunPh::getEmoji)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid lunAge: " + lunAge));

    }



}

