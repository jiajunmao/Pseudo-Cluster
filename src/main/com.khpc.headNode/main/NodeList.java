package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class NodeList extends ArrayList<Node> {

	public boolean deleteNode(String servIpAddrorNodeName) {
		for (Node node : this) {
			if (node.getServIpAddr() == servIpAddrorNodeName) {
				this.remove(node);
				return true;
			}
		}

		for (Node node : this) {
			if (node.getNodeName() == servIpAddrorNodeName) {
				this.remove(node);
				return true;
			}
		}

		return false;
	}

	public NodeList getNodeList() {
		return this;
	}

	public NodeList filterUsage(NodeList originList, String type, int usageLimit) {

		NodeList resultList = new NodeList();

		if (type == "CPU" || type == "cpu") {
			for (Node node : originList) {
				if (node.getCpuPercentage() <= usageLimit) {
					resultList.add(node);
				}
			}
		}

		if (type == "RAM" || type == "ram") {
			for (Node node : originList) {
				if (node.getRamPercentage() <= usageLimit) {
					resultList.add(node);
				}
			}
		}

		return resultList;
	}

	public NodeList filterCpuRamUsage(NodeList originList, int cpuUsageLimit, int ramUsageLimit) {
		NodeList resultList;
		resultList = filterUsage(this, "CPU", cpuUsageLimit);
		resultList = filterUsage(resultList, "RAM", ramUsageLimit);

		return resultList;
	}

	public Node getLeastNode(NodeList originList, String type) {
		int leastUsage = 100;
		Node leastNode = null;

		if (type == "CPU" || type == "cpu") {
			for (Node node : originList) {
				if (node.getCpuPercentage() < leastUsage) {
					leastUsage = node.getCpuPercentage();
					leastNode = node;
				}
			}
		}

		if (type == "RAM" || type == "ram") {
			for (Node node : originList) {
				if (node.getRamPercentage() < leastUsage) {
					leastUsage = node.getRamPercentage();
					leastNode = node;
				}
			}
		}

		return leastNode;
	}

	public void scanAndAdd(){

	}
}
