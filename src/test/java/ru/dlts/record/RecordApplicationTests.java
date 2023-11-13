package ru.dlts.record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dlts.record.entity.Record;
import ru.dlts.record.enums.Reference;
import ru.dlts.record.repository.RecordRepository;
import ru.dlts.record.service.impl.MessageServiceImpl;
import ru.dlts.record.service.impl.PublishServiceImpl;
import ru.dlts.record.service.impl.RecordServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class RecordApplicationTests {

  @InjectMocks
  private RecordServiceImpl recordService;
  @Mock
  private RecordRepository recordRepository;
  @InjectMocks
  private PublishServiceImpl publishService;
  @InjectMocks
  private MessageServiceImpl messageService;

  private Record record;

  @BeforeEach
  public void setup() {
    recordRepository = Mockito.mock(RecordRepository.class);
    recordService = new RecordServiceImpl(recordRepository,
        publishService,
        messageService,
        new ModelMapper());
    record = new Record(1, Reference.FORMED);
  }

  @Test
  void simultaneouslyRequestTest() throws InterruptedException {
    lenient().when(recordRepository.save(record)).thenReturn(record);

    given(recordRepository.findByRecordId(1)).willReturn(Optional.ofNullable(record));
    Thread t1 = new Thread(() -> {
      System.out.println("publish run");
      recordService.publish(1);
      System.out.println("publish end");
    });

    Thread t2 = new Thread(() -> {
      System.out.println("send run");
      recordService.send(1);
      System.out.println("send end");
    });

    t1.start();
    t2.start();

    t1.join();
    t2.join();

    Record savedRecord = recordRepository.findByRecordId(1).orElseThrow();
    //Приходит PUBLISHED, так как блокировки не отрабатывают
    assertEquals(Reference.PUBLISHED_AND_SENT, savedRecord.getStatus());
  }
}
