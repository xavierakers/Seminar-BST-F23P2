/**
 * @author Xavier Akers
 * 
 * @version Last Updated 2023-10-10
 * 
 * @since 2023-10-04
 * 
 *        A Composite BinTree
 * 
 */
public class Bintree {
    // The root of the Bintree
    private BintreeNode root;
    // Flyweight for empty leaf nodes
    private static final BintreeNode EMPTY_NODE = EmptyNode.getInstance();
    // The maximum x-axis size
    private int maxX;
    // The maximum y-axis size
    private int maxY;

    /**
     * Bintree constructor
     * 
     * @param maxX
     *            the maximum X world size
     * @param maxY
     *            the maximum Y world size
     */
    public Bintree(int maxX, int maxY) {
        root = EMPTY_NODE;
        this.maxX = maxX;
        this.maxY = maxY;
    }


    /**
     * Public insert method
     * 
     * @param sem
     *            seminar reference
     */
    public void insert(Seminar sem) {
        // Creating a new SeminarNode to insert into the tree
        SeminarNode semNode = new SeminarNode(sem);
        // Collecting the new root
        root = insertHelp(root, semNode, maxX / 2, maxY / 2, maxX / 2, maxY / 2,
            0);
    }


    /**
     * Recursive Insert Method to Traverse the tree
     * 
     * @param node
     *            the current node
     * @param semNode
     *            the seminarNode to insert
     * @param xDiscrim
     *            the X discriminator
     * @param yDiscrim
     *            the Y discriminator
     * @param width
     *            the width of the box
     * @param height
     *            the height of the box
     * @param level
     *            the height of the current node
     * @return the new node
     */
    public BintreeNode insertHelp(
        BintreeNode node,
        SeminarNode semNode,
        int xDiscrim,
        int yDiscrim,
        int width,
        int height,
        int level) {

        // If Flyweight, replace with node
        if (node == EMPTY_NODE) {
            return new LeafNode(semNode);
        }
        // New box sizes, will use depending on which level we are on
        int newWidth = width / 2;
        int newHeight = height / 2;
        // New tree depth
        int newLevel = level + 1;

        if (node.isLeaf()) {
            // storing the current leaf node
            LeafNode oldLeaf = (LeafNode)node;
            // creating the new leaf node to insert
            LeafNode newLeaf = new LeafNode(semNode);

            // Checking if the new and old seminar have the same coordinates
            if (oldLeaf.getSemNode().getSem().x() == newLeaf.getSemNode()
                .getSem().x() && oldLeaf.getSemNode().getSem().y() == newLeaf
                    .getSemNode().getSem().y()) {
                // Add the seminar node to the old leaf keep the same tree
                // structure
                oldLeaf.addSeminar(semNode.getSem());
                return node;
            }
            // replacing leaf node with a new internal node
            node = new InternalNode(EMPTY_NODE, EMPTY_NODE);

            // the coordinate to compare to
            int discrim;
            InternalNode internalNode = (InternalNode)node;
            // Work with x coords
            if (level % 2 == 0) {

                discrim = xDiscrim;
                // X is less than the dicriminator, thus send to the left
                if (oldLeaf.getSemNode().getSem().x() < discrim) {
                    internalNode.setLeft(insertHelp(internalNode.getLeft(),
                        oldLeaf.getSemNode(), xDiscrim - newWidth, yDiscrim,
                        newWidth, height, newLevel));
                }
                // going to the right
                else {
                    internalNode.setRight(insertHelp(internalNode.getRight(),
                        oldLeaf.getSemNode(), xDiscrim + newWidth, yDiscrim,
                        newWidth, height, newLevel));
                }
                // Same conditionals but for the new leaf node
                if (newLeaf.getSemNode().getSem().x() < discrim) {
                    internalNode.setLeft(insertHelp(internalNode.getLeft(),
                        newLeaf.getSemNode(), xDiscrim - newWidth, yDiscrim,
                        newWidth, height, newLevel));
                }
                // going to the right
                else {
                    internalNode.setRight(insertHelp(internalNode.getRight(),
                        newLeaf.getSemNode(), xDiscrim + (newWidth), yDiscrim,
                        newWidth, height, newLevel));
                }
            }
            // Work with y coords
            else {

                discrim = yDiscrim;
                // Y is less than the dicriminator, thus send to the left
                if (oldLeaf.getSemNode().getSem().y() < discrim) {
                    internalNode.setLeft(insertHelp(internalNode.getLeft(),
                        oldLeaf.getSemNode(), xDiscrim, yDiscrim - newHeight,
                        width, newHeight, newLevel));
                }
                // going to the right
                else {
                    internalNode.setRight(insertHelp(internalNode.getRight(),
                        oldLeaf.getSemNode(), xDiscrim, yDiscrim + newHeight,
                        width, newHeight, newLevel));
                }
                // same conditionals but with the new Leaf
                if (newLeaf.getSemNode().getSem().y() < discrim) {
                    internalNode.setLeft(insertHelp(internalNode.getLeft(),
                        newLeaf.getSemNode(), xDiscrim, yDiscrim - newHeight,
                        width, newHeight, newLevel));
                }
                // going to the right
                else {
                    internalNode.setRight(insertHelp(internalNode.getRight(),
                        newLeaf.getSemNode(), xDiscrim, yDiscrim + newHeight,
                        width, newHeight, newLevel));
                }
            }
        }
        // when node is an internal node
        else {

            int discrim;
            InternalNode internalNode = (InternalNode)node;
            // Works with X coords
            if (level % 2 == 0) {

                discrim = xDiscrim;
                // if x is less than the dicriminator, send to internalNodes
                // left child
                if (semNode.getSem().x() < discrim) {
                    internalNode.setLeft(insertHelp(internalNode.getLeft(),
                        semNode, xDiscrim - newWidth, yDiscrim, newWidth,
                        newHeight, newLevel));
                }
                // send to right child
                else {
                    internalNode.setRight(insertHelp(internalNode.getRight(),
                        semNode, xDiscrim + (newWidth), yDiscrim, newWidth,
                        height, newLevel));
                }
            }
            // Works with Y coords
            else {

                discrim = yDiscrim;
                // if y is less than the dicriminator, send to internalNodes
                // left child
                if (semNode.getSem().y() < discrim) {
                    internalNode.setLeft(insertHelp(internalNode.getLeft(),
                        semNode, xDiscrim, yDiscrim - newHeight, width, height,
                        newLevel));
                }
                // send to right child
                else {
                    internalNode.setRight(insertHelp(internalNode.getRight(),
                        semNode, xDiscrim, yDiscrim + (newHeight), width,
                        newHeight, newLevel));
                }

            }
        }

        return node;
    }


