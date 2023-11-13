package ru.dlts.record.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dlts.record.entity.Record;
import ru.dlts.record.service.MessageService;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

  @Override
  public void send(Record record) {
    System.out.println("Message sent");
  }
}
