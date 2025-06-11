package com.TrackingNumber;

import com.TrackingNumber.Controller.TrackingNumberController;
import com.TrackingNumber.Dto.TrackingResponse;
import com.TrackingNumber.Entity.TrackingRequest;
import com.TrackingNumber.Service.TrackingNumberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TrackingNumberControllerTest {

    @Mock
    private TrackingNumberService service;

    @InjectMocks
    private TrackingNumberController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testValidTrackingNumberRequest() {
        UUID customerId = UUID.randomUUID();

        TrackingRequest request = new TrackingRequest();
        request.setOrigin_country_id("US");
        request.setDestination_country_id("IN");
        request.setWeight(1.23);
        request.setCustomer_id(customerId.toString());
        request.setCustomer_name("Test Customer");
        request.setCustomer_slug("test-customer");

        TrackingResponse mockResponse = new TrackingResponse("TRACK123456", LocalDateTime.now());

        when(service.generateTrackingNumber(eq(request))).thenReturn(mockResponse);

        ResponseEntity<TrackingResponse> responseEntity = controller.getTrackingNumber(request);

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getTracking_number()).isEqualTo("TRACK123456");

        verify(service, times(1)).generateTrackingNumber(eq(request));
    }
}