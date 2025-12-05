# 集成测试环境设置指南

## 问题说明

PageRankTest 和其他算法测试是**集成测试**，需要以下服务运行：

1. **HugeGraph Server** - 图数据库服务器 (http://127.0.0.1:8080)
2. **Etcd** - 用于 BSP 分布式协调 (http://127.0.0.1:2379)

## 快速开始

### 方法 1: 使用提供的脚本（推荐）

```bash
# 1. 启动测试环境
cd /Users/admin/workspace/hugegraph-computer/computer
./start-test-env.sh

# 2. 运行测试
mvn test -Dtest=PageRankTest

# 3. 停止测试环境
./stop-test-env.sh
```

### 方法 2: 手动使用 Docker

```bash
# 1. 启动 Etcd
docker run -d --name hugegraph-etcd \
  -p 2379:2379 \
  -p 2380:2380 \
  --rm \
  quay.io/coreos/etcd:v3.5.0 \
  etcd \
  --advertise-client-urls http://0.0.0.0:2379 \
  --listen-client-urls http://0.0.0.0:2379 \
  --listen-peer-urls http://0.0.0.0:2380

# 2. 启动 HugeGraph Server
docker run -d --name hugegraph-server \
  -p 8080:8080 \
  --rm \
  hugegraph/hugegraph:latest

# 3. 等待服务启动（约15秒）
sleep 15

# 4. 验证服务
curl http://127.0.0.1:8080/versions  # 应该返回 HugeGraph 版本信息
curl http://127.0.0.1:2379/version   # 应该返回 Etcd 版本信息

# 5. 运行测试
mvn test -Dtest=PageRankTest

# 6. 停止服务
docker stop hugegraph-server hugegraph-etcd
```

## 修复内容

已修复以下配置问题：

1. **AlgorithmTestBase.java:100** - RPC 服务器主机从 `192.168.3.78` 改为 `127.0.0.1`
2. **UnitTestBase.java:90-94** - Etcd 端点默认值从 `http://192.168.3.78:2379` 改为 `http://127.0.0.1:2379`

## 环境变量

可以通过环境变量覆盖默认配置：

```bash
# 自定义 Etcd 端点
export BSP_ETCD_URL=http://192.168.1.100:2379
mvn test -Dtest=PageRankTest
```

## 故障排除

### HugeGraph 启动失败
```bash
# 查看日志
docker logs hugegraph-server

# 确保端口未被占用
lsof -i :8080
```

### Etcd 启动失败
```bash
# 查看日志
docker logs hugegraph-etcd

# 确保端口未被占用
lsof -i :2379
```

### 测试连接问题
```bash
# 测试 HugeGraph 连接
curl -v http://127.0.0.1:8080/versions

# 测试 Etcd 连接
curl -v http://127.0.0.1:2379/version
```

## 注意事项

- 这些是**集成测试**，不是单元测试
- 需要 Docker 环境
- 首次运行会下载 Docker 镜像（约几百 MB）
- 测试会创建真实的图数据，完成后会自动清理
