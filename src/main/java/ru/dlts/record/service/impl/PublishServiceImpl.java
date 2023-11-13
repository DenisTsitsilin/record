package ru.dlts.record.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dlts.record.entity.Record;
import ru.dlts.record.service.PublishService;

@Service
@RequiredArgsConstructor
public class PublishServiceImpl implements PublishService {

  @Override
  public void publish(Record record) {
    //TODO сделано для проверки
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Message published");
  }
}
