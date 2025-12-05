# HugeGraph Computer 算法快速入门指南

## 📚 什么是 HugeGraph Computer？

HugeGraph Computer 是一个分布式图计算框架，提供了多种常用的图算法。你可以用它来分析大规模图数据，比如社交网络、知识图谱等。

## 🚀 5分钟快速上手

### 第1步：查看可用算法

项目包含以下算法类别：

| 类别 | 算法 | 用途 |
|------|------|------|
| **中心性分析** | PageRank | 找出最重要的节点 |
| | 度中心性 | 找出连接最多的节点 |
| **社区发现** | 弱连通分量(WCC) | 找出相互连通的群组 |
| | 标签传播(LPA) | 自动发现社区 |
| | 三角形计数 | 分析朋友圈关系 |
| **路径分析** | 最短路径 | 找出两点间最短路径 |
| | 环检测 | 检测循环依赖 |
| **采样** | 随机游走 | 图特征提取 |

### 第2步：运行你的第一个测试

打开文件: `computer-test/src/main/java/org/apache/hugegraph/computer/algorithm/SimpleAlgorithmExamples.java`

选择任意一个测试方法，例如：

```java
@Test
public void example01_PageRank() throws InterruptedException {
    LOG.info("示例1: PageRank - 计算节点重要性");
    runAlgorithm("org.apache.hugegraph.computer.algorithm.centrality.pagerank.PageRankParams");
}
```

在 IDE 中右键点击 → Run 运行测试

### 第3步：理解代码结构

所有算法测试都遵循相同的模式：

```java
public class YourTest extends AlgorithmTestBase {

    @Test
    public void testAlgorithm() throws InterruptedException {
        // 调用 runAlgorithm，传入算法参数类的完整类名
        runAlgorithm("算法参数类的完整路径");
    }
}
```

就这么简单！

## 📖 详细示例

### 示例1: PageRank（最简单）

**场景**: 你想找出社交网络中最有影响力的人

```java
@Test
public void testPageRank() throws InterruptedException {
    runAlgorithm("org.apache.hugegraph.computer.algorithm.centrality.pagerank.PageRankParams");
}
```

### 示例2: 弱连通分量（找群组）

**场景**: 你想把用户分成不同的社交圈

```java
@Test
public void testFindCommunities() throws InterruptedException {
    runAlgorithm("org.apache.hugegraph.computer.algorithm.community.wcc.WccParams");
}
```

### 示例3: 三角形计数（朋友圈分析）

**场景**: 分析"我的朋友们互相认识吗"

```java
@Test
public void testTriangles() throws InterruptedException {
    runAlgorithm("org.apache.hugegraph.computer.algorithm.community.trianglecount.TriangleCountParams");
}
```

## 🔧 常见用法

### 如何添加自定义参数？

```java
runAlgorithm("算法参数类",
            "参数名1", "参数值1",
            "参数名2", "参数值2");
```

例如，限制最大迭代次数：

```java
runAlgorithm("org.apache.hugegraph.computer.algorithm.centrality.pagerank.PageRankParams",
            ComputerOptions.BSP_MAX_SUPER_STEP.name(), "5");
```

### 如何运行测试？

**方法1: 使用 IDE**
- 右键点击测试方法 → Run

**方法2: 使用 Maven**
```bash
# 运行所有示例
mvn test -Dtest=SimpleAlgorithmExamples

# 运行单个示例
mvn test -Dtest=SimpleAlgorithmExamples#example01_PageRank
```

## 📁 项目结构

```
computer/
├── computer-algorithm/        # 算法实现
│   └── src/main/java/
│       └── org/apache/hugegraph/computer/algorithm/
│           ├── centrality/    # 中心性算法
│           ├── community/     # 社区发现算法
│           ├── path/          # 路径算法
│           └── sampling/      # 采样算法
│
└── computer-test/            # 测试代码
    └── src/main/java/
        └── org/apache/hugegraph/computer/algorithm/
            ├── AlgorithmTestBase.java         # 测试基类
            └── SimpleAlgorithmExamples.java   # 简单示例 ⭐
```

## 💡 算法选择指南

**我应该用哪个算法？**

| 你想做什么 | 用这个算法 |
|-----------|----------|
| 找出最重要/最有影响力的节点 | PageRank |
| 找出朋友最多的人 | 度中心性 |
| 把图分成多个群组 | 弱连通分量(WCC) |
| 自动发现社区 | 标签传播(LPA) |
| 分析朋友圈紧密度 | 三角形计数 |
| 找出核心圈子 | K-Core |
| 找两点间最短路径 | 单源最短路径 |
| 检测循环依赖 | 环检测 |
| 图嵌入/推荐系统 | 随机游走 |

## 🎯 下一步

1. ✅ **运行示例代码**: 打开 `SimpleAlgorithmExamples.java` 运行几个测试
2. 📖 **查看详细文档**: 阅读 `SIMPLE_ALGORITHM_EXAMPLES.md`
3. 🔍 **深入学习**: 查看 `computer-algorithm` 目录下的算法实现
4. 🛠️ **自定义测试**: 基于示例创建自己的测试用例

## ❓ 常见问题

**Q: 测试数据从哪里来？**
A: 测试框架会自动生成模拟数据，你不需要准备真实数据。

**Q: 如何查看算法结果？**
A: 查看测试运行日志，或者查看各个算法的输出类（如 `PageRankOutput`）。

**Q: 算法执行时间很长怎么办？**
A: 可以减少最大迭代次数：
```java
runAlgorithm("...", ComputerOptions.BSP_MAX_SUPER_STEP.name(), "3");
```

**Q: 如何自定义算法参数？**
A: 查看对应的 `XXXParams` 类，里面定义了所有可配置参数。

## 📚 推荐学习路径

1. **初学者**: 运行 `SimpleAlgorithmExamples.java` 中的所有测试
2. **进阶**: 查看现有测试 (`computer-test/src/main/java/.../algorithm/`)
3. **高级**: 阅读算法实现 (`computer-algorithm/src/main/java/`)
4. **实战**: 使用真实图数据运行算法

## 🌟 快速参考

### 所有算法参数类列表

```java
// 中心性
org.apache.hugegraph.computer.algorithm.centrality.pagerank.PageRankParams
org.apache.hugegraph.computer.algorithm.centrality.degree.DegreeCentralityParams
org.apache.hugegraph.computer.algorithm.centrality.betweenness.BetweennessCentralityParams
org.apache.hugegraph.computer.algorithm.centrality.closeness.ClosenessCentralityParams

// 社区
org.apache.hugegraph.computer.algorithm.community.wcc.WccParams
org.apache.hugegraph.computer.algorithm.community.lpa.LpaParams
org.apache.hugegraph.computer.algorithm.community.kcore.KcoreParams
org.apache.hugegraph.computer.algorithm.community.trianglecount.TriangleCountParams
org.apache.hugegraph.computer.algorithm.community.cc.ClusteringCoefficientParams

// 路径
org.apache.hugegraph.computer.algorithm.path.rings.RingsDetectionParams
org.apache.hugegraph.computer.algorithm.path.shortest.SingleSourceShortestPathParams

// 采样
org.apache.hugegraph.computer.algorithm.sampling.RandomWalkParams
```

---

**开始使用吧！**运行你的第一个测试，探索图算法的强大功能！ 🚀
