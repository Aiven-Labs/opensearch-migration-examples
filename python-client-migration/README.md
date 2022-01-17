# Python OpenSearch Migration Examples
In order to run those examples locally, install the dependencies:

```shell
pip install -r requirements.txt
```

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



## Resources

- Compatibility https://opensearch.org/docs/latest/clients/index/
- Python https://opensearch.org/docs/latest/clients/python/
