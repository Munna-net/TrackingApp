package com.TrackingNumber.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class TrackingResponse {
    private String tracking_number;
    private LocalDateTime created_at;
}