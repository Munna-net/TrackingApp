# TrackingNumberApp

curl -X POST https://trackingapp1.onrender.com/get-tracking-number \
  -H "Content-Type: application/json" \
  -d '{
        "origin_country_id": "US",
        "destination_country_id": "IN",
        "weight": 2.5,
        "customer_id": "123e4567-e89b-12d3-a456-426614174001",
        "customer_name": "RedBox Logistics",
        "customer_slug": "redbox-logistics"
      }'
