package com.samsamoo.zzalu.tempGif.entity;

import com.samsamoo.zzalu.TitleHakwon.enums.TitleHakwonState;
import com.samsamoo.zzalu.TitleHakwon.enums.converter.TitleHakwonConverter;
import com.samsamoo.zzalu.tempGif.enums.RequestType;
import com.samsamoo.zzalu.tempGif.enums.RequestTypeConverter;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TempGif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TEMP_GIF_ID")
    private Long id;
    @Column(unique = true)
    private String gifPath;
    @Column(nullable = false)
    private String tags;
    private String relationsVideo;
    private String description;
    private String writerUsername;
    @Builder.Default
    private int permittedCount = 0;
    @Convert(converter =  RequestTypeConverter.class)
    private RequestType requestType;
    @Builder.Default
    private Long originGifsId = null;

    public void increseCount(int permittedCount) {
        this.permittedCount = permittedCount;
    }
}
