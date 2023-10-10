package com.modsen.library.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "book", schema = "diary_service")
public class Book {
    @Id
    private Long id;
    @Column(name = "taken")
    private LocalDateTime bookWasTaken;
    @Column(name = "returned")
    private LocalDateTime returnTheBookDate;

}
