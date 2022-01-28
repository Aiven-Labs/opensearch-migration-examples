# OpenSearch Migration Examples

This repository will provide examples for migrating native Elasticsearch clients to
the OpenSearch® equivalent.

## Java / SpringBoot

Testing shows that this is a minimal change. Install the new dependency, and change the `import` statements.

See this commit diff as an example change: [java-client-migration/src/main/java/.../Application.java](https://github.com/aiven/opensearch-migration-examples/commit/7453d659c06b234ae7f28f801a074e459c2f31c8)

```diff
- implementation 'org.elasticsearch.client:elasticsearch-rest-client:7.10.2'
- implementation 'org.elasticsearch.client:elasticsearch-rest-high-level-client:7.10.2'
+ implementation 'org.opensearch.client:opensearch-rest-client:1.1.0'
+ implementation 'org.opensearch.client:opensearch-rest-high-level-client:1.1.0'
```

### Resources

- [Compatibility](https://opensearch.org/docs/latest/clients/index/)
- [Java High Level Client](https://opensearch.org/docs/latest/clients/java-rest-high-level/)
- [Mvn Repository](https://mvnrepository.com/artifact/org.opensearch.client)

## Python

Testing shows that this is a four (4) line change. Change the `import` statement, and the Python client for ES (Elasticsearch) and OS (OpenSearch).

Changes related to `import`:

```diff
- from elasticsearch import Elasticsearch
+ from opensearchpy import OpenSearch
```

Changes related to clients and arguments:

```diff
- client_against_opensearch = Elasticsearch(OS_SERVICE_URI, use_ssl=True)
+ client_against_opensearch = OpenSearch(OS_SERVICE_URI, use_ssl=True)
```

See this commit diff as an example change: [python-client-migration/python_client_migration/main.py](https://github.com/aiven/opensearch-migration-examples/commit/f11b0e379dc63d9c023b62a032a72d9f1d4b9fc5)

### Resources

- [Compatibility](https://opensearch.org/docs/latest/clients/index/)
- [Python Client](https://opensearch.org/docs/latest/clients/python)

## Go

// Coming Soon...

## NodeJS

Testing shows that this is a two (2) line change. Install the new dependency, and change the `require`/`import` statement.

See this commit diff as an example change: [node-client-migration/src/main.js](https://github.com/aiven/opensearch-migration-examples/commit/deebaff2833bd8e851aa00001ac37ebf69cca9a3)

```shell
$ npm install --save @opensearch-project/opensearch
```

```diff
- const { Client } = require('@elastic/elasticsearch');
+ const { Client } = require('@opensearch-project/opensearch');
```

### Resources

- [Compatibility](https://opensearch.org/docs/latest/clients/index/)
- [NodeJS Client](https://opensearch.org/docs/latest/clients/javascript/)

## License
This work is licensed under a [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt).

## Trademarks

Elasticsearch is a trademark of Elasticsearch BV, registered in the U.S. and in other countries.

Java is a registered trademark of Oracle and/or its affiliates. Other names may be trademarks of their respective owners.

OpenSearch, NodeJS, Go, Python and SpringBoot are trademarks and property of their respective owners. All product and service names used in this website are for identification purposes only and do not imply endorsement.
