// https://www.npmjs.com/package/@elastic/elasticsearch/v/7.13.0
// Compatibility https://opensearch.org/docs/latest/clients/index/

require('dotenv').config();
const { Client } = require('@elastic/elasticsearch');

async function run(client, index = 'game-of-thrones') {
  // Let's start by indexing some data
  await client.index({
    index: index,
    body: {
      character: 'Ned Stark',
      quote: 'Winter is coming.',
    },
  });

  await client.index({
    index: index,
    body: {
      character: 'Daenerys Targaryen',
      quote: 'I am the blood of the dragon.',
    },
  });

  await client.index({
    index: index,
    body: {
      character: 'Tyrion Lannister',
      quote: 'A mind needs books like a sword needs a whetstone.',
    },
  });

  // here we are forcing an index refresh, otherwise we will not
  // get any result in the consequent search
  await client.indices.refresh({ index: index });

  // Let's search!
  const { body } = await client.search({
    index: index,
    body: {
      query: {
        match: { quote: 'winter' },
      },
    },
  });

  const count = body.hits.hits.length;
  return {
    success: count > 0,
    count: count,
    message: `Found ${count} record(s)`,
  };
}

const index = `game-of-thrones-${Math.trunc(Math.random() * 1000)}`;

// Test ES client against ES cluster
const ES_SERVICE_URI = process.env.ES_SERVICE_URI;
const esClient = new Client({ node: ES_SERVICE_URI });
run(esClient, index).then(console.log).catch(console.log);

// Test ES client against OS cluster
const OS_SERVICE_URI = process.env.OS_SERVICE_URI;
const osClient = new Client({ node: OS_SERVICE_URI });
run(osClient, index).then(console.log).catch(console.log);
