package io.makepad.qonto4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Date;

public record ExternalTransfer(
    String id,
    String slug,
    String debitIban,
    double debitAmount,
    int debitAmountCents,
    Currency currency,
    String initiatorId,
    String beneficiaryId,
    double creditAmount,
    int creditAmountCents,
    Currency creditCurrency,
    Double rateApplied,
    String paymentPurpose,
    String reference,
    String note,
    String declinedReason,
    TransferStatus status,
    Date scheduledDate,
    LocalDateTime createdAt,
    LocalDateTime completedAt,
    LocalDateTime processedAt,
    String transactionId) {}
