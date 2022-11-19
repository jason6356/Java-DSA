package ADT_Tree.Test;

import ADT_Tree.ADT.BinaryTree;
import ADT_Tree.ADT.BinaryTreeInterface;

import java.util.Iterator;

public class TreeTest {

    private static final BinaryTreeInterface<Integer> tree = new BinaryTree<>(1);

    public static void main(String[] args) {

        init();
        Iterator<Integer> it = tree.getInorderIterator();

        while(it.hasNext())
            System.out.println(it.next());
    }

    private static void init(){

        BinaryTreeInterface<Integer> leftSubSub = new BinaryTree<>();
                        leftSubSub.setTree(4,
                                new BinaryTree<>(8),
                                new BinaryTree<>(9));
                       BinaryTreeInterface<Integer> leftSubRight = new BinaryTree<>();
                       leftSubRight.setTree(5,
                               new BinaryTree<>(10),
                               new BinaryTree<>(11));

        BinaryTreeInterface<Integer> leftPart = new BinaryTree<>();
        leftPart.setTree(2,
                leftSubSub,
                leftSubRight);

        BinaryTreeInterface<Integer> rightSubLeft = new BinaryTree<>();
        rightSubLeft.setTree(
                6,
                new BinaryTree<>(12),
                new BinaryTree<>(13)
        );


        BinaryTreeInterface<Integer> rightSubRight = new BinaryTree<>();
        rightSubRight.setTree(
                7,
                new BinaryTree<>(14),
                new BinaryTree<>(15)
        );


        BinaryTreeInterface<Integer> rightPart = new BinaryTree<>();
        rightPart.setTree(3,
                rightSubLeft,
                rightSubRight);

        tree.setTree(tree.getRootData(),
                leftPart,
                rightPart);

    }
}
