package com.TrackingNumber.Entity;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingRequest {

    private String origin_country_id;

    private String destination_country_id;

    private Double weight;

    private String customer_id;

    private String customer_name;

    private String customer_slug;
}