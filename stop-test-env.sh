#!/usr/bin/env bash
#
# Stop test environment for hugegraph-computer integration tests
#

echo "Stopping test environment..."

docker stop hugegraph-server 2>/dev/null || echo "HugeGraph Server was not running"
docker stop hugegraph-etcd 2>/dev/null || echo "Etcd was not running"

echo "Test environment stopped."
