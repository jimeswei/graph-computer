# HugeGraph Computer 算法使用示例

这个文档提供最简单的测试用例，帮助你快速理解如何使用各种图算法。

## 项目包含的算法

### 1. 中心性算法 (Centrality)
- **PageRank** - 网页排名算法
- **DegreeCentrality** - 度中心性
- **BetweennessCentrality** - 介数中心性
- **ClosenessCentrality** - 紧密中心性

### 2. 社区发现算法 (Community)
- **WCC** - 弱连通分量
- **LPA** - 标签传播算法
- **Kcore** - K核算法
- **TriangleCount** - 三角形计数
- **ClusteringCoefficient** - 聚类系数

### 3. 路径算法 (Path)
- **SingleSourceShortestPath** - 单源最短路径
- **RingsDetection** - 环检测

### 4. 采样算法 (Sampling)
- **RandomWalk** - 随机游走

## 基础测试用例模板

所有算法测试都继承自 `AlgorithmTestBase`，基本结构如下：

```java
package org.apache.hugegraph.computer.algorithm.example;

import org.apache.hugegraph.computer.algorithm.AlgorithmTestBase;
import org.junit.Test;

public class SimpleAlgorithmTest extends AlgorithmTestBase {

    @Test
    public void testPageRank() throws InterruptedException {
        // 最简单的方式：只指定算法参数类
        runAlgorithm("org.apache.hugegraph.computer.algorithm.centrality.pagerank.PageRankParams");
    }
}
```

## 详细示例

### 示例 1: PageRank（最简单）

**用途**: 计算图中每个节点的重要性得分

```java
import org.apache.hugegraph.computer.algorithm.AlgorithmTestBase;
import org.junit.Test;

public class PageRankSimpleTest extends AlgorithmTestBase {

    @Test
    public void testPageRank() throws InterruptedException {
        // 运行 PageRank 算法
        runAlgorithm("org.apache.hugegraph.computer.algorithm.centrality.pagerank.PageRankParams");
    }
}
```

### 示例 2: 弱连通分量 WCC

**用途**: 找出图中所有相互连通的子图

```java
import org.apache.hugegraph.computer.algorithm.AlgorithmTestBase;
import org.apache.hugegraph.computer.core.config.ComputerOptions;
import org.junit.Test;

public class WccSimpleTest extends AlgorithmTestBase {

    @Test
    public void testWcc() throws InterruptedException {
        // 运行 WCC 算法，可以自定义一些参数
        runAlgorithm("org.apache.hugegraph.computer.algorithm.community.wcc.WccParams",
                     ComputerOptions.BSP_MAX_SUPER_STEP.name(), "10");  // 最大迭代次数
    }
}
```

### 示例 3: 标签传播算法 LPA

**用途**: 社区发现，将相似的节点分到同一个社区

```java
import org.apache.hugegraph.computer.algorithm.AlgorithmTestBase;
import org.junit.Test;

public class LpaSimpleTest extends AlgorithmTestBase {

    @Test
    public void testLpa() throws InterruptedException {
        runAlgorithm("org.apache.hugegraph.computer.algorithm.community.lpa.LpaParams");
    }
}
```

### 示例 4: 度中心性

**用途**: 计算每个节点的连接数量

```java
import org.apache.hugegraph.computer.algorithm.AlgorithmTestBase;
import org.junit.Test;

public class DegreeCentralitySimpleTest extends AlgorithmTestBase {

    @Test
    public void testDegreeCentrality() throws InterruptedException {
        runAlgorithm("org.apache.hugegraph.computer.algorithm.centrality.degree.DegreeCentralityParams");
    }
}
```

### 示例 5: 三角形计数

**用途**: 统计图中三角形的数量（用于社交网络分析）

```java
import org.apache.hugegraph.computer.algorithm.AlgorithmTestBase;
import org.junit.Test;

public class TriangleCountSimpleTest extends AlgorithmTestBase {

    @Test
    public void testTriangleCount() throws InterruptedException {
        runAlgorithm("org.apache.hugegraph.computer.algorithm.community.trianglecount.TriangleCountParams");
    }
}
```

### 示例 6: 单源最短路径

**用途**: 计算从指定源点到其他所有节点的最短路径

```java
import org.apache.hugegraph.computer.algorithm.AlgorithmTestBase;
import org.apache.hugegraph.computer.algorithm.path.shortest.SingleSourceShortestPathParams;
import org.apache.hugegraph.computer.core.config.ComputerOptions;
import org.junit.Test;

public class ShortestPathSimpleTest extends AlgorithmTestBase {

    @Test
    public void testShortestPath() throws InterruptedException {
        runAlgorithm("org.apache.hugegraph.computer.algorithm.path.shortest.SingleSourceShortestPathParams",
                     SingleSourceShortestPathParams.SOURCE_ID, "1");  // 指定源节点ID
    }
}
```

### 示例 7: 随机游走

**用途**: 从节点开始随机访问相邻节点（用于图嵌入、推荐系统等）

