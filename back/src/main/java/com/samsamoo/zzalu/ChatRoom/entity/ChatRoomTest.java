package com.samsamoo.zzalu.ChatRoom.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="UID", nullable = false)
    private Long uid;

    @Column(name="ROOM_NUMBER", nullable = false, unique = true)
    private String roomNumber;

    @Column(name="ROOM_NAME", nullable = false)
    private String roomName;

    @Column(name="ROOM_IMAGE_PATH")
    private String roomImagePath;

    @Column(name="ENROLL_DATE")
    @CreationTimestamp
    private Timestamp enrollDate;

    @Column(name="LIKE_COUNT", nullable = false)
    @Builder.Default
    private Long likeCount = 0L;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="TAGS")
    private String tags;

    @Column(name="LAST_ACTIVATION")
    @CreationTimestamp
    private Timestamp lastActivation;
}
