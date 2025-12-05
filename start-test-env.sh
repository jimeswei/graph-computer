#!/usr/bin/env bash
#
# Start test environment for hugegraph-computer integration tests
#

set -e

echo "Starting test environment for HugeGraph Computer..."

# 1. Start Etcd
echo "1. Starting Etcd on port 2379..."
docker run -d --name hugegraph-etcd \
  -p 2379:2379 \
  -p 2380:2380 \
  --rm \
  quay.io/coreos/etcd:v3.5.0 \
  etcd \
  --advertise-client-urls http://0.0.0.0:2379 \
  --listen-client-urls http://0.0.0.0:2379 \
  --listen-peer-urls http://0.0.0.0:2380

echo "Waiting for Etcd to start..."
sleep 3

# 2. Start HugeGraph Server (mapping port 8080 to container's 8080)
echo "2. Starting HugeGraph Server on port 8080..."
docker run -d --name hugegraph-server \
  -p 8080:8080 \
  --rm \
  hugegraph/hugegraph:latest

echo "Waiting for HugeGraph Server to start..."
sleep 15

# 3. Verify services are running
echo "3. Verifying services..."
if curl -s http://127.0.0.1:8080/versions > /dev/null; then
    echo "✓ HugeGraph Server is running"
else
    echo "✗ HugeGraph Server failed to start"
    exit 1
fi

if curl -s http://127.0.0.1:2379/version > /dev/null; then
    echo "✓ Etcd is running"
else
    echo "✗ Etcd failed to start"
    exit 1
fi

echo ""
echo "========================================="
echo "Test environment is ready!"
echo "========================================="
echo "HugeGraph: http://127.0.0.1:8080"
echo "Etcd:      http://127.0.0.1:2379"
echo ""
echo "To run tests, set the environment variable:"
echo "  export BSP_ETCD_URL=http://127.0.0.1:2379"
echo ""
echo "Then run your test:"
echo "  mvn test -Dtest=PageRankTest"
echo ""
echo "To stop the services:"
echo "  docker stop hugegraph-server hugegraph-etcd"
echo "========================================="
