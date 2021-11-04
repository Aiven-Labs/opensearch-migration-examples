# NodeJS - OpenSearch Migration Examples

Testing show that this is a two (2) line change. Install the new dependency, and change the `require`/`import` statement.

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
