package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers;

import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.RecordDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecordRestMapper {

    Record toRecord(RecordDTO recordDTO);
    RecordDTO toRecordDTO(Record record);

    List<Record> toRecordList(List<RecordDTO> recordsDTO);
    List<RecordDTO> toRecordDTOList(List<Record> records);
}
