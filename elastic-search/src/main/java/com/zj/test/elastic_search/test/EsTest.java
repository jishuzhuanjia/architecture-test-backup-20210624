package com.zj.test.elastic_search.test;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/1 21:39
 * @description: es单元测试，暂时中止测试
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Slf4j
public class EsTest {

    private static TransportClient client;

    static {
        log.info("elasticsearch 初始化...");

        Map<String,String> map = new HashMap<>();

        map.put("cluster.name","my_cluster");
        map.put("client.transport.ping_timeout","60s");

        Settings.Builder settings = Settings.builder().put(map);

        try {
            client= TransportClient.builder().settings(settings).build()
            .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), Integer.parseInt("9300")));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        List<DiscoveryNode> nodes = client.connectedNodes();

        log.info("DiscoveryNode:");
        for (DiscoveryNode node : nodes) {
            System.out.println(node.getHostAddress());
        }
    }

    public static void main(String[] args) {

    }
}
