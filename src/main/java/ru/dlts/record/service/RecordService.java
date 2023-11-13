package ru.dlts.record.service;

import ru.dlts.record.dto.RecordDto;

public interface RecordService {
  void publish(Integer recordId);
  void send(Integer recordId);

  RecordDto create(RecordDto record);
}
