package com.samsamoo.zzalu.statistics.dto;


import lombok.Builder;

import javax.persistence.*;

public class MemberTagStatisticsDto {
    private Long id;

    private Long memberId;

    private String tag;

    private Long count = 0L;
}
