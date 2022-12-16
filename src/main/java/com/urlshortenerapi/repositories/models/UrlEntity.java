package com.urlshortenerapi.repositories.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "URLS")
@Table(name = "urls")
@EntityListeners(AuditingEntityListener.class)
public class UrlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "orig_url", length = 256)
    private String origUrl;
    @Column(name = "shortened_path", length = 10)
    private String shortenedPath;
    @CreatedDate
    @Column(name = "created_date", columnDefinition = "datetime", updatable = false)
    private LocalDateTime createdDate;
}
