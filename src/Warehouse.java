public class Warehouse {

    /**
     * Move pieces in a warehouse from the picking point to their storage using an Automated Guided Vehicle System (AGVS).
     *
     * Creates three kind of storage bins for three kind of pieces,
     * then adds a conveyor that receives pieces and unloads them to a picking point,
     * finally adds an AGVS that loads pieces from the picking point and moves them to their storage, one piece at a time.
     * @param args program arguments, not used
     */
    public static void main(String[] args) {
        // Create storage bins for each kind of piece
        Storage cylindricalStorage = new Storage(new Position(0, 1));
        Storage squareStorage = new Storage(new Position(0, 2));
        Storage roundStorage = new Storage(new Position(0, 3));

        // Create the conveyor with a picking point position
        Conveyor conveyor = new Conveyor(new Position(3, 2));

        // Create some round pieces
        for (int i = 1; i <= 2; i++) {
            conveyor.add(new RoundPiece(i, roundStorage));
        }

        // Create some square pieces
        for (int i = 1; i <= 2; i++) {
            conveyor.add(new SquarePiece(i, squareStorage));
        }

        // Create some cylindrical pieces
        for (int i = 1; i <= 2; i++) {
            conveyor.add(new CylindricalPiece(i, cylindricalStorage));
        }

        System.out.println("Picking point is at " + conveyor.getPickingPointPosition());

        // Create the automated vehicle located at the picking point position
        AGVS agvs = new AGVS(conveyor.getPickingPointPosition());

        // Move pieces to their storage position using the AGVS
        agvs.consume(conveyor);

        // Print final status, showing where each piece has been stored

        System.out.println("ROUND STORAGE");
        System.out.println(roundStorage);

        System.out.println("SQUARE STORAGE");
        System.out.println(squareStorage);

        System.out.println("CYLINDRICAL STORAGE");
        System.out.println(cylindricalStorage);
    }
}
