# OpenSearch Migration Examples

This repository will provide examples for migrating native Elasticsearch clients to
the OpenSearch equivalent.

## Java / SpringBoot

Testing shows that this is a minimal change. Install the new dependency, and change the `import` statements.

See this commit diff as an example change: [java-client-migration/src/main/java/.../Application.java](https://github.com/aiven/opensearch-migration-examples/commit/7453d659c06b234ae7f28f801a074e459c2f31c8)

```groovy
implementation 'org.opensearch.client:opensearch-rest-client:1.1.0'
implementation 'org.opensearch.client:opensearch-rest-high-level-client:1.1.0'
```

## Resources

- [Compatibility](https://opensearch.org/docs/latest/clients/index/)
- [Java High Level Client](https://opensearch.org/docs/latest/clients/java-rest-high-level/)
- [Mvn Repository](https://mvnrepository.com/artifact/org.opensearch.client)

## Python

Testing shows that this is a four (4) line change. Change the `import` statement, and the Python client for ES (Elasticsearch) and OS (OpenSearch).

Changes related to `import`:

```python
- from elasticsearch import Elasticsearch
+ from opensearchpy import OpenSearch
```

Changes related to clients and arguments:

```python
- client_against_opensearch = Elasticsearch(OS_SERVICE_URI, use_ssl=True)
+ client_against_opensearch = OpenSearch(OS_SERVICE_URI, use_ssl=True)
```

See this commit diff as an example change: [python-client-migration/python_client_migration/main.py](https://github.com/aiven/opensearch-migration-examples/commit/46c5148d21f5a4814b4c798263c873ff7d98dc00)


## Go

// Coming Soon...

## NodeJS

Testing shows that this is a two (2) line change. Install the new dependency, and change the `require`/`import` statement.

See this commit diff as an example change: [node-client-migration/src/main.js](https://github.com/aiven/opensearch-migration-examples/commit/deebaff2833bd8e851aa00001ac37ebf69cca9a3)

```shell
$ npm install --save @opensearch-project/opensearch
```

```javascript
- const { Client } = require('@elastic/elasticsearch');
+ const { Client } = require('@opensearch-project/opensearch');
```

## Resources

- Compatibility https://opensearch.org/docs/latest/clients/index/
- NodeJS https://opensearch.org/docs/latest/clients/javascript/
