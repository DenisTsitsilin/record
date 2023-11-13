package ru.dlts.record.service;

import ru.dlts.record.entity.Record;

public interface MessageService {
  void send(Record record);
}
