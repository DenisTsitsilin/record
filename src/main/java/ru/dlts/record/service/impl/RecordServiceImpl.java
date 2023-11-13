package ru.dlts.record.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dlts.record.dto.RecordDto;
import ru.dlts.record.entity.Record;
import ru.dlts.record.enums.Reference;
import ru.dlts.record.repository.RecordRepository;
import ru.dlts.record.service.MessageService;
import ru.dlts.record.service.PublishService;
import ru.dlts.record.service.RecordService;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

  private final RecordRepository recordRepository;
  private final PublishService publishService;
  private final MessageService messageService;
  private final ModelMapper mapper;

  @Override
  @Transactional
  public void publish(Integer recordId) {
    System.out.println("publish call");
    Record record = recordRepository.findByRecordId(recordId).orElseThrow();
    if (record.getStatus() != Reference.FORMED) {
      return;
    }
    publishService.publish(record);
    record.setStatus(Reference.PUBLISHED);
    System.out.println(record.getStatus());
  }

  @Override
  @Transactional
  public void send(Integer recordId) {
    System.out.println("send call");
    Record record = recordRepository.findByRecordId(recordId).orElseThrow();
    if (record.getStatus() == Reference.SENT ||
        record.getStatus() == Reference.PUBLISHED_AND_SENT) {
      return;
    }
    messageService.send(record);
    record.setStatus(Reference.conversely(record.getStatus()));
    System.out.println(record.getStatus());
  }

  @Override
  @Transactional
  public RecordDto create(RecordDto record) {
    Record savedRecord = recordRepository.save(mapper.map(record, Record.class));
    return mapper.map(savedRecord, RecordDto.class);
  }
}
