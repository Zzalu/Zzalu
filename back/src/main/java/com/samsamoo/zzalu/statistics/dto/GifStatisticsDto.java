package com.samsamoo.zzalu.statistics.dto;


import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class GifStatisticsDto {

    private Long id;

    private Long gifId;

    private Long useCount;

    private Long downloadCount;
}
