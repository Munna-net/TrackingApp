package com.TrackingNumber.Controller;


import com.TrackingNumber.Dto.TrackingResponse;
import com.TrackingNumber.Entity.TrackingRequest;
import com.TrackingNumber.Service.TrackingNumberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class TrackingNumberController {


    private final TrackingNumberService trackingNumberService;
    @PostMapping("/get-tracking-number")
    public ResponseEntity<TrackingResponse> getTrackingNumber(@Valid @RequestBody TrackingRequest request) {
        log.info("Generating tracking number for customer: {}", request.getCustomer_name());
        TrackingResponse trackingResponse = trackingNumberService.generateTrackingNumber(request);
        return  new ResponseEntity<>(trackingResponse,HttpStatus.OK);
    }
}


