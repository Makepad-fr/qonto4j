package io.makepad.qonto4j;

import java.util.Locale;

public enum TransferStatus {
  PENDING,
  PROCESSING,
  CANCELED,
  DECLINED,
  SETTLED;

  /**
   * Create a transfer status instance from a String
   * @param input The transfer status string
   * @return TransferStatus value related to the given string
   */
  static TransferStatus fromString(String input) {
    return switch (input.trim().toLowerCase(Locale.ROOT)) {
      case "pending" -> PENDING;
      case "processing" -> PROCESSING;
      case "canceled" -> CANCELED;
      case "declined" -> DECLINED;
      case "settled" -> SETTLED;
      default -> throw new IllegalArgumentException(String.format("%s is not a valid transfer status", input));
    };
  }

}
