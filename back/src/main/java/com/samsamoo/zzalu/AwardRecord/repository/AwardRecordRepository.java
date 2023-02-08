package com.samsamoo.zzalu.AwardRecord.repository;

import com.samsamoo.zzalu.AwardRecord.entity.AwardRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AwardRecordRepository  extends JpaRepository<AwardRecord , Long> {



}


