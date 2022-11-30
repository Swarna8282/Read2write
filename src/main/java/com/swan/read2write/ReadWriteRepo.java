package com.swan.read2write;

import org.springframework.data.repository.CrudRepository;

public interface ReadWriteRepo extends CrudRepository<IdText, Integer> {
}
