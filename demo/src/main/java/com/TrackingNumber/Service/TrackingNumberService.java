package com.TrackingNumber.Service;

import com.TrackingNumber.Dto.TrackingResponse;
import com.TrackingNumber.Entity.TrackingRequest;
import com.TrackingNumber.Exception.InvalidRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class TrackingNumberService {

    private static final SecureRandom random = new SecureRandom();
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public TrackingResponse generateTrackingNumber(TrackingRequest request) {
        validateRequest(request);

        String customerSlugPart = request.getCustomer_slug()
                .replaceAll("-", "")
                .toUpperCase();

        if (customerSlugPart.length() > 4) {
            customerSlugPart = customerSlugPart.substring(0, 4);
        }

        String base = request.getOrigin_country_id().toUpperCase()
                + request.getDestination_country_id().toUpperCase()
                + customerSlugPart;

        String timeFragment = Long.toString(System.nanoTime(), 36).toUpperCase(); // More granular & shorter
        String randomPart = generateRandomAlphanumeric(4);

        String trackingNumber = (base + timeFragment + randomPart)
                .replaceAll("[^A-Z0-9]", "")
                .substring(0, Math.min(16, base.length() + timeFragment.length() + 4));

        log.info("Generated tracking number: {}", trackingNumber);
        return new TrackingResponse(trackingNumber, LocalDateTime.now());
    }

    private void validateRequest(TrackingRequest request) {
        if (isNullOrEmpty(request.getCustomer_id())) {
            throw new InvalidRequestException("Customer ID must not be null or empty");
        }

        try {
            UUID.fromString(request.getCustomer_id());
        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("Customer ID must be a valid UUID");
        }

        if (isNullOrEmpty(request.getCustomer_name())) {
            throw new InvalidRequestException("Customer name must not be null or empty");
        }

        if (isNullOrEmpty(request.getOrigin_country_id())) {
            throw new InvalidRequestException("Origin country ID must not be null or empty");
        }

        if (isNullOrEmpty(request.getDestination_country_id())) {
            throw new InvalidRequestException("Destination country ID must not be null or empty");
        }

        if (request.getWeight() <= 0) {
            throw new InvalidRequestException("Weight must be a positive number");
        }
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    private String generateRandomAlphanumeric(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}