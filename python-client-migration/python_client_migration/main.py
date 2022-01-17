# https://opensearch.org/docs/latest/clients/python/
# Compatibility https://opensearch.org/docs/latest/clients/index/

import os
from random import randint
from dotenv import load_dotenv
from opensearchpy import OpenSearch

load_dotenv()


def run(client, index="game-of-thrones"):
    ## Let's start by indexing some data
    client.index(
        index=index,
        body={
            "character": "Ned Stark",
            "quote": "Winter is coming.",
        },
    )

    client.index(
        index=index,
        body={
            "character": "Daenerys Targaryen",
            "quote": "I am the blood of the dragon.",
        },
    )

    client.index(
        index=index,
        body={
            "character": "Tyrion Lannister",
            "quote": "A mind needs books like a sword needs a whetstone.",
        },
    )

    response = client.search(
        body={
            "query": {
                "match": {"quote": "winter"},
            },
        },
        index=index,
    )

    count = len(response["hits"]["hits"])
    return {"success": count > 0, "count": count, "message": f"Found {count} record(s)"}


if __name__ == "__main__":
    # Generate random index
    index = "game-of-thrones-" + str(randint(0, 500))

    # Test OS client against ES cluster
    ES_SERVICE_URI = os.getenv("ES_SERVICE_URI")
    client_against_elastisearch = OpenSearch(ES_SERVICE_URI, use_ssl=True)
    print(run(client_against_elastisearch))

    # Test OS client against OS cluster
    OS_SERVICE_URI = os.getenv("OS_SERVICE_URI")
    client_against_opensearch = OpenSearch(OS_SERVICE_URI, use_ssl=True)
    print(run(client_against_opensearch))
