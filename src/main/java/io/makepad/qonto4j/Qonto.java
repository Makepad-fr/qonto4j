/*
	(c) Makepad Developers <dev@makepad.fr>
*/
package io.makepad.qonto4j;

import io.makepad.qonto4j.exceptions.APIException;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class Qonto {

    private final String organizationSlug, secretKey;
    private final Transfers transfers;
    private final Attachments attachments;
    private final APIWrapper api;
    /**
     * Create a new instance of the Qonto object and initialise the configuration
     *
     * @param organizationSlug The id of the Qonto organisation
     * @param secretKey The secret key for the APIWrapper
     */
    public Qonto(String organizationSlug, String secretKey) {
        this.api = new APIWrapper();
        this.organizationSlug = organizationSlug;
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
        private static final String BASE_URL = System.getenv("QONTO_API_BASE_URL");

        private APIWrapper() {
            this.httpClient =
                    HttpClient.newBuilder()
                            .version(Version.HTTP_2)
                            .authenticator(new QontoAPIAuthenticator())
                            .build();
        }

        /**
         * Get organization details for current organization slug and secret key
         * @return Organization instance for the current Organization
         */
        private CompletableFuture<HttpResponse<Organization>> getOrganizationDetails() throws APIException {
            try {
                HttpRequest request = this.request("/v2/organization", Duration.ofMinutes(1),
                    Map.of("Content-Type", "application/json"))
                    .GET()
                    .build();
                 return this.httpClient.sendAsync(request, Organization.bodyHandler());
            } catch (URISyntaxException|MalformedURLException e) {
                throw new APIException();
            }
        }

        /**
         * Create the URI object for given path and base url
         * @param path The path to add for API base url
         * @return the URL object created from base url and path
         */
        private URI buildURI(String path) throws MalformedURLException, URISyntaxException {
            return (new URL(new URL(APIWrapper.BASE_URL), path)).toURI();
        }

        /**
         * Create a request with common parameters such as path and timeout duration
         * @param path The path of the API
         * @param timeoutDuration The duration for the timeout
         * @param headers A hashmap of headers
         * @return The builder object with common properties of HTTP Reuqest
         * @throws MalformedURLException If the URL has a formatting problem
         * @throws URISyntaxException If there's a syntax issue in URI
         */
        private HttpRequest.Builder request(String path, Duration timeoutDuration, Map<String, String> headers) throws MalformedURLException, URISyntaxException {
            HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(this.buildURI(path))
                .timeout(timeoutDuration);
            for (Map.Entry<String, String> header: headers.entrySet()) {
                builder.header(header.getKey(), header.getValue());
            }
            return builder;
        }
        private final class QontoAPIAuthenticator extends Authenticator {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        Qonto.this.organizationSlug, Qonto.this.secretKey.toCharArray());
            }
        }
    }
}
