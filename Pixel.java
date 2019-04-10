package Part1;

/** A class to represent only one point in the given image matrix. */
class Pixel {
    /** Character value of the matrix element. */
    private char value;
    /** Elements row index at matrix. */
    private int indexRow;
    /** Elements column index at matrix. */
    private int indexColumn;

    /** Constructor */
    Pixel(char value, int indexRow, int indexColumn) {
        this.value = value;
        this.indexRow = indexRow;
        this.indexColumn = indexColumn;
    }

    /** Getter */
    char getValue() {
        return value;
    }

    /** Setter */
    void setValue(char value) {
        this.value = value;
    }

    /** Getter */
    int getIndexRow() {
        return indexRow;
    }

    /** Getter */
    int getIndexColumn() {
        return indexColumn;
    }
}