    /**
     * Public printTree method
     */
    public void printTree() {
        printTreeHelp(root, 0);
    }


    /**
     * Prints the values of the Bintree in Preorder traversal
     * Implementing composite print methods
     * 
     * @param node
     *            node to print
     * @param level
     *            where within the bintree
     */
    private void printTreeHelp(BintreeNode node, int level) {
        // creating proper tree spacing based on level
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        node.print();
        // If internal node, repeat with both children
        if (!node.isLeaf()) {
            printTreeHelp(((InternalNode)node).getLeft(), level + 1);
            printTreeHelp(((InternalNode)node).getRight(), level + 1);
        }

    }


    /**
     * Public search method
     * 
     * @param x
     *            x search location
     * @param y
     *            y search location
     * @param d
     *            distance from search location
     * @param count
     *            number of nodes visited
     * @return list of BintreeNode within that range
     */
    public Seminar[] search(int x, int y, int d, int[] count) {
        // List of seminars collected
        Seminar[] results = new Seminar[0];
        results = searchHelp(root, x, y, d, maxX / 2, maxY / 2, 0, results,
            count);
        return results;
    }


    /**
     * Recursive search method
     * Finds seminar within a certain radius of a coordinate
     * 
     * @param rt
     *            the current node
     * @param xSearch
     *            x search coord
     * @param ysearch
     *            y search coord
     * @param radius
     *            the radius from the search coords
     * @param xDiscrim
     *            the xCoord to compare to
     * @param yDiscrim
     *            the yCoord to compare to
     * @param level
     *            the current tree level
     * @param results
     *            list of seminars found
     * @param count
     *            the number of traversals
     * @return the list of seminar found
     */
    public Seminar[] searchHelp(
        BintreeNode rt,
        int xSearch,
        int ysearch,
        int radius,
        int xDiscrim,
        int yDiscrim,
        int level,
        Seminar[] results,
        int[] count) {
        level++;
        count[0]++;
        if (rt == EMPTY_NODE) {
            return results;

        }
        // New positions to compare to
        int newXDiscrim = xDiscrim / 2;
        int newYDiscrim = yDiscrim / 2;

        if (rt.isLeaf()) {
            LeafNode leaf = (LeafNode)rt;
            // iterating through seminars in the leaf node
            SeminarNode current = leaf.getSemNode();

            // while not end of linked list
            while (current != null) {
                // the distance the node is from the search position
                double distance = distance(current.getSem().x(), current
                    .getSem().y(), xSearch, ysearch);

                // if distance is within search distance
                if (distance <= radius) {
                    // adding to the return list
                    Seminar[] newList = new Seminar[results.length + 1];
                    System.arraycopy(results, 0, newList, 0, results.length);
                    newList[results.length] = current.getSem();
                    results = newList;
                }
                current = current.getNext();

            }

        }
        else {
            InternalNode internalNode = (InternalNode)rt;
            int discrim;
            // work with X coordinates
            if (level % 2 == 0) {
                discrim = xDiscrim;
                // if the radius reaches the point within the bounds,
                // makes sure to check both children
                if (xSearch - radius <= discrim) {
                    results = searchHelp(internalNode.getLeft(), xSearch,
                        ysearch, radius, newXDiscrim, yDiscrim, level, results,
                        count);
                }
                // if the radius reaches the point within the bounds
                if (xSearch + radius > discrim) {
                    results = searchHelp(internalNode.getRight(), xSearch,
                        ysearch, radius, newXDiscrim, yDiscrim, level, results,
                        count);
                }

            }
            // work with Y coordinates
            else {
                discrim = yDiscrim;
                // if the radius reaches the point within the bounds,
                // makes sure to check both children
                if (ysearch - radius <= discrim) {
                    results = searchHelp(internalNode.getLeft(), xSearch,
                        ysearch, radius, xDiscrim, newYDiscrim, level, results,
                        count);
                }
                // if the radius reaches the point within the bounds
                if (ysearch + radius > discrim) {
                    results = searchHelp(internalNode.getRight(), xSearch,
                        ysearch, radius, xDiscrim, newYDiscrim, level, results,
                        count);
                }
            }
        }
        return results;
    }


