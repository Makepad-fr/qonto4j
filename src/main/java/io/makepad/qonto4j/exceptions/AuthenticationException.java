package io.makepad.qonto4j.exceptions;

public class AuthenticationException extends Exception {

  public AuthenticationException(String message) {
    super(String.format("Authentication failed to the Qonto API %s", message));
  }
}
