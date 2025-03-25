package com.pack.inventory.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
  @NotEmpty(message = "The name of a product can not be empty")
  @JsonProperty("name")
  private String name;
  @NotNull(message = "The price can not be null")
  @Min(value = 0, message = "The price can not be negative")
  @JsonProperty("price")
  private Float price;
  @Min(value = 0, message = "The stock can not be negative")
  private int stock;
  @Min(value = 0, message = "The limit can not be negative")
  private int productLimit;
}
