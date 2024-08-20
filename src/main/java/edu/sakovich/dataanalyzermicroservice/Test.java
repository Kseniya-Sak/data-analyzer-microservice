package edu.sakovich.dataanalyzermicroservice;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        HashMap
        List<Node> treeList = new ArrayList<>();
        Node node1 = new Node(1, 2L);
        Node node2 = new Node(2, 7L);
        Node node3 = new Node(3, 8L);
        Node node4 = new Node(4, 9L);
        Node node5 = new Node(5, 9L);
        Node node6 = new Node(6, 10L);
        Node node7 = new Node(7, 10L);
        Node node8 = new Node(8, 10L);
        Node node9 = new Node(9, 10L);
        Node node10 = new Node(10, null);
        treeList.add(node1);
        treeList.add(node2);
        treeList.add(node3);
        treeList.add(node4);
        treeList.add(node5);
        treeList.add(node6);
        treeList.add(node7);
        treeList.add(node8);
        treeList.add(node9);
        treeList.add(node10);


        Map<Long, List<Node>> map = treeList.stream()
                .filter(node -> Objects.nonNull(node.parentId))
                .collect(Collectors.groupingBy(Node::getParentId,
                        Collectors.mapping(Function.identity(), Collectors.toList())));

        System.out.println(map);

        List<Node> nodes = treeList.stream()
                .peek(node -> {
                    node.setChildren(map.get(node.getId()));
                })
                .toList();

        for (Node node : nodes) {
            System.out.print("node with id: " + node.getId());
            System.out.println(" has children: " + node.getChildren());
        }



        @ManyToOne
    }




}


class Node {
    long id;
    Long parentId;
    List<Node> children = new ArrayList<>();

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", parentId=" + parentId +
                '}';
    }

    public Node(long id, Long parentId) {
        this.id = id;
        this.parentId = parentId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }
}