package ru.dlts.record.repository;

import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import ru.dlts.record.entity.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Optional<Record> findByRecordId(Integer id);
}
