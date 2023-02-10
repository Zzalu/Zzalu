package com.samsamoo.zzalu.statistics.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberStatistics {
    @Id
    @Column(name = "GIFS_STATISTICS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(name = "USE_COUNT")
    private Long useCount;

    @Column(name = "DOWNLOAD_COUNT")
    private Long downloadCount;
}