package ru.dlts.record.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dlts.record.enums.Reference;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordDto {

  private Integer recordId;
  private Reference status;
}
