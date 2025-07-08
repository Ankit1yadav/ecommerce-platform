package com.ecom.productservice.repository;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepository {
    @Autowired
    private DynamoDbClient dynamoDbClient;

    private static final String TABLE_NAME = "ecommerce-products";

    public void saveProduct(Map<String, AttributeValue> item) {
        Map<String, AttributeValue> request = new HashMap<>(item);
        PutItemRequest putItemRequest = PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(request)
                .build();
        dynamoDbClient.putItem(putItemRequest);
    }
}