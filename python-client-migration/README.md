# Python OpenSearch Migration Examples
In order to run those examples locally, install the dependencies:

```shell
pip install -r requirements.txt
```

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



## Resources

- [Compatibility](https://opensearch.org/docs/latest/clients/index/)
- [Python Client](https://opensearch.org/docs/latest/clients/python)
