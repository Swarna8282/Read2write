package com.swan.read2write;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ID_TEXT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IdText {
    @Id
    @Column(nullable = false)
    int id;
    @Column(nullable = false)
    String text;
}
