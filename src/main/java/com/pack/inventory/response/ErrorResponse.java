package com.pack.inventory.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
  private LocalDateTime timeStamp;
  private int status;
  private String error;
  private String message;
}
