/*
	(c) Makepad Developers <dev@makepad.fr>
*/
package io.makepad.qonto4j;

import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodySubscriber;
import java.net.http.HttpResponse.ResponseInfo;
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
        String transactionId) {

  /**
   * Get the body handler for external transfers
   * @return The HttpResponse.BodyHandler for ExternalTransfer
   */
  static HttpResponse.BodyHandler<ExternalTransfer> bodyHandler() {
    return new BodyHandler<ExternalTransfer>() {
      @Override
      public BodySubscriber<ExternalTransfer> apply(ResponseInfo responseInfo) {
        return null;
      }
    };
  }
}
