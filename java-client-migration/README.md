# Java Spring Boot - OpenSearch Migration Examples

Testing show that this is a minimal change. Install the new dependency, and change the `import` statements.

See this commit diff as an example change: [java-client-migration/src/main/java/.../Application.java](https://github.com/aiven/opensearch-migration-examples/commit/7453d659c06b234ae7f28f801a074e459c2f31c8)

```groovy
implementation 'org.opensearch.client:opensearch-rest-client:1.1.0'
implementation 'org.opensearch.client:opensearch-rest-high-level-client:1.1.0'
```

## Resources

- [Compatibility](https://opensearch.org/docs/latest/clients/index/)
- [Java High Level Client](https://opensearch.org/docs/latest/clients/java-rest-high-level/)
- [Mvn Repository](https://mvnrepository.com/artifact/org.opensearch.client)
