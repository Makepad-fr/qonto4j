/*
	(c) Makepad Developers <dev@makepad.fr>
*/
package io.makepad.qonto4j;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;

public class Qonto {

    private final String organisationSlug, secretKey;
    private final Transfers transfers;
    private final Attachments attachments;
    private final APIWrapper api;
    /**
     * Create a new instance of the Qonto object and initialise the configuration
     *
     * @param organisationSlug The id of the Qonto organisation
     * @param secretKey The secret key for the APIWrapper
     */
    public Qonto(String organisationSlug, String secretKey) {
        this.api = new APIWrapper();
        this.organisationSlug = organisationSlug;
        this.secretKey = secretKey;
        // TODO: Verify organization slug and secret key if not valid, throw an exception
        this.transfers = new Transfers();
        this.attachments = new Attachments();
    }

    private class Transfers {

        private final ExternalTransfers external;
        private final InternalTransfers internal;

        private Transfers() {
            this.external = new ExternalTransfers();
            this.internal = new InternalTransfers();
        }

        private class ExternalTransfers {}

        private class InternalTransfers {}
    }

    private class Attachments {

        private final Transactions transactions;

        private Attachments() {
            this.transactions = new Transactions();
        }

        private class Transactions {}
    }

    private class Beneficiaires {}

    private class Labels {}

    private class Memberships {}

    private class Organizations {}

    private class Requests {}

    private class Transactions {}

    private class APIWrapper {
        private final HttpClient httpClient;
        private static final String BASE_URL = "";

        private APIWrapper() {
            this.httpClient =
                    HttpClient.newBuilder()
                            .version(Version.HTTP_2)
                            .authenticator(new QontoAPIAuthenticator())
                            .build();
        }

        private final class QontoAPIAuthenticator extends Authenticator {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        Qonto.this.organisationSlug, Qonto.this.secretKey.toCharArray());
            }
        }
    }
}
