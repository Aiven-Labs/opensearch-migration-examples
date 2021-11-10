package io.aiven.sa.os;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.*;
import org.elasticsearch.action.get.*;
import org.elasticsearch.action.index.*;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.client.indices.*;
import org.elasticsearch.common.settings.Settings;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@SpringBootApplication
public class Application {

    private static final String CLIENT_LIBRARY = "org.elasticsearch.client:elasticsearch-rest-client:7.10.2";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private class ConnectionProperties {
        private String hostname;
        private int port;
        private String username;
        private String password;

        public ConnectionProperties(String hostname, int port, String username, String password) {
            this.hostname = hostname;
            this.port = port;
            this.username = username;
            this.password = password;
        }
    }

    private ConnectionProperties getEsConnectionProperties() {
        return new ConnectionProperties(
                "es-migration-test-davide-demo.aivencloud.com",
                27011,
                "migration-test-user",
                "XoRgwfVdVOwzN5Ur"
        );
    }

    private ConnectionProperties getOsConnectionProperties() {
        return new ConnectionProperties(
                "os-migration-test-davide-demo.aivencloud.com",
                27011,
                "migration-test-user",
                "WsjWgcCk9Xiw37Gu"
        );
    }

    private RestHighLevelClient getHighLevelClient(ConnectionProperties conn) {
        final CredentialsProvider credentialsProvider =
                new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(conn.username, conn.password));
        HttpHost host = new HttpHost(conn.hostname, conn.port, "https");
        RestClientBuilder builder = RestClient.builder(host).setHttpClientConfigCallback(
                httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)
        );
        return new RestHighLevelClient(builder);
    }

    private void testHighLevelClient(ConnectionProperties conn) throws IOException {
        RestHighLevelClient client = getHighLevelClient(conn);

        //Create a non-default index with custom settings and mappings.
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("custom-index-" + UUID.randomUUID());

        createIndexRequest.settings(Settings.builder() //Specify in the settings how many shards you want in the index.
                .put("index.number_of_shards", 4)
                .put("index.number_of_replicas", 3)
        );
        //Create a set of maps for the index's mappings.
        HashMap<String, String> typeMapping = new HashMap<String, String>();
        typeMapping.put("type", "integer");
        HashMap<String, Object> ageMapping = new HashMap<String, Object>();
        ageMapping.put("age", typeMapping);
        HashMap<String, Object> mapping = new HashMap<String, Object>();
        mapping.put("properties", ageMapping);
        createIndexRequest.mapping(mapping);
        CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);

        //Adding data to the index.
        IndexRequest request = new IndexRequest("custom-index"); //Add a document to the custom-index we created.
        request.id("1"); //Assign an ID to the document.

        HashMap<String, String> stringMapping = new HashMap<String, String>();
        stringMapping.put("message:", "Testing Java REST client");
        request.source(stringMapping); //Place your content into the index's source.
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);

        //Getting back the document
        GetRequest getRequest = new GetRequest("custom-index", "1");
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);

        System.out.println(response.getSourceAsString());

        //Delete the document
        DeleteRequest deleteDocumentRequest = new DeleteRequest("custom-index", "1"); //Index name followed by the ID.
        DeleteResponse deleteResponse = client.delete(deleteDocumentRequest, RequestOptions.DEFAULT);

        //Delete the index
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("custom-index"); //Index name.
        AcknowledgedResponse deleteIndexResponse = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);

        client.close();
    }

    @Bean
    public CommandLineRunner testHighLevelClient() {
        return (args) -> {
            // TEST CLIENT AGAINST Elasticsearch CLUSTER
            try {
                testHighLevelClient(getEsConnectionProperties());
                System.out.println(String.format("\"%s\" successfully connected to an Elasticsearch Cluster", CLIENT_LIBRARY));
            } catch (Exception e) {
                System.err.println(String.format("\"%s\" failed to connected to an Elasticsearch Cluster", CLIENT_LIBRARY));
                System.err.println(e);
            }

            // TEST CLIENT AGAINST OpenSearch CLUSTER
            try {
                testHighLevelClient(getOsConnectionProperties());
                System.out.println(String.format("\"%s\" successfully connected to an OpenSearch Cluster", CLIENT_LIBRARY));
            } catch (Exception e) {
                System.err.println(String.format("\"%s\" failed connected to an OpenSearch Cluster", CLIENT_LIBRARY));
                System.err.println(e);
            }

            System.exit(0);
        };
    }
}