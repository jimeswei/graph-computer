/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.apache.hugegraph.computer.algorithm;

import org.junit.Test;

/**
 * 简单的算法使用示例
 *
 * 这个测试类展示了如何使用 HugeGraph Computer 的各种图算法。
 * 每个测试方法都演示一个算法的基本用法。
 *
 * 使用方法：
 * 1. 直接在 IDE 中运行单个测试方法
 * 2. 或使用 Maven: mvn test -Dtest=SimpleAlgorithmExamples
 */
public class SimpleAlgorithmExamples extends AlgorithmTestBase {

    // ========================================
    // 中心性算法 - 用于分析节点的重要性
    // ========================================

    /**
     * PageRank 算法
     * 用途：计算图中每个节点的重要性得分（Google搜索引擎使用的算法）
     * 应用场景：网页排名、社交网络影响力分析
     */
    @Test
    public void example01_PageRank() throws InterruptedException {
        LOG.info("示例1: PageRank - 计算节点重要性");
        runAlgorithm("org.apache.hugegraph.computer.algorithm.centrality.pagerank.PageRankParams");
    }

    /**
     * 度中心性算法
     * 用途：统计每个节点的连接数量（出度+入度）
     * 应用场景：找出社交网络中朋友最多的人
     */
    @Test
    public void example02_DegreeCentrality() throws InterruptedException {
        LOG.info("示例2: 度中心性 - 统计节点连接数");
        runAlgorithm("org.apache.hugegraph.computer.algorithm.centrality.degree.DegreeCentralityParams");
    }

    /**
     * 介数中心性算法
     * 用途：计算经过某个节点的最短路径数量
     * 应用场景：找出网络中的关键节点（桥接作用）
     */
    @Test
    public void example03_BetweennessCentrality() throws InterruptedException {
        LOG.info("示例3: 介数中心性 - 找出关键桥接节点");
        runAlgorithm("org.apache.hugegraph.computer.algorithm.centrality.betweenness.BetweennessCentralityParams");
    }

    /**
     * 紧密中心性算法
     * 用途：计算节点到其他所有节点的平均距离
     * 应用场景：找出最容易到达其他节点的位置
     */
    @Test
    public void example04_ClosenessCentrality() throws InterruptedException {
        LOG.info("示例4: 紧密中心性 - 找出最中心的节点");
        runAlgorithm("org.apache.hugegraph.computer.algorithm.centrality.closeness.ClosenessCentralityParams");
    }

    // ========================================
    // 社区发现算法 - 用于发现图中的群组结构
    // ========================================

    /**
     * 弱连通分量算法 (WCC)
     * 用途：将图分解成多个互相连通的子图
     * 应用场景：社交网络分组、孤立群体检测
     */
    @Test
    public void example05_WeaklyConnectedComponents() throws InterruptedException {
        LOG.info("示例5: 弱连通分量 - 找出所有连通子图");
        runAlgorithm("org.apache.hugegraph.computer.algorithm.community.wcc.WccParams");
    }

    /**
     * 标签传播算法 (LPA)
     * 用途：基于标签传播的社区发现
     * 应用场景：社交网络社区划分、用户分组
     */
    @Test
    public void example06_LabelPropagation() throws InterruptedException {
        LOG.info("示例6: 标签传播 - 社区发现");
        runAlgorithm("org.apache.hugegraph.computer.algorithm.community.lpa.LpaParams");
    }

    /**
     * 三角形计数算法
     * 用途：统计图中三角形结构的数量
     * 应用场景：社交网络分析（朋友的朋友关系）
     */
    @Test
    public void example07_TriangleCount() throws InterruptedException {
        LOG.info("示例7: 三角形计数 - 统计三角形结构");
        runAlgorithm("org.apache.hugegraph.computer.algorithm.community.trianglecount.TriangleCountParams");
    }

    /**
     * K-Core 算法
     * 用途：找出图中的紧密核心结构
     * 应用场景：发现社交网络中的核心圈子
     */
    @Test
    public void example08_Kcore() throws InterruptedException {
        LOG.info("示例8: K-Core - 找出核心结构");
        runAlgorithm("org.apache.hugegraph.computer.algorithm.community.kcore.KcoreParams");
    }

    /**
     * 聚类系数算法
     * 用途：计算节点的邻居之间相互连接的紧密程度
     * 应用场景：评估社交网络的聚集程度
     */
    @Test
    public void example09_ClusteringCoefficient() throws InterruptedException {
        LOG.info("示例9: 聚类系数 - 计算节点聚集程度");
        runAlgorithm("org.apache.hugegraph.computer.algorithm.community.cc.ClusteringCoefficientParams");
    }

    // ========================================
    // 路径算法 - 用于路径分析
    // ========================================

    /**
     * 环检测算法
     * 用途：检测图中的环路结构
     * 应用场景：检测循环依赖、欺诈检测
     */
    @Test
    public void example10_RingsDetection() throws InterruptedException {
        LOG.info("示例10: 环检测 - 发现图中的环路");
        runAlgorithm("org.apache.hugegraph.computer.algorithm.path.rings.RingsDetectionParams");
    }

    // ========================================
    // 采样算法 - 用于图采样和特征提取
    // ========================================

    /**
     * 随机游走算法
     * 用途：从节点开始随机访问相邻节点生成路径
     * 应用场景：图嵌入（Node2Vec）、推荐系统
     */
    @Test
    public void example11_RandomWalk() throws InterruptedException {
        LOG.info("示例11: 随机游走 - 生成随机路径");
        runAlgorithm("org.apache.hugegraph.computer.algorithm.sampling.RandomWalkParams");
    }

    // ========================================
    // 带参数的算法示例
    // ========================================

    /**
     * 带自定义参数的 PageRank
     * 演示如何传递额外的配置参数
     */
    @Test
    public void example12_PageRankWithCustomParams() throws InterruptedException {
        LOG.info("示例12: 带参数的 PageRank");
        runAlgorithm("org.apache.hugegraph.computer.algorithm.centrality.pagerank.PageRankParams",
                     "worker.computation.class",
                     "org.apache.hugegraph.computer.algorithm.centrality.pagerank.PageRank");
    }
}
