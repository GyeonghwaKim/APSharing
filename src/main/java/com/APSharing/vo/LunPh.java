package com.APSharing.vo;

public enum LunPh {
    NEW_MOON(0, "New Moon", "달이 태양과 같은 방향에 있어 보이지 않음", "🌑"),
    WAXING_CRESCENT(1, 6, "Waxing Crescent", "오른쪽에서부터 달이 보이기 시작함", "🌒"),
    FIRST_QUARTER(7, "First Quarter", "달의 오른쪽 절반이 밝음", "🌓"),
    WAXING_GIBBOUS(8, 13, "Waxing Gibbous", "오른쪽 절반 이상이 밝음", "🌔"),
    FULL_MOON(14, "Full Moon", "달이 완전히 밝음", "🌕"),
    WANING_GIBBOUS(15, 20, "Waning Gibbous", "왼쪽에서부터 달이 점점 어두워짐", "🌖"),
    LAST_QUARTER(21, "Last Quarter", "달의 왼쪽 절반이 밝음", "🌗"),
    WANING_CRESCENT(22, 27, "Waning Crescent", "왼쪽 절반 이하가 어두워짐", "🌘"),
    DARK_MOON(28, 30, "Dark Moon", "달이 거의 보이지 않는 시기", "🌑");

    private final int startDay;
    private final int endDay;
    private final String phaseName;
    private final String description;
    private final String emoji;

    LunPh(int startDay, String phaseName, String description, String emoji) {
        this(startDay, startDay, phaseName, description, emoji);
    }

    LunPh(int startDay, int endDay, String phaseName, String description, String emoji) {
        this.startDay = startDay;
        this.endDay = endDay;
        this.phaseName = phaseName;
        this.description = description;
        this.emoji = emoji;
    }

    public double getStartDay() {
        return startDay;
    }

    public double getEndDay() {
        return endDay;
    }

    public String getPhaseName() {
        return phaseName;
    }

    public String getDescription() {
        return description;
    }

    public String getEmoji() {
        return emoji;
    }

    public static String fromDayToName(int lunAge) {
        for (LunPh phase : values()) {
            if (lunAge >= phase.startDay && lunAge <= phase.endDay) {
                return phase.getPhaseName();
            }
        }
        throw new IllegalArgumentException("Invalid lunAge: " + lunAge);
    }

    public static String fromDayToDescription(int lunAge) {
        for (LunPh phase : values()) {
            if (lunAge >= phase.startDay && lunAge <= phase.endDay) {
                return phase.getDescription();
            }
        }
        throw new IllegalArgumentException("Invalid lunAge: " + lunAge);
    }

    public static String fromDayToEmoji(int lunAge){
        for (LunPh phase : values()) {
            if (lunAge >= phase.startDay && lunAge <= phase.endDay) {
                return phase.getEmoji();
            }
        }
        throw new IllegalArgumentException("Invalid lunAge: " + lunAge);
    }



}