```java
import org.apache.hugegraph.computer.algorithm.AlgorithmTestBase;
import org.junit.Test;

public class RandomWalkSimpleTest extends AlgorithmTestBase {

    @Test
    public void testRandomWalk() throws InterruptedException {
        runAlgorithm("org.apache.hugegraph.computer.algorithm.sampling.RandomWalkParams");
    }
}
```

### 示例 8: K-Core

**用途**: 找出图中的紧密核心结构

```java
import org.apache.hugegraph.computer.algorithm.AlgorithmTestBase;
import org.junit.Test;

public class KcoreSimpleTest extends AlgorithmTestBase {

    @Test
    public void testKcore() throws InterruptedException {
        runAlgorithm("org.apache.hugegraph.computer.algorithm.community.kcore.KcoreParams");
    }
}
```

## 完整测试类示例

将所有算法放在一个测试类中：

```java
package org.apache.hugegraph.computer.algorithm;

import org.junit.Test;

/**
 * 所有算法的简单测试示例
 * 这个类展示了如何使用 HugeGraph Computer 的各种图算法
 */
public class AllAlgorithmsSimpleTest extends AlgorithmTestBase {

    // ========== 中心性算法 ==========

    @Test
    public void test01_PageRank() throws InterruptedException {
        System.out.println("运行 PageRank 算法 - 计算节点重要性");
        runAlgorithm("org.apache.hugegraph.computer.algorithm.centrality.pagerank.PageRankParams");
    }

    @Test
    public void test02_DegreeCentrality() throws InterruptedException {
        System.out.println("运行度中心性算法 - 统计节点连接数");
        runAlgorithm("org.apache.hugegraph.computer.algorithm.centrality.degree.DegreeCentralityParams");
    }

    // ========== 社区发现算法 ==========

    @Test
    public void test03_WeaklyConnectedComponents() throws InterruptedException {
        System.out.println("运行弱连通分量算法 - 找出连通子图");
        runAlgorithm("org.apache.hugegraph.computer.algorithm.community.wcc.WccParams");
    }

    @Test
    public void test04_LabelPropagation() throws InterruptedException {
        System.out.println("运行标签传播算法 - 社区发现");
        runAlgorithm("org.apache.hugegraph.computer.algorithm.community.lpa.LpaParams");
    }

    @Test
    public void test05_TriangleCount() throws InterruptedException {
        System.out.println("运行三角形计数算法 - 统计三角形结构");
        runAlgorithm("org.apache.hugegraph.computer.algorithm.community.trianglecount.TriangleCountParams");
    }

    @Test
    public void test06_Kcore() throws InterruptedException {
        System.out.println("运行K-Core算法 - 找出核心结构");
        runAlgorithm("org.apache.hugegraph.computer.algorithm.community.kcore.KcoreParams");
    }

    @Test
    public void test07_ClusteringCoefficient() throws InterruptedException {
        System.out.println("运行聚类系数算法 - 计算节点聚集程度");
        runAlgorithm("org.apache.hugegraph.computer.algorithm.community.cc.ClusteringCoefficientParams");
    }

    // ========== 采样算法 ==========

    @Test
    public void test08_RandomWalk() throws InterruptedException {
        System.out.println("运行随机游走算法 - 随机路径采样");
        runAlgorithm("org.apache.hugegraph.computer.algorithm.sampling.RandomWalkParams");
    }
}
```

## 如何运行测试

### 方法 1: 使用 Maven

```bash
# 运行所有算法测试
mvn test -Dtest=AllAlgorithmsSimpleTest

# 运行单个测试
mvn test -Dtest=AllAlgorithmsSimpleTest#test01_PageRank
```

### 方法 2: 使用 IDE

1. 在 IDEA/Eclipse 中打开测试类
2. 右键点击测试方法
3. 选择 "Run test..."

## 常用配置参数说明

```java
// 作业ID
ComputerOptions.JOB_ID.name(), "my_job"

// Worker数量
ComputerOptions.JOB_WORKERS_COUNT.name(), "1"

// 最大迭代次数
ComputerOptions.BSP_MAX_SUPER_STEP.name(), "10"

// 注册超时时间（毫秒）
ComputerOptions.BSP_REGISTER_TIMEOUT.name(), "100000"
```

## 测试数据

测试框架会使用 `MockWorkerService` 自动创建模拟数据，你不需要准备真实的图数据。

## 下一步

- 查看 `computer-test` 模块中的现有测试了解更多细节
- 阅读各个算法的参数类（如 `PageRankParams`）了解可配置选项
- 查看算法实现类了解算法的具体逻辑

## 总结

使用这个项目的算法非常简单：

1. 创建测试类继承 `AlgorithmTestBase`
2. 调用 `runAlgorithm()` 方法并传入算法参数类名
3. 可选：传入额外的配置参数
4. 运行测试即可

每个算法都有对应的 `XXXParams` 类，只需要将完整类名传给 `runAlgorithm()` 方法即可。
