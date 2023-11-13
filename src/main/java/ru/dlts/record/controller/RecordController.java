package ru.dlts.record.controller;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dlts.record.dto.RecordDto;
import ru.dlts.record.service.RecordService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/record")
public class RecordController {

  private final RecordService recordService;

  @PutMapping("/publish/{id}")
  public ResponseEntity<HttpStatus> publish(@PathVariable Integer id) {
    recordService.publish(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/send/{id}")
  public ResponseEntity<HttpStatus> send(@PathVariable Integer id) {
    recordService.send(id);
    return ResponseEntity.ok().build();
  }

  @PostMapping
  public ResponseEntity<RecordDto> create(@RequestBody RecordDto record) {
    return ResponseEntity.ok().body(recordService.create(record));
  }

}
