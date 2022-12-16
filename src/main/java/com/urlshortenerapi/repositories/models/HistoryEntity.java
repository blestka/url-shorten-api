package com.urlshortenerapi.repositories.models;

import com.urlshortenerapi.enums.UrlOperations;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "HISTORY")
@Table(name = "history")
@EntityListeners(AuditingEntityListener.class)
public class HistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private UrlEntity url;
    @Enumerated(STRING)
    @Column(name = "operation", length = 10)
    private UrlOperations operation;
    @CreatedDate
    @Column(name = "created_date", columnDefinition = "datetime", updatable = false)
    private LocalDateTime createdDate;
}
