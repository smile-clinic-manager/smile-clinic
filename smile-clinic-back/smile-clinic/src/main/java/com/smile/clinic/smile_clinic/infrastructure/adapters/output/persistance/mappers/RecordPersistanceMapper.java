package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers;

import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.RecordEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecordPersistanceMapper {

    RecordEntity toRecordEntity(Record record);
    Record toRecord(RecordEntity entity);

    List<RecordEntity> toRecordEntityList(List<Record> records);
    List<Record> toRecordList(List<RecordEntity> entities);
}