    /**
     * Euclidean distance formula
     * 
     * @param xPoint
     *            node x coord
     * @param yPoint
     *            node y coord
     * @param xSearch
     *            search x coord
     * @param ySearch
     *            search y coord
     * @return
     */
    private double distance(int xPoint, int yPoint, int xSearch, int ySearch) {

        return Math.sqrt(Math.pow(xPoint - xSearch, 2) + Math.pow(yPoint
            - ySearch, 2));
    }


    /**
     * Public delete method
     * 
     * @param sem
     *            the seminar to delete
     */
    public void delete(Seminar sem) {
        root = deleteHelp(root, sem, maxX / 2, maxY / 2, maxX / 2, maxY / 2, 0);
    }


    /**
     * Recursive delete method
     * 
     * @param rt
     *            the current node
     * @param sem
     *            the seminar to delete
     * @param xDiscrim
     *            the xCoord to compare to
     * @param yDiscrim
     *            the yCoord to compare to
     * @param width
     *            the width of the box
     * @param height
     *            the height of the box
     * @param level
     *            the current level of the tree
     * @return the new bintree root
     */
    public BintreeNode deleteHelp(
        BintreeNode rt,
        Seminar sem,
        int xDiscrim,
        int yDiscrim,
        int width,
        int height,
        int level) {

        // new box boundaries
        int newWidth = width / 2;
        int newHeight = height / 2;
        // new tree depth
        int newLevel = level + 1;

        if (rt == EMPTY_NODE) {
            return rt;
        }

        if (rt.isLeaf()) {
            LeafNode leaf = (LeafNode)rt;
            // delete the node from the linked list within the leaf
            if (leaf.deleteSem(sem)) {
                // if linked list is empty, replace with flyweight
                if (leaf.isEmpty()) {
                    return EMPTY_NODE;
                }
                return leaf;
            }
        }
        // continue searching for leaf node
        else {
            InternalNode internalNode = (InternalNode)rt;
            int discrim;
            // work with X coordinates
            if (level % 2 == 0) {
                discrim = xDiscrim;
                // if x is left child or right child, pass in respective node
                // child
                if (sem.x() < discrim) {
                    internalNode.setLeft(deleteHelp(internalNode.getLeft(), sem,
                        xDiscrim - newWidth, yDiscrim, newWidth, height,
                        newLevel));
                }
                // if x is right child
                else {
                    internalNode.setRight(deleteHelp(internalNode.getRight(),
                        sem, xDiscrim + newWidth, yDiscrim, newWidth, height,
                        newLevel));
                }
            }
            // work with Y coordinates
            else {
                discrim = yDiscrim;
                // if Y is left or right child
                if (sem.y() < discrim) {
                    internalNode.setLeft(deleteHelp(internalNode.getLeft(), sem,
                        xDiscrim, yDiscrim - newHeight, width, newHeight,
                        newLevel));
                }
                // Y is right child
                else {
                    internalNode.setRight(deleteHelp(internalNode.getRight(),
                        sem, xDiscrim, yDiscrim + newHeight, width, newHeight,
                        newLevel));
                }
            }
            // checking if both children are flyweights
            // replaces current node with flyweight
            if (internalNode.getLeft() == EMPTY_NODE && internalNode
                .getRight() == EMPTY_NODE) {
                return EMPTY_NODE;
            }

            return rt;
        }
        return EMPTY_NODE;
    }

}
