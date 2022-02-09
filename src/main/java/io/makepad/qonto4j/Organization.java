package io.makepad.qonto4j;

import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodySubscriber;
import java.net.http.HttpResponse.ResponseInfo;

public record Organization() {

  /**
   * Creates body handler for the current Organization
   * @return The HttpResponse.BodyHandler instance for Organization
   */
  static HttpResponse.BodyHandler<Organization> bodyHandler() {
    return new BodyHandler<Organization>() {
      @Override
      public BodySubscriber<Organization> apply(ResponseInfo responseInfo) {
        return null;
      }
    };
  }
}
