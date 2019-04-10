package Part1;

import Main.MyStack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/** Calculates the number of 'white components' for the given txt file in the right format. A white component is a set
 * of white matrix locations, such that, between any two of them, there is a path of white matrix locations, where
 * every consecutive pair are adjacent (through their top, left, right or bottom neighbor). */
public class WhiteComponentCounter {
    /** Binary digital Image representation by a matrix, each element is 1(white) or 0(black). */
    private Pixel[][] binaryDigitalImage;
    /** Row count of the matrix. */
    private int row;
    /** Column count of the matrix. */
    private int column;

    public WhiteComponentCounter(String fileName) {
        try {
            readImageFromFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Reads the proper formatted file to the array.
     * @param fileName Name of the text file.
     * @throws IOException If file with given path is not exists. */
    public void readImageFromFile(String fileName) throws IOException {
        File file = new File(fileName);

        BufferedReader reader = new BufferedReader(new FileReader(file));
        int lines = 0;
        String temp = reader.readLine().replaceAll("\\s","");
        column = temp.length();
        while (temp != null) {
            temp = reader.readLine();
            lines++;
        }
        reader.close();
        row = lines;

        binaryDigitalImage = new Pixel[row][column];
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        for (int i = 0; i < row; i++) {
            String line = br.readLine().replaceAll("\\s","");
            for (int j = 0; j < column; j++) {
                binaryDigitalImage[i][j] = new Pixel(line.charAt(j), i, j);
            }
        }
        br.close();
    }

    /** Calculates the number of white components.
     * @return Number of white components. */
    public int calculateComponents() {
        char startLabel = 'A';
        int componentCount = 0;

        // Iterate through all indexes of the matrix.
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                // If there is '1' character exists, look for its neighbours.
                if (binaryDigitalImage[i][j].getValue() == '1') {
                    findNeighboursByDFS(i, j, (char) (startLabel + componentCount));
                    componentCount++;
                }
            }
        }

        return componentCount;
    }

    /** Starting from given row and column, performs stack-based implementation of depth-first search on the matrix
     * to find components and groups them by using ASCII characters starting from label.
     * @param indexRow Row index of the matrix element.
     * @param indexColumn Column index of the matrix element.
     * @param label ASCII character to assign to given element, its neighbours and their neighbours, if they exists. */
    private void findNeighboursByDFS(int indexRow, int indexColumn, char label) {
        MyStack<Pixel> stack = new MyStack<Pixel>();
        // Add given starting point to the stack
        stack.push(binaryDigitalImage[indexRow][indexColumn]);

        // Until there is not a '1' character that being a neighbour to elements of stack.
        while (!stack.empty()) {
            Pixel temp = stack.pop();
            // Label the element on the given indexes.
            binaryDigitalImage[temp.getIndexRow()][temp.getIndexColumn()].setValue(label);
            // West
            if (!(temp.getIndexRow() == 0)) {
                // If it is a neighbouring '1', label it and add to the stack to check its neighbours too.
                if (binaryDigitalImage[temp.getIndexRow() - 1][temp.getIndexColumn()].getValue() == '1') {
                    binaryDigitalImage[temp.getIndexRow() - 1][temp.getIndexColumn()].setValue(label);
                    stack.push(binaryDigitalImage[temp.getIndexRow() - 1][temp.getIndexColumn()]);
                }
            }
            // East
            if (!(temp.getIndexRow() == row - 1)) {
                // If it is a neighbouring '1', label it and add to the stack to check its neighbours too.
                if (binaryDigitalImage[temp.getIndexRow() + 1][temp.getIndexColumn()].getValue() == '1') {
                    binaryDigitalImage[temp.getIndexRow() + 1][temp.getIndexColumn()].setValue(label);
                    stack.push(binaryDigitalImage[temp.getIndexRow() + 1][temp.getIndexColumn()]);
                }
            }
            // North
            if (!(temp.getIndexColumn() == 0)) {
                // If it is a neighbouring '1', label it and add to the stack to check its neighbours too.
                if (binaryDigitalImage[temp.getIndexRow()][temp.getIndexColumn() - 1].getValue() == '1') {
                    binaryDigitalImage[temp.getIndexRow()][temp.getIndexColumn() - 1].setValue(label);
                    stack.push(binaryDigitalImage[temp.getIndexRow()][temp.getIndexColumn() - 1]);
                }
            }
            // South
            if (!(temp.getIndexColumn() == column - 1)) {
                // If it is a neighbouring '1', label it and add to the stack to check its neighbours too.
                if (binaryDigitalImage[temp.getIndexRow()][temp.getIndexColumn() + 1].getValue() == '1') {
                    binaryDigitalImage[temp.getIndexRow()][temp.getIndexColumn() + 1].setValue(label);
                    stack.push(binaryDigitalImage[temp.getIndexRow()][temp.getIndexColumn() + 1]);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                sb.append(binaryDigitalImage[i][j].getValue());
            }
            sb.append("\n");
        }
        sb.append("\n");

        return sb.toString();
    }
}
